package com.jm.springboottemplate.system.exception.base;

import com.jm.springboottemplate.system.constant.UniversalStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: BaseException, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 16:23
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 5049763892480652887L;

    /**
     * Code is REQUIRED. Default code is 500.
     */
    private Integer code = UniversalStatus.ERROR.getCode();
    /**
     * Message is REQUIRED. Default message is: Error. A generic status for an error in the server itself.
     */
    private String message;
    private Object data;

    public BaseException(UniversalStatus universalStatus) {
        this.code = universalStatus.getCode();
        this.message = universalStatus.getMessage();
    }

    public BaseException(UniversalStatus universalStatus, Object data) {
        this(universalStatus);
        this.data = data;
    }

    public BaseException(UniversalStatus universalStatus, String message) {
        this(universalStatus);
        this.message = message;
    }

    public BaseException(String message) {
        this.message = message;
    }

    public BaseException(String message, Throwable throwable) {
        this(message);
        super.setStackTrace(throwable.getStackTrace());
    }

    public BaseException(Throwable throwable) {
        this(throwable.getMessage());
        super.setStackTrace(throwable.getStackTrace());
    }
}
