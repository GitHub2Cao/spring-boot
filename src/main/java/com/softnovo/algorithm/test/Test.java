package com.softnovo.algorithm.test;

public class Test {
    public static void main(String[] args) {
        // (i - 1) / 2
        // size / 2 - 1
        for (int i = 1; i <= 100; i++) {
            System.out.println(((i - 1) / 2 == ((i + 1) / 2 - 1)));
        }
    }
}
