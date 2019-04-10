package com.jm.springboottemplate.controller;

import com.jm.springboottemplate.common.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Description: DemoControllerTest, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-03
 * @time: 09:49
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoControllerTest {
    @Autowired
    private DemoService demoService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void helloTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/demo/hello")
                                                                    .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                                     .andExpect(MockMvcResultMatchers.status()
                                                                     .isOk())
                                     .andDo(MockMvcResultHandlers.print())
                                     .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getAllUrlTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/demo/getAllApi")
                                                                    .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                                     .andExpect(MockMvcResultMatchers.status()
                                                                     .isOk())
                                     .andDo(MockMvcResultHandlers.print())
                                     .andReturn();
        log.error("Result = {}", mvcResult.getResponse().getContentAsString());
        log.error("Simple name: {}, name: {}",
                  DemoControllerTest.class.getSimpleName(),
                  DemoControllerTest.class.getPackage().getName());
    }
}
