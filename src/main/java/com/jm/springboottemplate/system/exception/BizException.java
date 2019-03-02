package com.jm.springboottemplate.system.exception;


/**
 * Description: BizException, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-02
 * @time: 17:15
 **/
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 6403325238832002908L;

    public BizException(String message) {
        super(message);
    }

    public BizException(Integer status, String message) {
        super(status + ": " + message);
    }

    public BizException(Integer status, String message, Throwable throwable) {
        super(status + ": " + message, throwable);
    }

    public BizException(Throwable throwable) {
        super(throwable);
    }
}
