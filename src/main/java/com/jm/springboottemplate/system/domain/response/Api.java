package com.jm.springboottemplate.system.domain.response;

import com.jm.springboottemplate.system.controller.ApiManagementController;
import lombok.Data;

import java.util.List;

/**
 * Description: Api, response of API `/apiManagement/getApiByControllerClass`.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-04-12 09:35
 * @see ApiManagementController#getApiByControllerClass(java.lang.String, java.lang.Integer)
 **/
@Data
public class Api {
    private List<Uri> apiList;
    private Integer idledApiCount;
    private Integer inUseApiCount;

    @Data
    public static class Uri {
        private String url;
        private String method;
        private String description;
    }
}
