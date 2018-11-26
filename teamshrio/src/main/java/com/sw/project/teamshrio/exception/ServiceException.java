package com.sw.project.teamshrio.exception;

import com.sw.project.teamshrio.common.TeamShiroConstant;

/**
 * @author sw
 * @Title: ServiceException
 * @ProjectName knowproject
 * @Description: 业务异常类
 * @date 18-11-26 下午5:29
 */
public class ServiceException extends ApplicationException {
    private static final long serialVersionUID = -2678203134198782909L;

    public static final String MESSAGE = "业务逻辑异常";

    public ServiceException() {
        super(MESSAGE);
    }

    public ServiceException(String message) {
        super(message);
        this.code = TeamShiroConstant.SERVICE_ERROR;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.code = TeamShiroConstant.SERVICE_ERROR;
    }

    public ServiceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(Throwable cause) {
        super(cause);
        this.code = TeamShiroConstant.SERVICE_ERROR;
    }
}
