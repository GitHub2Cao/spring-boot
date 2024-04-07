package com.softnovo.algorithm.heap;

import com.softnovo.algorithm.priorityqueue.Entry;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        Entry[] array = new Entry[] {
                new Entry("f", 6),
                new Entry("c", 3),
                new Entry("a", 1),
                new Entry("b", 2),
                new Entry("d", 4),
                new Entry("e", 5)};
        System.out.println(Arrays.toString(array));
        MaxHeap<Entry<String>> maxHeap = new MaxHeap<>(array);
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = maxHeap.poll();
        }
        System.out.println(Arrays.toString(array));
    }

//    private static void swap(int i, int j) {
//        E t = array[i];
//        array[i] = array[j];
//        array[j] = t;
//    }
}
