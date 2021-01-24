package com.example.webdemo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author admin
 * @date 2021/1/24
 */
@ConfigurationProperties(prefix = "mq")
public class MqProperties {
    private String namesrvAddr;
    private boolean producerEnable = true;
    private boolean consumerEnable = true;

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public boolean isProducerEnable() {
        return producerEnable;
    }

    public void setProducerEnable(boolean producerEnable) {
        this.producerEnable = producerEnable;
    }

    public boolean isConsumerEnable() {
        return consumerEnable;
    }

    public void setConsumerEnable(boolean consumerEnable) {
        this.consumerEnable = consumerEnable;
    }
}
