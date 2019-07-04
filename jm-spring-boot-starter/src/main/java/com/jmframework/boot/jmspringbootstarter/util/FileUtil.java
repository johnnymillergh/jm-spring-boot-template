package com.jmframework.boot.jmspringbootstarter.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

/**
 * <h1>FileUtil</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-07-04 20:12
 **/
@Slf4j
@SuppressWarnings("unused")
public class FileUtil {
    /**
     * Convert multipart file to file
     *
     * @param multipartFile multipart file
     * @return file
     * @throws IOException IO exception
     */
    public static File convertFrom(MultipartFile multipartFile) throws IOException {
        log.info("Convert multipart file: {}", multipartFile.getOriginalFilename());
        File convertFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convertFile;
    }

    /**
     * Convert input stream to file
     *
     * @param inputStream input stream
     * @param savePath    path for saving file
     * @return file
     */
    public static File convertFrom(InputStream inputStream, String savePath) {
        File file = new File(savePath);
        try (OutputStream outputStream = new FileOutputStream(file)) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            log.info("convert InputStream to file done, savePath is: {}", savePath);
        } catch (IOException e) {
            log.error("Exception occurred when initializing FileOutputStream. Exception message: {}",
                      e.getMessage(),
                      e);
        }

        try {
            inputStream.close();
        } catch (IOException e) {
            log.error("Exception occurred when closing FileOutputStream. Exception message: {}", e.getMessage(), e);
        }

        return file;
    }
}
