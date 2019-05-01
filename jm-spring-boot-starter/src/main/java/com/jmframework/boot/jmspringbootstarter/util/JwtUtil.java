package com.jmframework.boot.jmspringbootstarter.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.jmframework.boot.jmspringbootstarter.config.JwtConfiguration;
import com.jmframework.boot.jmspringbootstarter.constant.Constants;
import com.jmframework.boot.jmspringbootstarter.constant.UniversalStatus;
import com.jmframework.boot.jmspringbootstarter.domain.UserPrincipal;
import com.jmframework.boot.jmspringbootstarter.exception.SecurityException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Description: JwtUtil.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-03 13:40
 **/
@Slf4j
@Configuration
@EnableConfigurationProperties(JwtConfiguration.class)
public class JwtUtil {
    private final JwtConfiguration jwtConfiguration;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public JwtUtil(JwtConfiguration jwtConfiguration,
                   StringRedisTemplate stringRedisTemplate) {
        this.jwtConfiguration = jwtConfiguration;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * Create JWT.
     *
     * @param rememberMe  Remember me
     * @param id          User id
     * @param subject     Username
     * @param roles       Roles
     * @param authorities Granted Authority
     * @return JWT
     */
    public String createJWT(Boolean rememberMe, Long id, String subject, List<String> roles,
                            Collection<? extends GrantedAuthority> authorities) {
        Date now = new Date();
        JwtBuilder builder = Jwts.builder()
                                 .setId(id.toString())
                                 .setSubject(subject)
                                 .setIssuedAt(now)
                                 .signWith(SignatureAlgorithm.HS256, jwtConfiguration.getSigningKey())
                                 .claim("roles", roles);
        // TODO: Don't generate authority information in JWT.
        //  .claim("authorities", authorities)

        // Set expire duration of JWT.
        Long ttl = rememberMe ? jwtConfiguration.getTtlForRememberMe() : jwtConfiguration.getTtl();
        if (ttl > 0) {
            builder.setExpiration(DateUtil.offsetMillisecond(now, ttl.intValue()));
        }

        String jwt = builder.compact();
        // Store new JWT in Redis
        stringRedisTemplate.opsForValue()
                           .set(Constants.REDIS_JWT_KEY_PREFIX + subject, jwt, ttl, TimeUnit.MILLISECONDS);
        return jwt;
    }

    /**
     * Create JWT.
     *
     * @param authentication Authentication information
     * @param rememberMe     Remember me
     * @return JWT
     */
    public String createJWT(Authentication authentication, Boolean rememberMe) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return createJWT(rememberMe, userPrincipal.getId(), userPrincipal.getUsername(), userPrincipal.getRoles(),
                         userPrincipal.getAuthorities());
    }

    /**
     * Parse JWT.
     *
     * @param jwt JWT
     * @return {@link Claims}
     */
    public Claims parseJWT(String jwt) {
        try {
            Claims claims = Jwts.parser()
                                .setSigningKey(jwtConfiguration.getSigningKey())
                                .parseClaimsJws(jwt)
                                .getBody();

            String username = claims.getSubject();
            String redisKey = Constants.REDIS_JWT_KEY_PREFIX + username;

            // Check if JWT exists
            Long expire = stringRedisTemplate.getExpire(redisKey, TimeUnit.MILLISECONDS);
            if (Objects.isNull(expire) || expire <= 0) {
                throw new SecurityException(UniversalStatus.TOKEN_EXPIRED);
            }

            // Check if current JWT is equal to the one in Redis.
            // If it's noe equal, that indicates current user has signed out or logged in before.
            // Both situations reveal the JWT expired.
            String redisToken = stringRedisTemplate.opsForValue().get(redisKey);
            if (!StrUtil.equals(jwt, redisToken)) {
                throw new SecurityException(UniversalStatus.TOKEN_OUT_OF_CONTROL);
            }
            return claims;
        } catch (ExpiredJwtException e) {
            log.error("Token expired.");
            throw new SecurityException(UniversalStatus.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.error("Token not supported.");
            throw new SecurityException(UniversalStatus.TOKEN_PARSE_ERROR);
        } catch (MalformedJwtException e) {
            log.error("Invalid token.");
            throw new SecurityException(UniversalStatus.TOKEN_PARSE_ERROR);
        } catch (SignatureException e) {
            log.error("Invalid signature of token.");
            throw new SecurityException(UniversalStatus.TOKEN_PARSE_ERROR);
        } catch (IllegalArgumentException e) {
            log.error("Token parameter not exists.");
            throw new SecurityException(UniversalStatus.TOKEN_PARSE_ERROR);
        }
    }

    /**
     * Invalidate JWT.
     *
     * @param request Request
     */
    public void invalidateJWT(HttpServletRequest request) {
        String jwt = getJwtFromRequest(request);
        String username = getUsernameFromJWT(jwt);
        // Delete JWT from redis
        Boolean result = stringRedisTemplate.delete(Constants.REDIS_JWT_KEY_PREFIX + username);
        log.error("Invalidate JWT. Username = {}, result = {}", username, result);
    }

    /**
     * Get username from JWT.
     *
     * @param jwt JWT
     * @return Username
     */
    public String getUsernameFromJWT(String jwt) {
        Claims claims = parseJWT(jwt);
        return claims.getSubject();
    }

    /**
     * Get JWT from request's header.
     *
     * @param request request
     * @return JWT
     */
    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constants.REQUEST_TOKEN_KEY);
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith(Constants.JWT_PREFIX)) {
            return bearerToken.substring(Constants.JWT_PREFIX.length());
        }
        return null;
    }
}
