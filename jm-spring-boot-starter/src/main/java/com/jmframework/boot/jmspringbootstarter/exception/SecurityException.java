package com.jmframework.boot.jmspringbootstarter.exception;

import com.jmframework.boot.jmspringbootstarter.exception.base.BaseException;
import com.jmframework.boot.jmspringbootstarterdomain.common.constant.HttpStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: SecurityException, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 16:26
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SecurityException extends BaseException {
    private static final long serialVersionUID = -767157443094687237L;

    public SecurityException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public SecurityException(HttpStatus httpStatus, Object data) {
        super(httpStatus, data);
    }
}
