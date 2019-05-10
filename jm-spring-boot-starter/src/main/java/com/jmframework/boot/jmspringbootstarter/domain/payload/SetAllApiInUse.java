package com.jmframework.boot.jmspringbootstarter.domain.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Description: SetAllApiInUse, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-10 22:36
 **/
@Data
public class SetAllApiInUse {
    @NotEmpty
    private String classFullName;
}
