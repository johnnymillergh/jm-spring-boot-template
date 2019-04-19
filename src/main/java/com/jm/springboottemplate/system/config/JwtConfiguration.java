package com.jm.springboottemplate.system.config;

import com.jm.springboottemplate.system.constant.ProjectProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: JWT configuration
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 14:24
 **/
@Data
@Slf4j
@ConfigurationProperties(prefix = "jwt.configuration")
public class JwtConfiguration {

    public JwtConfiguration(ProjectProperty projectProperty) {
        this.signingKey = projectProperty.getArtifactId();
        log.error("JWT signing key: {}", this.signingKey);
    }

    /**
     * JWT signing key, which is equal to the string value of group id of project.
     */
    private String signingKey;
    /**
     * Time to live of JWT. Default: 3 * 600000 milliseconds {@code 30 min}.
     */
    private Long ttl = 3 * 600000L;
    /**
     * Time to live of JWT for remember me, Default: 7 * 86400000 {@code 7 天}
     */
    private Long ttlForRememberMe = 7 * 86400000L;
}
