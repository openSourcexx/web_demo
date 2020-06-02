package com.example.webdemo.service.biz.compensate;

/**
 * 补偿核心类
 *
 * @author admin
 * @since 2.1.0 2020/6/2 14:00
 */
public interface CompensateCore {
    /**
     * 执行业务
     *
     * @param compensate
     * @return
     */
    boolean doCompensate(Compensate compensate);

    /**
     * 注册服务类
     *
     * @param compensateService
     */
    void registry(ICompensateService compensateService);
}
