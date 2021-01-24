package com.example.webdemo.common.mq;

/**
 * @author admin
 * @date 2021/1/24
 */
public interface Consumer {
    void start();

    void shutdown();

    void subscribe(String topic, String subExpress, MessageListener listener);
}
