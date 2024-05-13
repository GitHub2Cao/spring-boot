package com.softnovo.algorithm.juc;

public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("t1 finished");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("t2 finished");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
        t2.join();
        t1.join();




        System.out.println("main finished");

    }
}
