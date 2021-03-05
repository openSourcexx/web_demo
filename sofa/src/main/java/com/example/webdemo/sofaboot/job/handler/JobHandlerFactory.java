package com.example.webdemo.sofaboot.job.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * job处理器工厂
 *
 * @author 唐安全
 * @date 2020/09/17
 */
public class JobHandlerFactory {
    /**
     * 处理器集合
     * key:handlerName
     */
    private final Map<String, com.example.sofaboot.job.handler.IJobHandler> MAP = new HashMap<>();

    /**
     * 注册处理器
     *
     * @param handler
     */
    public void register(com.example.sofaboot.job.handler.IJobHandler handler) {
        String handlerName = handler.getName();
        MAP.put(handlerName, handler);
    }

    /**
     * 获取处理器
     *
     * @param name
     * @return
     */
    public com.example.sofaboot.job.handler.IJobHandler getJobHandler(String name) {
        if (MAP.containsKey(name)) {
            return MAP.get(name);
        } else {
            throw new RuntimeException("no suitable job handler for " + name);
        }
    }
}
