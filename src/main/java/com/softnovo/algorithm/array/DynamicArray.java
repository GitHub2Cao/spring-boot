package com.softnovo.algorithm.array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class DynamicArray<T> implements Iterable<T> {
    /**
     * 默认容量大小为8
     */
    private int capacity = 8;
    /**
     * 存储的数组
     */
    private T[] array = (T[]) new Object[]{};
    /**
     * 动态数组大小
     */
    private int size = 0;

    public DynamicArray() {
        this.array = (T[]) new Object[capacity];
    }

    public DynamicArray(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
    }

    public void addLast(T element) {
        grow();
        array[size++] = element;
    }

    private void grow() {
        if (size < capacity) {
            return;
        }

        this.capacity = this.capacity << 1;
        T[] newArray = (T[]) new Object[this.capacity];
        if (size >= 0) System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    public void add(int index, T element) {
        if (index > size) {
            throw new RuntimeException("toooooo");
        }

        grow();

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = element;
        size++;
    }

    public T remove(int index) {
        if (index > size - 1) {
            throw new RuntimeException("index too");
        }

        T element = array[index];

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        array[--size] = null;
        return element;
    }

    public T get(int index) {
        if (index > size - 1) {
            throw new RuntimeException("t00000000");
        }
        return array[index];
    }

    public int size() {
        return this.size;
    }

    public Stream<T> stream() {
        return Stream.of(array);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public T next() {
                return array[i++];
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (int i = 0; i < size; i++) {
            action.accept(array[i]);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public String toString() {
        return "DynamicArray{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    public static void main(String[] args) {
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        dynamicArray.addLast("a");
        dynamicArray.addLast("b");
        dynamicArray.add(2, "c");
        dynamicArray.add(0, "0");
        dynamicArray.add(1, "aaaa");
        dynamicArray.add(1, "bbb");
        dynamicArray.add(1, "aacccaa");
        dynamicArray.add(0, "ddd");
        dynamicArray.add(1, "rr");
        dynamicArray.add(1, "555");

        // ddd,555,rr,0,aacccaa,bbb,aaa,a,b,c

        System.out.println(dynamicArray);
//        dynamicArray.addLast("c");
//        dynamicArray.addLast("d");
//        dynamicArray.addLast("e");
//        dynamicArray.addLast("f");
//        dynamicArray.addLast("g");
//        dynamicArray.addLast("h");
//        dynamicArray.addLast("i");
//        dynamicArray.addLast("j");
//        dynamicArray.addLast("k");
//        System.out.println(dynamicArray);
//        System.out.println(dynamicArray.size());
//        System.out.println(dynamicArray.remove(0));
//        System.out.println(dynamicArray);
//        System.out.println(dynamicArray.size());
//
//        System.out.println(dynamicArray.remove(4));
//        System.out.println(dynamicArray);
//        System.out.println(dynamicArray.size());
    }
}
