package com.jmframework.boot.jmspringbootstarter.controller;

import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/get-permission-list")
    @ApiOperation(value = "Get permission list", notes = "Get permission list (group by controller)")
    public ResponseBodyBean getPermissionList() {
        return null;
    }
}
