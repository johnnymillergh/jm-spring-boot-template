package com.jmframework.boot.jmspringbootstarter.aspect.annotation;

import com.jmframework.boot.jmspringbootstarter.config.JwtAuthenticationFilter;

import java.lang.annotation.*;

/**
 * <p><strong>Description</strong>:</p>
 * <p>WebLog is an annotation for logging URL, HTTP method, client IP and other information when web resource was
 * accessed.</p>
 * <p><strong>Tips</strong>:</p>
 * <p>Not every method in controller needs to be decorated with this annotation, because we got
 * JwtAuthenticationFilter to log some necessary information for us. Use this annotation on essential controller
 * method.</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-05 19:52
 * @see JwtAuthenticationFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
 * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
 **/
@SuppressWarnings("JavadocReference")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {
}
