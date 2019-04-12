package com.jm.springboottemplate.system.exception;


import com.jm.springboottemplate.system.constant.UniversalStatus;
import com.jm.springboottemplate.system.exception.base.BaseException;

/**
 * Description: BizException, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-02 17:15
 **/
public class BizException extends BaseException {
    private static final long serialVersionUID = 6403325238832002908L;

    public BizException(UniversalStatus universalStatus) {
        super(universalStatus);
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(UniversalStatus universalStatus, String message) {
        super(universalStatus, message);
    }

    public BizException(Throwable throwable) {
        super(throwable);
    }
}
