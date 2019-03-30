package com.jm.springboottemplate.system.domain.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
    @Size(max = 50)
    @NotEmpty(message = "Username is required")
    private String username;
    /**
     * Password
     */
    @Size(min = 8, max = 24, message = "Length of password must be between 8 and 24")
    @NotEmpty(message = "Password is required")
    private String password;
}
