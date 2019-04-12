package com.jm.springboottemplate.system.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: RbacAuthorityService, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-04-07 19:52
 **/
public interface RbacAuthorityService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
