package com.example.webdemo.sofaboot.job.executor;

import java.util.HashMap;
import java.util.Map;

/**
 * job执行器工厂
 *
 * @author 唐安全
 * @date 2020/09/21
 */
public class JobExecutorFactory {
    /**
     * job执行器集合
     */
    private final Map<String, com.example.sofaboot.job.executor.IJobExecutor> MAP = new HashMap<>();

    /**
     * 注册执行器
     *
     * @param type
     * @param executor
     */
    public void register(String type, com.example.sofaboot.job.executor.IJobExecutor executor) {
        MAP.put(type, executor);
    }

    public com.example.sofaboot.job.executor.IJobExecutor getExecutor(String type) {
        if (MAP.containsKey(type)) {
            return MAP.get(type);
        }
        throw new RuntimeException("no suitable executor for " + type);
    }
}
