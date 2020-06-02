package com.example.webdemo.service.impl.compensate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.webdemo.common.enums.EnumCompensateType;
import com.example.webdemo.common.utils.GsonUtil;
import com.example.webdemo.service.biz.compensate.AbstractCompensateService;
import com.example.webdemo.service.biz.compensate.Compensate;

/**
 * @author admin
 * @since 2.1.0 2020/6/2 14:37
 */
@Component
public class DemoBCompensate extends AbstractCompensateService {
    private static final Logger log = LoggerFactory.getLogger(DemoBCompensate.class);

    @Override
    public void doCompensate(Compensate compensate) {
        log.info("DemoBCompensate begin,serial:{},param:{}", compensate.getSerialNo(),
            GsonUtil.obj2Json(compensate.getParam()));

    }

    @Override
    public String getBizType() {
        return EnumCompensateType.DEMO_B.getCode();
    }

}
