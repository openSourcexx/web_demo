package com.example.webdemo.common.exception;

/**
 * @author tangaq
 * @date 2019/7/22
 */
public class CheckException extends RuntimeException {
    private String code;

    public CheckException() {
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CheckException(String code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }

}
