package com.jm.springboottemplate.system.domain.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Description: Login, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-23
 * @time: 19:51
 **/
@Data
public class Login {
    /**
     * Username/Email/Phone
     */
    @NotBlank(message = "Login token cannot be empty.")
    private String usernameOrEmailOrPhone;
    /**
     * Password
     */
    @NotBlank(message = "Password cannot be empty.")
    private String password;
    /**
     * Remember me
     */
    private Boolean rememberMe = false;
}
