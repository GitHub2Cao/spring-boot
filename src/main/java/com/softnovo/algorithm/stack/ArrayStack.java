package com.softnovo.algorithm.stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ArrayStack<E> implements Iterable<E> {
    private final E[] array;
    private int top = 0;

    public ArrayStack(int capacity) {
        this.array = (E[]) new Object[capacity];
    }

    boolean push(E value) {
        if (isFull()) {
            throw new RuntimeException("too more");
        }
        array[top++] = value;
        return true;
    }
    E pop() {
        if (isEmpty()) {
            throw new RuntimeException("no elements");
        }
        top = top - 1;
        E value = array[top];
        array[top] = null;
        return value;
    }
    E peek() {
        if (isEmpty()) {
            throw new RuntimeException("no elements");
        }
        return array[--top];
    }
    boolean isEmpty() {
        return top == 0;
    }
    boolean isFull() {
        return top == array.length;
    }

    public static void main(String[] args) {
        ArrayStack<String> arrayStack = new ArrayStack<String>(6);
        System.out.println(arrayStack.isEmpty());
        System.out.println(arrayStack.isFull());

        arrayStack.push("a");
        //arrayStack.push("b");
        //arrayStack.push("c");
        System.out.println(arrayStack);
        System.out.println(Arrays.toString(arrayStack.array));
        System.out.println(arrayStack.pop());


        System.out.println(arrayStack);
        System.out.println(Arrays.toString(arrayStack.array));
//
//        System.out.println(arrayStack.peek());
//

//        System.out.println(arrayStack.pop());
//        System.out.println(arrayStack.pop());
//        System.out.println(arrayStack);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (isEmpty()) {
            return "";
        }
        for (int i = top - 1; i >= 0; i--) {
            stringBuilder.append(array[i]).append(" ");
        }

        return stringBuilder.toString() + "|";
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
