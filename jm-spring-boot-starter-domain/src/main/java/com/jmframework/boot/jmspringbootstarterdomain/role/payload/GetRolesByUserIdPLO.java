package com.jmframework.boot.jmspringbootstarterdomain.role.payload;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <h1>GetRolesByUserIdPLO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-29 15:07
 **/
@Data
public class GetRolesByUserIdPLO {
    @NotNull
    @Min(value = 1L, message = "invalid parameter")
    private Long userId;
}
