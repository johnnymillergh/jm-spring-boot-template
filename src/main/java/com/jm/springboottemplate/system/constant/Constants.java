package com.jm.springboottemplate.system.constant;

import com.jm.springboottemplate.system.util.ProjectPropertyUtil;

/**
 * Description: Constants
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23, email: 18:46
 **/
public interface Constants {
    /**
     * Super user
     */
    String SU = "root";
    /**
     * Key prefix of JWT stored in Redis.
     */
    String REDIS_JWT_KEY_PREFIX = ProjectPropertyUtil.getArtifactId() + ":jwt:";
    /**
     * Token key of request header.
     */
    String REQUEST_TOKEN_KEY = "Authorization";
    /**
     * Prefix of JWT.
     */
    String JWT_PREFIX = "Bearer ";
    /**
     * Star sign
     */
    String ASTERISK = "*";
    /**
     * At sign
     */
    String AT_SIGN = "@";
    /**
     * Default current page
     */
    Integer DEFAULT_CURRENT_PAGE = 1;
    /**
     * Default page size
     */
    Integer DEFAULT_PAGE_SIZE = 10;
    /**
     * Anonymous user's username
     */
    String ANONYMOUS_NAME = "Anonymous User";
}
