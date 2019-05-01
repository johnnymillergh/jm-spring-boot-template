package com.jmframework.boot.jmspringbootstarter.constant;

import lombok.Getter;

/**
 * Description: PermissionType, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 18:59
 **/
@Getter
public enum PermissionType {
    /**
     * Page
     */
    PAGE(1, "Page"),
    /**
     * Button
     */
    BUTTON(2, "Button");

    private Integer type;
    private String description;

    PermissionType(Integer type, String description) {
        this.type = type;
        this.description = description;
    }
}
