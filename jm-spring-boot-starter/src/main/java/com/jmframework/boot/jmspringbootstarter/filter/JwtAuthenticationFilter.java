package com.jmframework.boot.jmspringbootstarter.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.jmframework.boot.jmspringbootstarter.configuration.CustomConfiguration;
import com.jmframework.boot.jmspringbootstarter.exception.SecurityException;
import com.jmframework.boot.jmspringbootstarter.service.impl.CustomUserDetailsServiceImpl;
import com.jmframework.boot.jmspringbootstarter.util.JwtUtil;
import com.jmframework.boot.jmspringbootstarter.util.RequestUtil;
import com.jmframework.boot.jmspringbootstarter.util.ResponseUtil;
import com.jmframework.boot.jmspringbootstarterdomain.common.constant.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Description: Jwt Authentication Filter.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 14:24
 **/
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
    private final JwtUtil jwtUtil;
    private final CustomConfiguration customConfiguration;

    @Autowired
    public JwtAuthenticationFilter(CustomUserDetailsServiceImpl customUserDetailsServiceImpl,
                                   JwtUtil jwtUtil,
                                   CustomConfiguration customConfiguration) {
        this.customUserDetailsServiceImpl = customUserDetailsServiceImpl;
        this.jwtUtil = jwtUtil;
        this.customConfiguration = customConfiguration;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.error("[{}] Client requested access. URL: {}",
                  RequestUtil.getRequestIpAndPort(request),
                  request.getServletPath());

        // Check if disable Web Security.
        if (customConfiguration.getWebSecurityDisabled()) {
            filterChain.doFilter(request, response);
            return;
        }

        if (checkIgnores(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = jwtUtil.getJwtFromRequest(request);

        if (StrUtil.isNotBlank(jwt)) {
            try {
                String username = jwtUtil.getUsernameFromJWT(jwt);

                UserDetails userDetails = customUserDetailsServiceImpl.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext()
                                     .setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } catch (SecurityException e) {
                ResponseUtil.renderJson(response, e);
            }
        } else {
            ResponseUtil.renderJson(response, HttpStatus.UNAUTHORIZED, null);
        }

    }

    /**
     * Check if current request needs to be ignored. (Permission interception)
     *
     * @param request Current request
     * @return true - Ignored, false - Not ignored
     */
    private boolean checkIgnores(HttpServletRequest request) {
        String method = request.getMethod();

        HttpMethod httpMethod = HttpMethod.resolve(method);
        if (ObjectUtil.isNull(httpMethod)) {
            httpMethod = HttpMethod.GET;
        }

        Set<String> ignores = Sets.newHashSet();

        switch (httpMethod) {
            case GET:
                ignores.addAll(customConfiguration.getIgnores().getGet());
                break;
            case PUT:
                ignores.addAll(customConfiguration.getIgnores().getPut());
                break;
            case HEAD:
                ignores.addAll(customConfiguration.getIgnores().getHead());
                break;
            case POST:
                ignores.addAll(customConfiguration.getIgnores().getPost());
                break;
            case PATCH:
                ignores.addAll(customConfiguration.getIgnores().getPatch());
                break;
            case TRACE:
                ignores.addAll(customConfiguration.getIgnores().getTrace());
                break;
            case DELETE:
                ignores.addAll(customConfiguration.getIgnores().getDelete());
                break;
            case OPTIONS:
                ignores.addAll(customConfiguration.getIgnores().getOptions());
                break;
            default:
                break;
        }

        ignores.addAll(customConfiguration.getIgnores().getPattern());

        if (CollUtil.isNotEmpty(ignores)) {
            for (String ignore : ignores) {
                AntPathRequestMatcher matcher = new AntPathRequestMatcher(ignore, method);
                if (matcher.matches(request)) {
                    return true;
                }
            }
        }

        return false;
    }

}
