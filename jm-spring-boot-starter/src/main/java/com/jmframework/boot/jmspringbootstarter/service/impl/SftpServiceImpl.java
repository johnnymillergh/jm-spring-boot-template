package com.jmframework.boot.jmspringbootstarter.service.impl;

import com.jmframework.boot.jmspringbootstarter.configuration.SftpConfiguration;
import com.jmframework.boot.jmspringbootstarter.configuration.SftpUploadGateway;
import com.jmframework.boot.jmspringbootstarter.service.SftpService;
import com.jmframework.boot.jmspringbootstarter.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>SftpServiceImpl</h1>
 * <p>SFTP Service implementation</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-07-04 20:47
 **/
@Slf4j
@Service
public class SftpServiceImpl implements SftpService {
    private final SftpRemoteFileTemplate sftpRemoteFileTemplate;
    private final SftpUploadGateway sftpUploadGateway;
    private final SftpConfiguration sftpConfiguration;

    public SftpServiceImpl(SftpRemoteFileTemplate sftpRemoteFileTemplate,
                           SftpUploadGateway sftpUploadGateway,
                           SftpConfiguration sftpConfiguration) {
        this.sftpRemoteFileTemplate = sftpRemoteFileTemplate;
        this.sftpUploadGateway = sftpUploadGateway;
        this.sftpConfiguration = sftpConfiguration;
    }

    @Override
    public List<String> listFiles(String relativePath) {
        String fullPath = sftpConfiguration.getDirectory() + relativePath;
        log.info("Listing files, path: {}", fullPath);
        return sftpRemoteFileTemplate.execute(session -> {
            String[] strings = new String[0];
            try {
                strings = session.listNames(fullPath);
            } catch (IOException e) {
                log.error("Exception occurred when listing files. Exception message: {}", e.getMessage(), e);
            }
            return Arrays.asList(strings);
        });
    }

    @Override
    public boolean exist(String fileRelativePath) {
        String fileFullPath = sftpConfiguration.getDirectory() + fileRelativePath;
        log.info("Checking whether file exists, path: {}", fileFullPath);
        return sftpRemoteFileTemplate.execute(session -> session.exists(fileFullPath));
    }

    @Override
    public void upload(File file) {
        log.info("Upload single file. File name: {}", file.getName());
        sftpUploadGateway.upload(file);
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void upload(List<MultipartFile> files, boolean deleteSource) throws IOException {
        log.info("Upload multipart files (delete source file: {}). Files: {}", deleteSource, files);
        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty()) {
                continue;
            }
            File file = FileUtil.convertFrom(multipartFile);
            sftpUploadGateway.upload(file);
            if (deleteSource) {
                file.delete();
            }
        }
    }

    @Override
    public void upload(List<MultipartFile> files) throws IOException {
        upload(files, true);
    }

    @Override
    public void upload(MultipartFile multipartFile) throws IOException {
        log.info("Upload single multipart file. File name: {}", multipartFile.getOriginalFilename());
        sftpUploadGateway.upload(FileUtil.convertFrom(multipartFile));
    }

    @Override
    public File download(String fileRelativePath) {
        String fileFullPath = sftpConfiguration.getDirectory() + fileRelativePath;
        log.info("Downloading file, path: {}", fileFullPath);
        return sftpRemoteFileTemplate.execute(session -> {
            boolean existFile = session.exists(fileFullPath);
            if (existFile) {
                InputStream is = session.readRaw(fileFullPath);
                return FileUtil.convertFrom(is, fileFullPath);
            } else {
                log.info("Cannot download file. Caused by : file does not exist, path: {}", fileFullPath);
                return null;
            }
        });
    }

    @Override
    public boolean delete(String fileRelativePath) {
        String fileFullPath = sftpConfiguration.getDirectory() + fileRelativePath;
        log.info("Deleting file by path: {}", fileFullPath);
        return sftpRemoteFileTemplate.execute(session -> {
            boolean existFile = session.exists(fileFullPath);
            if (existFile) {
                return session.remove(fileFullPath);
            } else {
                log.info("Cannot delete file. Caused by: file does not exist, path: {} ", fileFullPath);
                return false;
            }
        });
    }
}
