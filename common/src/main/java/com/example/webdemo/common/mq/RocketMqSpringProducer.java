package com.example.webdemo.common.mq;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.UtilAll;
import com.alibaba.rocketmq.common.message.Message;
import com.example.webdemo.common.config.MqProperties;
import com.example.webdemo.common.exception.MqClientException;
import com.example.webdemo.common.utils.HessianSerializer;

/**
 * @author admin
 * @date 2021/1/24
 */
public class RocketMqSpringProducer implements Producer, InitializingBean {
    @Autowired
    private MqProperties mqProperties;
    public static final Logger logger = LoggerFactory.getLogger(RocketMqSpringProducer.class);

    private DefaultMQProducer producer;
    private String producerGroupName;
    private final String defaultGroupName = "_DEFAULT_PRODUCER_GROUP";
    private String namesrvAddr;
    private int sendMsgTimeout = 5000;

    private Map<String, MessageQueueSelector> messageQueueSelectorMap;
    private final AtomicBoolean started = new AtomicBoolean(false);
    private final AtomicBoolean closed = new AtomicBoolean(false);

    public RocketMqSpringProducer() {
    }

    @Override
    public void start() {
        if (!mqProperties.isProducerEnable()) {
            logger.warn("mq配置关闭了生产者");
        } else {
            try {
                if (this.started.compareAndSet(false, true)) {
                    this.producer.start();
                }
            } catch (MQClientException e) {
                throw new MqClientException(e.getMessage(), e);
            }
        }
    }

    @Override
    public void shutdown() {
        try {
            if (this.closed.compareAndSet(false, true)) {
                this.producer.start();
            }
        } catch (MQClientException e) {
            throw new MqClientException(e.getMessage(), e);
        }
    }

    @Override
    public SendResult send(Message message) {
        try {
            this.checkServerState();
            MessageQueueSelector selector = this.getSelector(message.getTopic() + "|" + message.getTags());
            SendResult sendResult;
            if (selector != null) {
                sendResult = this.producer.send(message, selector, message.getKeys());
            } else {
                sendResult = this.producer.send(message);
            }
            logger.info("发送成功,topic[{}],tags[{}],key[{}],sendResult[{}].", message.getTopic(), message.getTags(),
                message.getKeys(), sendResult);
            return sendResult;
        } catch (Exception e) {
            logger.error("发送消息异常", e);
            throw new MqClientException("发送消息异常:" + e.getMessage(), e);
        }
    }

    @Override
    public SendResult send(String topic, String tag, String key, Object body) {
        Message message = new Message(topic, tag, key, HessianSerializer.serialize(body));
        return this.send(message);
    }

    private MessageQueueSelector getSelector(String tag) {
        return null == this.messageQueueSelectorMap ? null : this.messageQueueSelectorMap.get(tag);
    }

    private void checkServerState() {
        if (!mqProperties.isProducerEnable()) {
            throw new MqClientException("producer 未启动");
        }
        switch (this.producer.getDefaultMQProducerImpl()
            .getServiceState()) {
            case CREATE_JUST:
                throw new MqClientException("producer 未启动");
            case SHUTDOWN_ALREADY:
                throw new MqClientException("producer 已关闭");
            case START_FAILED:
                throw new MqClientException("producer 启动失败");
            case RUNNING:
            default:
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (mqProperties.isProducerEnable()) {
            this.init();
        }
    }

    private void init() {
        this.producer = new DefaultMQProducer(
            null == this.producerGroupName ? this.defaultGroupName : this.producerGroupName);
        this.producer.setNamesrvAddr(this.namesrvAddr);
        this.producer.setInstanceName(
            "producer#" + UtilAll.getPid() + "#" + this.namesrvAddr.hashCode() + "#" + UUID.randomUUID()
                .toString());
        logger.info("实例化RocketMqSpringProducer:" + this.producer.getInstanceName());
        this.producer.setSendMsgTimeout(this.sendMsgTimeout);
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }

    public void setProducer(DefaultMQProducer producer) {
        this.producer = producer;
    }

    public String getProducerGroupName() {
        return producerGroupName;
    }

    public void setProducerGroupName(String producerGroupName) {
        this.producerGroupName = producerGroupName;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public int getSendMsgTimeout() {
        return sendMsgTimeout;
    }

    public void setSendMsgTimeout(int sendMsgTimeout) {
        this.sendMsgTimeout = sendMsgTimeout;
    }

    public AtomicBoolean getStarted() {
        return started;
    }

    public AtomicBoolean getClosed() {
        return closed;
    }

    public Map<String, MessageQueueSelector> getMessageQueueSelectorMap() {
        return messageQueueSelectorMap;
    }

    public void setMessageQueueSelectorMap(Map<String, MessageQueueSelector> messageQueueSelectorMap) {
        this.messageQueueSelectorMap = messageQueueSelectorMap;
    }
}
