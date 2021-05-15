package com.example.webdemo.common.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author taqo
 * @date 2021/5/7
 */
public class Test1 {
    public static void main(String[] args) {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(new Player(begin, end));
            t.start();
        }

        System.out.println("begin");
        begin.countDown();
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}

class Player implements Runnable {

    private CountDownLatch begin;
    private CountDownLatch end;

    public Player(CountDownLatch begin, CountDownLatch end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            begin.await();
            System.out.println(Thread.currentThread()
                .getName() + " arrived !");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
