package com.jm.springboottemplate.service;

import com.jm.springboottemplate.common.domain.TestTable;
import com.jm.springboottemplate.common.service.DemoService;
import com.jm.springboottemplate.system.dao.UserDao;
import com.jm.springboottemplate.system.domain.Role;
import com.jm.springboottemplate.system.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @Test
    public void getByIdTest() {
        TestTable testTable = demoService.getById(1);
        Assert.assertNotNull(testTable);
        System.out.println(testTable);
    }

    @Test
    public void getUserByUsernameTest() {
        List<Role> roles = userDao.getUserRolesByUserId(1);
        System.out.println(roles);
    }
}
