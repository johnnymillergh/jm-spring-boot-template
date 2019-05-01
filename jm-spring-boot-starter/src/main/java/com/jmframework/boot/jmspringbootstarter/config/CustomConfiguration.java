package com.jmframework.boot.jmspringbootstarter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: Custom config
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 14:24
 **/
@Data
@ConfigurationProperties(prefix = "custom.configuration")
public class CustomConfiguration {
    /**
     * Ignore URLs
     */
    private IgnoreConfiguration ignores;
    private String druidLoginName;
    private String druidPassword;
    /**
     * <p>Web security feature switch. Default is false.</p>
     * true - disable web security; false - enable web security.
     */
    private Boolean webSecurityDisabled = false;
}
