package com.jm.springboottemplate.system.response;

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
    private Date timestamp = new Date();
    private Integer status = ResponseBodyBeanStatusEnum.SUCCESS.getCode();
    private String message;
    private Object data;

    public ResponseBodyBean setResponse(Object data) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        return responseBodyBean;
    }

    public ResponseBodyBean setResponse(Object data, String message) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        responseBodyBean.message = message;
        return responseBodyBean;
    }

    public static ResponseBodyBean setResponse(Object data, String message, Integer status) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        responseBodyBean.message = message;
        responseBodyBean.status = status;
        return responseBodyBean;
    }

    public static ResponseBodyBean responseSuccess(Object data) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        responseBodyBean.status = ResponseBodyBeanStatusEnum.SUCCESS.getCode();
        return responseBodyBean;
    }

    public static ResponseBodyBean responseSuccess(Object data, String message) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        responseBodyBean.status = ResponseBodyBeanStatusEnum.SUCCESS.getCode();
        responseBodyBean.message = message;
        return responseBodyBean;
    }

    public static ResponseBodyBean responseFailure(String message) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.message = message;
        responseBodyBean.status = ResponseBodyBeanStatusEnum.FAILURE.getCode();
        return responseBodyBean;
    }

    public static ResponseBodyBean responseFailure(Object data) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        responseBodyBean.status = ResponseBodyBeanStatusEnum.FAILURE.getCode();
        return responseBodyBean;
    }

    public static ResponseBodyBean responseFailure(Object data, String message) {
        ResponseBodyBean responseBodyBean = new ResponseBodyBean();
        responseBodyBean.data = data;
        responseBodyBean.status = ResponseBodyBeanStatusEnum.FAILURE.getCode();
        responseBodyBean.message = message;
        return responseBodyBean;
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

    public static enum ResponseBodyBeanStatusEnum {
        /**
         * Success
         */
        SUCCESS(200, "Success"),
        /**
         * Waring or no content
         */
        WARNING(204, "Warning or no content"),
        /**
         * Failure
         */
        FAILURE(500, "Failure");
        private Integer code;
        private String name;

        ResponseBodyBeanStatusEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}
