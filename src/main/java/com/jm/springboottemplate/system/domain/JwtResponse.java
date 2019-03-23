package com.jm.springboottemplate.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: JWT response
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-23
 * @time: 20:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    /**
     * Token
     */
    private String token;
    /**
     * Token type
     */
    private String tokenType = "Bearer";

    public JwtResponse(String token) {
        this.token = token;
    }
}