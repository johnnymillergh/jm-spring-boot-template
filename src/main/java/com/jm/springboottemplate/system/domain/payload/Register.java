package com.jm.springboottemplate.system.domain.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Description: Register, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-23
 * @time: 19:48
 **/
@Data
public class Register {
    /**
     * Username
     */
    @NotEmpty(message = "Username cannot be empty.")
    private String username;
    /**
     * Password
     */
    @NotEmpty(message = "Password cannot be empty.")
    private String password;
}
