package com.jmframework.boot.jmspringbootstarter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jmframework.boot.jmspringbootstarter.constant.ApiStatus;
import com.jmframework.boot.jmspringbootstarter.constant.PermissionType;
import com.jmframework.boot.jmspringbootstarter.domain.payload.GetApiListPLO;
import com.jmframework.boot.jmspringbootstarter.domain.payload.SetAllApiInUsePLO;
import com.jmframework.boot.jmspringbootstarter.domain.payload.SetApiInUsePLO;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.PermissionPO;
import com.jmframework.boot.jmspringbootstarter.domain.response.ApiAnalysisRO;
import com.jmframework.boot.jmspringbootstarter.domain.response.ApiControllerRO;
import com.jmframework.boot.jmspringbootstarter.domain.response.ApiRO;
import com.jmframework.boot.jmspringbootstarter.domain.response.GetApiListRO;
import com.jmframework.boot.jmspringbootstarter.exception.BizException;
import com.jmframework.boot.jmspringbootstarter.service.ApiService;
import com.jmframework.boot.jmspringbootstarter.service.PermissionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Description: ApiServiceImpl, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-04-07 13:33
 **/
@Slf4j
@Service
public class ApiServiceImpl implements ApiService {
    private final WebApplicationContext webApplicationContext;
    private final PermissionService permissionService;

    public ApiServiceImpl(WebApplicationContext webApplicationContext,
                          PermissionService permissionService) {
        this.webApplicationContext = webApplicationContext;
        this.permissionService = permissionService;
    }

    @Override
    public ApiControllerRO getAllControllerClass() {
        ApiControllerRO apiControllerRO = new ApiControllerRO();
        // getBean(Class) method may throw an exception due to 2 beans are the same type.
        // Exception message: expected single matching bean but found 2: swagger2ControllerMapping,
        // requestMappingHandlerMapping
        RequestMappingHandlerMapping mapping =
                (RequestMappingHandlerMapping) webApplicationContext.getBean("requestMappingHandlerMapping");
        // handlerMethodMap -> URL:HandlerMethod
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = mapping.getHandlerMethods();
        Map<String, Class> controllerMap = new HashMap<>(32);
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethodMap.entrySet()) {
            controllerMap.put(String.valueOf(entry.getValue().getBean()), entry.getValue().getBeanType());
        }
        for (Map.Entry<String, Class> entry : controllerMap.entrySet()) {
            ApiControllerRO.ApiControllerSubclass apiControllerSubclass = new ApiControllerRO.ApiControllerSubclass();
            apiControllerSubclass.setClassName(entry.getValue().getSimpleName());
            apiControllerSubclass.setPackageName(entry.getValue().getPackage().getName());
            apiControllerRO.getControllerList().add(apiControllerSubclass);
        }
        return apiControllerRO;
    }

    @Override
    public ApiRO getApiByClassFullName(String classFullName, Integer apiStatus) {
        Class<?> clazz;
        try {
            clazz = Class.forName(classFullName);
        } catch (ClassNotFoundException e) {
            log.error("Error occurred when find class by classFullName: {}", e.getMessage(), e);
            throw new BizException("Error occurred when find class by classFullName: " + e.getMessage());
        }
        ApiStatus status = ApiStatus.getByStatus(apiStatus);
        return getPermissionsByClass(clazz, status);
    }

    @Override
    public ApiAnalysisRO getApiAnalysis(String classFullName) {
        ApiAnalysisRO apiAnalysisRO = new ApiAnalysisRO();
        if (StringUtils.isBlank(classFullName)) {
            // Query API statistics of global scope.
            ApiControllerRO apiControllerRO = this.getAllControllerClass();
            for (ApiControllerRO.ApiControllerSubclass acs : apiControllerRO.getControllerList()) {
                String clazzFullName = acs.getPackageName() + "." + acs.getClassName();
                ApiRO apiRO = this.getApiByClassFullName(clazzFullName, ApiStatus.IN_USED.getStatus());
                apiAnalysisRO.appendIdledApiCount(apiRO.getIdledApiCount());
                apiAnalysisRO.appendInUseApiCount(apiRO.getInUseApiCount());
            }
            apiAnalysisRO.calculateSum();
            return apiAnalysisRO;
        }
        // Query API statistics of specific class scope.
        ApiRO apiRO = this.getApiByClassFullName(classFullName, ApiStatus.IN_USED.getStatus());
        apiAnalysisRO.appendIdledApiCount(apiRO.getIdledApiCount());
        apiAnalysisRO.appendInUseApiCount(apiRO.getInUseApiCount());
        apiAnalysisRO.calculateSum();
        return apiAnalysisRO;
    }

    @Override
    public boolean setApiInUse(SetApiInUsePLO setApiInUsePLO) {
        PermissionPO permissionPO = new PermissionPO();
        BeanUtil.copyProperties(setApiInUsePLO, permissionPO);
        permissionPO.setType(PermissionType.BUTTON.getType());
        return permissionService.savePermission(permissionPO);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean setAllApiInUse(SetAllApiInUsePLO setAllApiInUsePLO) {
        ApiRO idledApi = this.getApiByClassFullName(setAllApiInUsePLO.getClassFullName(),
                                                    ApiStatus.IDLED.getStatus());
        if (CollectionUtils.isEmpty(idledApi.getApiList())) {
            throw new BizException("All api have been set in used");
        }
        idledApi.getApiList().forEach(item -> {
            PermissionPO permissionPO = new PermissionPO();
            BeanUtil.copyProperties(item, permissionPO);
            permissionPO.setType(PermissionType.BUTTON.getType());
            boolean status = permissionService.savePermission(permissionPO);
            if (!status) {
                throw new BizException("Unable to save");
            }
        });
        return true;
    }

    @Override
    public List<GetApiListRO> getApiList(GetApiListPLO getApiListPLO) {
        return permissionService.queryApiList(getApiListPLO);
    }

    /**
     * <p>Get permissions by class.</p>
     * <p>Permission model contains URL(API) information.</p>
     * TODO: Refactor this method which has more than 80 lines of code.
     *
     * @param clazz     class
     * @param apiStatus API status
     * @return permission list (API list), inUseApiCount, idledApiCount
     */
    private ApiRO getPermissionsByClass(Class<?> clazz, ApiStatus apiStatus) {
        boolean restControllerExisted = clazz.isAnnotationPresent(RestController.class);
        if (!restControllerExisted) {
            return new ApiRO();
        }
        List<ApiRO.Uri> result = new ArrayList<>();
        RequestMapping restControllersRequestMappingAnnotation = clazz.getAnnotation(RequestMapping.class);
        String urlPrefix = "";
        if (restControllersRequestMappingAnnotation != null) {
            urlPrefix = restControllersRequestMappingAnnotation.value()[0];
        }
        if (StringUtils.isBlank(urlPrefix)) {
            return new ApiRO();
        }
        if (apiStatus == ApiStatus.IN_USED) {
            ApiRO apiRO = new ApiRO();
            List<PermissionPO> permissionPOS = permissionService.selectApisByUrlPrefix(urlPrefix);
            int allMethodCount = clazz.getDeclaredMethods().length;
            int inUseApiCount = permissionPOS.size();
            int idledApiCount = allMethodCount - inUseApiCount;
            for (PermissionPO permissionPO : permissionPOS) {
                ApiRO.Uri uri = new ApiRO.Uri();
                uri.setUrl(permissionPO.getUrl());
                uri.setMethod(permissionPO.getMethod());
                uri.setDescription(permissionPO.getDescription());
                result.add(uri);
            }
            apiRO.setApiList(result);
            apiRO.setIdledApiCount(idledApiCount);
            apiRO.setInUseApiCount(inUseApiCount);
            return apiRO;
        }
        // Get all methods declared in class.
        Method[] methods = clazz.getDeclaredMethods();
        int allMethodCount = clazz.getDeclaredMethods().length;
        if (methods.length == 0) {
            return new ApiRO();
        }
        Map<String, Object> resultMap = new HashMap<>(4);
        for (Method method : methods) {
            if (method.getModifiers() != Modifier.PUBLIC) {
                continue;
            }
            boolean methodExisted = method.isAnnotationPresent(GetMapping.class)
                    || method.isAnnotationPresent(PostMapping.class);
            if (!methodExisted) {
                continue;
            }
            // FIXME: Current system only supports "GET" & "POST" requests.
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            PostMapping postMapping = method.getAnnotation(PostMapping.class);
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            ApiRO.Uri uri = new ApiRO.Uri();
            if (getMapping != null) {
                uri.setUrl(urlPrefix + getMapping.value()[0]);
                uri.setMethod("GET");
            } else if (postMapping != null) {
                uri.setUrl(urlPrefix + postMapping.value()[0]);
                uri.setMethod("POST");
            }
            if (apiOperation != null) {
                uri.setDescription(apiOperation.notes());
            }
            result.add(uri);
        }
        Iterator<ApiRO.Uri> iterator = result.iterator();
        while (iterator.hasNext()) {
            ApiRO.Uri uri = iterator.next();
            PermissionPO permissions = permissionService.selectApiByUrl(uri.getUrl());
            if (permissions == null) {
                continue;
            }
            iterator.remove();
        }
        int idledApiCount = result.size();
        int inUseApiCount = allMethodCount - idledApiCount;
        ApiRO apiRO = new ApiRO();
        apiRO.setApiList(result);
        apiRO.setIdledApiCount(idledApiCount);
        apiRO.setInUseApiCount(inUseApiCount);
        return apiRO;
    }
}
