package com.jmframework.boot.jmspringbootstarterdomain.authorization.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <h1>GetPermissionsPLO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-25 10:19
 **/
@Data
public class GetPermissionsPLO {
    @NotNull
    private Integer permissionType;
    private List<String> controllerFullClassName;
}
