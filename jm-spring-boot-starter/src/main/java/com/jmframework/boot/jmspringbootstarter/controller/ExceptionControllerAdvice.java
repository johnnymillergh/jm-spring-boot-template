package com.jmframework.boot.jmspringbootstarter.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.jmframework.boot.jmspringbootstarter.constant.UniversalStatus;
import com.jmframework.boot.jmspringbootstarter.exception.base.BaseException;
import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import com.jmframework.boot.jmspringbootstarter.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    /**
     * <p>Exception handler.</p>
     * <p><strong>ATTENTION</strong>: In this method, <strong><em>cannot throw any exception</em></strong>.</p>
     *
     * @param request   HTTP request
     * @param exception any kinds of exception occurred in controller
     * @return custom exception info
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseBodyBean handleException(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Exception exception) {
        log.error("Exception occurred when [{}] requested access. URL: {}",
                  RequestUtil.getRequestIpAndPort(request),
                  request.getServletPath());

        // FIXME: THIS IS NOT A PROBLEM
        //  ATTENTION: Use only ResponseBodyBean.ofStatus() in handleException() method and DON'T throw any exception
        if (exception instanceof NoHandlerFoundException) {
            log.error("[GlobalExceptionCapture] NoHandlerFoundException: Request URL = {}, HTTP method = {}",
                      ((NoHandlerFoundException) exception).getRequestURL(),
                      ((NoHandlerFoundException) exception).getHttpMethod());
            response.setStatus(UniversalStatus.NOT_FOUND.getCode());
            return ResponseBodyBean.ofStatus(UniversalStatus.NOT_FOUND);
        } else if (exception instanceof HttpRequestMethodNotSupportedException) {
            log.error("[GlobalExceptionCapture] HttpRequestMethodNotSupportedException: " +
                              "Current method is {}, Support HTTP method = {}",
                      ((HttpRequestMethodNotSupportedException) exception).getMethod(),
                      JSONUtil.toJsonStr(
                              ((HttpRequestMethodNotSupportedException) exception).getSupportedHttpMethods()));
            response.setStatus(UniversalStatus.METHOD_NOT_ALLOWED.getCode());
            return ResponseBodyBean.ofStatus(UniversalStatus.METHOD_NOT_ALLOWED);
        } else if (exception instanceof MethodArgumentNotValidException) {
            log.error("[GlobalExceptionCapture] MethodArgumentNotValidException: {}", exception.getMessage());
            response.setStatus(UniversalStatus.BAD_REQUEST.getCode());
            return ResponseBodyBean.ofStatus(UniversalStatus.BAD_REQUEST.getCode(),
                                             ((MethodArgumentNotValidException) exception).getBindingResult()
                                                                                          .getAllErrors()
                                                                                          .get(0)
                                                                                          .getDefaultMessage(),
                                             null);
        } else if (exception instanceof ConstraintViolationException) {
            log.error("[GlobalExceptionCapture] ConstraintViolationException: {}", exception.getMessage());
            response.setStatus(UniversalStatus.BAD_REQUEST.getCode());
            return ResponseBodyBean.ofStatus(UniversalStatus.BAD_REQUEST.getCode(),
                                             CollUtil.getFirst(((ConstraintViolationException) exception)
                                                                       .getConstraintViolations())
                                                     .getMessage(), null);
        } else if (exception instanceof MethodArgumentTypeMismatchException) {
            log.error("[GlobalExceptionCapture] MethodArgumentTypeMismatchException: " +
                              "Parameter name = {}, Exception information: {}",
                      ((MethodArgumentTypeMismatchException) exception).getName(),
                      ((MethodArgumentTypeMismatchException) exception).getMessage());
            response.setStatus(UniversalStatus.PARAM_NOT_MATCH.getCode());
            return ResponseBodyBean.ofStatus(UniversalStatus.PARAM_NOT_MATCH);
        } else if (exception instanceof HttpMessageNotReadableException) {
            log.error("[GlobalExceptionCapture] HttpMessageNotReadableException: {}",
                      ((HttpMessageNotReadableException) exception).getMessage());
            response.setStatus(UniversalStatus.PARAM_NOT_NULL.getCode());
            return ResponseBodyBean.ofStatus(UniversalStatus.PARAM_NOT_NULL);
        } else if (exception instanceof BadCredentialsException) {
            log.error("[GlobalExceptionCapture] BadCredentialsException: {}", exception.getMessage());
            response.setStatus(UniversalStatus.USERNAME_OR_PASSWORD_ERROR.getCode());
            return ResponseBodyBean.ofStatus(UniversalStatus.USERNAME_OR_PASSWORD_ERROR);
        } else if (exception instanceof DisabledException) {
            log.error("[GlobalExceptionCapture] DisabledException: {}", exception.getMessage());
            response.setStatus(UniversalStatus.USER_DISABLED.getCode());
            return ResponseBodyBean.ofStatus(UniversalStatus.USER_DISABLED);
        } else if (exception instanceof InternalAuthenticationServiceException) {
            log.error("[GlobalExceptionCapture] InternalAuthenticationServiceException: {}", exception.getMessage());
            response.setStatus(UniversalStatus.ROLE_NOT_FOUND.getCode());
            return ResponseBodyBean.ofStatus(UniversalStatus.ROLE_NOT_FOUND.getCode(), exception.getMessage(), null);
        } else if (exception instanceof BaseException) {
            log.error("[GlobalExceptionCapture] BaseException: Status code: {}, message: {}, data: {}",
                      ((BaseException) exception).getCode(),
                      exception.getMessage(),
                      ((BaseException) exception).getData());
            response.setStatus(((BaseException) exception).getCode());
            return ResponseBodyBean.ofStatus(((BaseException) exception).getCode(),
                                             exception.getMessage(),
                                             ((BaseException) exception).getData());
        } else if (exception instanceof BindException) {
            log.error("[GlobalExceptionCapture]: Exception information: {} ", exception.getMessage());
            response.setStatus(UniversalStatus.PARAM_INVALID.getCode());
            return ResponseBodyBean.ofStatus(UniversalStatus.PARAM_INVALID);
        }
        log.error("[GlobalExceptionCapture]: Exception information: {} ", exception.getMessage(), exception);
        response.setStatus(UniversalStatus.ERROR.getCode());
        return ResponseBodyBean.ofStatus(UniversalStatus.ERROR.getCode(), exception.getMessage(), null);
    }
}
