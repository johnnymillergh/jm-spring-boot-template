package com.jm.springboottemplate.system.config;

import com.jm.springboottemplate.system.constant.ProjectProperty;
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
 * <a href="http://localhost:8080/springboottemplate/swagger-ui.html">Click me to view<a/>
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
        return new ApiInfoBuilder()
                .title("API Documentation for SpringBootTemplate Project")
                .contact(new Contact("Johnny Miller", "http://github.com/johnnymillergh",
                                     "johnnysviva@outlook.com"))
                .version(projectProperty.getVersion())
                .description(projectProperty.getArtifactId())
                .build();
    }
}