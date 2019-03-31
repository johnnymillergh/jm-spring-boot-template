package com.jm.springboottemplate.system.service.impl;

import com.jm.springboottemplate.system.domain.persistence.User;
import com.jm.springboottemplate.system.mapper.UserMapper;
import com.jm.springboottemplate.system.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description: AuthServiceImpl, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-28
 * @time: 20:25
 **/
@Service
public class AuthServiceImpl implements AuthService {
    private final UserMapper userMapper;

    @Autowired
    public AuthServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean checkUsernameUniqueness(String username) {
        Integer count = userMapper.checkUsernameUniqueness(username);
        return count == 0;
    }

    @Override
    public boolean checkEmailUniqueness(String email) {
        Integer count = userMapper.checkEmailUniqueness(email);
        return count == 0;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public User register(User user) {
        userMapper.register(user);
        return user;
    }

    @Override
    public boolean validateUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return false;
        }
        Integer countOfUsername = userMapper.getUsernameCountByUsername(username);
        return countOfUsername != 0;
    }
}
