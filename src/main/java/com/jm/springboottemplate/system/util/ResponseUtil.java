package com.jm.springboottemplate.system.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.springboottemplate.system.constant.IUniversalStatus;
import com.jm.springboottemplate.system.exception.base.BaseException;
import com.jm.springboottemplate.system.response.ResponseBodyBean;
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
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(200);
            response.getWriter()
                    .write(MAPPER.writeValueAsString(ResponseBodyBean.ofStatus(status, data)));
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
            response.setStatus(200);
            response.getWriter()
                    .write(MAPPER.writeValueAsString(ResponseBodyBean.ofException(exception)));
        } catch (IOException e) {
            log.error("Error occurred when responding an exception JSON.", e);
        }
    }
}