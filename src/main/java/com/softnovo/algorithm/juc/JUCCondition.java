package com.softnovo.algorithm.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JUCCondition {
    private List<String> list = new ArrayList<>();
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void put(String elem) {
        lock.lock(); // 1）加锁
        try {
            list.add(count, elem);
            count++; // 2）更新状态变量
            condition.signal(); // 3）通知
        } finally {
            lock.unlock(); // 4) 解锁
        }
    }

    public String get() {
        lock.lock(); // 1）加锁
        try {
            while (count <= 0) { // 2）检查状态变量是否满足条件
                try {
                    condition.await(); // 3）等待并释放锁；4）被唤醒之后重新竞争获取锁
                } catch (InterruptedException e) {
                    return null;
                }
            }
            // 以下为业务逻辑
            count--;
            return list.get(count);
        } finally {
            lock.unlock(); // 5）解锁
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JUCCondition jucCondition = new JUCCondition();
        new Thread(() -> {
            String s = jucCondition.get();
            System.out.println(s);
        }).start();
        Thread.sleep(1000);
        new Thread(() -> jucCondition.put("aaau")).start();
    }
}
