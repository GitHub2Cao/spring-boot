package com.softnovo.algorithm.juc.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CancelThreadExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(new TimeConsumingTask());

        Thread.sleep(2000);

        // 假设在某些条件下你决定取消任务
        boolean cancelTask = true; // 这个条件根据你的实际情况来定
        if (cancelTask) {
            boolean mayInterruptIfRunning = true; // 表示是否应该中断正在运行的任务
            boolean cancelled = future.cancel(mayInterruptIfRunning);
            if (cancelled) {
                System.out.println("任务已取消");
            } else {
                System.out.println("任务取消失败");
            }
        }


        Thread.sleep(5000);


        // 关闭ExecutorService
        executorService.shutdown();
    }

    static class TimeConsumingTask implements Runnable {
        @Override
        public void run() {
            // 这里是耗时的任务
            for (int i = 0; i < 10000000; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 模拟耗时操作
                Math.random();

                System.out.println("还在---------  " + Thread.currentThread().isInterrupted());
                // 检查是否应该被取消
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("自己检测到 --- 任务被取消");
                    return;
                }
            }
            System.out.println("任务完成");
        }
    }
}
