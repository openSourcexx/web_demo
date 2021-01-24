package com.example.webdemo.common.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.webdemo.common.utils.Demo;
import com.example.webdemo.common.utils.GsonUtil;

/**
 * @author admin
 * @date 2021/1/24
 */
@Component
public class DemoOneEventListener extends AbstractEventConsumer<Demo> {
    public static final Logger logger = LoggerFactory.getLogger(DemoOneEventListener.class);

    @Override
    public void doBusiness(Demo demo) {
        logger.info("测试消费者1开始消费");
        logger.info("demo:{}", GsonUtil.obj2Json(demo));
    }
}
