package com.jmframework.boot.jmspringbootstarter.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import com.jmframework.boot.jmspringbootstarter.service.AuthorizationService;
import com.jmframework.boot.jmspringbootstarterdomain.authorization.payload.GetPermissionsPLO;
import com.jmframework.boot.jmspringbootstarterdomain.authorization.payload.GetRolesPLO;
import com.jmframework.boot.jmspringbootstarterdomain.authorization.payload.SubmitAuthorizationPLO;
import com.jmframework.boot.jmspringbootstarterdomain.authorization.response.GetPermissionsRO;
import com.jmframework.boot.jmspringbootstarterdomain.authorization.response.GetRolesRO;
import com.jmframework.boot.jmspringbootstarterdomain.common.constant.HttpStatus;
import com.jmframework.boot.jmspringbootstarterdomain.permission.constant.PermissionType;
import com.jmframework.boot.jmspringbootstarterdomain.role.persistence.RolePO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <h1>AuthorizationController</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-19 14:29
 **/
@RestController
@RequestMapping("/authorization")
@Api(tags = {"/authorization"})
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    @PostMapping("/get-roles")
    @ApiOperation(value = "/get-roles", notes = "Get roles (support lazy loading)")
    public ResponseBodyBean<GetRolesRO> getRoles(@Valid @RequestBody GetRolesPLO plo) {
        Page<RolePO> page = new Page<>(plo.getCurrentPage(), plo.getPageSize());
        List<RolePO> poList = authorizationService.getRoleListForSelection(page);
        GetRolesRO ro = new GetRolesRO();
        poList.forEach(item -> {
            GetRolesRO.RoleROBean role = new GetRolesRO.RoleROBean();
            BeanUtil.copyProperties(item, role);
            ro.getRoleList().add(role);
        });
        return ResponseBodyBean.ofSuccess(ro);
    }

    @PostMapping("/get-permissions")
    @ApiOperation(value = "/get-permissions", notes = "Get permissions (group by controller)")
    public ResponseBodyBean<GetPermissionsRO> getPermissionList(@Valid @RequestBody GetPermissionsPLO plo) {
        if (PermissionType.PAGE.getType().equals(plo.getPermissionType())) {
            return null;
        }
        if (PermissionType.BUTTON.getType().equals(plo.getPermissionType())) {
            return ResponseBodyBean.ofSuccess(authorizationService.getPermissions(plo.getControllerFullClassName()));
        }
        return ResponseBodyBean.setResponse(HttpStatus.INVALID_PARAM.getCode(),
                                            HttpStatus.INVALID_PARAM.getMessage(),
                                            null);
    }

    @PostMapping("/submit-authorization")
    @ApiOperation(value = "/submit-authorization", notes = "Submit to authorize permissions to roles")
    public ResponseBodyBean submitAuthorization(@Valid @RequestBody SubmitAuthorizationPLO plo) {
        if (authorizationService.authorizePermission(plo)) {
            return ResponseBodyBean.ofSuccess("Authorization done.");
        }
        return ResponseBodyBean.ofFailure("Authorization failed.");
    }

    // TODO: need API to query permissions that has been authorized to role
    // TODO: need API to submit authorization for single role
}
