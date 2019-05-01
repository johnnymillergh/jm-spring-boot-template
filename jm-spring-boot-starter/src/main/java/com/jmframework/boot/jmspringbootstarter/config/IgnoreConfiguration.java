package com.jmframework.boot.jmspringbootstarter.config;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Description: Ignored request configuration.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 14:24
 **/
@Data
public class IgnoreConfiguration {
    /**
     * Ignored URL pattern.
     */
    private List<String> pattern = Lists.newArrayList();
    /**
     * Ignored GET request.
     */
    private List<String> get = Lists.newArrayList();
    /**
     * Ignored POST request.
     */
    private List<String> post = Lists.newArrayList();
    /**
     * Ignored DELETE request.
     */
    private List<String> delete = Lists.newArrayList();
    /**
     * Ignored PUT request.
     */
    private List<String> put = Lists.newArrayList();
    /**
     * Ignored HEAD request.
     */
    private List<String> head = Lists.newArrayList();
    /**
     * Ignored PATCH request.
     */
    private List<String> patch = Lists.newArrayList();
    /**
     * Ignored OPTIONS request.
     */
    private List<String> options = Lists.newArrayList();
    /**
     * Ignored TRACE request.
     */
    private List<String> trace = Lists.newArrayList();
}
