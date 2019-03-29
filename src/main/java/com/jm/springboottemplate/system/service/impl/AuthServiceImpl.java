package com.jm.springboottemplate.system.service.impl;

import com.jm.springboottemplate.system.domain.persistence.User;
import com.jm.springboottemplate.system.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    @Override
    public Map checkUsernameUniqueness(String username) {
        Map<String, Object> resultMap = new HashMap<>(3);
        return resultMap;
    }

    @Override
    public void register(User user) {
    }
}
