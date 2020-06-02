package com.example.webdemo.service.biz.compensate;

/**
 * @author admin
 * @since 2.1.0 2020/6/2 13:59
 */
public interface ICompensateService {
    public void doCompensate(Compensate compensate);

    /**
     * 获取补偿业务类型EnumCompensateType
     *
     * @return
     */
    public String getBizType();
}
