package com.jmframework.boot.jmspringbootstarter.service;

import com.jmframework.boot.jmspringbootstarterdomain.authorization.response.GetPermissionsRO;

import java.util.List;

/**
 * <h1>AuthorizationService</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-19 15:01
 **/
public interface AuthorizationService {
    GetPermissionsRO getPermissions(List<String> controllerFullClassName);
}
