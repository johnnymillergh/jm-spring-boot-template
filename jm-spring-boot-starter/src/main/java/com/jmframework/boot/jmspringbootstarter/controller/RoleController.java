package com.jmframework.boot.jmspringbootstarter.controller;

import cn.hutool.core.bean.BeanUtil;
import com.jmframework.boot.jmspringbootstarter.domain.payload.CreateRolePLO;
import com.jmframework.boot.jmspringbootstarter.domain.payload.GetRoleListPLO;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.RolePO;
import com.jmframework.boot.jmspringbootstarter.domain.response.GetRoleListRO;
import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import com.jmframework.boot.jmspringbootstarter.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>RoleController</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-18 12:22
 **/
@Slf4j
@RestController
@RequestMapping("/role")
@Api(value = "Role Controller", tags = {"role"})
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/get-list")
    @ApiOperation(value = "Get role list", notes = "Get role page list")
    public ResponseBodyBean getList(@Valid @RequestBody GetRoleListPLO plo) {
        List<RolePO> poList = roleService.getList(plo);
        List<GetRoleListRO> roList = new ArrayList<>();
        poList.forEach(item -> {
            GetRoleListRO ro = new GetRoleListRO();
            BeanUtil.copyProperties(item, ro);
            roList.add(ro);
        });
        return ResponseBodyBean.ofSuccess(roList);
    }

    @GetMapping("/check-role-name")
    @ApiOperation(value = "Check role name", notes = "To ensure the uniqueness of name of role")
    public ResponseBodyBean checkRoleName(String roleName) {
        if (StringUtils.isBlank(roleName)) {
            return ResponseBodyBean.ofFailure("name of role is not blank");
        }
        roleName = StringUtils.trim(roleName).toLowerCase();
        roleName = roleName.replaceAll("\\s", "_");
        if (roleService.checkRoleName(roleName)) {
            return ResponseBodyBean.ofSuccess("Tne name of role is available");
        }
        return ResponseBodyBean.ofFailure("Tne name of role is not available");
    }

    @PostMapping("/create-role")
    @ApiOperation(value = "Create a role", notes = "Create a role")
    public ResponseBodyBean createRole(@Valid @RequestBody CreateRolePLO plo) {
        String name = StringUtils.trim(plo.getName()).toLowerCase();
        name = name.replaceAll("\\s", "_");
        plo.setName(name);
        RolePO po = new RolePO();
        BeanUtil.copyProperties(plo, po);
        if (roleService.insertRole(po)) {
            return ResponseBodyBean.ofSuccess("Role created successfully");
        }
        return ResponseBodyBean.ofFailure("Role cannot be created");
    }
}
