package com.jmframework.boot.jmspringbootstarter.service;

import com.jmframework.boot.jmspringbootstarterdomain.permission.payload.GetApiListPLO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.payload.SetApiInUsePLO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.response.ApiAnalysisRO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.response.ApiControllerRO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.response.GetApiByControllerClassRO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.response.GetApiListRO;

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
    ApiControllerRO getAllControllerClass();

    /**
     * Get permissions by class's full name.
     *
     * @param classFullName full name of class.
     * @param apiStatus     api status.
     * @return permission list.
     */
    GetApiByControllerClassRO getApiByClassFullName(String classFullName, Integer apiStatus);

    /**
     * Get API analysis.
     *
     * @param classFullName Full name of class.
     * @return API analysis.
     */
    ApiAnalysisRO getApiAnalysis(String classFullName);

    /**
     * Set API in use
     *
     * @param plo form data for setting api in use
     * @return true - successful operation; false - failed operation
     */
    boolean setApiInUse(SetApiInUsePLO plo);

    /**
     * Set a controller's all api in use
     *
     * @param controllerClass controller's full class name
     * @return true - successful operation; false - failed operation
     */
    boolean setAllApiInUse(String controllerClass);

    /**
     * Get API list
     *
     * @param plo payload object
     * @return API list (response object)
     */
    GetApiListRO getApiList(GetApiListPLO plo);
}
