package com.jm.springboottemplate.system.config;

import com.jm.springboottemplate.system.constant.ProjectProperty;
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
@Component
public class ServerConfiguration implements ApplicationListener<WebServerInitializedEvent> {
    private final ProjectProperty projectProperty;
    private int serverPort;

    public ServerConfiguration(ProjectProperty projectProperty) {
        this.projectProperty = projectProperty;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

    public String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            log.error("IP address of a host could not be determined.", e);
        }
        assert address != null;
        return "http://" + address.getHostAddress() + ":" + this.serverPort + projectProperty.getContextPath();
    }
}
