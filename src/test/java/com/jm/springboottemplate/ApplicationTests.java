package com.jm.springboottemplate;

import com.jm.springboottemplate.system.constant.UserStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(UserStatus.ENABLED.getStatus());
    }

}

