package com.jmframework.boot.jmspringbootsample;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.mapper.UserMapper;
import com.jmframework.boot.jmspringbootstarterdomain.user.persistence.UserPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description: ApplicationTests, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-02 01:11
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        Page<UserPO> page = new Page<>(1, 5);
        IPage<UserPO> allUser = userMapper.selectUserList(page);
        log.error("Pagination test. {}", allUser.getRecords().size());
    }
}
