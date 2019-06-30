package com.jmframework.boot.jmspringbootstarter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarterdomain.authorization.payload.SubmitAuthorizationPLO;
import com.jmframework.boot.jmspringbootstarterdomain.authorization.response.GetPermissionsRO;
import com.jmframework.boot.jmspringbootstarterdomain.role.persistence.RolePO;

import java.util.List;

/**
 * <h1>AuthorizationService</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-19 15:01
 **/
public interface AuthorizationService {
    /**
     * Get role list for selection
     *
     * @param page pagination object
     * @return role list
     */
    List<RolePO> getRoleListForSelection(Page page);

    /**
     * Get permissions
     *
     * @param controllerFullClassName list of controller full class name
     * @return permissions
     */
    GetPermissionsRO getPermissions(List<String> controllerFullClassName);

    /**
     * Authorization permissions to roles
     *
     * @param plo payload object
     * @return true - authorization done; false - authorization failed
     */
    boolean authorizePermission(SubmitAuthorizationPLO plo);
}
