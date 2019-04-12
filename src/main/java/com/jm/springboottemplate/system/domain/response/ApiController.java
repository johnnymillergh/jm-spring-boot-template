package com.jm.springboottemplate.system.domain.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: ApiController, response of API `/apiManagement/getController`.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-04-12 09:36
 * @see com.jm.springboottemplate.system.controller.ApiManagementController#getController()
 **/
@Data
public class ApiController {
    private List<ApiControllerSubclass> controllerList = new ArrayList<>();

    @Data
    public static class ApiControllerSubclass {
        private String className;
        private String packageName;
    }
}
