package com.jmframework.boot.jmspringbootstarter.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: WebMvcConfiguration, Spring MVC Configurations.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-02 12:34
 **/
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    private static final long MAX_AGE_SECS = 3600;
    private final CustomMessageSourceConfiguration customMessageSourceConfiguration;

    public WebMvcConfiguration(CustomMessageSourceConfiguration customMessageSourceConfiguration) {
        this.customMessageSourceConfiguration = customMessageSourceConfiguration;
    }

    /**
     * 1. Config static path pattern
     * 2. Config static resource location
     *
     * @param registry static resources register
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * <p>Override parent's validator by local validator to enable custom message.</p>
     * <p>We had already implemented the <em>WebMvcConfigurer</em>, to avoid having the custom validator ignored,
     * we’d have to set the validator by overriding the <em>getValidator()</em> method from the parent class.</p>
     * <a href="https://www.baeldung.com/spring-custom-validation-message-source">Reference</a>
     *
     * @return local validator
     */
    @Override
    public Validator getValidator() {
        return customMessageSourceConfiguration.getValidator();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }
}
