package com.jmframework.boot.jmspringbootstarterdomain.authorization.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <h1>SubmitAuthorizationPLO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-02 20:01
 **/
@Data
public class SubmitAuthorizationPLO {
    @NotEmpty(message = "role id list is required")
    private List<Long> roleIdList;
    @NotEmpty(message = "permission id list is required")
    private List<Long> permissionIdList;
}
