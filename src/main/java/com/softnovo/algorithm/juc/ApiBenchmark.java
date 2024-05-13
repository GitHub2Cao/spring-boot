package com.softnovo.algorithm.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ApiBenchmark {
    private static int numThread = 20; //并发度为20
    private static int numReqPerThread = 1000; //每个线程请求1000次接口

    private static CountDownLatch latch = new CountDownLatch(numThread);
    private static CyclicBarrier barrier = new CyclicBarrier(numThread, () -> {
        System.out.println("所有线程都已经准备完成");
    });

    public static void main(String[] args) throws InterruptedException {
        // 创建线程
        Thread[] threads = new Thread[numThread];
        TestRunnable[] runnables = new TestRunnable[numThread];
        for (int i = 0; i < numThread; ++i) {
            runnables[i] = new TestRunnable();
            threads[i] = new Thread(runnables[i]);
        }
        // 启动线程
        long startTime = System.nanoTime();
        for (int i = 0; i < numThread; ++i) {
            threads[i].start();
        }
        // 等待测试线程结束

        latch.await();
        long endTime = System.nanoTime();

        // 统计接口性能
        long qps = (numThread * numReqPerThread * 1000) / ((endTime - startTime) / 1000000);
        float avgRespTime = 0.0f;
        for (int i = 0; i < numThread; ++i) {
            for (Long respTime : runnables[i].respTimes) {
                avgRespTime += respTime;
            }
        }
        avgRespTime /= (numThread * numReqPerThread);
    }

    static class TestRunnable implements Runnable {
        public List<Long> respTimes = new ArrayList<>();

        @Override
        public void run() {
            try {
                // 等待所有线程都准备好. 统一开始.
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < numReqPerThread; ++i) {
                long reqStartTime = System.nanoTime();
                // ...调用接口
                long reqEndTime = System.nanoTime();
                respTimes.add(reqEndTime - reqStartTime);
            }
            // 已经测试完成.
            latch.countDown();
        }
    }
}
