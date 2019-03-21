package com.example.webdemo.exception;

/**
 * @author tangaq
 * @date 2019/3/21
 */
public class ServiceException extends RuntimeException {
    private String code;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }
}
