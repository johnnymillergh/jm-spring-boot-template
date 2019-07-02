package com.jmframework.boot.jmspringbootstarterdomain.user.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>GetUserListForSelectionRO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-30 19:45
 **/
@Data
public class GetUserListForSelectionRO {
    private List<User> userList = new ArrayList<>();

    @Data
    public static final class User {
        private Long id;
        private String username;
    }
}
