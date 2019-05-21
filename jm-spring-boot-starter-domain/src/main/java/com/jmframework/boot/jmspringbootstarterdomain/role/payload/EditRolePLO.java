package com.jmframework.boot.jmspringbootstarterdomain.role.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.annotation.Nonnegative;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <h1>EditRolePLO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-19 11:03
 **/
@Data
public class EditRolePLO {
    @NotNull
    @Nonnegative
    private Long id;
    @NotEmpty(message = "name is required")
    @Length(max = 50, message = "length of name cannot exceed 50")
    private String name;
    @NotEmpty(message = "description is required")
    @Length(max = 100, message = "length of description cannot exceed 100")
    private String description;
}
