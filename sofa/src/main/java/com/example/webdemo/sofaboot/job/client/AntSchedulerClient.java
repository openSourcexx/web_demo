package com.example.webdemo.sofaboot.job.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.alipay.remoting.NamedThreadFactory;
import com.example.sofaboot.job.executor.JobExecutorFactory;
import com.example.sofaboot.job.executor.SimpleJobExecutor;
import com.example.sofaboot.job.handler.IJobHandler;
import com.example.sofaboot.job.handler.JobHandlerFactory;
import com.example.sofaboot.job.process.JobProcessor;
import com.example.sofaboot.job.process.JobProcessorFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * AntScheduler客户端
 *
 * @author 唐安全
 * @date 2020/09/22
 */
@Slf4j
public class AntSchedulerClient {
    /** job处理器列表 */
    private final List<IJobHandler> handlers = new ArrayList<>();
    /** job处理器工厂 */
    private final JobHandlerFactory jobHandlerFactory = new JobHandlerFactory();
    /** job执行器工厂 */
    private final JobExecutorFactory jobExecutorFactory = new JobExecutorFactory();
    /** rpc请求处理器 */
    private final JobProcessorFactory jobProcessorFactory = new JobProcessorFactory();
    private final JobProcessor jobProcessor = new JobProcessor(jobProcessorFactory);
    /** 基本配置参数 */
    private Config config;
    /** 是否已经初始化 */
    private boolean isInited = false;
    private ThreadPoolExecutor jobThreadPool = new ThreadPoolExecutor(50, 300, 1, TimeUnit.HOURS,
        new ArrayBlockingQueue<>(500), new NamedThreadFactory("Antscheduler-default-job"), null);
    /** 长连接客户端 */
    private Client client;

    public AntSchedulerClient(Client client, List<IJobHandler> handlers) {
        this.client = client;
        if (handlers != null && !handlers.isEmpty()) {
            this.handlers.addAll(handlers);
        }
    }

    public AntSchedulerClient(Config config) {
        this.config = config;
    }

    /**
     * 初始化并启动
     */
    public void init() {
        doInit();
        //        doStart();
    }

    public void doInit() {
        log.info("[AntSchedulerClient] client begin init");
        this.initialize();
    }

    /**
     * 初始化
     */
    private void initialize() {
        synchronized (this) {
            if (!isInited) {
                // 装配组件
                assembleComponents();
                isInited = true;
            }
        }
    }

    /**
     * 组装各组件，控制依赖关系
     */
    private void assembleComponents() {
        // 组装任务执行器
        SimpleJobExecutor simpleJobExecutor = new SimpleJobExecutor(client, jobHandlerFactory, jobThreadPool);
        jobExecutorFactory.register("SIMPLE", simpleJobExecutor);
        registerHandlers();
    }

    private void registerHandlers() {
        for (IJobHandler handler : this.handlers) {
            jobHandlerFactory.register(handler);
        }
    }
}
