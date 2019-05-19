package com.jmframework.boot;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
//@SpringBootTest
public class JmSpringBootStarterApplicationTests {

    @Test
    public void contextLoads() {
        String name = StringUtils.trim("  abc abc  ").toLowerCase();
        name = name.replaceAll("\\s", "_");
        log.error("name={}", name);
    }

}
