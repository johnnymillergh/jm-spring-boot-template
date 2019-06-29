package com.jmframework.boot.jmspringbootstarter.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.mapper.UserMapper;
import com.jmframework.boot.jmspringbootstarter.service.UserService;
import com.jmframework.boot.jmspringbootstarterdomain.user.persistence.UserPO;
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

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserPO> getUserPageList(Page page) {
        return userMapper.selectUserPageList(page).getRecords();
    }

    @Override
    public boolean editUserBasicInfo(UserPO po) {
        return userMapper.updateUserBasicInfoById(po) == 1;
    }
}
