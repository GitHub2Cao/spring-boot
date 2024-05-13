package com.softnovo.algorithm.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {
    private static ThreadLocal threadLocal = new ThreadLocal();
    private static ThreadLocal threadLocal2 = new ThreadLocal();
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set(Thread.currentThread().getName());

        Thread first = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("first --> " + threadLocal.get());
            threadLocal.set("first");
            threadLocal2.set("aaaaa");
            threadLocal = new ThreadLocal();

            System.out.println("first --> " + threadLocal.get());

            System.out.println("aaa");

        }, "first");
        first.start();
        Thread.sleep(1000);

        System.out.println("main -- " + threadLocal.get());


        for (int i = 0; i < 10; i++) {
            People people = new People();
            people.say();
        }
    }

    static class People {
        static AtomicInteger counter = new AtomicInteger();
        private static final ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "hello" + counter.getAndIncrement());
        public void say() {
            System.out.println(threadLocal.get());
        }
    }

}
