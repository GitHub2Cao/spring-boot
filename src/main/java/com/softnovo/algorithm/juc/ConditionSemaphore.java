package com.softnovo.algorithm.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionSemaphore {
    private int permits = 10;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void apiX() {
        lock.lock();
        try {
            while (permits <= 0) {
                condition.awaitUninterruptibly();
            }
            permits--;
        } finally {
            lock.unlock();
        }

        try {
            // 执行业务逻辑
        } finally {
            lock.lock();
            permits++;
            condition.signal();
            lock.unlock();
        }
    }
}
