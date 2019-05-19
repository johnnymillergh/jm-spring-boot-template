package com.jmframework.boot.jmspringbootstarter.controller;

import cn.hutool.core.bean.BeanUtil;
import com.jmframework.boot.jmspringbootstarter.domain.payload.GetRoleListPLO;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.RolePO;
import com.jmframework.boot.jmspringbootstarter.domain.response.GetRoleListRO;
import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import com.jmframework.boot.jmspringbootstarter.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
