package com.jmframework.boot.jmspringbootstarter.domain.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Description: RegisterPLO, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 19:48
 **/
@Data
public class RegisterPLO {
    /**
     * Username (Unique)
     */
    @Size(min = 4, max = 50, message = "length of username must be between 4 and 50")
    @NotEmpty(message = "username is required")
    private String username;
    /**
     * Email (Unique)
     */
    @Size(max = 30, message = "length of email cannot exceed 30")
    @NotEmpty(message = "email is required")
    private String email;
    /**
     * Password
     */
    @Size(min = 8, max = 30, message = "length of password must be between 8 and 24")
    @NotEmpty(message = "password is required")
    private String password;
}
