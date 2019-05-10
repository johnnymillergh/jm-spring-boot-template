package com.jmframework.boot.jmspringbootstarter.controller;

import com.jmframework.boot.jmspringbootstarter.domain.payload.SetAllApiInUse;
import com.jmframework.boot.jmspringbootstarter.domain.payload.SetApiInUse;
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
@RequestMapping("/apiManagement")
@Api(value = "Api Management Controller", tags = {"api"})
public class ApiManagementController {
    private final ApiService apiService;

    public ApiManagementController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/getController")
    @ApiOperation(value = "Get controller list", notes = "Get controller list")
    public ResponseBodyBean getController() {
        return ResponseBodyBean.ofSuccess(apiService.getAllControllerClass());
    }

    @GetMapping("/getApiByControllerClass")
    @ApiOperation(value = "Get API by controller", notes = "Get API by controller")
    public ResponseBodyBean getApiByControllerClass(String controllerClass, Integer apiStatus) {
        if (StringUtils.isBlank(controllerClass)) {
            return ResponseBodyBean.ofFailure("controllerClass is not empty");
        }
        return ResponseBodyBean.ofSuccess(apiService.getApiByClassFullName(controllerClass, apiStatus));
    }

    @GetMapping("/getApiAnalysis")
    @ApiOperation(value = "Get API analysis", notes = "Get API analysis")
    public ResponseBodyBean getApiAnalysis(String classFullName) {
        return ResponseBodyBean.ofSuccess(apiService.getApiAnalysis(classFullName));
    }

    @PostMapping("/setApiInUse")
    @ApiOperation(value = "Set API in use", notes = "Set API in use")
    public ResponseBodyBean setApiInUse(@Valid @RequestBody SetApiInUse setApiInUse) {
        boolean operationStatus = apiService.setApiInUse(setApiInUse);
        if (operationStatus) {
            return ResponseBodyBean.ofSuccess("Operation done");
        }
        return ResponseBodyBean.ofFailure("Failed operation");
    }

    @PostMapping("/setAllApiInUse")
    @ApiOperation(value = "Set all api in use", notes = "Set all api in use")
    public ResponseBodyBean setAllApiInUse(@Valid @RequestBody SetAllApiInUse setAllApiInUse) {
        boolean operationStatus = apiService.setAllApiInUse(setAllApiInUse);
        if (operationStatus) {
            return ResponseBodyBean.ofSuccess("Operation done");
        }
        return ResponseBodyBean.ofFailure("Failed operation");
    }
}
