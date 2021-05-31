package com.example.webdemo.common.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import lombok.extern.slf4j.Slf4j;

/**
 * 手撕线程池
 *
 * @author taqo
 * @date 2021/5/31
 */
@Slf4j
public class MyThreadPool {
    // 任务的阻塞队列
    BlockingQueue<Runnable> taskQueue;

    // 线程列表
    List<MyThread> threads;

    public MyThreadPool(BlockingQueue<Runnable> taskQueue, int threadSize) {
        this.taskQueue = taskQueue;
        this.threads = new ArrayList<>(threadSize);

        // 初始化线程池
        for (int i = 0; i < threadSize; i++) {
            MyThread thread = new MyThread("my-thread-pool-" + i);
            thread.start();
            threads.add(thread);
        }
    }

    public void execute(Runnable task) throws InterruptedException {
        taskQueue.put(task);
    }

    class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                Runnable task = null;
                try {
                    task = taskQueue.take();
                } catch (InterruptedException e) {
                    log.error("异常...");
                }
                task.run();
            }
        }
    }
}
