package com.jmframework.boot.jmspringbootsample;

import cn.hutool.core.date.DateTime;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.UserPO;
import com.jmframework.boot.jmspringbootstarter.mapper.UserMapper;
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
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 15:08
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
        UserPO userPO = new UserPO();
        userPO.setUsername("user" + time);
        userPO.setNickname("Common User");
        userPO.setPassword(encoder.encode("123456"));
        userPO.setBirthday(DateTime.of("1996-11-22", "yyyy-MM-dd")
                .getTime());
        userPO.setEmail("user" + time + "@gamil.com");
        userPO.setPhone("19100" + time);
        userPO.setSex(1);
        userPO.setStatus(1);
        Long id = userMapper.save(userPO);
        log.error("Save user, ID = {}", userPO.getId());
    }

    @Test
    public void test() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        System.out.println(simpleDateFormat.format(new Date()));
    }
}
