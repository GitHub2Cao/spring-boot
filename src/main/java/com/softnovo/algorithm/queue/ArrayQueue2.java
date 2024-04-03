package com.softnovo.algorithm.queue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ArrayQueue2<E> implements Iterable<E> {
    private int head = 0;
    private int tail = 0;
    private final E[] array;
    private final int capacity;
    private int size = 0;

    public ArrayQueue2(int capacity) {
        this.capacity = capacity;
        this.array = (E[]) new Object[capacity];
    }

    public boolean offer(E value) {
        if (isFull()) {
            throw new RuntimeException("too more");
        }
        array[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
        return true;
    }

    public E poll() {
        if (isEmpty()) {
            throw new RuntimeException("no element");
        }
        E value = array[head];
        array[head] = null;
        head = (head + 1) % capacity;
        size--;
        return value;
    }

    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("no element");
        }
        return array[head];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }

    public static void main(String[] args) {
        ArrayQueue2 arrayQueue2 = new ArrayQueue2(4);
        System.out.println("empty true = " + arrayQueue2.isEmpty());
        System.out.println("full false = " + arrayQueue2.isFull());
        System.out.println("---- " + arrayQueue2);
        System.out.println(Arrays.toString(arrayQueue2.array));

        arrayQueue2.offer("a");
        arrayQueue2.offer("b");
        arrayQueue2.offer("c");
        arrayQueue2.offer("d");
        System.out.println("---- " + arrayQueue2);
        System.out.println(Arrays.toString(arrayQueue2.array));
        System.out.println("empty false = " + arrayQueue2.isEmpty());
        System.out.println("full true = " + arrayQueue2.isFull());

        System.out.println(arrayQueue2.peek());
        System.out.println(arrayQueue2.poll());
        System.out.println("---- " + arrayQueue2);
        System.out.println(Arrays.toString(arrayQueue2.array));

        arrayQueue2.offer("e");
        System.out.println("---- " + arrayQueue2);
        System.out.println(Arrays.toString(arrayQueue2.array));

        arrayQueue2.poll();
        arrayQueue2.poll();
        arrayQueue2.offer("f");
        System.out.println("---- " + arrayQueue2);
        System.out.println(Arrays.toString(arrayQueue2.array));

        int capacity = 6;
        System.out.println(capacity & capacity - 1);

        capacity = 8;
        System.out.println(capacity & capacity - 1);

        System.out.println(1 << 1);
        System.out.println(1 << 2);
        System.out.println(1 << 3);
        System.out.println(1 << 4);
        System.out.println(1 << 5);

        
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        int tempSize = size;
        for (int i = head; tempSize > 0; tempSize--, i++, i = (i) % capacity) {
            stringBuffer.append(" -> ").append(array[i]);
        }
        return stringBuffer.toString().replaceFirst(" -> ", "");
    }

    @Override
    public Iterator<E> iterator() {
        return null;
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
