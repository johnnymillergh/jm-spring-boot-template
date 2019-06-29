package com.jmframework.boot.jmspringbootstarterdomain.user.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * <h1>EditUserPLO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-29 17:52
 **/
@Data
public class EditUserPLO {
    @NotNull
    @Min(value = 1L)
    private Long id;
    @Length(max = 11)
    private String cellphone;
    @Length(max = 255)
    private String fullName;
    private Date birthday;
    private String gender;
    private Integer status;
}
