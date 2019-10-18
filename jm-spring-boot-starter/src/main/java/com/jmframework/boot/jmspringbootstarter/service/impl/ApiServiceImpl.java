package com.jmframework.boot.jmspringbootstarter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jmframework.boot.jmspringbootstarter.exception.BizException;
import com.jmframework.boot.jmspringbootstarter.service.ApiService;
import com.jmframework.boot.jmspringbootstarter.service.PermissionService;
import com.jmframework.boot.jmspringbootstarterdomain.permission.constant.ApiStatus;
import com.jmframework.boot.jmspringbootstarterdomain.permission.constant.PermissionType;
import com.jmframework.boot.jmspringbootstarterdomain.permission.payload.GetApiListPLO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.payload.SetApiInUsePLO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.persistence.PermissionPO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.response.ApiAnalysisRO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.response.ApiControllerRO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.response.GetApiByControllerClassRO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.response.GetApiListRO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.StringUtils;
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
 * <h1>ApiServiceImpl</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-04-07 13:33
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
    private final WebApplicationContext webApplicationContext;
    private final PermissionService permissionService;

    @Override
    public ApiControllerRO getAllControllerClass() {
        ApiControllerRO ro = new ApiControllerRO();
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
            ApiControllerRO.Controller controller = new ApiControllerRO.Controller();
            controller.setClassName(entry.getValue().getSimpleName());
            controller.setPackageName(entry.getValue().getPackage().getName());
            ro.getControllerList().add(controller);
        }
        return ro;
    }

    @Override
    public GetApiByControllerClassRO getApiByClassFullName(String classFullName, Integer apiStatus) {
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
            for (ApiControllerRO.Controller acs : apiControllerRO.getControllerList()) {
                String clazzFullName = acs.getPackageName() + "." + acs.getClassName();
                GetApiByControllerClassRO getApiByControllerClassRO = this.getApiByClassFullName(clazzFullName,
                                                                                                 ApiStatus.IN_USE.getStatus());
                apiAnalysisRO.appendIdledApiCount(getApiByControllerClassRO.getIdledApiCount());
                apiAnalysisRO.appendInUseApiCount(getApiByControllerClassRO.getInUseApiCount());
            }
            apiAnalysisRO.calculateSum();
            return apiAnalysisRO;
        }
        // Query API statistics of specific class scope.
        GetApiByControllerClassRO getApiByControllerClassRO = this.getApiByClassFullName(classFullName,
                                                                                         ApiStatus.IN_USE.getStatus());
        apiAnalysisRO.appendIdledApiCount(getApiByControllerClassRO.getIdledApiCount());
        apiAnalysisRO.appendInUseApiCount(getApiByControllerClassRO.getInUseApiCount());
        apiAnalysisRO.calculateSum();
        return apiAnalysisRO;
    }

    @Override
    public boolean setApiInUse(SetApiInUsePLO plo) {
        PermissionPO permissionPO = new PermissionPO();
        BeanUtil.copyProperties(plo, permissionPO);
        permissionPO.setType(PermissionType.BUTTON.getType());
        return permissionService.savePermission(permissionPO);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean setAllApiInUse(String controllerClass) {
        GetApiByControllerClassRO idledApi = this.getApiByClassFullName(controllerClass, ApiStatus.IDLED.getStatus());
        if (CollectionUtils.isEmpty(idledApi.getApiList())) {
            throw new BizException("All APIs have been set in use");
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
    public GetApiListRO getApiList(GetApiListPLO plo) {
        GetApiListRO re = new GetApiListRO();
        List<PermissionPO> poList = permissionService.queryApiList(plo);
        poList.forEach(item -> {
            GetApiListRO.Api api = new GetApiListRO.Api();
            BeanUtil.copyProperties(item, api);
            re.getApiList().add(api);
        });
        return re;
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
    private GetApiByControllerClassRO getPermissionsByClass(Class<?> clazz, ApiStatus apiStatus) {
        boolean restControllerExisted = clazz.isAnnotationPresent(RestController.class);
        if (!restControllerExisted) {
            return new GetApiByControllerClassRO();
        }
        List<GetApiByControllerClassRO.Uri> result = new ArrayList<>();
        RequestMapping restControllersRequestMappingAnnotation = clazz.getAnnotation(RequestMapping.class);
        String urlPrefix = "";
        if (restControllersRequestMappingAnnotation != null) {
            urlPrefix = restControllersRequestMappingAnnotation.value()[0];
        }
        if (StringUtils.isBlank(urlPrefix)) {
            return new GetApiByControllerClassRO();
        }
        // Get all methods declared in class.
        List<Method> unfilteredMethods = Arrays.asList(clazz.getDeclaredMethods());
        // methods is a list of method that annotated by `@GetMapping` and `@PostMapping`
        List<Method> methods = new ArrayList<>();
        unfilteredMethods.forEach(item -> {
            GetMapping getMapping = item.getAnnotation(GetMapping.class);
            PostMapping postMapping = item.getAnnotation(PostMapping.class);
            if (getMapping != null || postMapping != null) {
                methods.add(item);
            }
        });
        if (apiStatus == ApiStatus.IN_USE) {
            GetApiByControllerClassRO getApiByControllerClassRO = new GetApiByControllerClassRO();
            List<PermissionPO> permissionPOS = permissionService.selectApisByUrlPrefix(urlPrefix);
            int allMethodCount = methods.size();
            int inUseApiCount = permissionPOS.size();
            int idledApiCount = allMethodCount - inUseApiCount;
            for (PermissionPO permissionPO : permissionPOS) {
                GetApiByControllerClassRO.Uri uri = new GetApiByControllerClassRO.Uri();
                uri.setUrl(permissionPO.getUrl());
                uri.setMethod(permissionPO.getMethod());
                uri.setDescription(permissionPO.getDescription());
                result.add(uri);
            }
            getApiByControllerClassRO.setApiList(result);
            getApiByControllerClassRO.setIdledApiCount(idledApiCount);
            getApiByControllerClassRO.setInUseApiCount(inUseApiCount);
            return getApiByControllerClassRO;
        }
        int allMethodCount = methods.size();
        if (methods.size() == 0) {
            return new GetApiByControllerClassRO();
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
            GetApiByControllerClassRO.Uri uri = new GetApiByControllerClassRO.Uri();
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
        Iterator<GetApiByControllerClassRO.Uri> iterator = result.iterator();
        while (iterator.hasNext()) {
            GetApiByControllerClassRO.Uri uri = iterator.next();
            ApiStatus checkResult = permissionService.checkApiIsInUse(uri.getUrl());
            if (ApiStatus.IDLED.getStatus().equals(checkResult.getStatus())) {
                continue;
            }
            iterator.remove();
        }
        int idledApiCount = result.size();
        int inUseApiCount = allMethodCount - idledApiCount;
        GetApiByControllerClassRO getApiByControllerClassRO = new GetApiByControllerClassRO();
        getApiByControllerClassRO.setApiList(result);
        getApiByControllerClassRO.setIdledApiCount(idledApiCount);
        getApiByControllerClassRO.setInUseApiCount(inUseApiCount);
        return getApiByControllerClassRO;
    }
}
