package com.jm.springboottemplate.system.constant;

/**
 * Description: IUniversalStatus, interface of RESTful API Status code
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 16:48
 **/
public interface IUniversalStatus {
    /**
     * Get status code.
     *
     * @return Status code
     */
    Integer getCode();

    /**
     * Get message of status.
     *
     * @return message of status
     */
    String getMessage();
}