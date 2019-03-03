package com.jm.springboottemplate;

import com.jm.springboottemplate.common.util.ProjectPropertyUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Description: Application, entrance of the project.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-02-17
 * @time: 17:11
 **/
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.jm.springboottemplate.*.mapper")
@EnableCaching
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.error("Congratulations! [{}:{}] has started up successfully!", ProjectPropertyUtils.getName(),
                ProjectPropertyUtils.getVersion());
    }
}
