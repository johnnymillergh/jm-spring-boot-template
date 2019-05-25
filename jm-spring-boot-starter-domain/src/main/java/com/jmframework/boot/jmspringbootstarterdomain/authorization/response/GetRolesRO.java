package com.jmframework.boot.jmspringbootstarterdomain.authorization.response;

import lombok.Data;

import java.util.Date;

/**
 * <h1>GetRolesRO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-25 09:36
 **/
@Data
public class GetRolesRO {
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
