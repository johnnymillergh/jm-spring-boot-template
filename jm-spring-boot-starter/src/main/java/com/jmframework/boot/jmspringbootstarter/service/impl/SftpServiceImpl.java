package com.jmframework.boot.jmspringbootstarter.service.impl;

import com.jcraft.jsch.ChannelSftp;
import com.jmframework.boot.jmspringbootstarter.configuration.SftpClientConfiguration;
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
    private final SftpClientConfiguration sftpClientConfiguration;

    public SftpServiceImpl(SftpRemoteFileTemplate sftpRemoteFileTemplate,
                           SftpUploadGateway sftpUploadGateway,
                           SftpClientConfiguration sftpClientConfiguration) {
        this.sftpRemoteFileTemplate = sftpRemoteFileTemplate;
        this.sftpUploadGateway = sftpUploadGateway;
        this.sftpClientConfiguration = sftpClientConfiguration;
    }

    @Override
    public List<String> listFiles(String relativePath) {
        String fullPath = sftpClientConfiguration.getDirectory() + relativePath;
        log.info("Listing files, full path: {}", fullPath);
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
        String fileFullPath = sftpClientConfiguration.getDirectory() + fileRelativePath;
        log.info("Checking whether file exists in SFTP server, full path: {}", fileFullPath);
        return sftpRemoteFileTemplate.execute(session -> session.exists(fileFullPath));
    }

    @Override
    public Long getFileSize(String fileRelativePath) {
        String fileFullPath = sftpClientConfiguration.getDirectory() + fileRelativePath;
        if (!exist(fileRelativePath)) {
            throw new IllegalArgumentException(
                    "Cannot get file size from SFTP server. Caused by: file does not exist, full path: " + fileFullPath);
        }
        String[] splits = fileFullPath.split("/");
        String fileName = splits[splits.length - 1];
        String listPath = fileFullPath.substring(0, fileFullPath.lastIndexOf(fileName) - 1);
        log.info("Retrieve file size from SFTP server, full path: {}", fileFullPath);
        final Long[] fileSize = new Long[1];
        sftpRemoteFileTemplate.execute(session -> {
            ChannelSftp.LsEntry[] lsEntries = session.list(listPath);
            for (ChannelSftp.LsEntry lsEntry : lsEntries) {
                if (lsEntry.getFilename().equals(fileName)) {
                    fileSize[0] = lsEntry.getAttrs().getSize();
                }
            }
            return null;
        });
        return fileSize[0];
    }

    @Override
    public void upload(File file) {
        log.info("Uploading single file to SFTP server. File name: {}", file.getName());
        sftpUploadGateway.upload(file);
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void upload(List<MultipartFile> files, boolean deleteSource) throws IOException {
        log.info("Uploading multipart files to SFTP server (delete source file: {}). Files: {}", deleteSource, files);
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
        log.info("Uploading single multipart file to SFTP server. File name: {}", multipartFile.getOriginalFilename());
        sftpUploadGateway.upload(FileUtil.convertFrom(multipartFile));
    }

    @Override
    public File download(String fileRelativePath) {
        String fileFullPath = sftpClientConfiguration.getDirectory() + fileRelativePath;
        log.info("Downloading file from SFTP server, full path: {}", fileFullPath);
        return sftpRemoteFileTemplate.execute(session -> {
            boolean existFile = session.exists(fileFullPath);
            if (existFile) {
                InputStream is = session.readRaw(fileFullPath);
                return FileUtil.convertFrom(is, fileFullPath);
            } else {
                log.info("Cannot download file from SFTP server. Caused by : file does not exist, full path: {}",
                         fileFullPath);
                return null;
            }
        });
    }

    @Override
    public boolean delete(String fileRelativePath) {
        String fileFullPath = sftpClientConfiguration.getDirectory() + fileRelativePath;
        log.info("Deleting SFTP server's file by full path: {}", fileFullPath);
        return sftpRemoteFileTemplate.execute(session -> {
            boolean existFile = session.exists(fileFullPath);
            if (existFile) {
                return session.remove(fileFullPath);
            } else {
                log.info("Cannot delete SFTP server's file. Caused by: file does not exist, full path: {} ",
                         fileFullPath);
                return false;
            }
        });
    }
}
