package com.jmframework.boot.jmspringbootstarter.configuration;

import com.jmframework.boot.jmspringbootstarter.util.ResponseUtil;
import com.jmframework.boot.jmspringbootstarterdomain.common.constant.HttpStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Description: Security handler configuration.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-26 14:30
 **/
@Configuration
public class SecurityHandlerConfiguration {
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> ResponseUtil.renderJson(response,
                                                                                     HttpStatus.FORBIDDEN,
                                                                                     null);
    }
}
