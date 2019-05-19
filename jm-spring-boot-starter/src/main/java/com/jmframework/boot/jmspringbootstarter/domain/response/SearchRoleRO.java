package com.jmframework.boot.jmspringbootstarter.domain.response;

import lombok.Data;

import java.util.Date;

/**
 * <h1>SearchRoleRO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-19 10:01
 **/
@Data
public class SearchRoleRO {
    /**
     * Primary key
     */
    private Long id;
    /**
     * Role name
     */
    private String name;
    /**
     * Role description
     */
    private String description;
    /**
     * Create time
     */
    private Date gmtCreated;
    /**
     * Modify time
     */
    private Date gmtModified;
}
