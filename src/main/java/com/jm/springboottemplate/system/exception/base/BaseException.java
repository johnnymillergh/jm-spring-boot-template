package com.jm.springboottemplate.system.exception.base;

import com.jm.springboottemplate.system.constant.UniversalStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: BaseException, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-23
 * @time: 16:23
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 5049763892480652887L;

    private Integer code;
    private String message;
    private Object data;

    public BaseException(UniversalStatus universalStatus) {
        super(universalStatus.getMessage());
        this.code = universalStatus.getCode();
        this.message = universalStatus.getMessage();
    }

    public BaseException(UniversalStatus universalStatus, Object data) {
        this(universalStatus);
        this.data = data;
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(Integer code, String message, Object data) {
        this(code, message);
        this.data = data;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }
}
