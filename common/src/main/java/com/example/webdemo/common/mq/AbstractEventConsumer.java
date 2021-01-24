package com.example.webdemo.common.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.example.webdemo.common.enums.Action;
import com.example.webdemo.common.utils.HessianSerializer;

/**
 * @author admin
 * @date 2021/1/24
 */
public abstract class AbstractEventConsumer<T> implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(AbstractEventConsumer.class);

    @Override
    public Action consume(MessageExt message, ConsumeConcurrentlyContext context) {
        logger.info("开始消费mid:{}", message.getMsgId());
        try {
            T t = (T) HessianSerializer.deserialize(message.getBody());
            logger.info("消费内容:{}", t);
            doBusiness(t);
            logger.info("消费成功");
            return Action.CommitMessage;
        } catch (Exception e) {
            logger.error("消费异常", e);
            return Action.ReconsumeLater;
        }
    }

    public abstract void doBusiness(T t);
}
