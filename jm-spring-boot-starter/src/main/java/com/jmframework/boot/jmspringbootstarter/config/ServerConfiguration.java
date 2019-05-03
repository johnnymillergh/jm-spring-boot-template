package com.jmframework.boot.jmspringbootstarter.config;

import com.jmframework.boot.jmspringbootstarter.constant.ProjectProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description: ServerConfiguration, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-04-26 16:02
 **/
@Slf4j
@Getter
@Component
public class ServerConfiguration implements ApplicationListener<WebServerInitializedEvent> {
    public static final String DEVELOPMENT_ENVIRONMENT_ALIAS = "dev";
    private final ProjectProperty projectProperty;
    private int serverPort;

    public ServerConfiguration(ProjectProperty projectProperty) {
        this.projectProperty = projectProperty;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

    /**
     * <p>Get base URL of backend server.</p>
     * <p>The result will be like:</p>
     * <ol>
     * <li>http://[serverIp]/[contextPath]</li>
     * <li>https://[serverIp]/[contextPath]</li>
     * </ol>
     *
     * @return base URL
     * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
     * @date 2019-05-03 16:05
     */
    public String getBaseUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            log.error("IP address of a host could not be determined.", e);
        }
        assert address != null;
        // HTTPS is not enabled under development environment.
        if (DEVELOPMENT_ENVIRONMENT_ALIAS.equals(projectProperty.getEnvironmentAlias())) {
            return "http://" + address.getHostAddress() + projectProperty.getContextPath();
        }
        return "https://" + address.getHostAddress() + projectProperty.getContextPath();
    }
}
