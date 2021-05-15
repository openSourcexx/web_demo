package com.example.webdemo.common.thread.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

import com.example.webdemo.common.utils.ThreadPoolUtils;

/**
 * 3kw/7m
 * for循环30次db,丢到线程池,countdown一次
 * 100w,每处理3w就countdown一次,3w按照每页1k,拆分成30个分页,写一个task
 * 每读一个分页数据,塞到task,然后把task丢到线程池,30个分页丢进去后就countdown一次,
 * 等3w条处理完继续处理
 *
 * @author taqo
 * @date 2021/5/7
 */
public class MyTest {
    private static int c;

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(30);
        ThreadPoolExecutor pool = ThreadPoolUtils.newFixThreadPool(30);
        int total = 1000000;
        while (true) {
            // 循环30次获取db数据
            for (int i = 0; i < 30; i++) {
                // 每次读取1k
                List<Integer> list = new ArrayList<>(1000);
                for (int j = 1; j <= 1000; j++) {
                    list.add(j);
                }
                System.out.println("db读取1k数据加入task");
                Task task = new Task(list, countDownLatch);
                c += list.size();
                pool.execute(task);
            }
            if (c >= total) {
                break;
            }
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        System.out.println(Thread.currentThread()
            .getName() + "执行完:" + c);
        long end = System.currentTimeMillis();
        System.out.println("cost:" + (end - begin));
    }
}

class Task implements Runnable {
    private List<Integer> data;
    CountDownLatch countDownLatch;

    public Task(List<Integer> data, CountDownLatch countDownLatch) {
        this.data = data;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread()
                .getName() + "处理数据:" + data.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (this.countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }
}
