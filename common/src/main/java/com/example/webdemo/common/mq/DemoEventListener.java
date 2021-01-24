package com.example.webdemo.common.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.example.webdemo.common.enums.Action;

/**
 * @author admin
 * @date 2021/1/24
 */
@Component
public class DemoEventListener implements MessageListener {
    public static final Logger logger = LoggerFactory.getLogger(DemoEventListener.class);

    @Override
    public Action consume(MessageExt message, ConsumeConcurrentlyContext context) {
        logger.info("测试消费者开始消费,mid:{}", message.getMsgId());
        logger.info("消费成功");
        return Action.CommitMessage;
    }
}
