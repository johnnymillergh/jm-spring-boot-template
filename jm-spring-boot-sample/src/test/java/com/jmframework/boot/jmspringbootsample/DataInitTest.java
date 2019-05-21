package com.jmframework.boot.jmspringbootsample;

import com.jmframework.boot.jmspringbootstarter.mapper.UserMapper;
import com.jmframework.boot.jmspringbootstarterdomain.user.persistence.UserPO;
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
        userPO.setFullName("Common User");
        userPO.setPassword(encoder.encode("123456"));
        userPO.setBirthday(new Date());
        userPO.setEmail("user" + time + "@gamil.com");
        userPO.setCellphone("19100" + time);
        userPO.setGender("Cis Male");
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
