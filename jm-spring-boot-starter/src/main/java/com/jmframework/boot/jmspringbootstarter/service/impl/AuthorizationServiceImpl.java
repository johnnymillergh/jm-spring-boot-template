package com.jmframework.boot.jmspringbootstarter.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.jmframework.boot.jmspringbootstarter.exception.BizException;
import com.jmframework.boot.jmspringbootstarter.mapper.RolePermissionMapper;
import com.jmframework.boot.jmspringbootstarter.service.AuthorizationService;
import com.jmframework.boot.jmspringbootstarter.service.PermissionService;
import com.jmframework.boot.jmspringbootstarterdomain.authorization.payload.SubmitAuthorizationPLO;
import com.jmframework.boot.jmspringbootstarterdomain.authorization.response.GetPermissionsRO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.persistence.PermissionPO;
import com.jmframework.boot.jmspringbootstarterdomain.role.persistence.RolePermissionPO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>AuthorizationServiceImpl</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-19 15:01
 **/
@Slf4j
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final PermissionService permissionService;
    private final RolePermissionMapper rolePermissionMapper;

    public AuthorizationServiceImpl(PermissionService permissionService, RolePermissionMapper rolePermissionMapper) {
        this.permissionService = permissionService;
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public GetPermissionsRO getPermissions(List<String> controllerFullClassNameList) {
        GetPermissionsRO ro = new GetPermissionsRO();
        for (String cfcn : controllerFullClassNameList) {
            List<PermissionPO> permissions = getPermissionsByControllerFullClassName(cfcn);
            if (CollectionUtil.isEmpty(permissions)) {
                continue;
            }
            GetPermissionsRO.Controller controller = new GetPermissionsRO.Controller();
            controller.setControllerFullClassName(cfcn);
            String[] splits = cfcn.split("\\.");
            controller.setControllerName(splits[splits.length - 1]);
            for (PermissionPO po : permissions) {
                GetPermissionsRO.Api api = new GetPermissionsRO.Api();
                api.setPermissionId(po.getId());
                api.setUrl(po.getUrl());
                api.setDescription(po.getDescription());
                controller.getApiList().add(api);
            }
            ro.getControllerList().add(controller);
        }
        return ro;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean authorizePermission(SubmitAuthorizationPLO plo) {
        RolePermissionPO po = new RolePermissionPO();
        for (Long roleId : plo.getRoleIdList()) {
            po.setRoleId(roleId);
            rolePermissionMapper.deleteByRoleId(po);
        }
        List<RolePermissionPO> poList = new ArrayList<>();
        for (Long roleId : plo.getRoleIdList()) {
            for (Long permissionId : plo.getPermissionIdList()) {
                po = new RolePermissionPO();
                po.setRoleId(roleId);
                po.setPermissionId(permissionId);
                poList.add(po);
            }
        }
        Integer affectedRows = rolePermissionMapper.insertBatch(poList);
        return affectedRows > 0;
    }

    private List<PermissionPO> getPermissionsByControllerFullClassName(String controllerFullClassName) {
        Class<?> clazz;
        try {
            clazz = Class.forName(controllerFullClassName);
        } catch (ClassNotFoundException e) {
            log.error("Error occurred when find class by classFullName: {}", e.getMessage(), e);
            throw new BizException("Error occurred when find class by classFullName: " + e.getMessage());
        }

        RequestMapping restControllersRequestMappingAnnotation = clazz.getAnnotation(RequestMapping.class);
        String urlPrefix = "";
        if (restControllersRequestMappingAnnotation != null) {
            urlPrefix = restControllersRequestMappingAnnotation.value()[0];
        }
        if (StringUtils.isBlank(urlPrefix)) {
            return null;
        }
        return permissionService.selectApisByUrlPrefix(urlPrefix);
    }
}
