package com.jmframework.boot.jmspringbootstarter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.mapper.UserMapper;
import com.jmframework.boot.jmspringbootstarter.service.RoleService;
import com.jmframework.boot.jmspringbootstarter.service.UserService;
import com.jmframework.boot.jmspringbootstarterdomain.role.persistence.RolePO;
import com.jmframework.boot.jmspringbootstarterdomain.user.persistence.UserPO;
import com.jmframework.boot.jmspringbootstarterdomain.user.response.GetUserInfoRO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>UserServiceImpl</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-07 11:42
 **/
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final RoleService roleService;

    public UserServiceImpl(UserMapper userMapper,
                           RoleService roleService) {
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    @Override
    public List<UserPO> getUserPageList(Page page) {
        return userMapper.selectUserPageList(page).getRecords();
    }

    @Override
    public UserPO getUserByIdAndStatus(UserPO po) {
        return userMapper.selectByIdAndStatus(po);
    }

    @Override
    public GetUserInfoRO getUserInfo(UserPO po) {
        UserPO userByIdAndStatus = getUserByIdAndStatus(po);
        GetUserInfoRO ro = new GetUserInfoRO();
        BeanUtil.copyProperties(userByIdAndStatus, ro);

        List<RolePO> rolesByUserId = roleService.getRolesByUserId(po.getId());
        if (CollectionUtil.isEmpty(rolesByUserId)) {
            return ro;
        }
        rolesByUserId.forEach(role -> {
            GetUserInfoRO.UsersRole usersRole = new GetUserInfoRO.UsersRole();
            usersRole.setRoleId(role.getId());
            usersRole.setRoleName(role.getName());
            usersRole.setRoleDescription(role.getDescription());
            ro.getUsersRoles().add(usersRole);
        });
        return ro;
    }

    @Override
    public boolean editUserBasicInfo(UserPO po) {
        return userMapper.updateUserBasicInfoById(po) == 1;
    }

    @Override
    public UserPO searchUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
