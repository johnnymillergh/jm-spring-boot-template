package com.jm.springboottemplate.system.controller;

import com.jm.springboottemplate.common.util.RequestUtils;
import com.jm.springboottemplate.system.exception.BizException;
import com.jm.springboottemplate.system.response.ResponseBodyBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * Description: BizExceptionControllerAdvice
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-02
 * @time: 17:39
 **/
@ControllerAdvice
public class BizExceptionControllerAdvice {
    private Logger logger = LoggerFactory.getLogger(BizExceptionControllerAdvice.class);

    @ResponseBody
    @ExceptionHandler(BizException.class)
    public ResponseBodyBean bisException(HttpServletRequest request, BizException bizException) {
        String errorMessage = "Error occurred when ["
                + RequestUtils.getRequestIpAndPort(request) + "] requested access. URL: "
                + request.getServletPath();
        logger.error(errorMessage, bizException);
        return ResponseBodyBean.responseFailure(bizException.getMessage());
    }
}
