package com.softnovo.algorithm.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // 等待
                    countDownLatch.await();
                    String parter = "【" + Thread.currentThread().getName() + "】";
                    System.out.println(parter + "开始执行……");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        // 等等所有线程动作做完
        Thread.sleep(2000);

        System.out.println("准备做大事");
       // countDownLatch.countDown();
        System.out.println("开始做大事");

    }
}
