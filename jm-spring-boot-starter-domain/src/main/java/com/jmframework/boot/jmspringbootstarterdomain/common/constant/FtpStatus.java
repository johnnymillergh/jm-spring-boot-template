package com.jmframework.boot.jmspringbootstarterdomain.common.constant;

/**
 * <h1>FtpStatus</h1>
 * <p>File Transfer Protocol (FTP) Status Code</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-02 18:54
 * @see <a href="https://www.ietf.org/rfc/rfc959.txt">FILE TRANSFER PROTOCOL (FTP)</a>
 * @see <a href="https://en.wikipedia.org/wiki/List_of_FTP_server_return_codes">List of FTP server return codes</a>
 **/
@SuppressWarnings("unused")
public enum FtpStatus implements IUniversalStatus {
    // --- 1xx Positive Preliminary reply ---
    // --- 2xx Positive Completion reply ---
    OK(200, "Command okay.");
    // --- 3xx Positive Intermediate reply ---
    // --- 4xx Transient Negative Completion reply ---
    // --- 5xx Permanent Negative Completion reply ---
    // --- 6xx Protected reply ---

    /**
     * Code of status
     */
    private Integer code;
    /**
     * Message of status
     */
    private String message;

    FtpStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
