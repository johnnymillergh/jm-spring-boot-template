package com.jm.springboottemplate.system.config;

import com.jm.springboottemplate.system.interceptor.RequestRecordingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: WebMvcConfiguration, Spring MVC Configurations.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-02
 * @time: 12:34
 **/
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    /**
     * 1. Config static path pattern
     * 2. Config static resource location
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestRecordingInterceptor()).addPathPatterns("/**");
    }
}
