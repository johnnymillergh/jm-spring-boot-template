package com.jmframework.boot.jmspringbootstarter.aspect;

import cn.hutool.json.JSONUtil;
import com.jmframework.boot.jmspringbootstarter.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: WebLogAspect, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-05 19:55
 **/
@Slf4j
@Aspect
@Component
public class WebLogAspect {
    @Pointcut("@annotation(com.jmframework.boot.jmspringbootstarter.aspect.annotation.WebLog)")
    public void webLog() {
    }

    @Before("webLog()")
    public void onRequest(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        log.info("================================ WEB LOG START ================================");
        log.info("URL            : {}", request.getRequestURL().toString());
        log.info("HTTP Method    : {}", request.getMethod());
        log.info("Client IP:Port : {}", RequestUtil.getRequestIpAndPort(request));
        log.info("Class Method   : {}.{}",
                 joinPoint.getSignature().getDeclaringTypeName(),
                 joinPoint.getSignature().getName());
        log.info("Request Params : {}", JSONUtil.toJsonStr(joinPoint.getArgs()));
    }

    @Around("webLog()")
    public Object onProcess(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;
        log.info("Response       : {}", JSONUtil.toJsonStr(result));
        log.info("Elapsed time   : {} s ({} ms)", elapsedTime / 1000, elapsedTime);
        return result;
    }

    @After("webLog()")
    public void onEnd() {
        log.info("================================= WEB LOG END =================================");
    }

    @AfterThrowing(pointcut = "webLog()", throwing = "e")
    public void onEndWithThrowing(JoinPoint joinPoint, Exception e) {
        log.info("Signature      : {}", joinPoint.getSignature().toShortString());
        log.info("Exception Info : {}, message: {}", e.toString(), e.getMessage());
        log.info("========================== WEB LOG END WITH EXCEPTION ==========================");
    }
}
