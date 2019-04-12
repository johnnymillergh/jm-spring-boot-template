package com.jm.springboottemplate.system.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.jm.springboottemplate.system.constant.UniversalStatus;
import com.jm.springboottemplate.system.exception.base.BaseException;
import com.jm.springboottemplate.system.response.ResponseBodyBean;
import com.jm.springboottemplate.system.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;


/**
 * Description: ExceptionControllerAdvice
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-02 17:39
 **/
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseBodyBean bisException(HttpServletRequest request, Exception exception) {
        String errorMessage = "Exception occurred when ["
                + RequestUtil.getRequestIpAndPort(request) + "] requested access. URL: "
                + request.getServletPath();
        log.error(errorMessage);

        if (exception instanceof NoHandlerFoundException) {
            log.error("[GlobalExceptionCapture] NoHandlerFoundException: Request URL = {}, HTTP method = {}",
                      ((NoHandlerFoundException) exception).getRequestURL(),
                      ((NoHandlerFoundException) exception).getHttpMethod());
            return ResponseBodyBean.ofStatus(UniversalStatus.NOT_FOUND);
        } else if (exception instanceof HttpRequestMethodNotSupportedException) {
            log.error("[GlobalExceptionCapture] HttpRequestMethodNotSupportedException: " +
                              "Current method is {}, Support HTTP method = {}",
                      ((HttpRequestMethodNotSupportedException) exception).getMethod(),
                      JSONUtil.toJsonStr(
                              ((HttpRequestMethodNotSupportedException) exception).getSupportedHttpMethods()));
            return ResponseBodyBean.ofStatus(UniversalStatus.METHOD_NOT_ALLOWED);
        } else if (exception instanceof MethodArgumentNotValidException) {
            log.error("[GlobalExceptionCapture] MethodArgumentNotValidException", exception);
            return ResponseBodyBean.setResponse(UniversalStatus.BAD_REQUEST.getCode(),
                                                ((MethodArgumentNotValidException) exception).getBindingResult()
                                                                                             .getAllErrors()
                                                                                             .get(0)
                                                                                             .getDefaultMessage(),
                                                null);
        } else if (exception instanceof ConstraintViolationException) {
            log.error("[GlobalExceptionCapture] ConstraintViolationException", exception);
            return ResponseBodyBean.setResponse(UniversalStatus.BAD_REQUEST.getCode(),
                                                CollUtil.getFirst(((ConstraintViolationException) exception)
                                                                          .getConstraintViolations())
                                                        .getMessage(), null);
        } else if (exception instanceof MethodArgumentTypeMismatchException) {
            log.error("[GlobalExceptionCapture] MethodArgumentTypeMismatchException: " +
                              "Parameter name = {}, Exception information: {}",
                      ((MethodArgumentTypeMismatchException) exception).getName(),
                      ((MethodArgumentTypeMismatchException) exception).getMessage());
            return ResponseBodyBean.ofStatus(UniversalStatus.PARAM_NOT_MATCH);
        } else if (exception instanceof HttpMessageNotReadableException) {
            log.error("[GlobalExceptionCapture] HttpMessageNotReadableException: {}",
                      ((HttpMessageNotReadableException) exception).getMessage());
            return ResponseBodyBean.ofStatus(UniversalStatus.PARAM_NOT_NULL);
        } else if (exception instanceof BadCredentialsException) {
            log.error("[GlobalExceptionCapture] BadCredentialsException: {}", exception.getMessage());
            return ResponseBodyBean.ofStatus(UniversalStatus.USERNAME_OR_PASSWORD_ERROR);
        } else if (exception instanceof DisabledException) {
            log.error("[GlobalExceptionCapture] DisabledException: {}", exception.getMessage());
            return ResponseBodyBean.ofStatus(UniversalStatus.USER_DISABLED);
        } else if (exception instanceof InternalAuthenticationServiceException) {
            log.error("[GlobalExceptionCapture] InternalAuthenticationServiceException: {}", exception.getMessage());
            return ResponseBodyBean.setResponse(UniversalStatus.ROLE_NOT_FOUND.getCode(), exception.getMessage(), null);
        } else if (exception instanceof BaseException) {
            log.error("[GlobalExceptionCapture] BaseException: Status code: {}, {}",
                      ((BaseException) exception).getCode(), exception.getMessage());
            return ResponseBodyBean.ofException((BaseException) exception);
        }
        log.error("[GlobalExceptionCapture]: Exception information: {} ", exception.getMessage());
        return ResponseBodyBean.ofStatus(UniversalStatus.ERROR);
    }
}
