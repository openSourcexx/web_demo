package com.example.webdemo.sofaboot.job.process;

import java.util.concurrent.ThreadPoolExecutor;

import com.alipay.remoting.BizContext;
import com.example.sofaboot.job.ClientCommonResult;
import com.example.sofaboot.job.executor.IJobExecutor;
import com.example.sofaboot.job.executor.JobExecuteRequest;
import com.example.sofaboot.job.executor.JobExecutorFactory;

/**
 * job请求处理器
 *
 * @author 唐安全
 * @date 2020/09/21
 */
public abstract class AbstractJobProcessor<T extends JobExecuteRequest>
    extends com.example.sofaboot.job.process.SyncUserProcessor<T> {
    protected ThreadPoolExecutor tpcThreadPool;

    protected JobExecutorFactory jobExecutorFactory;

    @Override
    public ClientCommonResult handleRequest(BizContext bizCtx, T request) throws Exception {
        request.setServerIp(bizCtx.getRemoteHost());
        request.setServerPort(bizCtx.getRemotePort());
        request.setHandler(request.fetchNextHandler());
        IJobExecutor executor = jobExecutorFactory.getExecutor(getExecutorType());
        boolean isEnd = executor.execute(request);
        return ClientCommonResult.buildSuccessResult(isEnd);
    }

    /**
     * 获取执行器类型
     *
     * @return
     */
    protected abstract String getExecutorType();

    private String getName() {
        return this.getClass()
            .getSimpleName();
    }

    public ThreadPoolExecutor getTpcThreadPool() {
        return tpcThreadPool;
    }

    public void setTpcThreadPool(ThreadPoolExecutor tpcThreadPool) {
        this.tpcThreadPool = tpcThreadPool;
    }
}
