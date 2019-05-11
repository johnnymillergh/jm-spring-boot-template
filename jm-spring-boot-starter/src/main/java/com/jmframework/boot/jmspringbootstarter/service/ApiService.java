package com.jmframework.boot.jmspringbootstarter.service;

import com.jmframework.boot.jmspringbootstarter.domain.payload.SetAllApiInUsePLO;
import com.jmframework.boot.jmspringbootstarter.domain.payload.SetApiInUsePLO;
import com.jmframework.boot.jmspringbootstarter.domain.response.ApiAnalysisRO;
import com.jmframework.boot.jmspringbootstarter.domain.response.ApiControllerRO;
import com.jmframework.boot.jmspringbootstarter.domain.response.ApiRO;

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
    ApiRO getApiByClassFullName(String classFullName, Integer apiStatus);

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
     * @param setApiInUsePLO form data for setting api in use
     * @return true - successful operation; false - failed operation
     */
    boolean setApiInUse(SetApiInUsePLO setApiInUsePLO);

    /**
     * Set a controller's all api in use
     *
     * @param setAllApiInUsePLO form data
     * @return true - successful operation; false - failed operation
     */
    boolean setAllApiInUse(SetAllApiInUsePLO setAllApiInUsePLO);
}
