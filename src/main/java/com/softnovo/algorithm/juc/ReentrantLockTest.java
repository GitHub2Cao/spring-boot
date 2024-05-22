package com.softnovo.algorithm.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                //lock.lockInterruptibly();
                System.out.println("try get lock");
                lock.lock();
                System.out.println("get the lock");
            } catch (Exception e) {
                System.out.println("I am interrupted");
                return;
            } catch (Throwable e) {
                System.out.println("aaaaaaaa");
                e.printStackTrace();
                return;
            }

            try {
                System.out.println("I got lock");
                while (true) {
                    int i = 1;
                }
            } finally {
                lock.unlock();
            }
        }, "t1111").start();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lock.lock();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        lock.unlock();
    }
}
