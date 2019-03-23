package com.jm.springboottemplate.system.domain.persistance;

import lombok.Data;

/**
 * Description: User-Role relation.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-23
 * @time: 19:52
 **/
@Data
public class UserRole {
    /**
     * User ID.
     */
    private Long userId;

    /**
     * Role ID.
     */
    private Long roleId;
}