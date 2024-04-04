package com.softnovo.algorithm.deque;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ArrayDeque<E> implements Iterable<E> {
    private final E[] array;
    private int head;
    private int tail;
    private int size;

    public ArrayDeque(int capacity) {
        this.array = (E[]) new Object[capacity];
    }

    public boolean offerFirst(E value) {
        checkFull();
        head = dec(head, array.length);
        array[head] = value;
        size++;
        return true;
    }

    public boolean offerLast(E value) {
        checkFull();
        array[tail] = value;
        tail = inc(tail, array.length);
        size++;
        return true;
    }
    public E pollFirst() {
        checkEmpty();
        E value = array[head];
        array[head] = null;
        head = inc(head, array.length);
        size--;
        return value;
    }
    public E pollLast() {
        checkEmpty();
        tail = dec(tail, array.length);
        E value = array[tail];
        array[tail] = null;
        size--;
        return value;
    }
    public E peekFirst() {
        checkEmpty();
        return array[head];
    }

    public E peekLast() {
        checkEmpty();
        return array[dec(tail, array.length)];
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public boolean isFull() {
        return size == array.length;
    }

    public static void main(String[] args) {
        ArrayDeque arrayDeque = new ArrayDeque(6);
        System.out.println(arrayDeque.isEmpty());
        System.out.println(arrayDeque.isFull());
        System.out.println("----- " + arrayDeque);
        System.out.println(Arrays.toString(arrayDeque.array));

        arrayDeque.offerFirst("a");
        arrayDeque.offerFirst("b");
        arrayDeque.offerFirst("c");
        System.out.println("----- " + arrayDeque);
        System.out.println(Arrays.toString(arrayDeque.array));

        arrayDeque.offerLast("1");
        arrayDeque.offerLast("2");
        arrayDeque.offerLast("3");
        System.out.println("----- " + arrayDeque);
        System.out.println(Arrays.toString(arrayDeque.array));

        System.out.println(arrayDeque.peekFirst());
        System.out.println(arrayDeque.peekLast());

        System.out.println(arrayDeque.pollFirst());
        System.out.println("----- " + arrayDeque);
        System.out.println(Arrays.toString(arrayDeque.array));

        System.out.println(arrayDeque.pollLast());
        System.out.println("----- " + arrayDeque);
        System.out.println(Arrays.toString(arrayDeque.array));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = head;
        if (!isEmpty()) {
            do {
                stringBuilder.append(" -> ").append(array[i]);
                i = inc(i, array.length);
            } while (i != tail);
        }
        return stringBuilder.toString().replaceFirst(" -> ", "") + " (" + size + ")";
    }

    private int inc(int i, int length) {
        if (i + 1 >= length) {
            return 0;
        }
        return i + 1;
    }

    private int dec(int i, int length) {
        if (i - 1 < 0) {
            return length - 1;
        }
        return i - 1;
    }

    private void checkFull() {
        if (isFull()) {
            throw new RuntimeException("too more");
        }
    }

    private void checkEmpty() {
        if (isEmpty()) {
            throw new RuntimeException("no elements");
        }
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
