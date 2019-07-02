package com.jmframework.boot.jmspringbootstarterdomain.user.payload;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <h1>GetUserInfoPLO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-30 11:00
 **/
@Data
public class GetUserInfoPLO {
    @NotNull
    @Min(value = 1L)
    private Long userId;
    @NotNull
    private Integer status;
}
