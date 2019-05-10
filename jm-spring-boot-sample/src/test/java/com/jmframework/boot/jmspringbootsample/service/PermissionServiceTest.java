package com.jmframework.boot.jmspringbootsample.service;

import com.jmframework.boot.jmspringbootstarter.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description: PermissionServiceTest, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-10 20:57
 **/
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PermissionServiceTest {
    @Autowired
    private PermissionService permissionService;

    @Test
    public void getByIdTest() {
        log.error("Get by ID: {}", permissionService.getById(1));
    }
}
