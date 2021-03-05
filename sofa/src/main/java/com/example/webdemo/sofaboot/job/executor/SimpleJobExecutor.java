package com.example.webdemo.sofaboot.job.executor;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang3.StringUtils;

import com.example.sofaboot.job.ClientCallbackResult;
import com.example.sofaboot.job.ClientCommonResult;
import com.example.sofaboot.job.client.Client;
import com.example.sofaboot.job.handler.ISimpleJobHandle;
import com.example.sofaboot.job.handler.JobHandlerFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 简单任务执行器
 *
 * @author 唐安全
 * @date 2020/09/22
 */
@Slf4j
public class SimpleJobExecutor extends
    AbstractJobExecutor<com.example.sofaboot.job.executor.JobExecuteRequest, com.example.sofaboot.job.executor.JobExecuteContext, ISimpleJobHandle> {
    public SimpleJobExecutor(Client client, JobHandlerFactory jobHandlerFactory, ThreadPoolExecutor jobThreadPool) {

    }

    @Override
    protected Class getHandlerType() {
        return ISimpleJobHandle.class;
    }

    @Override
    protected void callbackComplete(com.example.sofaboot.job.executor.JobExecuteRequest request,
        ClientCommonResult bizResult, ClientCallbackResult callbackResult) {
        if (!bizResult.isSuccess()) {
            if (!callbackResult.isPaused()) {
                // 如果配置了多个handler，执行下一个handler
                String nextHandlerName = request.fetchNextHandler();
                if (StringUtils.isNotBlank(nextHandlerName)) {
                    request.setHandler(nextHandlerName);
                    execute(request);
                }
            } else {
                log.error("[SimpleJobExecutor] trigger of job {} is paused, requestId={}", request.getJobItemId(),
                    request.getRequestId());
            }
        }
    }

    @Override
    protected ClientCommonResult handler(ISimpleJobHandle handler,
        com.example.sofaboot.job.executor.JobExecuteContext context) throws Exception {
        return handler.handle(context);
    }

    @Override
    protected com.example.sofaboot.job.executor.JobExecuteContext buildContext(
        com.example.sofaboot.job.executor.JobExecuteRequest request) throws IOException {
        return new com.example.sofaboot.job.executor.JobExecuteContext(request);
    }
}
