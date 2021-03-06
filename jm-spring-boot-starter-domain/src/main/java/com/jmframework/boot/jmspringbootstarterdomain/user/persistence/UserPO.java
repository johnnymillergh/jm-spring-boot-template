package com.jmframework.boot.jmspringbootstarterdomain.user.persistence;

import lombok.Data;

import java.util.Date;

/**
 * <h1>UserPO</h1>
 * <p>Persistence class for table `t_user`</p>
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
     * User avatar full path on SFTP server
     */
    private String avatar;
    /**
     * Status. 1 - enabled, 2 - disabled
     */
    private Integer status;
    /**
     * Create time
     */
    private Date gmtCreated;
    /**
     * Modify time
     */
    private Date gmtModified;
}
