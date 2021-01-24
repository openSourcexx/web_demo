package com.example.webdemo.common.mq;

import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * @author admin
 * @date 2021/1/24
 */
public interface Producer {
    void start();

    void shutdown();

    SendResult send(Message message);

    SendResult send(String topic, String tag, String key, Object body);
}
