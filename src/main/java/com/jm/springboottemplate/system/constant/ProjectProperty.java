package com.jm.springboottemplate.system.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: ProjectProperty, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-04-18 13:01
 **/
@Data
@Component
@ConfigurationProperties(prefix = "project.property")
public class ProjectProperty {
    private String groupId;
    private String artifactId;
    private String version;
    private String name;
    private String description;
    private String jdkVersion;
    private String currentEnvironment;
}
