package com.softnovo.algorithm.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class QueueSemaphore {
    private static final int Q_SIZE = 5;
    private Semaphore semaphore = new Semaphore(Q_SIZE);
    private List<String> list = new ArrayList<>(Q_SIZE);
    private int count = 0;

    public void put(String elem) {
        semaphore.acquireUninterruptibly();
        synchronized (this) {
            list.add(count, elem);
            count++;
        }
    }

    public String get() {
        if (count == 0) return null;
        synchronized (this) {
            if (count == 0) return null; //双重检测
            String ret = list.get(--count);
            semaphore.release();
            return ret;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        QueueSemaphore queueSemaphore = new QueueSemaphore();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {queueSemaphore.put("--- > " + finalI);}).start();
        }

        Thread.sleep(2000);
        System.out.println(queueSemaphore.list);
        queueSemaphore.semaphore.release();

        Thread.sleep(2000);
        queueSemaphore.semaphore.release();
        System.out.println(queueSemaphore.list);


        Thread.sleep(2000);
        queueSemaphore.semaphore.release();
        System.out.println(queueSemaphore.list);

        Thread.sleep(2000);
        queueSemaphore.semaphore.release();
        System.out.println(queueSemaphore.list);

        Thread.sleep(2000);
        queueSemaphore.semaphore.release();
        System.out.println(queueSemaphore.list);

        Thread.sleep(2000);
        queueSemaphore.semaphore.release();
        System.out.println(queueSemaphore.list);

        Thread.sleep(2000);
        queueSemaphore.semaphore.release();
        System.out.println(queueSemaphore.list);



    }
}
