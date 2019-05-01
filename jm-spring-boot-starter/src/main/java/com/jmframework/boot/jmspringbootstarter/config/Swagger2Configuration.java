package com.jmframework.boot.jmspringbootstarter.config;

import com.jmframework.boot.jmspringbootstarter.constant.ProjectProperty;
import org.apache.maven.model.Developer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>Description: Swagger 2 Config.</p>
 * API Documentation:
 * <a href="http://192.168.1.4:8080/jm-spring-boot-template-dev/swagger-ui.html">Click me to view<a/>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-02-07 16:15
 **/
@EnableSwagger2
@Configuration
public class Swagger2Configuration {
    private final ProjectProperty projectProperty;

    public Swagger2Configuration(ProjectProperty projectProperty) {
        this.projectProperty = projectProperty;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Developer developer = projectProperty.getDevelopers().get(0);
        String projectArtifactId = projectProperty.getProjectArtifactId();
        String version = projectProperty.getVersion();
        return new ApiInfoBuilder()
                .title("API Documentation for " + projectArtifactId + "@" + version)
                .contact(new Contact(developer.getName(), projectProperty.getUrl(), developer.getEmail()))
                .version(version)
                .description(projectArtifactId + ", environment: " + projectProperty.getEnvironmentAlias())
                .build();
    }
}