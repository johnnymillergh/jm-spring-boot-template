package com.jmframework.boot.jmspringbootsample.service;

import com.jmframework.boot.jmspringbootstarter.service.SftpService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

/**
 * <h1>SftpServiceTest</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-07-04 21:18
 **/
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class SftpServiceTest {
    @Autowired
    private SftpService sftpService;

    @Test
    public void fileOperationTest() {
        File file = new File("/Users/johnny/Downloads/Git Commit Message Conventions.docx");
        sftpService.upload(file);
        List<String> files = sftpService.listFiles("/11");
        log.error("ls: {}", files);
        boolean existence = sftpService.exist("/Git Commit Message Conventions.docx");
        log.error("Whether file exists: {}", existence);
        boolean deleted = sftpService.delete("/Git Commit Message Conventions.docx");
        log.error("File deleted: {}", deleted);
        Assert.assertTrue(existence);
        Assert.assertTrue(deleted);
    }
}
