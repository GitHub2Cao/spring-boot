package com.softnovo.algorithm.priorityqueue;

public class Entry<E> implements Priority {
    private E value;
    private int priority;

    public Entry(int priority) {
        this.priority = priority;
    }

    public Entry(E value, int priority) {
        this.value = value;
        this.priority = priority;
    }
    @Override
    public int priority() {
        return priority;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
