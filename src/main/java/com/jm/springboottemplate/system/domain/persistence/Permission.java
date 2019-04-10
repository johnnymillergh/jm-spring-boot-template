package com.jm.springboottemplate.system.domain.persistence;

import lombok.Data;

/**
 * Description: Permission, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-23
 * @time: 19:48
 **/
@Data
public class Permission {
    /**
     * Primary key
     */
    private Long id;
    /**
     * URL.
     * If type is equal to 1 (page), it stands for route.
     * If type is equal to 2 (button), it stands for API's url.
     */
    private String url;
    /**
     * Permission description
     */
    private String description;
    /**
     * Permission type. Page-1, Button-2
     */
    private Integer type;
    /**
     * Permission expression.
     */
    private String permission;
    /**
     * Request method of API.
     */
    private String method;
    /**
     * Sort.
     */
    private Integer sort;
    /**
     * Primary key of parent.
     */
    private Long parentId;
}
