package com.softnovo.algorithm.recursion;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RecursionTest {
    static Map<Integer, Integer> cacheMap = new HashMap<>();
    public static void main(String[] args) {
//        String str = "123456789";
//
//        reversePrint(str.toCharArray(), 0);
//        int[] arr = new int[]{2, 3, 1, 0, 5, 7, 6, 9, 8};
//        System.out.println(Arrays.toString(arr));
//        insertSortRecursion(arr);
//        System.out.println(Arrays.toString(arr));
//
        long start = System.currentTimeMillis();
        cacheMap.putIfAbsent(0, 1);
        cacheMap.putIfAbsent(1, 1);
        fibonacci(cacheMap, 45);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        // -----------------------
        start = System.currentTimeMillis();
        int n = 6;
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;
        f(cache, n);
//        System.out.println("===== " + Arrays.toString(cache));
        end = System.currentTimeMillis();
        System.out.println(end - start);



//        start = System.currentTimeMillis();
//        fibonacciArr = new int[45];
//        for (int i = 0; i < 45; i++) {
//            fibonacciArr[i] = fibonacci(new HashMap<>(), i);
//        }
//        System.out.println(Arrays.toString(fibonacciArr));
//        end = System.currentTimeMillis();
//        System.out.println(end - start);

    }





    public static int fibonacci(int n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int fibonacci(Map<Integer, Integer> cache111, int n) {
        if (cacheMap.containsKey(n)) {
            return cacheMap.get(n);
        }

        int value = fibonacci(n - 1) + fibonacci(n - 2);
        cacheMap.putIfAbsent(n, value);

        return value;
    }

    public static int f(int[] cache, int n) {
        if (cache[n] != -1) {
            return cache[n];
        }
        cache[n] = f(cache, n - 1) + f(cache, n - 2);
        return cache[n];
    }

    public static int f(int n) {
        if (n == 1) {
            return 1;
        }
        return n * f(n - 1);
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for ( int j = i + 1; j < arr.length - 1; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;

            System.out.println(Arrays.toString(arr));
        }
    }

    public static void bubble(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    public static void bubbleRecursion(int[] arr) {
        bubbleRecursion(arr, 0, arr.length - 1);
    }

    private static void bubbleRecursion(int[] arr, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int j = lo;
        for (int i = lo; i < hi; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
            j = i;
        }
        bubbleRecursion(arr, lo, j);
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j--];
            }
            arr[j + 1] = current;
        }
    }

    public static void insertSortRecursion(int[] arr) {
        insertSortRrecursion(arr, 1);
    }

    private static void insertSortRrecursion(int[] arr, int partition) {
        if (partition == arr.length) {
            return;
        }
        // 未排序的元素值
        int t = arr[partition];

        // 已排序的又边界
        int i = partition - 1;

        while (i >= 0 && arr[i] > t) {
            // 符合条件右移，并且 i 要减少一个.
            arr[i + 1] = arr[i--];
        }

        // 放入属于自己的位置
        arr[i + 1] = t;

        insertSortRrecursion(arr, partition + 1);
    }

    public static void reversePrint(char[] charArr, int index) {
        //str.getBytes(StandardCharsets.UTF_8);
        if (index == charArr.length) {
            return;
        }
        System.out.printf(String.valueOf(charArr[index]));
        reversePrint(charArr, index + 1);
        System.out.printf(String.valueOf(charArr[index]));
    }
}
