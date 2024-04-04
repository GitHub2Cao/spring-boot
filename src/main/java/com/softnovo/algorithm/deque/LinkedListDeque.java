package com.softnovo.algorithm.deque;

import com.softnovo.algorithm.stack.LinkedListStack;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class LinkedListDeque<E> implements Iterable<E> {
    private final Node<E> sentinel;
    private int capacity;
    private int size;

    public LinkedListDeque(int capacity) {
        this.capacity = capacity;
        this.sentinel = new Node<>(null, null, null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
    }

    public boolean offerFirst(E value) {
        checkFull();
        Node prev = sentinel;
        Node next = sentinel.getNext();
        Node node = new Node(prev, value, next);
        prev.setNext(node);
        next.setPrev(node);
        size++;

        return true;
    }

    private void checkFull() {
        if (isFull()) {
            throw new RuntimeException("too more");
        }
    }

    public boolean offerLast(E value) {
        checkFull();
        Node prev = sentinel.getPrev();
        Node next = sentinel;

        Node node = new Node(prev, value, next);
        prev.setNext(node);
        next.setPrev(node);
        size++;

        return true;
    }

    private void checkEmpty() {
        if (isEmpty()) {
            throw new RuntimeException("no elements");
        }
    }

    public E pollFirst() {
        checkEmpty();
        E value = sentinel.next.value;
        Node prev = sentinel;
        Node next = sentinel.getNext().getNext();
        prev.setNext(next);
        next.setPrev(prev);
        size--;
        return value;
    }

    public E pollLast() {
        checkEmpty();
        E value = sentinel.prev.value;
        Node prev = sentinel.getPrev().getPrev();
        Node next = sentinel;
        prev.setNext(next);
        next.setPrev(prev);
        size--;
        return value;
    }
    public E peekFirst() {
        checkEmpty();
        return sentinel.next.value;
    }
    public E peekLast() {
        checkEmpty();
        return sentinel.prev.value;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        LinkedListDeque<String> linkedListDeque = new LinkedListDeque<>(6);
        System.out.println(linkedListDeque.isEmpty());
        System.out.println(linkedListDeque.isFull());
        System.out.println(linkedListDeque);

        linkedListDeque.offerFirst("a");
        linkedListDeque.offerFirst("b");
        linkedListDeque.offerFirst("c");
        System.out.println(linkedListDeque);

        linkedListDeque.offerLast("1");
        linkedListDeque.offerLast("2");
        linkedListDeque.offerLast("3");
        System.out.println(linkedListDeque);

        System.out.println(linkedListDeque.peekFirst()); // c
        System.out.println(linkedListDeque.peekLast()); // 3

        System.out.println(linkedListDeque.pollFirst()); // c
        System.out.println(linkedListDeque);

        System.out.println(linkedListDeque.pollLast()); // 3
        System.out.println(linkedListDeque);
    }

    @Override
    public String toString() {
        return recursiveNextToString(sentinel, true) + " (" + size + ") " + recursivePrevToString(sentinel, true);
    }

    private String recursiveNextToString(Node<E> current, boolean isFirst) {
        if (current == sentinel && !isFirst) {
            return "sentinel";
        } else {
            if (isFirst) {
                return "sentinel -> " + recursiveNextToString(current.next, false);
            } else {
                return current.value + " -> " + recursiveNextToString(current.next, false);
            }
        }
    }

    private String recursivePrevToString(Node<E> current, boolean isFirst) {
        if (current == sentinel && !isFirst) {
            return "sentinel";
        } else {
            if (isFirst) {
                return "sentinel -> " + recursivePrevToString(current.prev, false);
            } else {
                return current.value + " -> " + recursivePrevToString(current.prev, false);
            }
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

    static class Node<E> {
        private Node<E> prev;
        private E value;
        private Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
