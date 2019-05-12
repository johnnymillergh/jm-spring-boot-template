package com.jmframework.boot.jmspringbootstarter.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>RoleManagementController</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-12 14:57
 **/
@Slf4j
@RestController
@RequestMapping("/roleManagement")
@Api(value = "Role Management Controller", tags = {"role"})
public class RoleManagementController {
}
