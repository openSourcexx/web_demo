package com.example.webdemo.common.mq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.UtilAll;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.example.webdemo.common.config.MqProperties;
import com.example.webdemo.common.enums.Action;
import com.example.webdemo.common.exception.MqClientException;

/**
 * @author admin
 * @date 2021/1/24
 */
public class RocketMqSpringPushConsumer implements Consumer, InitializingBean {
    public static final Logger logger = LoggerFactory.getLogger(RocketMqSpringPushConsumer.class);

    @Autowired
    private MqProperties mqProperties;
    private DefaultMQPushConsumer consumer;
    private String namesrvAddr;
    private String consumerGroup;
    private String topic;
    private String tag;

    /** key:topic,val:tag */
    private Map<String, String> subscription;
    /** 消费者监听器 key:topic|tag,val: */
    private Map<String, MessageListener> messageListenerMap;

    private int consumeThreadNums = 30;
    private int pullBatchSize = 32;
    private long pullInterval = 0L;
    private int consumeMessageBatchMaxSize = 1;

    private final AtomicBoolean started = new AtomicBoolean(false);
    private final AtomicBoolean closed = new AtomicBoolean(false);

    public RocketMqSpringPushConsumer() {
    }

    public void init() {
        this.initSubscription();
        this.initConsumer();
        this.subscribe();
    }

    private void subscribe() {
        for (Map.Entry<String, String> entry : this.subscription.entrySet()) {
            try {
                this.consumer.subscribe(entry.getKey(), entry.getValue());
            } catch (MQClientException ex) {
                throw new MqClientException("消费异常:" + ex.getMessage(), ex);
            }
        }

    }

    private void initConsumer() {
        if (null == this.consumerGroup) {
            throw new MqClientException("consumerGroup is null");
        } else {
            this.consumer = new DefaultMQPushConsumer(this.consumerGroup);
            // 消费位点
            this.consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            this.consumer.setNamesrvAddr(this.namesrvAddr);
            this.consumer.setMessageModel(MessageModel.CLUSTERING);
            this.consumer.setInstanceName(
                "consumer#" + UtilAll.getPid() + "#" + this.namesrvAddr.hashCode() + "#" + UUID.randomUUID()
                    .toString());
            logger.info("实例化RocketMqSpringPushConsumer:" + this.consumer.getInstanceName());
            if (null != this.topic && !this.topic.isEmpty()) {
                this.subscription.put(this.topic, this.tag);
            }

            this.consumer.setPullInterval(this.pullInterval);
            this.consumer.setPullBatchSize(this.pullBatchSize);
            this.consumer.setConsumeMessageBatchMaxSize(this.consumeMessageBatchMaxSize);
            logger.info("默认拉去时间间隔{}ms,一次拉取{}条数据,一次消费{}条数据,消费线程数:{}", this.pullInterval, this.pullBatchSize,
                this.consumeMessageBatchMaxSize, this.consumeThreadNums);
            if (this.consumeThreadNums != 0) {
                this.consumer.setConsumeThreadMax(this.consumeThreadNums);
                this.consumer.setConsumeThreadMin(this.consumeThreadNums);
            }
        }
    }

    private void initSubscription() {
        if (null == this.subscription) {
            this.subscription = new HashMap<>();
        }
    }

    @Override
    public void start() {
        if (!mqProperties.isConsumerEnable()) {
            logger.warn("mq配置关闭了消费者");
        } else {
            this.consumer.registerMessageListener(new RocketMqSpringPushConsumer.MessageListenerImpl());
            try {
                if (this.started.compareAndSet(false, true)) {
                    this.consumer.start();
                }
            } catch (Exception e) {
                throw new MqClientException(e.getMessage(), e);
            }
        }
    }

    @Override
    public void shutdown() {
        if (this.closed.compareAndSet(false, true)) {
            this.consumer.shutdown();
        }
    }

    @Override
    public void subscribe(String topic, String subExpress, MessageListener listener) {
        if (null == topic) {
            throw new MqClientException("topic is null");
        } else if (null == listener) {
            throw new MqClientException("listener is null");
        } else {
            try {
                this.subscription.put(topic, subExpress);
                this.messageListenerMap.put(topic + "|" + subExpress, listener);
                this.consumer.subscribe(topic, subExpress);
            } catch (MQClientException e) {
                throw new MqClientException("消费异常:" + e.getMessage(), e);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (mqProperties.isConsumerEnable()) {
            this.init();
        }
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Map<String, String> getSubscription() {
        return subscription;
    }

    public void setSubscription(Map<String, String> subscription) {
        this.subscription = subscription;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public int getConsumeThreadNums() {
        return consumeThreadNums;
    }

    public void setConsumeThreadNums(int consumeThreadNums) {
        this.consumeThreadNums = consumeThreadNums;
    }

    public int getPullBatchSize() {
        return pullBatchSize;
    }

    public void setPullBatchSize(int pullBatchSize) {
        this.pullBatchSize = pullBatchSize;
    }

    public long getPullInterval() {
        return pullInterval;
    }

    public void setPullInterval(long pullInterval) {
        this.pullInterval = pullInterval;
    }

    public int getConsumeMessageBatchMaxSize() {
        return consumeMessageBatchMaxSize;
    }

    public void setConsumeMessageBatchMaxSize(int consumeMessageBatchMaxSize) {
        this.consumeMessageBatchMaxSize = consumeMessageBatchMaxSize;
    }

    public AtomicBoolean getStarted() {
        return started;
    }

    public AtomicBoolean getClosed() {
        return closed;
    }

    public Map<String, MessageListener> getMessageListenerMap() {
        return messageListenerMap;
    }

    public void setMessageListenerMap(Map<String, MessageListener> messageListenerMap) {
        this.messageListenerMap = messageListenerMap;
    }

    class MessageListenerImpl implements MessageListenerConcurrently {

        public MessageListenerImpl() {
        }

        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgRMQList,
            ConsumeConcurrentlyContext contextRMQ) {
            MessageExt messageExt = msgRMQList.get(0);
            MessageListener listener = RocketMqSpringPushConsumer.this.messageListenerMap.get(
                messageExt.getTopic() + "|" + messageExt.getTags());
            if (listener == null) {
                logger.warn("messageExt not foune MessageListener.{}", messageExt);
                throw new MqClientException("MessageListener is null");
            } else {
                Action action = listener.consume(messageExt, contextRMQ);
                if (action != null) {
                    switch (action) {
                        case CommitMessage:
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        case ReconsumeLater:
                            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                return null;
            }
        }
    }
}
