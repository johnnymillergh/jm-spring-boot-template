package com.jm.springboottemplate.system.service;

import com.jm.springboottemplate.system.domain.persistence.User;

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
     * @return true - username available; false - username not available
     */
    boolean checkUsernameUniqueness(String username);

    /**
     * Check uniqueness of email.
     *
     * @param email email string
     * @return true - email available; false - email not available
     */
    boolean checkEmailUniqueness(String email);

    /**
     * Register a new user.
     *
     * @param user new user
     */
    User register(User user);

    /**
     * Validate username.
     *
     * @param username username string
     * @return true - username is valid; false - username is not valid
     */
    boolean validateUsername(String username);
}
