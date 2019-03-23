package com.jm.springboottemplate.common.controller;

import com.jm.springboottemplate.common.domain.Book;
import com.jm.springboottemplate.common.domain.TestTable;
import com.jm.springboottemplate.common.service.DemoService;
import com.jm.springboottemplate.system.constant.UniversalStatus;
import com.jm.springboottemplate.system.exception.BizException;
import com.jm.springboottemplate.system.response.ResponseBodyBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

/**
 * Description: DemoController, used for testing new features.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-02-07
 * @time: 16:16
 **/
@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private DemoService demoService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private WebApplicationContext applicationContext;

    @ResponseBody
    @GetMapping("/hello")
    public ResponseBodyBean hello(HttpServletRequest request) {
        log.debug(request.getServletPath());
        return ResponseBodyBean.ofSuccess("Hello world!", "Welcome to my website.");
    }

    @ResponseBody
    @GetMapping("/getTestRecordById/{id}")
    public ResponseBodyBean getTestRecordById(@PathVariable Integer id) {
        TestTable testTable = demoService.getById(id);
        if (testTable == null) {
            return ResponseBodyBean.setResponse(UniversalStatus.WARNING.getCode(), "The data does not exist.", null);
        }
        return ResponseBodyBean.ofSuccess(testTable);
    }

    @ResponseBody
    @GetMapping("/jsonTest")
    public ResponseBodyBean jsonTest() {
        Map<String, Object> map = new HashMap<>(10);
        map.put("datetime", new Date());
        map.put("timestamp", new Timestamp(System.currentTimeMillis()));
        map.put("long", new Long("123456789987656123"));
        return ResponseBodyBean.ofSuccess(map);
    }

    @GetMapping("/bixExceptionTest")
    public void bizExceptionTest() {
        throw new BizException(new NullPointerException("I can't do that!"));
    }

    @ResponseBody
    @GetMapping("/redisTest")
    public ResponseBodyBean redisTest() {
        ValueOperations<String, String> operations1 = stringRedisTemplate.opsForValue();
        String bookName = "Call Me by Your Name";
        operations1.set("name", bookName);
        log.error("Save value into Redis: {}", bookName);
        log.error("Get value from Redis: {}", operations1.get("name"));
        ValueOperations<String, Object> operations2 = redisTemplate.opsForValue();
        Book book = new Book();
        book.setId(1);
        book.setName(bookName);
        book.setAuthor("Andre Aciman");
        operations2.set("book", book);
        log.error("Get value from Redis: {}", operations2.get("book"));
        return ResponseBodyBean.ofSuccess(operations2.get("book"));
    }

    @ResponseBody
    @PostMapping("/bookValidation")
    public ResponseBodyBean bookValidation(@Validated Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseBodyBean.setResponse(UniversalStatus.PARAM_NOT_MATCH.getCode(),
                    "Error count = " + bindingResult.getErrorCount(), null);
        }
        return ResponseBodyBean.ofSuccess("No error");
    }

    @GetMapping("/getAllUrl")
    @ResponseBody
    public List<String> getAllUrl() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        //获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        List<String> urlList = new ArrayList<>();
        for (RequestMappingInfo info : map.keySet()) {
            //获取url的Set集合，一个方法可能对应多个url
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            urlList.addAll(patterns);
        }
        return urlList;
    }
}
