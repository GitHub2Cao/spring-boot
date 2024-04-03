package com.softnovo.algorithm.queue;

import org.checkerframework.checker.units.qual.A;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ArrayQueue3<E> implements Iterable<E> {
    private int head = 0;
    private int tail = 0;
    private final E[] array;
    private final int capacity;

    public ArrayQueue3(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("too small");
        }
        if ((capacity & capacity - 1) != 0) {
            throw new IllegalArgumentException("capacity 必须为 2 的幂");
        }

        this.capacity = capacity;
        this.array = (E[]) new Object[this.capacity];
    }

    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail & capacity - 1] = value;
        tail++;
        return true;
    }

    public boolean isEmpty() {
        return tail - head == 0;
    }


    public boolean isFull() {
        return tail - head == capacity;
    }



    public static void main(String[] args) {
        ArrayQueue3<String> arrayQueue3 = new ArrayQueue3<>(4);
        System.out.println(arrayQueue3);
        System.out.println(Arrays.toString(arrayQueue3.array));

        arrayQueue3.offer("a");
        arrayQueue3.offer("b");
        arrayQueue3.offer("c");
        System.out.println(arrayQueue3);
        System.out.println(Arrays.toString(arrayQueue3.array));


    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = head; i != tail; i++, i = i % capacity) {
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
