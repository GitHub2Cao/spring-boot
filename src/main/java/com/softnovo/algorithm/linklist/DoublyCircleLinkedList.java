package com.softnovo.algorithm.linklist;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DoublyCircleLinkedList<T> implements Iterable<T> {
    private final Node sentinel = new Node(null, "sentinel", null);
    private int size;

    public DoublyCircleLinkedList() {
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }

    public void addFirst(T value) {
        Node next = sentinel.getNext();
        Node prev = sentinel;
        Node node = new Node(prev, value, next);
        prev.setNext(node);
        next.setPrev(node);
        size++;
    }

    public void addLast(T value) {
        Node prev = sentinel.getPrev();
        Node next = sentinel;
        Node node = new Node(prev, value, next);
        prev.setNext(node);
        next.setPrev(node);
        size++;
    }

    public void insert(int index, T value) {
        Node prev = findNode(index);
        Node next = prev.getNext();
        Node node = new Node(prev, value, next);
        prev.setNext(node);
        next.setPrev(node);
        size++;
    }

    private Node findNode(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index 不合法");
        }

        int p = 0;
        for (Node current = sentinel.next; current != sentinel; current = current.next, p++) {
            if (p == index) {
                return current;
            }
        }

        return sentinel;
    }

    public Object removeFirst() {
        if(size <= 0) {
            throw new RuntimeException("no element");
        }

        Object value = sentinel.getNext().getValue();
        Node prev = sentinel;
        Node next = sentinel.getNext().getNext();
        prev.setNext(next);
        next.setPrev(prev);
        size--;
        return value;
    }

    public Object removeLast() {
        if(size <= 0) {
            throw new RuntimeException("no element");
        }
        Object value = sentinel.getPrev().getValue();
        Node prev = sentinel.getPrev().getPrev();
        Node next = sentinel;
        prev.setNext(next);
        next.setPrev(prev);

        size--;
        return value;
    }

    public void removeByValue(T value) {
        if (size < 0) {
            throw new RuntimeException("no element");
        }
        Node nodeByValue = findNodeByValue(value);
        if (nodeByValue == null) {
            return;
        }

        Node prev = nodeByValue.getPrev();
        Node next = nodeByValue.getNext();

        prev.setNext(next);
        next.setPrev(prev);
        size--;
    }

    private Node findNodeByValue(T value) {
        for (Node current = sentinel.next; current != null; current = current.getNext()) {
            if (current.getValue().equals(value)) {
                return current;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        DoublyCircleLinkedList<String> doublyCircleLinkedList = new DoublyCircleLinkedList();
        doublyCircleLinkedList.addFirst("a");
        doublyCircleLinkedList.addFirst("b");
        doublyCircleLinkedList.addFirst("c");
        System.out.println(doublyCircleLinkedList);

        doublyCircleLinkedList.addLast("1");
        doublyCircleLinkedList.addLast("2");
        doublyCircleLinkedList.addLast("3");
        System.out.println(doublyCircleLinkedList);

        doublyCircleLinkedList.insert(0, "00");
        doublyCircleLinkedList.insert(1, "11");
        doublyCircleLinkedList.insert(7, "66");
        System.out.println(doublyCircleLinkedList);

        System.out.println(doublyCircleLinkedList.removeFirst());
        System.out.println(doublyCircleLinkedList.removeFirst());
        System.out.println(doublyCircleLinkedList);

        System.out.println(doublyCircleLinkedList.removeLast());
        System.out.println(doublyCircleLinkedList.removeLast());
        System.out.println(doublyCircleLinkedList);

        System.out.println(doublyCircleLinkedList.findNodeByValue("b").getValue());
        System.out.println(doublyCircleLinkedList.findNodeByValue("11").getValue());
        System.out.println(doublyCircleLinkedList.findNodeByValue("2").getValue());

        doublyCircleLinkedList.removeByValue("a");
        doublyCircleLinkedList.removeByValue("11");
        doublyCircleLinkedList.removeByValue("2");
        System.out.println(doublyCircleLinkedList);
    }

    @Override
    public String toString() {
        return recursiveNextToString(sentinel, true) + " (" + size + ") " + recursivePrevToString(sentinel, true);
    }

    private String recursiveNextToString(Node<T> current, boolean isFirst) {
        if ((current == null || current == sentinel) && !isFirst) {
            return "sentinel";
        } else {
            return current.value + " -> " + recursiveNextToString(current.next, false);
        }
    }

    private String recursivePrevToString(Node<T> current, boolean isFirst) {
        if ((current == null || current == sentinel) && !isFirst) {
            return "sentinel";
        } else {
            return current.value + " -> " + recursivePrevToString(current.prev, false);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    private class Node<T> {
        private Node prev;
        private T value;
        private Node next;

        public Node(Node prev, T value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
