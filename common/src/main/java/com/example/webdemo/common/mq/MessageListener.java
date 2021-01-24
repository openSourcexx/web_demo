package com.example.webdemo.common.mq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.example.webdemo.common.enums.Action;

/**
 * @author admin
 * @date 2021/1/24
 */
public interface MessageListener {
    Action consume(final MessageExt message, final ConsumeConcurrentlyContext context);
}
