package com.softnovo.algorithm.backtracking;

public class Backtracking {
    public static void main(String[] args) {
        rec(1);
    }

    private static void rec(int n) {
        if (n == 6) {
            return;
        }
        System.out.println("---- " + n);
        rec(n + 1);
        System.out.println("==== " + n);
    }
}
