package com.jmframework.boot.jmspringbootstarter.common.constant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Description: Constants
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23, email: 18:46
 **/
@Slf4j
@Component
public class Constants {

    public Constants(ProjectProperty projectProperty) {
        Constants.REDIS_JWT_KEY_PREFIX = projectProperty.getProjectArtifactId() + ":jwt:";
    }

    /**
     * Super user
     */
    public static final String SU = "root";
    /**
     * Key prefix of JWT stored in Redis.
     */
    public static String REDIS_JWT_KEY_PREFIX;
    /**
     * Token key of request header.
     */
    public static final String REQUEST_TOKEN_KEY = "Authorization";
    /**
     * Prefix of JWT.
     */
    public static final String JWT_PREFIX = "Bearer ";
    /**
     * Star sign
     */
    public static final String ASTERISK = "*";
    /**
     * At sign
     */
    public static final String AT_SIGN = "@";
    /**
     * Default current page
     */
    public static final Integer DEFAULT_CURRENT_PAGE = 1;
    /**
     * Default page size
     */
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    /**
     * Anonymous user's username
     */
    public static final String ANONYMOUS_NAME = "Anonymous User";
}
