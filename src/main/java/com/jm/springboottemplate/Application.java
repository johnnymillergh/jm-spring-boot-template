package com.jm.springboottemplate;

import com.jm.springboottemplate.system.util.ProjectPropertyUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Description: Application, entrance of the project.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-02-17
 * @time: 17:11
 **/
@Slf4j
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.jm.springboottemplate.*.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.error("Congratulations! [{}:v{}] has started up successfully!", ProjectPropertyUtil.getName(),
                ProjectPropertyUtil.getVersion());
    }
}
