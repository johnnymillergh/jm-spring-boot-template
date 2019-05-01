package com.jmframework.boot.jmspringbootstarter.constant;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.model.Developer;
import org.apache.maven.model.License;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Description: ProjectProperty, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-04-18 13:01
 **/
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "project.property")
public class ProjectProperty {
    private static final String POM_FILE_NAME = "pom.xml";
    private String contextPath;
    private String groupId;
    private String artifactId;
    private String projectArtifactId;
    private String version;
    private String description;
    private String jdkVersion;
    private String environment;
    private String environmentAlias;
    private String url;
    private String inceptionYear;
    private String organizationName;
    private String organizationUrl;
    private String issueManagementSystem;
    private String issueManagementUrl;
    private Model model;
    private List<License> licenses;
    private List<Developer> developers;

    /**
     * Init properties after dependencies injection is done.
     */
    @PostConstruct
    public void init() {
        log.info("Start to init properties after dependency injection was done.");
        this.model = this.parsePom();
        assert this.model != null;
        this.licenses = this.model.getLicenses();
        this.developers = this.model.getDevelopers();
    }

    /**
     * Alternative way to init properties after dependencies injection is done.
     *
     * @param event application ready event.
     */
    // @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        log.info("Start to init properties. On received event: {}", event.toString());
        this.model = this.parsePom();
        assert this.model != null;
        this.licenses = this.model.getLicenses();
        this.developers = this.model.getDevelopers();
    }

    /**
     * Parse pom.xml
     *
     * @return Project model
     */
    private Model parsePom() {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        // Read pom.xml from relative path
        try {
            File pom = new File(POM_FILE_NAME);
            if (pom.exists()) {
                return reader.read(new FileReader(pom));
            }
        } catch (IOException | XmlPullParserException e) {
            log.error("Error occurred when read pom.xml. Filepath: {}", POM_FILE_NAME, e);
        }
        // Read pom.xml from packaged jar
        String pomFilePath = "/META-INF/maven/" + groupId + "/" + artifactId + "/" + POM_FILE_NAME;
        log.error("Read pom.xml from packaged jar. Filepath = {}", pomFilePath);
        try {
            return reader.read(
                    new InputStreamReader(ProjectProperty.class.getResourceAsStream(pomFilePath))
            );
        } catch (IOException | XmlPullParserException e) {
            log.error("Error occurred when read pom.xml. Filepath: {}", pomFilePath, e);
        }
        return null;
    }
}
