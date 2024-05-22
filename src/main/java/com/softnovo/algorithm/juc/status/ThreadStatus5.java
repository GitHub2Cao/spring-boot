package com.softnovo.algorithm.juc.status;

public class ThreadStatus5 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ThreadStatus5.class) {
                    try {
                        Thread.sleep(1000); //1000s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ThreadStatus5.class) {
                    while (true) {
                        int i = 0;
                    }
                }
            }
        }, "t2");
        t2.start();

        System.out.println("a");
        t1.join();
        System.out.println("b");
        t2.join();
        System.out.println("c");
    }
}
