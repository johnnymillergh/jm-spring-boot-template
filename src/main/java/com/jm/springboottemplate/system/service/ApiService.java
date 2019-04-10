package com.jm.springboottemplate.system.service;

import com.jm.springboottemplate.system.response.ResponseBodyBean;

import java.util.List;

/**
 * Description: ApiService, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-04-07
 * @time: 13:30
 **/
public interface ApiService {
    /**
     * Get controller classes information list.
     *
     * @return controller list.
     */
    List getAllControllerClass();

    /**
     * Get permissions by class's full name.
     *
     * @param classFullName full name of class.
     * @return permission list.
     */
    ResponseBodyBean getPermissionsByClassFullName(String classFullName, Integer apiStatus);
}
