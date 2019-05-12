package com.jmframework.boot.jmspringbootstarter.domain.persistence;

import lombok.Data;

import java.util.Date;

/**
 * Description: RolePO, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 19:50
 **/
@Data
public class RolePO {
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
