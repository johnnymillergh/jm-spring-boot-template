package com.jmframework.boot.jmspringbootstarter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jmframework.boot.jmspringbootstarter.constant.ApiStatus;
import com.jmframework.boot.jmspringbootstarter.constant.PermissionType;
import com.jmframework.boot.jmspringbootstarter.domain.payload.SetAllApiInUse;
import com.jmframework.boot.jmspringbootstarter.domain.payload.SetApiInUse;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.Permission;
import com.jmframework.boot.jmspringbootstarter.domain.response.Api;
import com.jmframework.boot.jmspringbootstarter.domain.response.ApiAnalysis;
import com.jmframework.boot.jmspringbootstarter.domain.response.ApiController;
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
    public ApiController getAllControllerClass() {
        ApiController apiController = new ApiController();
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
            ApiController.ApiControllerSubclass apiControllerSubclass = new ApiController.ApiControllerSubclass();
            apiControllerSubclass.setClassName(entry.getValue().getSimpleName());
            apiControllerSubclass.setPackageName(entry.getValue().getPackage().getName());
            apiController.getControllerList().add(apiControllerSubclass);
        }
        return apiController;
    }

    @Override
    public Api getApiByClassFullName(String classFullName, Integer apiStatus) {
        Class<?> clazz;
        try {
            clazz = Class.forName(classFullName);
        } catch (ClassNotFoundException e) {
            log.error("Error occurred when find class by classFullName. {}", e.getMessage(), e);
            throw new BizException("Error occurred when find class by classFullName. " + e.getMessage());
        }
        ApiStatus status = ApiStatus.getByStatus(apiStatus);
        return getPermissionsByClass(clazz, status);
    }

    @Override
    public ApiAnalysis getApiAnalysis(String classFullName) {
        ApiAnalysis apiAnalysis = new ApiAnalysis();
        if (StringUtils.isBlank(classFullName)) {
            // Query API statistics of global scope.
            ApiController apiController = this.getAllControllerClass();
            for (ApiController.ApiControllerSubclass acs : apiController.getControllerList()) {
                String clazzFullName = acs.getPackageName() + "." + acs.getClassName();
                Api api = this.getApiByClassFullName(clazzFullName, ApiStatus.IN_USED.getStatus());
                apiAnalysis.appendIdledApiCount(api.getIdledApiCount());
                apiAnalysis.appendInUseApiCount(api.getInUseApiCount());
            }
            apiAnalysis.calculateSum();
            return apiAnalysis;
        }
        // Query API statistics of specific class scope.
        Api api = this.getApiByClassFullName(classFullName, ApiStatus.IN_USED.getStatus());
        apiAnalysis.appendIdledApiCount(api.getIdledApiCount());
        apiAnalysis.appendInUseApiCount(api.getInUseApiCount());
        apiAnalysis.calculateSum();
        return apiAnalysis;
    }

    @Override
    public boolean setApiInUse(SetApiInUse setApiInUse) {
        Permission permission = new Permission();
        BeanUtil.copyProperties(setApiInUse, permission);
        permission.setType(PermissionType.BUTTON.getType());
        return permissionService.savePermission(permission);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean setAllApiInUse(SetAllApiInUse setAllApiInUse) {
        Api idledApi = this.getApiByClassFullName(setAllApiInUse.getClassFullName(),
                                                  ApiStatus.IDLED.getStatus());
        if (CollectionUtils.isEmpty(idledApi.getApiList())) {
            throw new BizException("All api have been set in used");
        }
        idledApi.getApiList().forEach(item -> {
            Permission permission = new Permission();
            BeanUtil.copyProperties(item, permission);
            permission.setType(PermissionType.BUTTON.getType());
            boolean status = permissionService.savePermission(permission);
            if (!status) {
                throw new BizException("Unable to save");
            }
        });
        return true;
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
    private Api getPermissionsByClass(Class<?> clazz, ApiStatus apiStatus) {
        boolean restControllerExisted = clazz.isAnnotationPresent(RestController.class);
        if (!restControllerExisted) {
            return new Api();
        }
        List<Api.Uri> result = new ArrayList<>();
        RequestMapping restControllersRequestMappingAnnotation = clazz.getAnnotation(RequestMapping.class);
        String urlPrefix = "";
        if (restControllersRequestMappingAnnotation != null) {
            urlPrefix = restControllersRequestMappingAnnotation.value()[0];
        }
        if (StringUtils.isBlank(urlPrefix)) {
            return new Api();
        }
        if (apiStatus == ApiStatus.IN_USED) {
            Api api = new Api();
            List<Permission> permissions = permissionService.selectApisByUrlPrefix(urlPrefix);
            int allMethodCount = clazz.getDeclaredMethods().length;
            int inUseApiCount = permissions.size();
            int idledApiCount = allMethodCount - inUseApiCount;
            for (Permission permission : permissions) {
                Api.Uri uri = new Api.Uri();
                uri.setUrl(permission.getUrl());
                uri.setMethod(permission.getMethod());
                uri.setDescription(permission.getDescription());
                result.add(uri);
            }
            api.setApiList(result);
            api.setIdledApiCount(idledApiCount);
            api.setInUseApiCount(inUseApiCount);
            return api;
        }
        // Get all methods declared in class.
        Method[] methods = clazz.getDeclaredMethods();
        int allMethodCount = clazz.getDeclaredMethods().length;
        if (methods.length == 0) {
            return new Api();
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
            Api.Uri uri = new Api.Uri();
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
        Iterator<Api.Uri> iterator = result.iterator();
        while (iterator.hasNext()) {
            Api.Uri uri = iterator.next();
            Permission permissions = permissionService.selectApiByUrl(uri.getUrl());
            if (permissions == null) {
                continue;
            }
            iterator.remove();
        }
        int idledApiCount = result.size();
        int inUseApiCount = allMethodCount - idledApiCount;
        Api api = new Api();
        api.setApiList(result);
        api.setIdledApiCount(idledApiCount);
        api.setInUseApiCount(inUseApiCount);
        return api;
    }
}
