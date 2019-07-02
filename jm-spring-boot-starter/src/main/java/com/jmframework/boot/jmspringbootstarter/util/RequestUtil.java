package com.jmframework.boot.jmspringbootstarter.util;

import org.codehaus.plexus.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: RequestUtil, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-02 20:00
 **/
public class RequestUtil {
    /**
     * Get request user's IP and port information. If the request is through reverse proxy, this method will not work
     * out.
     *
     * @param request HTTP request.
     * @return user's IP and port information.
     */
    public static String getRequestIpAndPort(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        int port = request.getRemotePort();
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
