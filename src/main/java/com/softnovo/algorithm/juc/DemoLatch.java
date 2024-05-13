package com.softnovo.algorithm.juc;

import java.util.concurrent.CountDownLatch;

public class DemoLatch {
    private static final CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new RunnableForLatch()).start();
        new Thread(new RunnableForLatch()).start();
        latch.await();
        System.out.println("main finished, 所有先前事件做完");
    }

    static class RunnableForLatch implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("do something before latch");
            //...do something...
            latch.countDown();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("do something after latch");
            //...do other thing...
        }
    }
}
