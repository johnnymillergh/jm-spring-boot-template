package com.jm.springboottemplate.common.controller;

import com.jm.springboottemplate.common.domain.TestTable;
import com.jm.springboottemplate.common.service.DemoService;
import com.jm.springboottemplate.system.exception.BizException;
import com.jm.springboottemplate.system.response.ResponseBodyBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
@RequestMapping("/demo")
public class DemoController {
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoService demoService;

    @ResponseBody
    @RequestMapping("/hello")
    public ResponseBodyBean hello(HttpServletRequest request) {
        logger.debug(request.getServletPath());
        return ResponseBodyBean.responseSuccess("Hello world!", "Welcome to my website.");
    }

    @ResponseBody
    @RequestMapping("/getTestRecordById/{id}")
    public ResponseBodyBean getTestRecordById(@PathVariable Integer id) {
        TestTable testTable = demoService.getById(id);
        return ResponseBodyBean.responseSuccess(testTable);
    }

    @ResponseBody
    @RequestMapping("/jsonTest")
    public ResponseBodyBean jsonTest() {
        Map<String, Object> map = new HashMap<>(10);
        map.put("datetime", new Date());
        map.put("timestamp", new Timestamp(System.currentTimeMillis()));
        map.put("long", new Long("123456789987656123"));
        return ResponseBodyBean.responseSuccess(map);
    }

    @RequestMapping("/bixExceptionTest")
    public void bizExceptionTest() {
        throw new BizException(new NullPointerException("I can't do that!"));
    }
}
