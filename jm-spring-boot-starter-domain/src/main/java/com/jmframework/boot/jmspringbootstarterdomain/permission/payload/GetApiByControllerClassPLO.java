package com.jmframework.boot.jmspringbootstarterdomain.permission.payload;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <h1>GetApiByControllerClassPLO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-08 00:57
 **/
@Data
public class GetApiByControllerClassPLO {
    @NotEmpty
    private String controllerClass;
    @NotNull
    @Min(value = 0)
    private Integer apiStatus;
}
