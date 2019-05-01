package com.jmframework.boot.jmspringbootsample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description: TestServiceInSample, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-01 23:10
 **/
@Service
@Slf4j
public class TestServiceInSample {
    public void sayHi() {
        log.info("Hi from sample");
    }
}
