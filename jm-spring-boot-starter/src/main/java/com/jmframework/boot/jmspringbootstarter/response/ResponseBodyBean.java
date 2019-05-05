package com.jmframework.boot.jmspringbootstarter.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jmframework.boot.jmspringbootstarter.constant.IUniversalStatus;
import com.jmframework.boot.jmspringbootstarter.constant.UniversalStatus;
import com.jmframework.boot.jmspringbootstarter.controller.ExceptionControllerAdvice;
import com.jmframework.boot.jmspringbootstarter.exception.BizException;
import com.jmframework.boot.jmspringbootstarter.exception.base.BaseException;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: ResponseBodyBean, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-02 17:52
 **/
public class ResponseBodyBean implements Serializable {
    private static final long serialVersionUID = -6799356701376964494L;
    private static final Integer SUCCESS = 200;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timestamp = new Date();
    /**
     * Default status is SUCCESS[200]
     */
    private Integer status = UniversalStatus.SUCCESS.getCode();
    private String message;
    private Object data;

    /**
     * <p>Respond to client with IUniversalStatus (status may be SUCCESS or other).</p>
     * <p><strong>ATTENTION:</strong></p>
     * <p>This method CANNOT be used by controller or service or other class, only provided for Exception controller
     * .</p>
     *
     * @param status IUniversalStatus
     * @return response body for ExceptionControllerAdvice
     * @see ExceptionControllerAdvice#handleException(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Exception)
     */
    public static ResponseBodyBean ofStatus(IUniversalStatus status) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.status = status.getCode();
        responseBodyBean.message = status.getMessage();
        return responseBodyBean;
    }

    /**
     * <p>Respond to client with IUniversalStatus and data.</p>
     * <p><strong>ATTENTION:</strong></p>
     * <p>This method CANNOT be used by controller or service or other class, only provided for Exception controller
     * .</p>
     *
     * @param status IUniversalStatus
     * @param data   data to be responded to client
     * @return response body for ExceptionControllerAdvice
     * @see ExceptionControllerAdvice#handleException(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Exception)
     */
    public static ResponseBodyBean ofStatus(IUniversalStatus status, Object data) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.status = status.getCode();
        responseBodyBean.message = status.getMessage();
        responseBodyBean.data = data;
        return responseBodyBean;
    }

    /**
     * <p>Highly customizable response. Status might be any UniversalStatus&#39; code value.</p>
     * <p><strong>ATTENTION:</strong></p>
     * <p>This method CANNOT be used by controller or service or other class, only provided for Exception controller
     * .</p>
     *
     * @param status  status code
     * @param message message to be responded
     * @param data    data to be responded
     * @return response body for ExceptionControllerAdvice
     * @see ExceptionControllerAdvice#handleException(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Exception)
     */
    public static ResponseBodyBean ofStatus(Integer status, String message, Object data) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.status = status;
        responseBodyBean.message = message;
        responseBodyBean.data = data;
        return responseBodyBean;
    }

    /**
     * <p>Highly customizable response. Status might be any UniversalStatus&#39; code value. </p>
     * <p><strong>ATTENTION:</strong></p>
     * <p>This method CANNOT be used in ExceptionControllerAdvice.</p>
     *
     * @param status  status code
     * @param message message to be responded
     * @param data    data to be responded
     * @return response body
     */
    public static ResponseBodyBean setResponse(Integer status, String message, Object data) {
        if (!SUCCESS.equals(status)) {
            throw new BaseException(status, message, data);
        }
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.status = status;
        responseBodyBean.message = message;
        responseBodyBean.data = data;
        return responseBodyBean;
    }

    /**
     * Respond data and status is SUCCESS.
     *
     * @param data data to be responded to client.
     * @return response body
     */
    public static ResponseBodyBean ofData(Object data) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        return responseBodyBean;
    }

    /**
     * Respond a message and status id SUCCESS.
     *
     * @param message message to be responded
     * @return response body
     */
    public static ResponseBodyBean ofMessage(String message) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.message = message;
        return responseBodyBean;
    }

    /**
     * Respond data, message and status is SUCCESS.
     *
     * @param data    data to be responded
     * @param message message to be responded
     * @return response body
     */
    public static ResponseBodyBean ofDataAndMessage(Object data, String message) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        responseBodyBean.message = message;
        return responseBodyBean;
    }

    /**
     * Respond data, and status is SUCCESS.
     *
     * @param data data to be responded
     * @return response body
     */
    public static ResponseBodyBean ofSuccess(Object data) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        return responseBodyBean;
    }

    /**
     * Respond a message and status is SUCCESS.
     *
     * @param message message to be responded
     * @return response body
     */
    public static ResponseBodyBean ofSuccess(String message) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.message = message;
        return responseBodyBean;
    }

    /**
     * Respond data, message and status is SUCCESS.
     *
     * @param data    data to be responded
     * @param message message to be responded
     * @return response body
     */
    public static ResponseBodyBean ofSuccess(Object data, String message) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        responseBodyBean.message = message;
        return responseBodyBean;
    }

    /**
     * Respond a message and status is FAILURE(500).
     *
     * @param message message to be responded.
     * @return response body
     */
    @SuppressWarnings("ConstantConditions")
    public static ResponseBodyBean ofFailure(String message) {
        throw new BizException(message);
    }

    /**
     * Respond a message and status is FAILURE(500).
     *
     * @param data data to be responded
     * @return response body
     */
    @SuppressWarnings("ConstantConditions")
    public static ResponseBodyBean ofFailure(Object data) {
        throw new BizException(data);
    }

    /**
     * Respond data and message, and status if FAILURE(500).
     *
     * @param data    data to be responded
     * @param message message to be responded
     * @return response body
     */
    @SuppressWarnings("ConstantConditions")
    public static ResponseBodyBean ofFailure(Object data, String message) {
        throw new BizException(data, message);
    }

    /**
     * Response an exception.
     *
     * @param t   exception
     * @param <T> Sub class of {@link BaseException}
     * @return Exception information
     */
    public static <T extends BaseException> ResponseBodyBean ofException(T t) {
        throw new BaseException(t.getCode(), t.getMessage(), t.getData());
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
