package com.jmframework.boot.jmspringbootstarterdomain.user.persistence;

import lombok.Data;

/**
 * <h1>UserRolePO</h1>
 * <p>User-role relation. Persistence class for table `t_user_role`</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 19:52
 **/
@Data
public class UserRolePO {
    /**
     * User ID.
     */
    private Long userId;
    /**
     * Role ID.
     */
    private Long roleId;
}
