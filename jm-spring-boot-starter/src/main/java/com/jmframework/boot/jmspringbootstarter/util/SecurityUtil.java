package com.jmframework.boot.jmspringbootstarter.util;

import cn.hutool.core.util.ObjectUtil;
import com.jmframework.boot.jmspringbootstarter.common.constant.Constants;
import com.jmframework.boot.jmspringbootstarterdomain.user.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Description: Security Util
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 18:32
 **/
public class SecurityUtil {
    /**
     * Get current username.
     *
     * @return Current username
     */
    public static String getCurrentUsername() {
        UserPrincipal currentUser = getCurrentUser();
        return ObjectUtil.isNull(currentUser) ? Constants.ANONYMOUS_NAME : currentUser.getUsername();
    }

    /**
     * Get current use's id.
     *
     * @return Current use's id
     */
    public static Long getCurrentUserId() {
        UserPrincipal currentUser = getCurrentUser();
        assert currentUser != null;
        assert currentUser.getId() != null;
        return currentUser.getId();
    }

    /**
     * Get current user.
     *
     * @return Current user. Return null if user is anonymous.
     */
    private static UserPrincipal getCurrentUser() {
        Object userInfo = SecurityContextHolder.getContext()
                                               .getAuthentication()
                                               .getPrincipal();
        if (userInfo instanceof UserDetails) {
            return (UserPrincipal) userInfo;
        }
        return null;
    }
}
