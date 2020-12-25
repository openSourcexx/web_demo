package com.example.webdemo.xxljob.core.schedule;

import com.example.webdemo.xxljob.core.biz.ExecutorBiz;
import com.example.webdemo.xxljob.core.execute.ExecutorBizClient;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tangaq
 * @date 2020/12/23
 */
public class XxlJobScheduler {
    private static ConcurrentHashMap<String, ExecutorBiz> executorBizRepository = new ConcurrentHashMap<>();

    public static ExecutorBiz getExecutorBiz(String address) {
        if (StringUtils.isBlank(address)) {
            return null;
        }

        address = address.trim();
        ExecutorBiz executorBiz = executorBizRepository.get(address);
        if (executorBiz != null) {
            return executorBiz;
        }
        executorBiz = new ExecutorBizClient(address);
        executorBizRepository.putIfAbsent(address, executorBiz);
        return executorBiz;
    }
}
