package com.jm.springboottemplate.system.mapper;

import com.jm.springboottemplate.system.domain.persistence.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

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
     * Check uniqueness of username.
     *
     * @param username Username string
     * @return the count of username occurrence
     */
    Integer checkUsernameUniqueness(String username);

    /**
     * Check uniqueness of email.
     *
     * @param email Email string
     * @return the count of email occurrence
     */
    Integer checkEmailUniqueness(String email);

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

    /**
     * Get count of username by username.
     *
     * @param username Username string
     * @return Count of username
     */
    Integer getUsernameCountByUsername(String username);
}
