package com.jmframework.boot.jmspringbootstarter.configuration;

import com.jmframework.boot.jmspringbootstarter.filter.JwtAuthenticationFilter;
import com.jmframework.boot.jmspringbootstarter.service.impl.CustomUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Description: Web security configuration.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-26 14:28
 **/
@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final CustomConfiguration customConfiguration;
    private final AccessDeniedHandler accessDeniedHandler;
    private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsServiceImpl).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Check if disable Web Security.
        if (customConfiguration.getWebSecurityDisabled()) {
            http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
            return;
        }

        http.cors()
            // Disable CSRF (Cross-site request forgery)
            .and().csrf().disable()
            // Disable form login to use custom login
            .formLogin().disable()
            .httpBasic().disable()

            // Allows restricting access based upon the HttpServletRequest
            .authorizeRequests()
            // RBAC URL authorization
            .anyRequest()
            .access("@rbacAuthorityServiceImpl.hasPermission(request,authentication)")

            // Disable logout to use custom logout
            .and().logout().disable()
            .sessionManagement()
            // Disable session management
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            // Exception handling
            .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        // Add customized JWT filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Add ignored HTTP request list
     * Same as {@link #configure(HttpSecurity)}
     * {@code http.authorizeRequests().antMatchers("/api/auth/**").permitAll()}
     */
    @Override
    public void configure(WebSecurity web) {
        WebSecurity and = web.ignoring().and();

        // GET ignored list
        customConfiguration.getIgnores().getGet().forEach(url -> and.ignoring().antMatchers(HttpMethod.GET, url));

        // POST ignored list
        customConfiguration.getIgnores().getPost().forEach(url -> and.ignoring().antMatchers(HttpMethod.POST, url));

        // DELETE ignored list
        customConfiguration.getIgnores().getDelete().forEach(url -> and.ignoring().antMatchers(HttpMethod.DELETE,
                                                                                               url));

        // PUT ignored list
        customConfiguration.getIgnores().getPut().forEach(url -> and.ignoring().antMatchers(HttpMethod.PUT, url));

        // HEAD ignored list
        customConfiguration.getIgnores().getHead().forEach(url -> and.ignoring().antMatchers(HttpMethod.HEAD, url));

        // PATCH ignored list
        customConfiguration.getIgnores().getPatch().forEach(url -> and.ignoring().antMatchers(HttpMethod.PATCH, url));

        // OPTIONS ignored list
        customConfiguration.getIgnores().getOptions().forEach(url -> and.ignoring().antMatchers(HttpMethod.OPTIONS,
                                                                                                url));

        // TRACE ignored list
        customConfiguration.getIgnores().getTrace().forEach(url -> and.ignoring().antMatchers(HttpMethod.TRACE, url));

        // Pattern ignored list
        customConfiguration.getIgnores().getPattern().forEach(url -> and.ignoring().antMatchers(url));
    }
}
