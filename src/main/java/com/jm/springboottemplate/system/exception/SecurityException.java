package com.jm.springboottemplate.system.exception;

import com.jm.springboottemplate.system.constant.UniversalStatus;
import com.jm.springboottemplate.system.exception.base.BaseException;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: SecurityException, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-23
 * @time: 16:26
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SecurityException extends BaseException {
    private static final long serialVersionUID = -767157443094687237L;

    public SecurityException(UniversalStatus universalStatus) {
        super(universalStatus);
    }

    public SecurityException(UniversalStatus universalStatus, Object data) {
        super(universalStatus, data);
    }
}
