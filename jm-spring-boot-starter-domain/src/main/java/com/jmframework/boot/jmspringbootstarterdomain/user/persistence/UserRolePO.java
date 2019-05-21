package com.jmframework.boot.jmspringbootstarterdomain.user.persistence;

import lombok.Data;

/**
 * Description: User-Role relation.
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
