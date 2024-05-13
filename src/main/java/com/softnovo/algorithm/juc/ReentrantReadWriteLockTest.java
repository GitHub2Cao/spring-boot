package com.softnovo.algorithm.juc;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {
    private List<String> list = new LinkedList<>();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private Lock rLock = rwLock.readLock(); //读锁
    private Lock wLock = rwLock.writeLock(); //写锁

    public static void main(String[] args) {
        ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
//        test.rLock.lock();
//        test.wLock.lock();
//
//        test.rLock.unlock();
//        test.wLock.unlock();
        test.wLock.lock();
        test.rLock.lock();

        test.wLock.unlock();
        test.rLock.unlock();

    }
}
