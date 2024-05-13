package com.softnovo.algorithm.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockTryLock {
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void f() {
        for(;;) {
            lock1.lock();
            try {
                boolean locked = lock2.tryLock();
                if (locked) { //两个锁都获取了
                    try {
                        //...业务逻辑...
                        return;
                    } finally {
                        lock2.unlock();
                    }
                } // else 没有获取lock2锁，执行finally，释放lock1锁
            } finally {
                lock1.unlock();
            }
        }
    }

    public void g() {
        for(;;) {
            lock2.lock();
            try {
                boolean locked = lock1.tryLock();
                if (locked) { //两个锁都获取了
                    try {
                        //...业务逻辑...
                        return;
                    } finally {
                        lock1.unlock();
                    }
                } // else 没有获取lock1锁，执行finally，释放lock2锁
            } finally {
                lock2.unlock();
            }
        }
    }
}
