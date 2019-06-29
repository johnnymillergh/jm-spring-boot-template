package com.jmframework.boot.jmspringbootstarter.controller;

import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import com.jmframework.boot.jmspringbootstarter.service.ApiService;
import com.jmframework.boot.jmspringbootstarterdomain.common.constant.HttpStatus;
import com.jmframework.boot.jmspringbootstarterdomain.permission.constant.ApiStatus;
import com.jmframework.boot.jmspringbootstarterdomain.permission.payload.GetApiByControllerClassPLO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.payload.GetApiListPLO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.payload.SetApiInUsePLO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.response.ApiAnalysisRO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.response.ApiControllerRO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.response.GetApiByControllerClassRO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.response.GetApiListRO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.StringUtils;
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
    @ApiOperation(value = "/get-controller", notes = "Get controller list")
    public ResponseBodyBean<ApiControllerRO> getController() {
        return ResponseBodyBean.ofSuccess(apiService.getAllControllerClass());
    }

    @GetMapping("/get-api-by-controller-class")
    @ApiOperation(value = "/get-api-by-controller-class", notes = "Get API by controller")
    public ResponseBodyBean<GetApiByControllerClassRO> getApiByControllerClass(@Valid GetApiByControllerClassPLO plo) {
        ApiStatus status = ApiStatus.getByStatus(plo.getApiStatus());
        if (status == null) {
            return ResponseBodyBean.setResponse(HttpStatus.PARAM_INVALID.getCode(),
                                                HttpStatus.PARAM_INVALID.getMessage(),
                                                null);
        }
        return ResponseBodyBean.ofSuccess(apiService.getApiByClassFullName(plo.getControllerClass(),
                                                                           plo.getApiStatus()));
    }

    @GetMapping("/get-api-analysis")
    @ApiOperation(value = "/get-api-analysis", notes = "Get API analysis")
    public ResponseBodyBean<ApiAnalysisRO> getApiAnalysis(String classFullName) {
        return ResponseBodyBean.ofSuccess(apiService.getApiAnalysis(classFullName));
    }

    @PostMapping("/set-api-in-use")
    @ApiOperation(value = "/set-api-in-use", notes = "Set API in use")
    public ResponseBodyBean setApiInUse(@Valid @RequestBody SetApiInUsePLO setApiInUsePLO) {
        boolean operationStatus = apiService.setApiInUse(setApiInUsePLO);
        if (operationStatus) {
            return ResponseBodyBean.ofSuccess("Operation done");
        }
        return ResponseBodyBean.ofFailure("Failed operation");
    }

    @PostMapping("/set-all-api-in-use")
    @ApiOperation(value = "/set-all-api-in-use", notes = "Set all api in use")
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
    @ApiOperation(value = "/get-api-list", notes = "Get API list")
    public ResponseBodyBean<GetApiListRO> getApiList(@Valid @RequestBody GetApiListPLO getApiListPLO) {
        return ResponseBodyBean.ofSuccess(apiService.getApiList(getApiListPLO));
    }
}
