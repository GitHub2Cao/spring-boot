package com.softnovo.algorithm.stack;

import com.softnovo.algorithm.queue.LinkedListQueue;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class LinkedListStack<E> implements Iterable<E> {
    private final int capacity;
    private int size;
    private final Node<E> head;

    public LinkedListStack(int capacity) {
        this.capacity = capacity;
        this.head = new Node<>(null, null);
    }

    boolean push(E value) {
        if (isFull()) {
            throw new RuntimeException("too more");
        }
        head.next = new Node<>(value, head.next);
        size++;
        return true;
    }

    E pop() {
        if (isEmpty()) {
            throw new RuntimeException("no element");
        }
        E value = head.next.value;
        head.next = head.next.next;
        size--;
        return value;
    }

    E peek() {
        if (isEmpty()) {
            throw new RuntimeException("no element");
        }
        return head.next.value;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        LinkedListStack linkedListStack = new LinkedListStack(6);
        linkedListStack.push("a");
        linkedListStack.push("b");
        linkedListStack.push("c");
        linkedListStack.push("d");
        System.out.println(linkedListStack);

        System.out.println(linkedListStack.peek());
        System.out.println(linkedListStack.pop());
        System.out.println(linkedListStack.pop());
        System.out.println(linkedListStack.pop());
        System.out.println(linkedListStack.pop());
        System.out.println(linkedListStack.pop());
        System.out.println(linkedListStack.pop());
        System.out.println(linkedListStack);
    }

    @Override
    public String toString() {
        return nodeToString(head) + "|";
    }

    private String nodeToString(Node node) {
        if (node == null) {
            return "";
        }
        if (node == head) {
            return nodeToString(node.next);
        } else {
            return node.value + " " + nodeToString(node.next);
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
        private E value;
        private Node<E> next;
        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
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
