package com.jmframework.boot.jmspringbootstarterdomain.common.constant;

import lombok.Getter;

/**
 * Description: UserStatus, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 18:48
 **/
@Getter
public enum UserStatus {
    /**
     * Enabled user
     */
    ENABLED(1, "Enabled user"),
    /**
     * Disabled user
     */
    DISABLED(0, "Disabled user");

    private Integer status;
    private String description;

    UserStatus(Integer status, String description) {
        this.status = status;
        this.description = description;
    }
}
