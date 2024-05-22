package com.softnovo.algorithm.recursion;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    private static Map<Integer, Integer> memo = new HashMap<>();

    public static int fib(int n) {
        if (n < 2) {
            return n;
        }

        // 检查memoization表
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int result = fib(n - 1) + fib(n - 2);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(fib(50)); // 运行结果将会被缓存，不会重复计算
    }
}
