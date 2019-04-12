package com.jm.springboottemplate;

import com.jm.springboottemplate.system.util.ProjectPropertyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Description: Application, entrance of the project.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-02-17 17:11
 **/
@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class Application {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(Application.class, args);
        long endTime = System.currentTimeMillis();
        log.error("Congratulations! [{}:v{}] has started up successfully! Deployment duration: {} seconds ({} ms)",
                  ProjectPropertyUtil.getName(),
                  ProjectPropertyUtil.getVersion(),
                  (endTime - startTime) / 1000,
                  (endTime - startTime));
    }
}
