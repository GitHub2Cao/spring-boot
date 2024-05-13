package com.softnovo.algorithm.juc;

public class SelfLockSemaphore {
    private int permits = 10;

    public void apiX() {
        if (permits <= 0) {
            return;
        }
        synchronized (this) {
            if (permits <= 0) return; //双重检测
            permits--;
        }

        try {
            // 执行业务逻辑
        } finally {
            synchronized (this) {
                permits++;
            }
        }
    }
}
