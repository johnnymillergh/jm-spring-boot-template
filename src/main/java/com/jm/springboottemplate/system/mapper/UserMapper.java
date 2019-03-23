package com.jm.springboottemplate.system.mapper;

import com.jm.springboottemplate.system.domain.persistance.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Description: UserMapper, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-02
 * @time: 17:32
 **/
@Mapper
@Component
public interface UserMapper {
    /**
     * Find by username, email or Phone.
     *
     * @param username Username
     * @param email    Email
     * @param phone    phone
     * @return User information.
     */
    Optional<User> findByUsernameOrEmailOrPhone(@Param("username") String username,
                                                @Param("email") String email,
                                                @Param("phone") String phone);

    /**
     * 根据用户名列表查询用户列表
     *
     * @param usernameList 用户名列表
     * @return 用户列表
     */
    List<User> findByUsernameIn(List<String> usernameList);

    /**
     * Save user
     *
     * @param user A new user.
     * @return Last inserted ID.
     */
    Long save(User user);

    /**
     * Register
     *
     * @param user User info
     * @return Registered user ID
     */
    Long register(User user);
}
