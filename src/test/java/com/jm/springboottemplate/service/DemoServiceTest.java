package com.jm.springboottemplate.service;

import com.jm.springboottemplate.common.domain.TestTable;
import com.jm.springboottemplate.common.service.DemoService;
import com.jm.springboottemplate.system.domain.persistence.User;
import com.jm.springboottemplate.system.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description: DemoServiceTest, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-03
 * @time: 09:50
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoServiceTest {
    @Autowired
    private DemoService demoService;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void getByIdTest() {
        TestTable testTable = demoService.getById(1);
        Assert.assertNotNull(testTable);
        System.out.println(testTable);
    }

    @Test
    public void getUserByUsernameTest() {
        User user = userMapper.findByUsernameOrEmailOrPhone("admin", "admin", "admin")
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        System.out.println(user.getPassword());
    }
}
