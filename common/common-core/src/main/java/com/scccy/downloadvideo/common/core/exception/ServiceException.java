package com.scccy.downloadvideo.common.core.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String code;
    private final String message;

    public ServiceException(String message) {
        this("500", message);
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ServiceException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}