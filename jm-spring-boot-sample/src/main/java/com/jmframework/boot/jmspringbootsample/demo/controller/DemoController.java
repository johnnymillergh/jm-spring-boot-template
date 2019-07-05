package com.jmframework.boot.jmspringbootsample.demo.controller;

import com.jmframework.boot.jmspringbootstarter.exception.BizException;
import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import com.jmframework.boot.jmspringbootstarter.util.JwtUtil;
import com.jmframework.boot.jmspringbootstarterdomain.common.constant.HttpStatus;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: DemoController, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-04 07:58
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {
    private final JwtUtil jwtUtil;

    public DemoController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/get-current-user")
    @ApiOperation(value = "Get current user", notes = "Get current user from JWT")
    public ResponseBodyBean getCurrentUser(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>(4);
        String jwtString = jwtUtil.getJwtFromRequest(request);
        Claims claims = jwtUtil.parseJwt(jwtString);
        resultMap.put("jwtString", jwtString);
        resultMap.put("claims", claims);
        response.setStatus(org.springframework.http.HttpStatus.BAD_REQUEST.value());
        return ResponseBodyBean.ofFailure(resultMap);
    }

    @GetMapping("/test-response-and-exception")
    @ApiOperation(value = "Test response and exception", notes = "Test response and exception")
    public ResponseBodyBean testResponseAndException(Integer code) throws InterruptedException {
        Thread.sleep(2000);
        switch (code) {
            case 1:
                return ResponseBodyBean.ofSuccess("Test success message");
            case 2:
                return ResponseBodyBean.ofFailure("Test error message");
            case 3:
                return ResponseBodyBean.setResponse(HttpStatus.FAILURE.getCode(),
                                                    HttpStatus.FAILURE.getMessage(),
                                                    null);
            case 4:
                return ResponseBodyBean.setResponse(HttpStatus.WARNING.getCode(),
                                                    HttpStatus.WARNING.getMessage(),
                                                    null);
            case 5:
                return ResponseBodyBean.ofException(new BizException("Respond a exception"));
            case 6:
                throw new RuntimeException("Code 5 is not recognized");
            case 7:
                throw new NullPointerException("Code 6 is mocking a NullPointerException");
            default:
                throw new BizException("Throw a test exception");
        }
    }

    @PostMapping("/test-post-1")
    public void testPost1() throws InterruptedException {
        Thread.sleep(500);
    }

    @GetMapping("/test-get-1")
    public void testGet1() throws InterruptedException {
        Thread.sleep(500);
    }
}
