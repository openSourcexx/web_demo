package com.example.webdemo.service.impl.compensate;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.webdemo.service.biz.compensate.Compensate;
import com.example.webdemo.service.biz.compensate.CompensateCore;
import com.example.webdemo.service.biz.compensate.ICompensateService;

/**
 * 补偿实现类
 *
 * @author admin
 * @since 2.1.0 2020/6/2 14:12
 */
@Service
public class CompensateCoreImpl implements CompensateCore {
    private static final Logger log = LoggerFactory.getLogger(CompensateCoreImpl.class);
    private static final ConcurrentHashMap<String, ICompensateService> COMPENSATE_MAP
        = new ConcurrentHashMap<String, ICompensateService>();

    @Override
    public boolean doCompensate(Compensate request) {
        log.info("补偿异步任务发起,请求参数为:{}", request.getParam());
        log.info("开始发起补偿,开始时间:{},流水号:{}", new Date(), request.getSerialNo());
        ICompensateService compensateService = COMPENSATE_MAP.get(request.getType());
        // 执行异步任务
        if (compensateService != null) {
            compensateService.doCompensate(request);
            return true;
        }
        return true;
    }

    @Override
    public void registry(ICompensateService compensateService) {
        COMPENSATE_MAP.put(compensateService.getBizType(), compensateService);
    }
}
