package com.jmframework.boot.jmspringbootsample;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>JasyptTest</h1>
 * <p>Encrypt configuration value for application.yml</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 4/25/2019 7:53 PM
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptTest {
    @Autowired
    @SuppressWarnings("SpellCheckingInspection")
    StringEncryptor encryptor;

    @ToString
    private class EncryptedInformation {
        // MySQL
        String urlOfDataSource = encryptor.encrypt(
                "jdbc:mysql://127.0.0.1:3306/jm_spring_boot_template?useSSL=true&useUnicode=true&characterEncoding" +
                        "=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8");
        String usernameForMySQL = encryptor.encrypt("jm_spring_boot_template_w");
        String passwordForMySQL = encryptor.encrypt("H'Tkh9@!*_/B'_j");

        // Redis
        String hostOfRedis = encryptor.encrypt("192.168.158.136");
        String passwordForRedis = encryptor.encrypt("123456");

        // Druid
        String druidLoginName = encryptor.encrypt("johnny");
        String druidPassword = encryptor.encrypt("123456");
        String keystoreForSSL = encryptor.encrypt("keystoreforprojectname");

        // SFTP
        String sftpHost = encryptor.encrypt("sftpHostHere");
        String sftpUser = encryptor.encrypt("sftpUserHere");
        String sftpPassword = encryptor.encrypt("sftpPasswordHere");
        String sftpDirectory = encryptor.encrypt("sftpDirectoryHere");
    }

    @Test
    public void getEncryptedInformation() {
        EncryptedInformation encryptedInformation = new EncryptedInformation();
        log.error("Encrypted information = {}", encryptedInformation.toString());
    }
}
