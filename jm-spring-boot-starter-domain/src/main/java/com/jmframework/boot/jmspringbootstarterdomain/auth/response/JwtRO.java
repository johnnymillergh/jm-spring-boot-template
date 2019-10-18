package com.jmframework.boot.jmspringbootstarterdomain.auth.response;

import lombok.Data;

/**
 * Description: JWT response
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 20:52
 **/
@Data
public class JwtRO {
    /**
     * Token
     */
    private String token;
    /**
     * Token type
     */
    private String tokenType = "Bearer";
    private String username;
    private String fullName;

    public JwtRO(String token) {
        this.token = token;
    }
}
