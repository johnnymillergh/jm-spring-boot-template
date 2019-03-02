package com.jm.springboottemplate.common.controller;

import com.jm.springboottemplate.common.domain.Book;
import com.jm.springboottemplate.common.domain.TestTable;
import com.jm.springboottemplate.common.service.DemoService;
import com.jm.springboottemplate.system.exception.BizException;
import com.jm.springboottemplate.system.response.ResponseBodyBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: DemoController, used for testing new features.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-02-07
 * @time: 16:16
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoService demoService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseBodyBean hello(HttpServletRequest request) {
        logger.debug(request.getServletPath());
        return ResponseBodyBean.responseSuccess("Hello world!", "Welcome to my website.");
    }

    @ResponseBody
    @RequestMapping(value = "/getTestRecordById/{id}", method = RequestMethod.GET)
    public ResponseBodyBean getTestRecordById(@PathVariable Integer id) {
        TestTable testTable = demoService.getById(id);
        if (testTable == null) {
            return ResponseBodyBean.setResponse(null, "The data does not exist.",
                    ResponseBodyBean.ResponseBodyBeanStatusEnum.WARNING.getCode());
        }
        return ResponseBodyBean.responseSuccess(testTable);
    }

    @ResponseBody
    @RequestMapping(value = "/jsonTest", method = RequestMethod.GET)
    public ResponseBodyBean jsonTest() {
        Map<String, Object> map = new HashMap<>(10);
        map.put("datetime", new Date());
        map.put("timestamp", new Timestamp(System.currentTimeMillis()));
        map.put("long", new Long("123456789987656123"));
        return ResponseBodyBean.responseSuccess(map);
    }

    @RequestMapping(value = "/bixExceptionTest", method = RequestMethod.GET)
    public void bizExceptionTest() {
        throw new BizException(new NullPointerException("I can't do that!"));
    }

    @ResponseBody
    @RequestMapping(value = "/redisTest", method = RequestMethod.GET)
    public ResponseBodyBean redisTest() {
        ValueOperations<String, String> operations1 = stringRedisTemplate.opsForValue();
        String bookName = "Call Me by Your Name";
        operations1.set("name", bookName);
        logger.error("Save value into Redis: {}", bookName);
        logger.error("Get value from Redis: {}", operations1.get("name"));
        ValueOperations<String, Object> operations2 = redisTemplate.opsForValue();
        Book book = new Book();
        book.setId(1);
        book.setName(bookName);
        book.setAuthor("Andre Aciman");
        operations2.set("book", book);
        logger.error("Get value from Redis: {}", operations2.get("book"));
        return ResponseBodyBean.responseSuccess(operations2.get("book"));
    }
}
