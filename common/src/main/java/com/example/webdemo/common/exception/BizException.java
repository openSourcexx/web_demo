package com.example.webdemo.common.exception;

/**
 * @author safe
 * @date 2021/2/19
 */
public class BizException extends RuntimeException {
    private String code;

    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable e) {
        super(message, e);
    }

    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(String code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }
}
