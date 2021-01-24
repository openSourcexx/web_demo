package com.example.webdemo.common.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.webdemo.common.constant.MqConstant;
import com.example.webdemo.common.mq.DemoOneEventListener;
import com.example.webdemo.common.mq.DemoTwoEventListener;
import com.example.webdemo.common.mq.MessageListener;
import com.example.webdemo.common.mq.RocketMqSpringPushConsumer;

/**
 * @author admin
 * @date 2021/1/24
 */
@Configuration
@EnableConfigurationProperties(MqProperties.class)
public class CommonConsumerConfig {
    @Autowired
    private MqProperties mqProperties;
    @Autowired
    private DemoOneEventListener demoOneEventListener;
    @Autowired
    private DemoTwoEventListener demoTwoEventListener;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public RocketMqSpringPushConsumer commonConsumer() {
        RocketMqSpringPushConsumer consumer = new RocketMqSpringPushConsumer();
        consumer.setNamesrvAddr(mqProperties.getNamesrvAddr());
        consumer.setConsumerGroup("common_consumer");

        Map<String, String> subscription = new HashMap<>();
        subscription.put(MqConstant.TOPIC_DEMO1, MqConstant.TAG_DEMO1);
        subscription.put(MqConstant.TOPIC_DEMO2, MqConstant.TAG_DEMO2);
        consumer.setSubscription(subscription);

        Map<String, MessageListener> map = new HashMap<>();
        map.put(MqConstant.TOPIC_DEMO1 + "|" + MqConstant.TAG_DEMO1, demoOneEventListener);
        map.put(MqConstant.TOPIC_DEMO2 + "|" + MqConstant.TAG_DEMO2, demoTwoEventListener);
        consumer.setMessageListenerMap(map);
        return consumer;
    }
}
