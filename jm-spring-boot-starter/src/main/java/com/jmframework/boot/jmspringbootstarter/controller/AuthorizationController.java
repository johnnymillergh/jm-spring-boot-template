package com.jmframework.boot.jmspringbootstarter.controller;

import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import com.jmframework.boot.jmspringbootstarter.service.RoleService;
import com.jmframework.boot.jmspringbootstarterdomain.role.persistence.RolePO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class AuthorizationController {
    private final RoleService roleService;

    public AuthorizationController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/get-roles")
    @ApiOperation(value = "/get-roles", notes = "Get roles (support lazy loading)")
    public ResponseBodyBean getRoles() {
        List<RolePO> poList = roleService.getList(null);
//        poList.forEach(item -> {
//            GetRoleListRO ro = new GetRoleListRO();
//            BeanUtil.copyProperties(item, ro);
//            roList.add(ro);
//        });
        return null;
    }

    @GetMapping("/get-permissions")
    @ApiOperation(value = "/get-permissions", notes = "Get permissions (group by controller)")
    public ResponseBodyBean getPermissionList() {
        return null;
    }
}
