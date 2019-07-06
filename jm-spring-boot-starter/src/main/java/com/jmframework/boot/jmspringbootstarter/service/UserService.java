package com.jmframework.boot.jmspringbootstarter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarterdomain.user.constant.UserStatus;
import com.jmframework.boot.jmspringbootstarterdomain.user.persistence.UserPO;
import com.jmframework.boot.jmspringbootstarterdomain.user.response.GetUserInfoRO;
import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
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

    /**
     * Get user by ID and status
     *
     * @param po persistence object
     * @return user po
     */
    UserPO getUserByIdAndStatus(UserPO po);

    /**
     * Get user info (contained role info)
     *
     * @param po persistence object
     * @return user info
     */
    GetUserInfoRO getUserInfo(UserPO po);

    /**
     * Edit user
     *
     * @param po persistence object
     * @return true - edited; false - not edited
     */
    boolean editUserBasicInfo(UserPO po);

    /**
     * Search user by username
     *
     * @param username username
     * @return user
     */
    UserPO searchUserByUsername(String username);

    /**
     * Get user list for selection
     *
     * @param page pagination object/
     * @return user list
     */
    List<UserPO> getUserListForSelection(Page page);

    /**
     * Check whether the user account is enabled
     *
     * @param userId   user ID
     * @param username username
     * @return user status
     */
    UserStatus checkUserIsEnabled(Long userId, String username);

    /**
     * Assign role(s) to user
     *
     * @param userId     user ID
     * @param roleIdList role ID list
     */
    void assignRoleToUser(Long userId, List<Long> roleIdList);

    /**
     * Get user's avatar resource
     *
     * @param username username
     * @return user's avatar
     * @throws IOException IO exception
     */
    ByteArrayResource getUserAvatarResource(String username) throws IOException;
}
