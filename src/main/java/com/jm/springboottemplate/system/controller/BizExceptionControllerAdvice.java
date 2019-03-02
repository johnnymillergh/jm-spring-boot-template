package com.jm.springboottemplate.system.controller;

import com.jm.springboottemplate.system.exception.BizException;
import com.jm.springboottemplate.system.response.ResponseBodyBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Description: BizExceptionControllerAdvice, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-02
 * @time: 17:39
 **/
@ControllerAdvice
public class BizExceptionControllerAdvice {
    @ResponseBody
    @ExceptionHandler(BizException.class)
    public ResponseBodyBean bisException(BizException bizException) {
        return ResponseBodyBean.responseFailure(bizException.getMessage());
    }
}
