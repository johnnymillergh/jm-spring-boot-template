package com.jm.springboottemplate.system.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jm.springboottemplate.system.constant.IUniversalStatus;
import com.jm.springboottemplate.system.constant.UniversalStatus;
import com.jm.springboottemplate.system.exception.base.BaseException;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: ResponseBodyBean, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-02
 * @time: 17:52
 **/
public class ResponseBodyBean implements Serializable {
    private static final long serialVersionUID = -6799356701376964494L;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timestamp = new Date();
    /**
     * Default status is SUCCESS[200]
     */
    private Integer status = UniversalStatus.SUCCESS.getCode();
    private String message;
    private Object data;

    public static ResponseBodyBean ofStatus(IUniversalStatus status) {
        return setResponse(status.getCode(), status.getMessage(), null);
    }

    public static ResponseBodyBean ofStatus(IUniversalStatus status, Object data) {
        return setResponse(status.getCode(), status.getMessage(), data);
    }

    public static ResponseBodyBean ofData(Object data) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        return responseBodyBean;
    }

    public static ResponseBodyBean ofMessage(String message) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.message = message;
        return responseBodyBean;
    }

    public static ResponseBodyBean ofDataAndMessage(Object data, String message) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        responseBodyBean.message = message;
        return responseBodyBean;
    }

    public static ResponseBodyBean setResponse(Integer status, String message, Object data) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.status = status;
        responseBodyBean.message = message;
        responseBodyBean.data = data;
        return responseBodyBean;
    }

    public static ResponseBodyBean ofSuccess(Object data) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        responseBodyBean.status = UniversalStatus.SUCCESS.getCode();
        return responseBodyBean;
    }

    public static ResponseBodyBean ofSuccess(String message) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.message = message;
        responseBodyBean.status = UniversalStatus.SUCCESS.getCode();
        return responseBodyBean;
    }

    public static ResponseBodyBean ofSuccess(Object data, String message) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        responseBodyBean.status = UniversalStatus.SUCCESS.getCode();
        responseBodyBean.message = message;
        return responseBodyBean;
    }

    public static ResponseBodyBean ofFailure(String message) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.message = message;
        responseBodyBean.status = UniversalStatus.FAILURE.getCode();
        return responseBodyBean;
    }

    public static ResponseBodyBean ofFailure(Object data) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        responseBodyBean.status = UniversalStatus.FAILURE.getCode();
        return responseBodyBean;
    }

    public static ResponseBodyBean ofFailure(Object data, String message) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        responseBodyBean.status = UniversalStatus.FAILURE.getCode();
        responseBodyBean.message = message;
        return responseBodyBean;
    }

    /**
     * Response an exception.
     *
     * @param t   exception
     * @param <T> Sub class of {@link BaseException}
     * @return Exception information
     */
    public static <T extends BaseException> ResponseBodyBean ofException(T t) {
        return setResponse(t.getCode(), t.getMessage(), t.getData());
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
