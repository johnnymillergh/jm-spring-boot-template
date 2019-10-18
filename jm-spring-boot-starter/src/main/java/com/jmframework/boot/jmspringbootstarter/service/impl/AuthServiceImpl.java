package com.jmframework.boot.jmspringbootstarter.service.impl;

import com.jmframework.boot.jmspringbootstarter.mapper.UserMapper;
import com.jmframework.boot.jmspringbootstarter.service.AuthService;
import com.jmframework.boot.jmspringbootstarterdomain.user.persistence.UserPO;
import lombok.RequiredArgsConstructor;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <h1>AuthServiceImpl</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-28 20:25
 **/
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserMapper userMapper;

    @Override
    public boolean checkUsernameUniqueness(String username) {
        Integer count = userMapper.countByUsername(username);
        return count == 0;
    }

    @Override
    public boolean checkEmailUniqueness(String email) {
        Integer count = userMapper.countByEmail(email);
        return count == 0;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public UserPO register(UserPO po) {
        userMapper.register(po);
        return po;
    }

    @Override
    public boolean validateUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return false;
        }
        Integer countByUsername = userMapper.countByUsername(username);
        return countByUsername != 0;
    }
}
