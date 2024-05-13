package com.softnovo.algorithm.juc;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
    private StampedLock slock = new StampedLock();
    private List<String> list = new LinkedList<>();

    public void add(int idx, String elem) {
        long stamp = slock.writeLock(); //加写锁
        try {
            list.add(idx, elem);
        } finally {
            slock.unlockWrite(stamp); //释放写锁
        }
    }

    public String get(int idx) {
        long stamp = slock.tryOptimisticRead(); //加乐观读锁
        String res = list.get(idx);
        if (slock.validate(stamp)) { //没写操作干扰
            return res;
        }

        // 有写操作干扰，重新使用读锁，重新执行读操作
        stamp = slock.readLock(); //加读锁
        try {
            return list.get(idx);
        } finally {
            slock.unlockRead(stamp); //释放读锁
        }
    }
}
