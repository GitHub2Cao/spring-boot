package com.softnovo.algorithm.juc;

import java.util.ArrayList;
import java.util.List;

public class ObjectCondition {
    private List<String> list = new ArrayList<>();
    private int count = 0;

    public void put(String elem) {
        synchronized (this) { // 1）加锁
            list.add(count, elem);
            count++; // 2）更新状态变量
            this.notify(); // 3）通知
        } // 4）解锁
    }

    public String get() {
        synchronized (this) { // 1）加锁
            System.out.println("1");
            while (count <= 0) { // 2）检查状态变量是否满足条件
                System.out.println("2");
                try {
                    this.wait(); // 3）等待并释放锁；4）被唤醒之后重新竞争获取锁
                } catch (InterruptedException e) {
                    return null;
                }
            }
            // 以下为业务逻辑
            count--;
            return list.get(count);
        } // 5）解锁
    }

    public static void main(String[] args) throws InterruptedException {
        ObjectCondition objectCondition = new ObjectCondition();
        new Thread(() -> {
            String s = objectCondition.get();
            System.out.println(s);
        }).start();
        Thread.sleep(1000);
        new Thread(() -> objectCondition.put("aaa")).start();
    }
}
