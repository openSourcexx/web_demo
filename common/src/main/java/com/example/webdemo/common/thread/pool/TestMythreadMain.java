package com.example.webdemo.common.thread.pool;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author taqo
 * @date 2021/5/31
 */
public class TestMythreadMain {
    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(new LinkedBlockingQueue<>(10), 5);
        try {
            for (int i = 0; i < 20; i++) {
                pool.execute(() -> System.out.println(Thread.currentThread()
                    .getName() + ":pool-test"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
