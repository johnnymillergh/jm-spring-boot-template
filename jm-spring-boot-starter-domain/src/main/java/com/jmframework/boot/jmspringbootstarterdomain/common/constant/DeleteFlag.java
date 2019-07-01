package com.jmframework.boot.jmspringbootstarterdomain.common.constant;

import lombok.Getter;

/**
 * <h1>DeleteFlag</h1>
 * <p>Enumeration for the database table field `deleted`</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-07-01 10:08
 **/
@Getter
public enum DeleteFlag {
    /**
     * Record (row) is not deleted logically
     */
    NOT_DELETED(0, "Not deleted"),
    /**
     * Record (row) has been deleted logically
     */
    DELETED(1, "Deleted");

    private Integer value;
    private String description;

    DeleteFlag(Integer value, String description) {
        this.value = value;
        this.description = description;
    }
}
