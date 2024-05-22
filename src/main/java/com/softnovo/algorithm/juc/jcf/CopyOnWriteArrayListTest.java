package com.softnovo.algorithm.juc.jcf;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        List<Integer> cowList = new CopyOnWriteArrayList<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; ++i) {
            cowList.add(i);
        }
        System.out.println(System.currentTimeMillis() - startTime);

        List<Integer> list = new ArrayList<>();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; ++i) {
            list.add(i);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
