package com.jmframework.boot.jmspringbootstarterdomain.user.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <h1>UpdateAvatarPLO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-07-09 16:33
 **/
@Data
public class UpdateAvatarPLO {
    @NotEmpty
    private String username;
}
