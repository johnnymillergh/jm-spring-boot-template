package com.jmframework.boot.jmspringbootstarterdomain.role.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <h1>GetRoleListRO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-18 13:03
 **/
@Data
public class GetRoleListRO {
    private List<Role> roleList = new ArrayList<>();

    @Data
    public static final class Role {
        /**
         * Primary key
         */
        private Long id;
        /**
         * Role name
         */
        private String name;
        /**
         * Role description
         */
        private String description;
        /**
         * Create time
         */
        private Date gmtCreated;
        /**
         * Modify time
         */
        private Date gmtModified;
    }
}
