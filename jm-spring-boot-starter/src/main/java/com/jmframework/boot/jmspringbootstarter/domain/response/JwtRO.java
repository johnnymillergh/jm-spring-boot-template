package com.jmframework.boot.jmspringbootstarter.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: JWT response
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 20:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRO {
    /**
     * Token
     */
    private String token;
    /**
     * Token type
     */
    private String tokenType = "Bearer";

    public JwtRO(String token) {
        this.token = token;
    }
}
