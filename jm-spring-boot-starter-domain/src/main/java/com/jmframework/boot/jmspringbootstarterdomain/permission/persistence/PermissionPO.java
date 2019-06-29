package com.jmframework.boot.jmspringbootstarterdomain.permission.persistence;

import lombok.Data;

import java.util.Date;

/**
 * Description: PermissionPO, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 19:48
 **/
@Data
public class PermissionPO {
    /**
     * Primary key
     */
    private Long id;
    /**
     * URL. If type is equal to 1 (page), it stands for route. If type is equal to 2 (button), it stands for URL of
     * API.
     */
    private String url;
    /**
     * PermissionPO description
     */
    private String description;
    /**
     * PermissionPO type. Page-1, Button-2
     */
    private Integer type;
    /**
     * PermissionPO expression.
     */
    private String permissionExpression;
    /**
     * HTTP method of API.
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
    /**
     * Created time
     */
    private Date gmtCreated;
    /**
     * Modified time
     */
    private Date gmtModified;
    /**
     * Deleted flag
     */
    private Byte deleted;
}
