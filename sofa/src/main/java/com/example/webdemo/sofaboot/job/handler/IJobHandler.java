package com.example.webdemo.sofaboot.job.handler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * job处理器
 *
 * @author 唐安全
 * @date 2020/09/17
 */
public interface IJobHandler {
    /**
     * 获取handler名字
     *
     * @return
     */
    String getName();

    /**
     * 可以留空，使用默认执行线程池
     *
     * @return
     */
    ThreadPoolExecutor getThreadPool();
}
