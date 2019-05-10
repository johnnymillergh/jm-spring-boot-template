package com.jmframework.boot.jmspringbootstarter.service;

import com.jmframework.boot.jmspringbootstarter.domain.payload.SetAllApiInUse;
import com.jmframework.boot.jmspringbootstarter.domain.payload.SetApiInUse;
import com.jmframework.boot.jmspringbootstarter.domain.response.Api;
import com.jmframework.boot.jmspringbootstarter.domain.response.ApiAnalysis;
import com.jmframework.boot.jmspringbootstarter.domain.response.ApiController;

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

    /**
     * Set API in use
     *
     * @param setApiInUse form data for setting api in use
     * @return true - successful operation; false - failed operation
     */
    boolean setApiInUse(SetApiInUse setApiInUse);

    /**
     * Set a controller's all api in use
     *
     * @param setAllApiInUse form data
     * @return true - successful operation; false - failed operation
     */
    boolean setAllApiInUse(SetAllApiInUse setAllApiInUse);
}
