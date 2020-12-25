package com.example.webdemo.common.utils;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * 线程池工具类
 *
 * @author tangaq
 * @date 2020/11/3
 */
public class ThreadPoolUtils {

    /**
     * 固定大小线程池，无队列,默认SynchronousQueue[一个不存储元素的阻塞队列]
     *
     * @param coreSize 初始化线程池
     * @return
     */
    public static ThreadPoolExecutor newFixThreadPool(int coreSize) {
        return new ThreadPoolExecutor(coreSize, coreSize, 0, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>());
    }

    /**
     * 固定大小线程池，自定义队列
     *
     * @param coreSize 初始化线程池
     * @param queue    线程池队列
     * @return
     */
    public static ThreadPoolExecutor newFixThreadPool(int coreSize, BlockingQueue<Runnable> queue) {
        return new ThreadPoolExecutor(coreSize, coreSize, 0, TimeUnit.MILLISECONDS, queue);
    }

    /**
     * 缓冲线程池（1分钟无调用销毁），自定义队列、线程池工厂和拒绝策略
     *
     * @param corePoolSize    初始化线程池
     * @param maximumPoolSize 最大线程池
     * @param keepAliveTime   回收时间(ms):60000/分钟
     * @param queue           线程池队列
     * @param threadFactory   线程池工厂
     * @return the thread pool executor
     */
    public static ThreadPoolExecutor newCachedThreadPool(int corePoolSize, int maximumPoolSize, int keepAliveTime,
                                                         BlockingQueue<Runnable> queue, ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, queue,
                threadFactory);
    }

    /**
     * 缓冲线程池（1分钟无调用销毁），自定义队列、线程池工厂和拒绝策略
     *
     * @param corePoolSize    初始化线程池
     * @param maximumPoolSize 最大线程池
     * @param keepAliveTime   回收时间(ms):60000/分钟
     * @param queue           线程池队列
     * @param threadFactory   线程池工厂
     * @param handler         拒绝策略
     * @return the thread pool executor
     */
    public static ThreadPoolExecutor newCachedThreadPool(int corePoolSize, int maximumPoolSize, int keepAliveTime,
                                                         BlockingQueue<Runnable> queue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, queue,
                threadFactory, handler);
    }

    public static ThreadPoolExecutor buildDefaultThreadPool(int corePoolSize, String factoryName) {
        return new ThreadPoolExecutor(0, corePoolSize, 60L, TimeUnit.SECONDS, buildQueue(2000),
                new DefaultThreadFactory(factoryName), (r, executor) -> {
            throw new RuntimeException(factoryName + "ThreadPool is EXHAUSTED!");
        });
    }

    /**
     * 构建队列
     *
     * @param size 队列大小
     * @return 队列
     */
    public static BlockingQueue<Runnable> buildQueue(int size) {
        return buildQueue(size, false);
    }

    /**
     * 构建队列
     *
     * @param size       队列大小
     * @param isPriority 是否优先级队列
     * @return 队列
     */
    private static BlockingQueue<Runnable> buildQueue(int size, boolean isPriority) {
        BlockingQueue<Runnable> queue;
        if (size == 0) {
            // 默认无队列
            queue = new SynchronousQueue<>();
        } else {
            if (isPriority) {
                // 无限队列
                queue = size < 0 ? new PriorityBlockingQueue<>() : new PriorityBlockingQueue<>(size);
            } else {
                // 有限队列
                queue = size < 0 ? new LinkedBlockingQueue<>() : new LinkedBlockingQueue<>(size);
            }
        }
        return queue;
    }

}
