package com.jmframework.boot.jmspringbootstarterdomain.role.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>GetRolesByUserIdRO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-29 15:32
 **/
@Data
public class GetRolesByUserIdRO {
    private List<Role> roleList = new ArrayList<>();

    @Data
    public static final class Role {
        /**
         * Role name
         */
        private String name;
        /**
         * Role description
         */
        private String description;
    }
}
