package com.jm.springboottemplate.system.constant;

import lombok.Getter;

/**
 * Description: UniversalStatus, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-23
 * @time: 16:48
 **/
@Getter
public enum UniversalStatus implements IUniversalStatus {
    /**
     * Success
     */
    SUCCESS(200, "Success"),
    /**
     * Log out successfully
     */
    LOGOUT(200, "Log out successfully"),
    /**
     * Error or failure
     */
    ERROR(500, "Error. A generic status for an error in the server itself."),
    /**
     * Failure
     */
    FAILURE(500, "Failure"),
    /**
     * Warning
     */
    WARNING(500, "Warning"),
    /**
     * Role not found
     */
    ROLE_NOT_FOUND(500, "Role not found."),
    /**
     * Unauthorized
     */
    UNAUTHORIZED(401, "Unauthorized. The requester is not authorized to access the resource."),
    /**
     * Forbidden
     */
    FORBIDDEN(403, "Forbidden. " +
            "The request was formatted correctly but the server is refusing to supply the requested resource."),
    /**
     * Not found
     */
    NOT_FOUND(404, "Not found. The resource could not be found."),
    /**
     * Method not allowed
     */
    METHOD_NOT_ALLOWED(405, "The resource was requested using a method that is not allowed."),
    /**
     * Bad request
     */
    BAD_REQUEST(400, "Bad request."),
    /**
     * Param not matched
     */
    PARAM_NOT_MATCH(400, "Param not matched. " +
            "The request could not be fulfilled due to the incorrect syntax of the request."),
    /**
     * Param not null
     */
    PARAM_NOT_NULL(400, "Param not null."),
    /**
     * User disabled
     */
    USER_DISABLED(403, "User disabled."),
    /**
     * Username or password error
     */
    USERNAME_OR_PASSWORD_ERROR(5001, "Username or password error."),
    /**
     * Token expired
     */
    TOKEN_EXPIRED(5002, "Token expired."),
    /**
     * Token parse error
     */
    TOKEN_PARSE_ERROR(5002, "Token parse error."),
    /**
     * Token out of control
     */
    TOKEN_OUT_OF_CONTROL(5003, "Token out of control. " +
            "Current user has logged in before. Please try to reset current password or sign in again."),
    /**
     * Kick out self 无法手动踢出自己，请尝试退出登录操作！
     */
    KICK_OUT_SELF(5004, "Cannot kick self out. Please try to sign in again.");

    /**
     * Code of status
     */
    private Integer code;
    /**
     * Message of status
     */
    private String message;

    UniversalStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static UniversalStatus fromCode(Integer code) {
        UniversalStatus[] universalStatuses = UniversalStatus.values();
        for (UniversalStatus universalStatus : universalStatuses) {
            if (universalStatus.getCode()
                    .equals(code)) {
                return universalStatus;
            }
        }
        return SUCCESS;
    }

    @Override
    public String toString() {
        return "UniversalStatus{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
