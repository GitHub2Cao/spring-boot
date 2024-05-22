package com.softnovo.algorithm.juc.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        // 创建与配置
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                5, 10, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(15),
                new ThreadFactory() {
                    private final AtomicInteger idx = new AtomicInteger(1);

                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "pool-" + idx.getAndIncrement());
                    }
                }, new ThreadPoolExecutor.DiscardPolicy());
//        pool.prestartCoreThread();
//        pool.prestartCoreThread();

        pool.execute(new Runnable() {
            @Override
            public void run() {
//                while (true) {
//                    int i = 0;
//                }
                //System.out.println("xiao zheng ge!");
            }
        });

        Thread.sleep(5000000);




        // 关闭
        pool.shutdown(); //发起关闭请求
        boolean terminated = false;
        while (!terminated) {
            // 返回值为false表示超时，返回值为true表示线程池真正关闭。
            terminated = pool.awaitTermination(100, TimeUnit.SECONDS);
        }
        System.out.println("pool is shutdown.");
    }
}
