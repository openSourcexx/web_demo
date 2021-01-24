package com.example.webdemo.common.exception;

/**
 * @author admin
 * @date 2021/1/24
 */
public class MqClientException extends RuntimeException {
    public MqClientException() {
    }

    public MqClientException(String message) {
        super(message);
    }

    public MqClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
