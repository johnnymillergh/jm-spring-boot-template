package com.jm.springboottemplate.system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: Custom config
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-23
 * @time: 14:24
 **/
@ConfigurationProperties(prefix = "custom.config")
@Data
public class CustomConfiguration {
    /**
     * Ignore URLs
     */
    private IgnoreConfiguration ignores;
    private String druidLoginName;
    private String druidPassword;
}
