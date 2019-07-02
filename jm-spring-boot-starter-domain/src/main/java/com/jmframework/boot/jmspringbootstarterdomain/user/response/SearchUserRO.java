package com.jmframework.boot.jmspringbootstarterdomain.user.response;

import lombok.Data;

import java.util.Date;

/**
 * <h1>SearchUserRO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-30 15:19
 **/
@Data
public class SearchUserRO {
    private Long id;
    private String username;
    private String email;
    private String gender;
    private Date gmtCreated;
    private Date gmtModified;
    private Integer status;
}
