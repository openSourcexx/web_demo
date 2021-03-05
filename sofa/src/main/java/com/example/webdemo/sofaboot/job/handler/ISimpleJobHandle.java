package com.example.webdemo.sofaboot.job.handler;

import com.example.sofaboot.job.ClientCommonResult;
import com.example.sofaboot.job.executor.JobExecuteContext;

/**
 * @author 唐安全
 * @date 2020/09/17
 */
public interface ISimpleJobHandle extends com.example.sofaboot.job.handler.IJobHandler {
    /**
     * 处理请求
     *
     * @param context
     * @return
     */
    ClientCommonResult handle(JobExecuteContext context) throws Exception;
}
