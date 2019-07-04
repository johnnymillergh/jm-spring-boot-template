package com.jmframework.boot.jmspringbootstarter.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <h1>SftpService</h1>
 * <p>SFTP service interface</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-07-04 20:35
 **/
public interface SftpService {
    /**
     * List all files under the relative path
     *
     * @param relativePath relative path
     * @return file names
     */
    List<String> listFiles(String relativePath);

    /**
     * Check whether file exists according to file path
     *
     * @param fileRelativePath file's relative path
     * @return true - file exists; false - file not exists
     */
    boolean exist(String fileRelativePath);

    /**
     * Upload single file
     *
     * @param file file
     */
    void upload(File file);

    /**
     * Upload files
     *
     * @param files        file list
     * @param deleteSource true - delete source file; false - not delete source file
     * @throws IOException IO exception
     */
    void upload(List<MultipartFile> files, boolean deleteSource) throws IOException;

    /**
     * Upload files
     *
     * @param files file list
     * @throws IOException IO exception
     */
    void upload(List<MultipartFile> files) throws IOException;

    /**
     * Upload file
     *
     * @param multipartFile multipart file
     * @throws IOException IO exception
     */
    void upload(MultipartFile multipartFile) throws IOException;

    /**
     * Download file
     *
     * @param fileRelativePath file's relative path
     * @return file
     */
    File download(String fileRelativePath);

    /**
     * Delete file according to file path
     *
     * @param fileRelativePath file's relative path
     * @return true - file deleted; false - file not deleted
     */
    boolean delete(String fileRelativePath);
}
