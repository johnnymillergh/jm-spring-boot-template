package com.jm.springboottemplate.system.service;

import com.jm.springboottemplate.system.domain.persistence.User;

import java.util.Map;

/**
 * Description: AuthService, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-28
 * @time: 20:23
 **/
public interface AuthService {
    /**
     * Check uniqueness of username.
     *
     * @param username Username string
     * @return Map
     */
    Map checkUsernameUniqueness(String username);

    /**
     * Register a new user.
     *
     * @param user new user
     */
    void register(User user);
}
