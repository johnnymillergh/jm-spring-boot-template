package com.jmframework.boot.jmspringbootstarter.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import com.jmframework.boot.jmspringbootstarter.service.RoleService;
import com.jmframework.boot.jmspringbootstarterdomain.authorization.payload.GetRolesPLO;
import com.jmframework.boot.jmspringbootstarterdomain.authorization.response.GetRolesRO;
import com.jmframework.boot.jmspringbootstarterdomain.role.persistence.RolePO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public ResponseBodyBean getRoles(@Valid @RequestBody GetRolesPLO plo) {
        Page<RolePO> page = new Page<>(plo.getCurrentPage(), plo.getPageSize());
        List<RolePO> poList = roleService.getList(page);
        List<GetRolesRO> roList = new ArrayList<>();
        poList.forEach(item -> {
            GetRolesRO ro = new GetRolesRO();
            BeanUtil.copyProperties(item, ro);
            roList.add(ro);
        });
        return ResponseBodyBean.ofSuccess(roList);
    }

    @GetMapping("/get-permissions")
    @ApiOperation(value = "/get-permissions", notes = "Get permissions (group by controller)")
    public ResponseBodyBean getPermissionList() {
        return null;
    }
}
