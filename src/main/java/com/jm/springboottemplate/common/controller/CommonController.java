package com.jm.springboottemplate.common.controller;

import com.jm.springboottemplate.common.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: CommonController, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-02-07
 * @time: 16:16
 **/
@Controller
@RequestMapping("/commonController")
public class CommonController {
    @Autowired
    private TestService testService;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Welcome!";
    }

    @ResponseBody
    @RequestMapping("/getTestRecordById/{id}")
    public Object getTestRecordById(@PathVariable Integer id) {
        return testService.getById(id);
    }

    @ResponseBody
    @RequestMapping("/jsonTest")
    public Map jsonTest() {
        Map<String, Object> map = new HashMap<>(10);
        map.put("datetime", new Date());
        map.put("timestamp", new Timestamp(System.currentTimeMillis()));
        map.put("long", new Long("123456789987656123"));
        return map;
    }
}
