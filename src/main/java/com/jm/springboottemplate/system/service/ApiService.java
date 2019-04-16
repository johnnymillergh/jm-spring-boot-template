package com.jm.springboottemplate.system.service;

import com.jm.springboottemplate.system.domain.response.Api;
import com.jm.springboottemplate.system.domain.response.ApiAnalysis;
import com.jm.springboottemplate.system.domain.response.ApiController;

/**
 * Description: ApiService, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-04-07 13:30
 **/
public interface ApiService {
    /**
     * Get controller classes information list.
     *
     * @return controller list.
     */
    ApiController getAllControllerClass();

    /**
     * Get permissions by class's full name.
     *
     * @param classFullName full name of class.
     * @param apiStatus     api status.
     * @return permission list.
     */
    Api getApiByClassFullName(String classFullName, Integer apiStatus);

    /**
     * Get API analysis.
     *
     * @param classFullName Full name of class.
     * @return API analysis.
     */
    ApiAnalysis getApiAnalysis(String classFullName);
}
