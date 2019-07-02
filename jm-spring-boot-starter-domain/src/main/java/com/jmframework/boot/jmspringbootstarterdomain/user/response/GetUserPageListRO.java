package com.jmframework.boot.jmspringbootstarterdomain.user.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <h1>GetUserPageListRO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-29 16:57
 **/
@Data
public class GetUserPageListRO {
    private List<User> userList = new ArrayList<>();

    @Data
    public static final class User {
        private Long id;
        private String username;
        private String email;
        private String gender;
        private Date gmtCreated;
        private Date gmtModified;
        private Integer status;
    }
}
