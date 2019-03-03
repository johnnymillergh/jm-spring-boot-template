package com.jm.springboottemplate.system.dao;

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
public interface UserDao {
    User getUserByUsername(String username);

    List<Role> getUserRolesByUserId(Integer userId);
}
