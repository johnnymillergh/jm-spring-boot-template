package com.jm.springboottemplate.system.interceptor;

import com.jm.springboottemplate.common.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: RequestRecordingInterceptor, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-02
 * @time: 20:51
 **/
public class RequestRecordingInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(RequestRecordingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessorInformation = "[" + RequestUtils.getRequestIpAndPort(request) + "] requested access. URL: "
                + request.getServletPath();
        logger.error(accessorInformation);
        return true;
    }
}
