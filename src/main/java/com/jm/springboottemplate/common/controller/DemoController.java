package com.jm.springboottemplate.common.controller;

import com.jm.springboottemplate.common.domain.Book;
import com.jm.springboottemplate.common.domain.TestTable;
import com.jm.springboottemplate.common.service.DemoService;
import com.jm.springboottemplate.system.constant.UniversalStatus;
import com.jm.springboottemplate.system.exception.BizException;
import com.jm.springboottemplate.system.response.ResponseBodyBean;
import com.jm.springboottemplate.system.service.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-02-07 16:16
 **/
@Slf4j
@Api(value = "Demo Controller", tags = {"demo", "test"})
@RestController
@RequestMapping("/demo")
public class DemoController {
    private final DemoService demoService;
    private final RedisTemplate redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;
    private final WebApplicationContext applicationContext;
    private final ApiService apiService;

    public DemoController(DemoService demoService,
                          RedisTemplate redisTemplate,
                          StringRedisTemplate stringRedisTemplate,
                          WebApplicationContext applicationContext, ApiService apiService) {
        this.demoService = demoService;
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
        this.applicationContext = applicationContext;
        this.apiService = apiService;
    }

    @GetMapping("/hello")
    @ApiOperation(value = "Hello", notes = "Test hello")
    public ResponseBodyBean hello(HttpServletRequest request) {
        log.debug(request.getServletPath());
        return ResponseBodyBean.ofSuccess("Hello world!", "Welcome to my website.");
    }

    @GetMapping("/getTestRecordById/{id}")
    @ApiOperation(value = "Get test record by id", notes = "id is required")
    public ResponseBodyBean getTestRecordById(@PathVariable Integer id) {
        TestTable testTable = demoService.getById(id);
        if (testTable == null) {
            return ResponseBodyBean.setResponse(UniversalStatus.WARNING.getCode(), "The data does not exist.", null);
        }
        return ResponseBodyBean.ofSuccess(testTable);
    }

    @GetMapping("/jsonTest")
    @ApiOperation(value = "Json test")
    public ResponseBodyBean jsonTest() {
        Map<String, Object> map = new HashMap<>(10);
        map.put("datetime", new Date());
        map.put("timestamp", new Timestamp(System.currentTimeMillis()));
        map.put("long", new Long("123456789987656123"));
        return ResponseBodyBean.ofSuccess(map);
    }

    @GetMapping("/bixExceptionTest")
    @ApiOperation(value = "Test exception")
    public void bizExceptionTest() {
        throw new BizException(new NullPointerException("I can't do that!"));
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/redisTest")
    @ApiOperation(value = "Test Redis")
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

    @PostMapping("/bookValidation")
    @ApiOperation(value = "Test validation")
    public ResponseBodyBean bookValidation(@Validated Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseBodyBean.setResponse(UniversalStatus.PARAM_NOT_MATCH.getCode(),
                                                "Error count = " + bindingResult.getErrorCount(), null);
        }
        return ResponseBodyBean.ofSuccess("No error");
    }

    @GetMapping("/getAllUrl")
    @ApiOperation(value = "Get all url")
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
