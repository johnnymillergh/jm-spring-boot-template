package com.jmframework.boot.jmspringbootstarterdomain.user.payload;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <h1>AssignRoleToUserPLO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-07-02 08:53
 **/
@Data
public class AssignRoleToUserPLO {
    @NotEmpty
    private String username;
    @NotNull
    @Min(value = 1L)
    private Long userId;
    @NotEmpty
    private List<@Min(value = 1L) Long> roleIdList;
}
