package com.jmframework.boot.jmspringbootstarter.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * <h1>CustomMessageSourceConfiguration</h1>
 * <p><em>MessageSource</em> is a powerful feature available in Spring applications. This helps application
 * developers handle various complex scenarios with writing much extra code, such as environment-specific
 * configuration, internationalization or configurable values.</p>
 * <p>One more scenario could be modifying the default validation messages to more user-friendly/custom messages.</p>
 * <a href="https://www.baeldung.com/spring-custom-validation-message-source">Reference and Tutorial Guide</a>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-11 11:45
 **/
@Configuration
public class CustomMessageSourceConfiguration {
    /**
     * messageSource is instanced by Spring from application.yml
     */
    private final MessageSource messageSource;

    public CustomMessageSourceConfiguration(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * <p>Define a <strong>LocalValidatorFactoryBean</strong> and register the <strong>messageSource</strong>. Now we
     * can define a property
     * message like:</p>
     * <p><strong>email.notEmpty=&lt;Custom_Message&gt;</strong></p>
     * <p>instead of</p>
     * <p><strong>javax.validation.constraints.NotEmpty.message=&lt;Custom_message&gt;</strong></p>
     *
     * @return local validator
     */
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean localValidator = new LocalValidatorFactoryBean();
        localValidator.setValidationMessageSource(messageSource);
        return localValidator;
    }
}
