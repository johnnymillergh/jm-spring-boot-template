package com.jmframework.boot.jmspringbootstarter.domain.payload;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

/**
 * Description: SetApiInUsePLO, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-10 21:44
 **/
@Data
public class SetApiInUsePLO {
    /**
     * URL
     */
    @NotEmpty
    @Max(200)
    private String url;
    /**
     * Description of API
     */
    @NotEmpty
    @Max(100)
    private String description;
    /**
     * HTTP method
     */
    @NotEmpty
    private String method;
}
