package com.jmframework.boot.jmspringbootstarterdomain.permission.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: ApiControllerRO, response of API `/apiManagement/getController`.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-04-12 09:36
 **/
@Data
public class ApiControllerRO {
    private List<Controller> controllerList = new ArrayList<>();

    @Data
    public static class Controller {
        private String className;
        private String packageName;
    }
}
