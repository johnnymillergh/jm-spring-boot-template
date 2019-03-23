package com.jm.springboottemplate.system.config;

import com.jm.springboottemplate.system.constant.UniversalStatus;
import com.jm.springboottemplate.system.util.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Description: Security handler configuration.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-26
 * @time: 14:30
 **/
@Configuration
public class SecurityHandlerConfiguration {
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> ResponseUtil.renderJson(response,
                UniversalStatus.FORBIDDEN, null);
    }
}
