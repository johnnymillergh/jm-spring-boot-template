package com.jmframework.boot.jmspringbootstarter.initialization;

import com.jmframework.boot.jmspringbootstarter.configuration.SftpClientConfiguration;
import com.jmframework.boot.jmspringbootstarterdomain.common.constant.SftpSubDirectory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.stereotype.Component;

/**
 * <h1>SftpSubDirectoryRunner</h1>
 * <p>After dependency injection finished, we must inti the SFTP server's sub directory for out business. If you want
 * to customize initialization configuration, config SftpSubDirectory.</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-07-05 08:51
 * @see SftpSubDirectory
 **/
@Slf4j
@Component
public class SftpSubDirectoryRunner implements ApplicationRunner {
    private final SftpRemoteFileTemplate sftpRemoteFileTemplate;
    private final SftpClientConfiguration sftpClientConfiguration;

    public SftpSubDirectoryRunner(SftpRemoteFileTemplate sftpRemoteFileTemplate,
                                  SftpClientConfiguration sftpClientConfiguration) {
        this.sftpRemoteFileTemplate = sftpRemoteFileTemplate;
        this.sftpClientConfiguration = sftpClientConfiguration;
    }

    @Override
    @SuppressWarnings("RedundantThrows")
    public void run(ApplicationArguments args) throws Exception {
        log.error("Staring to initial SFTP server sub directory.");
        sftpRemoteFileTemplate.execute(session -> {
            for (SftpSubDirectory sftpSubDirectory : SftpSubDirectory.values()) {
                String fullPath = sftpClientConfiguration.getDirectory() + sftpSubDirectory.getSubDirectory();
                if (!session.exists(fullPath)) {
                    log.error("SFTP server sub directory does not exist. Creating sub directory: {}", fullPath);
                    session.mkdir(fullPath);
                } else {
                    log.error("SFTP server sub directory exists. Path: {}", fullPath);
                }
            }
            return null;
        });
        log.error("Initialing SFTP server sub directory is done.");
    }
}
