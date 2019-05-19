package com.jmframework.boot.jmspringbootstarter.service;

import com.jmframework.boot.jmspringbootstarter.domain.payload.GetRoleListPLO;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.RolePO;

import java.util.List;

/**
 * <h1>RoleService</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-18 12:03
 **/
public interface RoleService {
    /**
     * Get role list
     *
     * @param getRoleListPLO payload object
     * @return role list
     */
    List<RolePO> getList(GetRoleListPLO getRoleListPLO);

    /**
     * Check the uniqueness of name of role
     *
     * @param roleName name of role
     * @return true - available; false - not available
     */
    boolean checkRoleName(String roleName);

    /**
     * Insert a role
     *
     * @param rolePO persistence object
     * @return true - insert successfully; false - insert failure
     */
    boolean insertRole(RolePO rolePO);
}
