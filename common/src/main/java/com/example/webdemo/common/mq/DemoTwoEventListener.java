package com.example.webdemo.common.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.webdemo.common.utils.Demo;

/**
 * @author admin
 * @date 2021/1/24
 */
@Component
public class DemoTwoEventListener extends AbstractEventConsumer<Demo> {
    public static final Logger logger = LoggerFactory.getLogger(DemoTwoEventListener.class);

    @Override
    public void doBusiness(Demo demo) {
        logger.info("测试消费者2开始消费");
    }
}
