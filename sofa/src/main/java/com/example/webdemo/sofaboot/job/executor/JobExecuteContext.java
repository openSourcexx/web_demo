package com.example.webdemo.sofaboot.job.executor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

import com.google.common.collect.Maps;

import lombok.Data;

/**
 * 任务执行上下文
 *
 * @author 唐安全
 * @date 2020/09/22
 */
@Data
public class JobExecuteContext implements Serializable {
    /**
     * job的数据库id
     */
    private int jobItemId;
    /**
     * 请求id，每次触发都会生成一个uuid
     */
    private String requestId;
    /**
     * 执行id
     */
    private String executeId;

    /**
     * job类型
     */
    private transient String type;
    /**
     * 调用方式
     */
    private transient String invokeType;
    /**
     * 分片总数
     */
    private int shardingCount;
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
     * tracerId
     */
    private String tracerId;
    /**
     * tracer 上下文
     */
    private String sofaTracerSpanContext;
    /**
     * 后续执行器列表
     */
    private transient LinkedList<String> nextHandlers;

    private transient String serverIp;

    private transient int serverPort;
    /**
     * 触发时间
     */
    private transient Date gmtTrigger;

    /**
     * 执行时间
     */
    private transient Date gmtExecute;
    /**
     * 扩展属性
     */
    private Properties extentionProperties = new Properties();
    /**
     * 临时参数集合，可以放一些暂时需要用到的参数，不会合并到任务的上下文当中，任务执行结束后就会销毁
     */
    private Map<String, Object> tempMap = Maps.newHashMap();

    private boolean dryRun;

    public JobExecuteContext(com.example.sofaboot.job.executor.JobExecuteRequest request) {
        this.jobItemId = request.getJobItemId();
        this.requestId = request.getRequestId();
        this.executeId = request.getExecuteId();
        this.type = request.getType();
        this.invokeType = request.getInvokeType();
        this.shardingCount = request.getShardingCount();
        this.sharding = request.getSharding();
        this.activitySharding = request.getActivitySharding();
        this.handler = request.getHandler();
        this.nextHandlers = new LinkedList<String>(request.getHandlers());
        this.serverIp = request.getServerIp();
        this.serverPort = request.getServerPort();
        this.gmtTrigger = request.getGmtTrigger();
        this.gmtExecute = request.getGmtExecute();
        this.tracerId = request.getTracerId();
        this.sofaTracerSpanContext = request.getSofaTracerSpanContext();
        this.extentionProperties.putAll(request.getExtentionProperties());
        Object obj = extentionProperties.get("customParam");
        if (obj == null) {
            extentionProperties.put("customParam", new HashMap<String, Object>());
        }
    }
}

