package com.jmframework.boot.jmspringbootstarterdomain.user.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <h1>GetAvatarPLO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-07-06 23:54
 **/
@Data
public class GetAvatarPLO {
    @NotEmpty
    private String username;
}
