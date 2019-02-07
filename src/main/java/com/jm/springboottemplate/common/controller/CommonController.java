package com.jm.springboottemplate.common.controller;

import com.jm.springboottemplate.common.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Welcome!";
    }

    @RequestMapping("/getTestRecordById/{id}")
    @ResponseBody
    public Object getTestRecordById(@PathVariable Integer id) {
        return testService.getById(id);
    }
}
