package com.jmframework.boot;

import com.jmframework.boot.jmspringbootsample.TestServiceInSample;
import com.jmframework.boot.jmspringbootstarter.config.ServerConfiguration;
import com.jmframework.boot.jmspringbootstarter.constant.ProjectProperty;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;
import java.util.TimeZone;

/**
 * <p>Description: Application, entrance of the project.</p>
 * <p><strong><em>ATTENTION</em></strong>: Application.java should only exist in the <strong>BASE PACKAGE
 * (artifactId)</strong>, or other resources cannot be autowired.</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-02-17 17:11
 **/
@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@EnableEncryptableProperties
public class Application {
    private static ProjectProperty projectProperty;
    private static ServerConfiguration serverConfiguration;
    private static TestServiceInSample testServiceInSample;

    public Application(ProjectProperty projectProperty,
                       ServerConfiguration serverConfiguration,
                       TestServiceInSample testServiceInSample) {
        Application.projectProperty = projectProperty;
        Application.serverConfiguration = serverConfiguration;
        Application.testServiceInSample = testServiceInSample;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(Application.class, args);
        long endTime = System.currentTimeMillis();
        log.error("🍃 Congratulations! 🎉");
        log.error("{}@{} started successfully!", projectProperty.getProjectArtifactId(), projectProperty.getVersion());
        log.error("Current environment: {} ({})",
                  projectProperty.getEnvironment(),
                  projectProperty.getEnvironmentAlias());
        log.error("Deployment duration: {} seconds ({} ms)",
                  (endTime - startTime) / 1000,
                  (endTime - startTime));
        log.error("Server started at {} (timezone - {}), base URL: {}",
                  new Date(),
                  TimeZone.getDefault().getDisplayName(),
                  serverConfiguration.getUrl());
    }
}
