package com.jmframework.boot.jmspringbootstarter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarterdomain.user.persistence.UserPO;

import java.util.List;

/**
 * <h1>UserService</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-07 11:42
 **/
public interface UserService {
    /**
     * Get user page list
     *
     * @param page pagination object
     * @return user page list
     */
    List<UserPO> getUserPageList(Page page);
}
