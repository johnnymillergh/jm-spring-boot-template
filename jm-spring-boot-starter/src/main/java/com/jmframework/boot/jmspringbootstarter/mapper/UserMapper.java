package com.jmframework.boot.jmspringbootstarter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarterdomain.user.persistence.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Description: UserMapper, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-02 17:32
 **/
@Mapper
@Component
public interface UserMapper extends BaseMapper<UserPO> {
    /**
     * Find by username, email or Phone.
     *
     * @param username Username
     * @param email    Email
     * @param phone    phone
     * @return User information.
     */
    Optional<UserPO> findByUsernameOrEmailOrPhone(@Param("username") String username,
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
     * @param po A new user.
     * @return Last inserted ID.
     */
    Long save(UserPO po);

    /**
     * Register
     *
     * @param po User info
     * @return Registered user ID
     */
    Long register(UserPO po);

    /**
     * Get count of username by username.
     *
     * @param username Username string
     * @return Count of username
     */
    Integer getUsernameCountByUsername(String username);

    /**
     * Select user page list
     *
     * @param page pagination object
     * @return user page list
     */
    IPage<UserPO> selectUserPageList(Page page);

    /**
     * Update user's basic information by ID
     *
     * @param updated updated user
     * @return affected rows
     */
    int updateUserBasicInfoById(@Param("updated") UserPO updated);
}
