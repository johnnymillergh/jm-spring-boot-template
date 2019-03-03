package com.jm.springboottemplate.common.controller;

import com.jm.springboottemplate.system.response.ResponseBodyBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: HelloController, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-03
 * @time: 14:09
 **/
@RestController
public class HelloController {
    @ResponseBody
    @GetMapping("/admin/hello")
    public ResponseBodyBean adminHello() {
        return ResponseBodyBean.responseSuccess("Hello admin!");
    }

    @ResponseBody
    @GetMapping("/db/hello")
    public ResponseBodyBean dbHello() {
        return ResponseBodyBean.responseSuccess("Hello DBA!");
    }

    @ResponseBody
    @GetMapping("/user/hello")
    public ResponseBodyBean userHello() {
        return ResponseBodyBean.responseSuccess("Hello user!");
    }
}
