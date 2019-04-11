package com.jm.springboottemplate.system.service.impl;

import com.jm.springboottemplate.system.constant.ApiStatus;
import com.jm.springboottemplate.system.domain.Uri;
import com.jm.springboottemplate.system.domain.persistence.Permission;
import com.jm.springboottemplate.system.exception.BizException;
import com.jm.springboottemplate.system.mapper.PermissionMapper;
import com.jm.springboottemplate.system.service.ApiService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-04-07
 * @time: 13:33
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
    public List getAllControllerClass() {
        List<Map> resultList = new ArrayList<>();
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // handlerMethodMap -> URL:HandlerMethod
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = mapping.getHandlerMethods();
        Map<String, Class> controllerMap = new HashMap<>(32);
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethodMap.entrySet()) {
            controllerMap.put(String.valueOf(entry.getValue().getBean()), entry.getValue().getBeanType());
        }
        for (Map.Entry<String, Class> entry : controllerMap.entrySet()) {
            Map<String, Object> singleMap = new HashMap<>(4);
            singleMap.put("className", entry.getValue().getSimpleName());
            singleMap.put("packageName", entry.getValue().getPackage().getName());
            resultList.add(singleMap);
        }
        return resultList;
    }

    @Override
    public List getApiByClassFullName(String classFullName, Integer apiStatus) {
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
     * @param clazz class
     * @return permission list (API list)
     */
    private List getPermissionsByClass(Class<?> clazz, ApiStatus apiStatus) {
        boolean restControllerExisted = clazz.isAnnotationPresent(RestController.class);
        if (!restControllerExisted) {
            return null;
        }
        List<Uri> result = new ArrayList<>();
        RequestMapping restControllersRequestMappingAnnotation = clazz.getAnnotation(RequestMapping.class);
        String urlPrefix = "";
        if (restControllersRequestMappingAnnotation != null) {
            urlPrefix = restControllersRequestMappingAnnotation.value()[0];
        }
        if (apiStatus == ApiStatus.IN_USED) {
            List<Permission> permissions = permissionMapper.findApisByUrlPrefix(urlPrefix);
            for (Permission permission : permissions) {
                Uri uri = new Uri();
                uri.setUrl(permission.getUrl());
                uri.setMethod(permission.getMethod());
                uri.setDescription(permission.getDescription());
                result.add(uri);
            }
            return result;
        }
        // Get all methods declared in class.
        Method[] methods = clazz.getDeclaredMethods();
        if (methods.length == 0) {
            return result;
        }
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
            Uri uri = new Uri();
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
        Iterator<Uri> iterator = result.iterator();
        while (iterator.hasNext()) {
            Uri uri = iterator.next();
            Permission permissions = permissionMapper.findApiByUrl(uri.getUrl());
            if (permissions == null) {
                continue;
            }
            iterator.remove();
        }
        return result;
    }
}
