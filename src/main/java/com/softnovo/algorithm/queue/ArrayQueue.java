package com.softnovo.algorithm.queue;


import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ArrayQueue<E> implements Iterable<E> {
    private int head = 0;
    private int tail = 0;
    private final E[] array;
    private final int length;

    public ArrayQueue(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("不合法参数 capacity");
        }
        this.length = capacity + 1;
        this.array = (E[]) new Object[this.length];
    }

    public boolean offer(E value) {
        if (isFull()) {
            throw new RuntimeException("is full");
        }
        array[tail] = value;
        tail = (tail + 1) % length;
        return true;
    }

    public E poll() {
        E value = array[head];
        array[head] = null;
        head = (head + 1) % length;
        return value;
    }

    public E peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("no element");
        }
        return array[head];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        // 需要空一个，要不然与 isEmpty 矛盾.
        return (tail + 1) % length == head;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = head; i % length != tail; i++, i = (i) % length) {
            stringBuffer.append(" -> ").append(array[i]);
        }
        return stringBuffer.toString().replaceFirst(" -> ", "");
    }

    public static void main(String[] args) {
        ArrayQueue<String> arrayQueue = new ArrayQueue<>(4);
        System.out.println("full false = " + arrayQueue.isFull());
        System.out.println("empty true = " + arrayQueue.isEmpty());

        System.out.println(" --- " + arrayQueue);
        System.out.println(Arrays.toString(arrayQueue.array));
        arrayQueue.offer("a");
        System.out.println("empty false = " + arrayQueue.isEmpty());
        arrayQueue.offer("b");
        arrayQueue.offer("c");
        arrayQueue.offer("d");
        System.out.println("full true = " + arrayQueue.isFull());

        System.out.println(" --- " + arrayQueue);
        System.out.println(Arrays.toString(arrayQueue.array));
        System.out.println(arrayQueue.peek());
        System.out.println(arrayQueue.poll());

        System.out.println(" --- " + arrayQueue);
        System.out.println(Arrays.toString(arrayQueue.array));
        System.out.println(arrayQueue.poll());

        System.out.println(" --- " + arrayQueue);
        System.out.println(Arrays.toString(arrayQueue.array));
        arrayQueue.offer("e");
        arrayQueue.offer("f");

        System.out.println(" --- " + arrayQueue);
        System.out.println(Arrays.toString(arrayQueue.array));
        System.out.println(arrayQueue.peek());
        System.out.println(arrayQueue.poll());

        System.out.println(" --- " + arrayQueue);
        System.out.println(Arrays.toString(arrayQueue.array));
        arrayQueue.offer("g");

        System.out.println(" --- " + arrayQueue);
        System.out.println(Arrays.toString(arrayQueue.array));
        System.out.println(arrayQueue.tail - arrayQueue.head);

    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                return value;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }
}
