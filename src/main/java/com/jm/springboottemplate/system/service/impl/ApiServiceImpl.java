package com.jm.springboottemplate.system.service.impl;

import com.jm.springboottemplate.system.constant.ApiStatus;
import com.jm.springboottemplate.system.domain.persistence.Permission;
import com.jm.springboottemplate.system.domain.response.Api;
import com.jm.springboottemplate.system.domain.response.ApiController;
import com.jm.springboottemplate.system.exception.BizException;
import com.jm.springboottemplate.system.mapper.PermissionMapper;
import com.jm.springboottemplate.system.service.ApiService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
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
    private final WebApplicationContext applicationContext;
    private final PermissionMapper permissionMapper;

    public ApiServiceImpl(WebApplicationContext applicationContext,
                          PermissionMapper permissionMapper) {
        this.applicationContext = applicationContext;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public ApiController getAllControllerClass() {
        ApiController apiController = new ApiController();
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
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
            log.error("Error occurred when find class by className. {}", e.getMessage(), e);
            throw new BizException("Error occurred when find class by className. " + e.getMessage());
        }
        ApiStatus status = ApiStatus.getByStatus(apiStatus);
        return getPermissionsByClass(clazz, status);
    }

    /**
     * <p>Get permissions by class.</p>
     * <p>Permission model contains URL(API) information.</p>
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
            List<Permission> permissions = permissionMapper.findApisByUrlPrefix(urlPrefix);
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
            Permission permissions = permissionMapper.findApiByUrl(uri.getUrl());
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
