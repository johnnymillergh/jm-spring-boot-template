package com.jmframework.boot.jmspringbootstarterdomain.permission.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <h1>GetApiListPLO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-11 14:08
 **/
@Data
public class GetApiListRO {
    private List<Api> apiList = new ArrayList<>();

    @Data
    public static final class Api {
        private Long id;
        private String url;
        private String description;
        private String permissionExpression;
        private String method;
        private Integer sort;
        private Date gmtCreated;
        private Date gmtModified;
    }
}
