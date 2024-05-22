package com.softnovo.algorithm.juc.status;

public class ThreadStatus1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(10000); //10s
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // catch 会清除标记，重新设置标记
                    // 也可以直接 return.
                }
            }
        });
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}
