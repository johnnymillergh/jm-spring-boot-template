package com.jmframework.boot.jmspringbootstarter.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import com.jmframework.boot.jmspringbootstarter.service.UserService;
import com.jmframework.boot.jmspringbootstarterdomain.common.constant.HttpStatus;
import com.jmframework.boot.jmspringbootstarterdomain.user.constant.UserStatus;
import com.jmframework.boot.jmspringbootstarterdomain.user.payload.*;
import com.jmframework.boot.jmspringbootstarterdomain.user.persistence.UserPO;
import com.jmframework.boot.jmspringbootstarterdomain.user.response.GetUserInfoRO;
import com.jmframework.boot.jmspringbootstarterdomain.user.response.GetUserListForSelectionRO;
import com.jmframework.boot.jmspringbootstarterdomain.user.response.GetUserPageListRO;
import com.jmframework.boot.jmspringbootstarterdomain.user.response.SearchUserRO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <h1>UserController</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-07 11:39
 **/
@Slf4j
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
//     2. Configure role for user. (done)
//      2.1 Retrieve the roles that the user has; (done)
//      2.2 Configure role for user. (done)
//     3. API for statistic of user aspect:
//      3.1 Statistic of Valid and Invalid user;
//      3.2 Statistic of ...
//     4. Assign role to user

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

    @GetMapping("/get-user-info")
    @ApiOperation(value = "/get-user-info", notes = "Get user info")
    public ResponseBodyBean<GetUserInfoRO> getUserInfo(@Valid GetUserInfoPLO plo) {
        UserPO po = new UserPO();
        po.setId(plo.getUserId());
        po.setStatus(plo.getStatus());
        return ResponseBodyBean.ofSuccess(userService.getUserInfo(po));
    }

    @PostMapping("/edit-user-basic-info")
    @ApiOperation(value = "/edit-user-basic-info", notes = "Edit user's basic information")
    public ResponseBodyBean editUserBasicInfo(@Valid @RequestBody EditUserPLO plo) {
        UserPO po = new UserPO();
        BeanUtil.copyProperties(plo, po);
        if (userService.editUserBasicInfo(po)) {
            return ResponseBodyBean.ofSuccess("user updated");
        }
        return ResponseBodyBean.ofFailure("failed to update user");
    }

    @GetMapping("/search-user")
    @ApiOperation(value = "/search-user", notes = "Search user by username")
    public ResponseBodyBean<SearchUserRO> searchUser(@Valid SearchUserPLO plo) {
        UserPO po = userService.searchUserByUsername(plo.getUsername());
        if (po == null) {
            return ResponseBodyBean.setResponse(HttpStatus.WARNING.getCode(), "No result", null);
        }
        SearchUserRO ro = new SearchUserRO();
        BeanUtil.copyProperties(po, ro);
        return ResponseBodyBean.ofSuccess(ro);
    }

    @GetMapping("/get-user-list-for-selection")
    @ApiOperation(value = "/get-user-list-for-selection", notes = "Get user list for selection (support lazy loading)")
    public ResponseBodyBean<GetUserListForSelectionRO> getUserListForSelection(@Valid GetUserListForSelectionPLO plo) {
        Page page = new Page().setCurrent(plo.getCurrentPage()).setSize(plo.getPageSize());
        List<UserPO> userListForSelection = userService.getUserListForSelection(page);
        GetUserListForSelectionRO ro = new GetUserListForSelectionRO();
        userListForSelection.forEach(user -> {
            GetUserListForSelectionRO.User userItem = new GetUserListForSelectionRO.User();
            BeanUtil.copyProperties(user, userItem);
            ro.getUserList().add(userItem);
        });
        return ResponseBodyBean.ofSuccess(ro);
    }

    @PostMapping("/assign-role-to-user")
    @ApiOperation(value = "/assign-role-to-user", notes = "Assign role(s) to user")
    public ResponseBodyBean assignRoleToUser(@Valid @RequestBody AssignRoleToUserPLO plo) {
        UserStatus userStatus = userService.checkUserIsEnabled(plo.getUserId(), plo.getUsername());
        if (userStatus == UserStatus.DISABLED) {
            String failureMessage = "Account [" + plo.getUsername() + "] has been disabled. " +
                    "Cannot assign role(s) to it";
            log.error(failureMessage);
            return ResponseBodyBean.ofFailure(failureMessage);
        }
        userService.assignRoleToUser(plo.getUserId(), plo.getRoleIdList());
        return ResponseBodyBean.ofSuccess("Assigned role(s) to user");
    }
}
