package com.example.webdemo.common.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

import com.example.webdemo.common.utils.ThreadPoolUtils;

/**
 * @author taqo
 * @date 2021/5/7
 */
public class CountDownLatchDemo implements Runnable {
    private static CountDownLatch countDownLatch = new CountDownLatch(4);

    public static void main(String[] args) {
        ThreadPoolExecutor pool = ThreadPoolUtils.newFixThreadPool(6);
        for (int i = 0; i < 5; i++) {
            pool.execute(new CountDownLatchDemo());
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        System.out.println(Thread.currentThread()
            .getName() + "执行完");
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread()
            .getName() + "执行");
        countDownLatch.countDown();
    }
}
