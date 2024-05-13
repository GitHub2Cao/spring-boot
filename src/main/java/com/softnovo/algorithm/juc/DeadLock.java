package com.softnovo.algorithm.juc;

public class DeadLock {
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void f() {
        synchronized (lock1) {
            try {
                System.out.println("get lock1");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock2) {
                System.out.println("lock1 -> lock2");
            }
        }
    }

    public void g() {
        synchronized (lock1) {
            try {
                System.out.println("get lock2");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock2) {
                System.out.println("lock2 -> lock1");
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(() -> deadLock.f()).start();
        new Thread(() -> deadLock.g()).start();
    }
}
