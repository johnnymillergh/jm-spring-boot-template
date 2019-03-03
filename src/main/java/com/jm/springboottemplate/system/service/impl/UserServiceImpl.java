package com.jm.springboottemplate.system.service.impl;

import com.jm.springboottemplate.system.domain.User;
import com.jm.springboottemplate.system.mapper.UserMapper;
import com.jm.springboottemplate.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Description: UserService, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-03
 * @time: 11:17
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("This account doesn't exist!");
        }
        user.setRoles(userMapper.getUserRolesByUserId(user.getUserId()));
        return user;
    }
}
