package com.jm.springboottemplate.system.domain.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Description: Login, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 19:51
 **/
@Data
public class Login {
    /**
     * Username/Email/Phone
     */
    @NotBlank(message = "Login token is required")
    private String usernameOrEmailOrPhone;
    /**
     * Password
     */
    @NotBlank(message = "Password is required")
    private String password;
    /**
     * Remember me
     */
    private Boolean rememberMe = false;
}
