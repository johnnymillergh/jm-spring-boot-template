package com.jmframework.boot.jmspringbootstarter.controller;

import com.jmframework.boot.jmspringbootstarter.constant.ApiStatus;
import com.jmframework.boot.jmspringbootstarter.domain.payload.GetApiListPLO;
import com.jmframework.boot.jmspringbootstarter.domain.payload.SetApiInUsePLO;
import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import com.jmframework.boot.jmspringbootstarter.service.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Description: ApiManagementController, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-04-11 10:02
 **/
@Slf4j
@RestController
@RequestMapping("/api-management")
@Api(tags = {"/api-management"})
public class ApiManagementController {
    private final ApiService apiService;

    public ApiManagementController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/get-controller")
    @ApiOperation(value = "Get controller list", notes = "Get controller list")
    public ResponseBodyBean getController() {
        return ResponseBodyBean.ofSuccess(apiService.getAllControllerClass());
    }

    @GetMapping("/get-api-by-controller-class")
    @ApiOperation(value = "Get API by controller", notes = "Get API by controller")
    public ResponseBodyBean getApiByControllerClass(String controllerClass, Integer apiStatus) {
        ApiStatus status = ApiStatus.getByStatus(apiStatus);
        if (status == null) {
            return ResponseBodyBean.ofError();
        }
        if (StringUtils.isBlank(controllerClass)) {
            return ResponseBodyBean.ofFailure("controllerClass is empty");
        }
        return ResponseBodyBean.ofSuccess(apiService.getApiByClassFullName(controllerClass, apiStatus));
    }

    @GetMapping("/get-api-analysis")
    @ApiOperation(value = "Get API analysis", notes = "Get API analysis")
    public ResponseBodyBean getApiAnalysis(String classFullName) {
        return ResponseBodyBean.ofSuccess(apiService.getApiAnalysis(classFullName));
    }

    @PostMapping("/set-api-in-use")
    @ApiOperation(value = "Set API in use", notes = "Set API in use")
    public ResponseBodyBean setApiInUse(@Valid @RequestBody SetApiInUsePLO setApiInUsePLO) {
        boolean operationStatus = apiService.setApiInUse(setApiInUsePLO);
        if (operationStatus) {
            return ResponseBodyBean.ofSuccess("Operation done");
        }
        return ResponseBodyBean.ofFailure("Failed operation");
    }

    @PostMapping("/set-all-api-in-use")
    @ApiOperation(value = "Set all api in use", notes = "Set all api in use")
    public ResponseBodyBean setAllApiInUse(String controllerClass) {
        if (StringUtils.isBlank(controllerClass)) {
            return ResponseBodyBean.ofFailure("controllerClass is not provided");
        }
        boolean operationStatus = apiService.setAllApiInUse(controllerClass);
        if (operationStatus) {
            return ResponseBodyBean.ofSuccess("Operation done");
        }
        return ResponseBodyBean.ofFailure("Failed operation");
    }

    @PostMapping("/get-api-list")
    @ApiOperation(value = "Get API list", notes = "Get API list")
    public ResponseBodyBean getApiList(@Valid @RequestBody GetApiListPLO getApiListPLO) {
        return ResponseBodyBean.ofSuccess(apiService.getApiList(getApiListPLO));
    }
}
