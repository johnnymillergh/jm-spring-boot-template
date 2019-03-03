package com.jm.springboottemplate.system.mapper;

import com.jm.springboottemplate.system.domain.Role;
import com.jm.springboottemplate.system.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: UserDao, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-03
 * @time: 11:17
 **/
@Mapper
@Component
public interface UserMapper {
    /**
     * Get user by username.
     *
     * @param username Username.
     * @return User.
     */
    User getUserByUsername(String username);

    /**
     * Get the roles that user has by userId.
     *
     * @param userId userId.
     * @return List of role that user has.
     */
    List<Role> getUserRolesByUserId(Integer userId);
}
