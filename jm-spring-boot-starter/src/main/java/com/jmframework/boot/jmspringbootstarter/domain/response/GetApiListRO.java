package com.jmframework.boot.jmspringbootstarter.domain.response;

import lombok.Data;

import java.util.Date;

/**
 * <h1>GetApiListPLO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-11 14:08
 **/
@Data
public class GetApiListRO {
    private Long id;
    private String url;
    private String description;
    private String permissionExpression;
    private String method;
    private Integer sort;
    private Date gmtCreated;
    private Date gmtModified;
}
