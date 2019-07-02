package com.jmframework.boot.jmspringbootstarter.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmframework.boot.jmspringbootstarter.exception.base.BaseException;
import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import com.jmframework.boot.jmspringbootstarterdomain.common.constant.IUniversalStatus;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: ResponseUtil
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 18:02
 **/
@Slf4j
public class ResponseUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Write data JSON to response.
     *
     * @param response Response
     * @param status   Status
     * @param data     Data
     */
    public static void renderJson(HttpServletResponse response, IUniversalStatus status, Object data) {
        try {
            ResponseBodyBean responseBodyBean = ResponseBodyBean.ofStatus(status.getCode(), status.getMessage(), data);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(status.getCode());
            response.getWriter().write(MAPPER.writeValueAsString(responseBodyBean));
        } catch (IOException e) {
            log.error("Error occurred when responding a data JSON.", e);
        }
    }

    /**
     * Write exception JSON to response.
     *
     * @param response  Response
     * @param exception Exception
     */
    public static void renderJson(HttpServletResponse response, BaseException exception) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(exception.getCode());
            ResponseBodyBean responseBodyBean = ResponseBodyBean.ofStatus(exception.getCode(),
                                                                          exception.getMessage(),
                                                                          exception.getData());
            response.getWriter().write(MAPPER.writeValueAsString(responseBodyBean));
        } catch (IOException e) {
            log.error("Error occurred when responding an exception JSON.", e);
        }
    }
}