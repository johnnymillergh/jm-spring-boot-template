package com.jmframework.boot.jmspringbootstarter.configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Description: MyBatisPlusConfiguration, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-02 11:57
 **/
@EnableTransactionManagement
@Configuration
public class MyBatisPlusConfiguration {
    /**
     * Inject bean to enable pagination.
     *
     * @return bean for pagination
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
