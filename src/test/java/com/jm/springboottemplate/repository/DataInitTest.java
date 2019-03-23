package com.jm.springboottemplate.repository;

import cn.hutool.core.date.DateTime;
import com.jm.springboottemplate.system.domain.persistance.User;
import com.jm.springboottemplate.system.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: DataInitTest.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-23
 * @time: 15:08
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataInitTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void createUser() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        String time = simpleDateFormat.format(new Date());
        User user = new User();
        user.setUsername("user" + time);
        user.setNickname("Common User");
        user.setPassword(encoder.encode("123456"));
        user.setBirthday(DateTime.of("1996-11-22", "yyyy-MM-dd")
                .getTime());
        user.setEmail("user" + time + "@gamil.com");
        user.setPhone("19100" + time);
        user.setSex(1);
        user.setStatus(1);
        Long id = userMapper.save(user);
        log.error("Save user, ID = {}", user.getId());
    }

    @Test
    public void test() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        System.out.println(simpleDateFormat.format(new Date()));
    }
}
