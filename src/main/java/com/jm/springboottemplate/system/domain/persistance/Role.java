package com.jm.springboottemplate.system.domain.persistance;

import lombok.Data;

import java.util.Date;

/**
 * Description: Role, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-23
 * @time: 19:50
 **/
@Data
public class Role {
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
    private Date createTime;
    /**
     * Update time
     */
    private Date updateTime;
}