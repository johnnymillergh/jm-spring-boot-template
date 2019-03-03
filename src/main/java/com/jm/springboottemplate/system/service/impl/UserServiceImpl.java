package com.jm.springboottemplate.system.service.impl;

import com.jm.springboottemplate.system.dao.UserDao;
import com.jm.springboottemplate.system.domain.User;
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
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("This account doesn't exist!");
        }
        user.setRoles(userDao.getUserRolesByUserId(user.getUserId()));
        return user;
    }
}
