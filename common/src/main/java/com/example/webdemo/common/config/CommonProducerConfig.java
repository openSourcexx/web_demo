package com.example.webdemo.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.webdemo.common.mq.RocketMqSpringProducer;

/**
 * @author admin
 * @date 2021/1/24
 */
@Configuration
@EnableConfigurationProperties(MqProperties.class)
public class CommonProducerConfig {
    @Autowired
    private MqProperties mqProperties;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public RocketMqSpringProducer commonProducer() {
        RocketMqSpringProducer producer = new RocketMqSpringProducer();
        producer.setNamesrvAddr(mqProperties.getNamesrvAddr());
        producer.setProducerGroupName("common_producer");
        return producer;
    }
}
