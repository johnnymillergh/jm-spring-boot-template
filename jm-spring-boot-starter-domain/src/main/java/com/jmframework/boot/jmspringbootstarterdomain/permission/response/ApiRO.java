package com.jmframework.boot.jmspringbootstarterdomain.permission.response;

import lombok.Data;

import java.util.List;

/**
 * Description: ApiRO, response of API `/apiManagement/getApiByControllerClass`.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-04-12 09:35
 **/
@Data
public class ApiRO {
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
