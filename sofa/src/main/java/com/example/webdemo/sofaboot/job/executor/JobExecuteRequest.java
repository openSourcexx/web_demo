package com.example.webdemo.sofaboot.job.executor;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;

import lombok.Data;

/**
 * job执行请求
 *
 * @author 唐安全
 * @date 2020/09/21
 */
@Data
public class JobExecuteRequest implements Serializable {

    /**
     * job的数据库id
     */
    @Deprecated
    private int jobId;

    /**
     *
     */
    private int jobItemId;

    /**
     * 请求ID, 每次触发都会随机生成一个UUID
     */
    private String requestId;

    /**
     * 执行ID, 每次执行都会随机生成一个UUID
     */
    private String executeId;

    /**
     * tracerId
     */
    private String tracerId;

    /**
     * tracer 上下文
     */
    private String sofaTracerSpanContext;

    /**
     * job类型
     */
    private String type;

    /**
     * 调用方式
     */
    private String invokeType;

    /**
     * 分片总数
     */
    private int shardingCount = 1;

    /**
     * 分片
     */
    private int sharding;

    /**
     * 拓扑节点实例分片
     */
    private String activitySharding;

    /**
     * 当前执行器
     */
    private String handler;

    /**
     * 后续执行器列表
     */
    private LinkedList<String> handlers;

    /**
     * 对应拓扑节点实例ID
     */
    private int activityInstanceId;

    private String ip;

    /**
     * 触发的server
     */
    private String serverIp;

    private int serverPort;

    /**
     * 触发时间
     */
    private Date gmtTrigger;

    /**
     * 执行时间
     */
    private Date gmtExecute;

    private Properties extentionProperties = new Properties();

    private boolean isRetry;

    /**
     * 获取下一个handler名字
     *
     * @return
     */
    public String fetchNextHandler() {
        return handlers.poll();
    }
}
