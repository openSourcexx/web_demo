package com.example.webdemo.sofaboot.job.executor;

/**
 * job执行器接口
 *
 * @author 唐安全
 * @date 2020/09/21
 */
public interface IJobExecutor<R> {
    /**
     * 执行job
     *
     * @param request
     * @return
     */
    boolean execute(R request);
}
