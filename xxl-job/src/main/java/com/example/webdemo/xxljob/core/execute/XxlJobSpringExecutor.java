package com.example.webdemo.xxljob.core.execute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;

/**
 * 初始化EmbServer
 *
 * @author tangaq
 * @date 2020/12/23
 */
public class XxlJobSpringExecutor extends XxlJobExecutor implements SmartInitializingSingleton, DisposableBean {
    private static final Logger logger = LoggerFactory.getLogger(XxlJobSpringExecutor.class);

    @Override
    public void afterSingletonsInstantiated() {
        try {
            super.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
