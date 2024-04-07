package com.softnovo.algorithm.heap;

import com.softnovo.algorithm.priorityqueue.Entry;
import com.softnovo.algorithm.priorityqueue.Priority;

import java.util.Arrays;

/**
 * 存储从0开始.
 *
 * 索引 0 开始
 *   节点 i 的父节点为 floor((i - 1) / 2)，当 i > 0 时;
 *   节点 i 的左子节点为 2i + 1，右子节点为 2i + 2，当然它们得 < size;
 * 索引 1 开始
 *   节点 i 的父节点为 floor(i / 2)，当 i > 1 时;
 *   节点 i 的左子节点为 2i，右子节点为 2i + 1，同样得 < size;
 */
public class MaxHeap<E extends Priority> {
    private final E[] array;
    int size;

    /**
     * 一个一个插入
     * @param capacity
     */
    public MaxHeap(int capacity) {
        this.array = (E[]) new Priority[capacity];
    }

    /**
     * 批量倒入.
     * @param array
     */
    public MaxHeap(E[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    /**
     * (i - 1) / 2
     * 1. 找到最后一个非叶子节点;
     * 2. 从后向前，对每个节点执行下潜.
     */
    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    private void down(int parent) {
        int max = parent;
        int left = (parent << 1) + 1;
        int right = left + 1;
        if (left < size && array[left].priority() > array[max].priority()) {
            max = left;
        }
        if (right < size && array[right].priority() > array[max].priority()) {
            max = right;
        }
        if (max != parent) {
            swap(max, parent);
            down(max);
        }
    }

    private void swap(int i, int j) {
        E t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("no elements");
        }
        return array[0];
    }

    public E poll() {
        if (isEmpty()) {
            throw new RuntimeException("no elements");
        }
        swap(0, size - 1);
        E value = array[size - 1];
        array[size - 1] = null;
        size--;
        down(0);
        return value;
    }

    public boolean offer(E offered) {
        if (isFull()) {
            throw new RuntimeException("too more");
        }
        int child = size;
        size++;
        int parent = (child - 1) >> 1;
        while (child > 0 && offered.priority() > array[parent].priority()) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) >> 1;
        }
        array[child] = offered;
        return true;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public static void main(String[] args) {
        MaxHeap<Entry<String>> maxHeap = new MaxHeap<>(new Entry[]{
                new Entry("a", 1),
                new Entry("b", 2),
                new Entry("c", 3),
                new Entry("aa", 11),
                new Entry("bb", 22),
                new Entry("cc", 33)
        });

        System.out.println(maxHeap.peek());
        System.out.println(maxHeap);
        System.out.println(maxHeap.poll());
        System.out.println(maxHeap);
        System.out.println(maxHeap.poll());
        System.out.println(maxHeap);
        System.out.println(maxHeap.poll());
        System.out.println(maxHeap);
        System.out.println(maxHeap.offer(new Entry<>("111", 100)));
        System.out.println(maxHeap);
        System.out.println(maxHeap.poll());
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
