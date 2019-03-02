package com.jm.springboottemplate.common.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: RequestUtils, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-02
 * @time: 20:00
 **/
public class RequestUtils {
    /**
     * Get request user's IP and port information.
     * If the request is through reverse proxy, this method will not work out.
     *
     * @param request
     * @return
     */
    public static String getRequestIpAndPort(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        Integer port = request.getRemotePort();
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // The first ip is actual ip
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index) + ":" + port;
            } else {
                return ip + ":" + port;
            }
        } else {
            return request.getRemoteAddr() + ":" + port;
        }
    }
}
