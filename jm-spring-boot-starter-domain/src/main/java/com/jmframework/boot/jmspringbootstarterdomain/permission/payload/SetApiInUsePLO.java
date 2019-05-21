package com.jmframework.boot.jmspringbootstarterdomain.permission.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 200)
    private String url;
    /**
     * Description of API
     */
    @NotEmpty
    @Length(max = 100)
    private String description;
    /**
     * HTTP method
     */
    @NotEmpty
    private String method;
}
