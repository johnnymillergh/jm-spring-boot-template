package com.jmframework.boot.jmspringbootstarter.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import com.jmframework.boot.jmspringbootstarter.service.UserService;
import com.jmframework.boot.jmspringbootstarterdomain.user.payload.GetUserPageListPLO;
import com.jmframework.boot.jmspringbootstarterdomain.user.persistence.UserPO;
import com.jmframework.boot.jmspringbootstarterdomain.user.response.GetUserPageListRO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <h1>UserController</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-07 11:39
 **/
@RestController
@RequestMapping("/user")
@Api(tags = {"/user"})
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    TODO: ROADMAP
//     1. Get user page list. (done)
//     2. Configure role for user.
//      2.1 Retrieve the roles that the user has; (done)
//      2.2 Configure role for user.
//     3. API for statistic of user aspect:
//      3.1 Statistic of Valid and Invalid user;
//      3.2 Statistic of ...

    @GetMapping("/get-user-page-list")
    @ApiOperation(value = "/get-user-page-list", notes = "Get user page list")
    public ResponseBodyBean<GetUserPageListRO> getUserPageList(@Valid GetUserPageListPLO plo) {
        Page page = new Page().setCurrent(plo.getCurrentPage()).setSize(plo.getPageSize());
        List<UserPO> userPageList = userService.getUserPageList(page);
        GetUserPageListRO ro = new GetUserPageListRO();
        userPageList.forEach(item -> {
            GetUserPageListRO.User user = new GetUserPageListRO.User();
            BeanUtil.copyProperties(item, user);
            ro.getUserList().add(user);
        });
        return ResponseBodyBean.ofSuccess(ro);
    }
}
