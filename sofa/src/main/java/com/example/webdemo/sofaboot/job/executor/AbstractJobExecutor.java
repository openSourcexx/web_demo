package com.example.webdemo.sofaboot.job.executor;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

import com.example.sofaboot.job.ClientCallbackResult;
import com.example.sofaboot.job.ClientCommonResult;
import com.example.sofaboot.job.handler.IJobHandler;
import com.example.sofaboot.job.handler.JobHandlerFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * job执行器基类
 *
 * @author 唐安全
 * @date 2020/09/22
 */
@Slf4j
public abstract class AbstractJobExecutor<R extends com.example.sofaboot.job.executor.JobExecuteRequest, C extends com.example.sofaboot.job.executor.JobExecuteContext, H extends IJobHandler>
    implements com.example.sofaboot.job.executor.IJobExecutor<R> {
    protected ThreadPoolExecutor jobThreadPool;

    protected JobHandlerFactory jobHandlerFactory;

    /**
     * 检测handler类型
     *
     * @return
     */
    protected abstract Class getHandlerType();

    @Override
    public boolean execute(final R request) {
        final String handlerName = request.getHandler();

        final IJobHandler handler = jobHandlerFactory.getJobHandler(handlerName);
        ThreadPoolExecutor threadPool = handler.getThreadPool();
        if (threadPool == null) {
            threadPool = jobThreadPool;
        }
        threadPool.execute(() -> {
            doExecute(request, (H) handler, request.getServerIp() + ":" + request.getServerPort());
        });
        return false;
    }

    /**
     * 执行任务
     *
     * @param request
     * @param handler
     * @param host
     */
    protected void doExecute(R request, H handler, String host) {
        try {
            long beginTime = System.currentTimeMillis();

            C context = buildContext(request);
            ClientCommonResult result = handler(handler, context);
            if (request == null) {
                throw new RuntimeException("result is null");
            }

            if (result.isSuccess()) {
                callbackComplete(request, result, ClientCallbackResult.buildSuccessResult(false));
            } else {
                log.error("failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回调完成
     *
     * @param request
     * @param bizResult
     * @param callbackResult
     */
    protected abstract void callbackComplete(R request, ClientCommonResult bizResult,
        ClientCallbackResult callbackResult);

    /**
     * 执行任务handler
     *
     * @param handler
     * @param context
     * @return
     */
    protected abstract ClientCommonResult handler(H handler, C context) throws Exception;

    /**
     * 构建执行上下文
     *
     * @param request
     * @return
     * @throws IOException
     */
    protected abstract C buildContext(R request) throws IOException;
}
