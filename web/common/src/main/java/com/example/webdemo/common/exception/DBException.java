package com.example.webdemo.common.exception;

/**
 * 数据库异常
 * @author tangaq
 * @date 2019/3/21
 */
public class DBException extends RuntimeException {
    private String code;

    public DBException() {
    }

    public DBException(String message) {
        super(message);
    }

    public DBException(String code, String message) {
        super(message);
        this.code = code;
    }

    public DBException(String code, String message, Throwable e) {
        super(message,e);
        this.code = code;
    }

}
