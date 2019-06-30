package com.jmframework.boot.jmspringbootstarterdomain.user.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <h1>SearchUserPLO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-30 15:39
 **/
@Data
public class SearchUserPLO {
    @NotEmpty
    private String username;
}
