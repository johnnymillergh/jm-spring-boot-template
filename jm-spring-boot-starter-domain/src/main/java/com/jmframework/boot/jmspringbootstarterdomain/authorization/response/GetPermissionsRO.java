package com.jmframework.boot.jmspringbootstarterdomain.authorization.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>GetPermissionsRO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-25 10:20
 **/
@Data
public class GetPermissionsRO {
    private List<Controller> controllerList = new ArrayList<>();

    @Data
    public static class Controller {
        private String controllerFullClassName;
        private String controllerName;
        private List<Api> apiList = new ArrayList<>();
    }

    @Data
    public static class Api {
        private Long permissionId;
        private String url;
        private String description;
    }
}
