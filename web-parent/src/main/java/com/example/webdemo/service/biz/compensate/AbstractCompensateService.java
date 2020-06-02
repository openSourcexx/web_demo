package com.example.webdemo.service.biz.compensate;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 补偿服务抽象类
 *
 * @author admin
 * @since 2.1.0 2020/6/2 14:02
 */
public abstract class AbstractCompensateService implements ICompensateService, InitializingBean {
    @Autowired
    private CompensateCore compensateCore;

    @Override
    public void afterPropertiesSet() throws Exception {
        compensateCore.registry(this);
    }
}
