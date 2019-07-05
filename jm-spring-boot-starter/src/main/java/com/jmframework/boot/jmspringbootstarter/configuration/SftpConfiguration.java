package com.jmframework.boot.jmspringbootstarter.configuration;

import com.jcraft.jsch.ChannelSftp;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.outbound.SftpMessageHandler;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * <h1>SftpConfiguration</h1>
 * <p>SFTP configuration</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-07-04 18:18
 **/
@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "sftp.configuration")
public class SftpConfiguration {
    /**
     * SFTP server IP
     */
    private String host;
    /**
     * SFTP server port
     */
    private Integer port;
    /**
     * Login user
     */
    private String user;
    /**
     * Login password
     */
    private String password;
    /**
     * Remote directory
     */
    private String directory;
    /**
     * Private key
     */
    private Resource privateKey;
    /**
     * Private key pass phrase
     */
    private String privateKeyPassPhrase;

    @Bean
    public SessionFactory<ChannelSftp.LsEntry> sftpSessionFactory() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory(true);
        factory.setHost(host);
        factory.setPort(port);
        factory.setUser(user);
        if (privateKey != null) {
            factory.setPrivateKey(privateKey);
            factory.setPrivateKeyPassphrase(privateKeyPassPhrase);
        } else {
            factory.setPassword(password);
        }
        factory.setAllowUnknownKeys(true);
        // We return a caching session factory, so that we don't have to reconnect to SFTP server for each time
        return new CachingSessionFactory<>(factory);
    }

    @Bean
    @ServiceActivator(inputChannel = "toSftpChannel")
    @SuppressWarnings("UnresolvedMessageChannel")
    public MessageHandler handler() {
        SftpMessageHandler handler = new SftpMessageHandler(sftpSessionFactory());
        handler.setRemoteDirectoryExpression(new LiteralExpression(directory));
        handler.setFileNameGenerator(message -> {
            if (message.getPayload() instanceof File) {
                return ((File) message.getPayload()).getName();
            } else {
                throw new IllegalArgumentException("File expected as payload.");
            }
        });
        return handler;
    }

    @Bean
    public SftpRemoteFileTemplate template() {
        return new SftpRemoteFileTemplate(sftpSessionFactory());
    }
}
