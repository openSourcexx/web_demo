package com.example.webdemo.sofaboot.job;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.stereotype.Component;

import com.alipay.sofa.common.profile.enumeration.internal.EnumConstant;
import com.example.sofaboot.job.executor.JobExecuteContext;
import com.example.sofaboot.job.handler.ISimpleJobHandle;

/**
 * @author 唐安全
 * @date 2020/09/17
 */
@Component
public class MyJob2 implements ISimpleJobHandle {

    @Override
    public String getName() {
        return EnumConstant.FLAG_SET_INNER_CLASS_NAME;
    }

    @Override
    public ThreadPoolExecutor getThreadPool() {
        return null;
    }

    @Override
    public com.example.sofaboot.job.ClientCommonResult handle(JobExecuteContext context) throws Exception {
        return com.example.sofaboot.job.ClientCommonResult.buildSuccessResult();
    }
}
