package com.softnovo.algorithm.priorityqueue;

import java.util.Arrays;

/**
 * 存储从0开始.
 * @param <E>
 * 索引 0 开始
 *   节点 i 的父节点为 floor((i-1)/2)，当 i>0 时;
 *   节点 i 的左子节点为 2i+1，右子节点为 2i+2，当然它们得 < size;
 * 索引 1 开始
 *   节点 i 的父节点为 floor(i/2)，当 i > 1 时;
 *   节点 i 的左子节点为 2i，右子节点为 2i+1，同样得 < size;
 */
public class PriorityQueueHeap0<E extends Priority> implements Queue<E> {
    private final E[] array;
    int size;

    public PriorityQueueHeap0(int capacity) {
        this.array = (E[]) new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            throw new RuntimeException("too more");
        }
        // 插入都是放到屁股.
        int child = size++;
        int parent = getParent(child);
        while (child > 0 && value.priority() > array[parent].priority()) {
            array[child] = array[parent];
            child = parent;
            parent = getParent(child);
        }
        array[child] = value;
        return true;
    }

    private static int getParent(int child) {
        return (child - 1) >> 1;
    }

    /**
     * 交换 0 与 size 的位置. 下沉.
     * @return
     */
    @Override
    public E poll() {
        if (isEmpty()) {
            throw new RuntimeException("no elements");
        }
        swap(0, --size);
        E value = array[size];
        array[size] = null;
        down(0);

        return value;
    }

    private void down(int parent) {
        int max = parent;
        int left = (parent << 1) + 1;
        int right = left + 1;
        if (left < size && array[max].priority() < array[left].priority()) {
            max = left;
        }

        if (right < size && array[max].priority() < array[right].priority()) {
            max = right;
        }

        if (max != parent) {
            swap(max, parent);
            down(max);// parent 已经调换下来了.
        }
    }

    private void swap(int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("no elements");
        }
        return this.array[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    public static void main(String[] args) {
        PriorityQueueHeap0<Entry<String>> priorityQueueHeap0 = new PriorityQueueHeap0(6);
        System.out.println(priorityQueueHeap0.isFull());
        System.out.println(priorityQueueHeap0.isEmpty());
        System.out.println(priorityQueueHeap0);

        Entry<String> a = new Entry<>("a", 1);
        Entry<String> b = new Entry<>("b", 20);
        Entry<String> c = new Entry<>("c", 3);
        Entry<String> aa = new Entry<>("aa", 1000);
        Entry<String> bb = new Entry<>("bb", 21);
        Entry<String> cc = new Entry<>("cc", 30);

        priorityQueueHeap0.offer(a);
        priorityQueueHeap0.offer(b);
        priorityQueueHeap0.offer(c);
        priorityQueueHeap0.offer(aa);
        priorityQueueHeap0.offer(bb);
        priorityQueueHeap0.offer(cc);
        System.out.println(priorityQueueHeap0);

        System.out.println(priorityQueueHeap0.peek());
        System.out.println(priorityQueueHeap0.poll());
        System.out.println(priorityQueueHeap0);
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
