package com.jmframework.boot.jmspringbootstarter.domain.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Description: SetAllApiInUsePLO, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-10 22:36
 **/
@Data
public class SetAllApiInUsePLO {
    @NotEmpty
    private String classFullName;
}
