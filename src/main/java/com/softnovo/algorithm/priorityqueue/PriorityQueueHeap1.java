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
public class PriorityQueueHeap1<E extends Priority> implements Queue<E> {
    private final E[] array;
    private int size;

    public PriorityQueueHeap1(int capacity) {
        this.array = (E[]) new Priority[capacity + 1];
    }
    @Override
    public boolean offer(E value) {
        int child = (size + 1);
        size++;

        int parent = child >> 1;
        while (parent >= 1 && value.priority() > array[parent].priority()) {
            array[child] = array[parent];
            child = parent;
            parent = child >> 1;
        }
        array[child] = value;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new RuntimeException("no elements");
        }
        swap(1, size);
        E value = array[size];
        array[size] = null;
        size--; // 在下沉之前就需要减去

        int parent = 1;
        int max = parent;
        while (parent <= size) {
            int left = parent << 1;
            int right = left + 1;
            if (left <= size && array[left].priority() > array[max].priority()) {
                max = left;
            }
            if (right <= size && array[right].priority() > array[max].priority()) {
                max = right;
            }
            if (max != parent) {
                swap(max, parent);
                parent = max;
                continue;
            }
            break;
        }

        return value;
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
        return array[1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length - 1;
    }

    public static void main(String[] args) {
        PriorityQueueHeap1<Entry<String>> priorityQueueHeap1 = new PriorityQueueHeap1<>(6);

        System.out.println(priorityQueueHeap1.isEmpty());
        System.out.println(priorityQueueHeap1.isFull());
        System.out.println(priorityQueueHeap1);

        priorityQueueHeap1.offer(new Entry<>("a", 1));
        priorityQueueHeap1.offer(new Entry<>("b", 2));
        priorityQueueHeap1.offer(new Entry<>("c", 3));
        priorityQueueHeap1.offer(new Entry<>("d", 4));
        priorityQueueHeap1.offer(new Entry<>("e", 5));
        priorityQueueHeap1.offer(new Entry<>("f", 6));
        System.out.println(priorityQueueHeap1);
        System.out.println(priorityQueueHeap1.isFull());
        System.out.println(priorityQueueHeap1.isEmpty());
        System.out.println(priorityQueueHeap1.peek());
        System.out.println(priorityQueueHeap1);
        System.out.println(priorityQueueHeap1.poll());
        System.out.println(priorityQueueHeap1);

        System.out.println(priorityQueueHeap1.poll());
        System.out.println(priorityQueueHeap1);


        System.out.println(priorityQueueHeap1.poll());
        System.out.println(priorityQueueHeap1);

        System.out.println(priorityQueueHeap1.poll());
        System.out.println(priorityQueueHeap1);

        System.out.println(priorityQueueHeap1.poll());
        System.out.println(priorityQueueHeap1);

        System.out.println(priorityQueueHeap1.poll());
        System.out.println(priorityQueueHeap1);

        System.out.println(priorityQueueHeap1.poll());
        System.out.println(priorityQueueHeap1);

    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
