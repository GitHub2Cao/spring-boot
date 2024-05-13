package com.softnovo.algorithm.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class SelfSemaphore {
    private final AtomicInteger atomicInteger;

    public SelfSemaphore(int permits) {
        atomicInteger = new AtomicInteger(permits);
    }

    public void apiX() {
        int newPermits = atomicInteger.decrementAndGet();
        if (newPermits < 0) {
            atomicInteger.incrementAndGet();
            return; // 拒绝执行业务逻辑，直接返回
        }
        try {
            // do something
        } finally {
            atomicInteger.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        SelfSemaphore selfSemaphore = new SelfSemaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                //int i1 = selfSemaphore.apiX();
                //System.out.println(i1);
            }).start();
        }
    }
}
