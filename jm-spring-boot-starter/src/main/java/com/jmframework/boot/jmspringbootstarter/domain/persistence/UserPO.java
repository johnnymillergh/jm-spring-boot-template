package com.jmframework.boot.jmspringbootstarter.domain.persistence;

import lombok.Data;

import java.util.Date;

/**
 * Description: User
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 19:51
 **/
@Data
public class UserPO {
    /**
     * Primary key
     */
    private Long id;
    /**
     * Username
     */
    private String username;
    /**
     * Password
     */
    private String password;
    /**
     * Nickname
     */
    private String nickname;
    /**
     * Phone
     */
    private String phone;
    /**
     * Email
     */
    private String email;
    /**
     * Birthday
     */
    private Long birthday;
    /**
     * Gender
     */
    private Integer sex;
    /**
     * Status
     */
    private Integer status;
    /**
     * Create time
     */
    private Date createTime;
    /**
     * Modify time
     */
    private Date modifyTime;
}
