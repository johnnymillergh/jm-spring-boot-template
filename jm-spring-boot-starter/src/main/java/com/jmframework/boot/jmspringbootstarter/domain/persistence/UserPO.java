package com.jmframework.boot.jmspringbootstarter.domain.persistence;

import lombok.Data;

import java.util.Date;

/**
 * Description: User (Persistence Object)
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
     * Email
     */
    private String email;
    /**
     * Cellphone number
     */
    private String cellphone;
    /**
     * Password
     */
    private String password;
    /**
     * Nickname
     */
    private String fullName;
    /**
     * Birthday (yyyy-MM-dd)
     */
    private Date birthday;
    /**
     * 58 gender options
     */
    private String gender;
    /**
     * Create time
     */
    private Date gmtCreated;
    /**
     * Modify time
     */
    private Date gmtModified;
    /**
     * Status. Enabled-1，Disenabled-0
     */
    private Integer status;
}
