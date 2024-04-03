package com.softnovo.algorithm.queue;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class LinkedListQueue<E> implements Iterable<E> {
    private Node<E> head = new Node<>(null, null);
    private Node<E> tail = head;
    private int size;
    private int capacity = Integer.MAX_VALUE;

    public LinkedListQueue() {
    }

    /**
     * 添加的一端称为尾，移除的一端称为头
     * @param value
     * @return
     */
    public boolean offer(E value) {
        Node<E> node = new Node<>(value, null);
        tail.setNext(node);
        tail = node;
        size++;
        return true;
    }

    E poll() {
        if (isEmpty()) {
            throw new RuntimeException("no element");
        }
        E value = head.getNext().getValue();
        head.setNext(head.getNext().getNext());
        size--;
        return value;
    }

    E peek() {
        if (isEmpty()) {
            throw new RuntimeException("no element");
        }
        return head.getNext().getValue();
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        LinkedListQueue<String> linkedListQueue = new LinkedListQueue<>();
        System.out.println(linkedListQueue);
        System.out.println(linkedListQueue.isEmpty());
        linkedListQueue.offer("a");
        System.out.println(linkedListQueue.isEmpty());
        linkedListQueue.offer("b");
        linkedListQueue.offer("c");
        System.out.println(linkedListQueue.isFull());
        System.out.println(linkedListQueue);

        System.out.println(linkedListQueue.peek());
        System.out.println(linkedListQueue.poll());
        System.out.println(linkedListQueue);
        System.out.println(linkedListQueue.poll());
        System.out.println(linkedListQueue);
        System.out.println(linkedListQueue.poll());
        System.out.println(linkedListQueue);

    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "";
        }
        return loopString(head) + " (" + size + ")";
    }

    private String loopString(Node<E> node) {
        if (node == tail) {
            return node.value.toString();
        }
        if (node == head) {
            return loopString(node.next);
        } else {
            return node.value + " -> " + loopString(node.next);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
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

    private class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value, Node<E> next) {
            this.next = next;
            this.value = value;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }
    }
}
