package com.softnovo.algorithm.linklist;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DoublyLinkedListSentinel<T> implements Iterable<T> {
    private final Node head;
    private final Node tail;
    private int size;

    public DoublyLinkedListSentinel() {
        head = new Node(null, "head", null);
        tail = new Node(null, "tail", null);
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(T value) {
        Node next = head.getNext();
        Node node = new Node(head, value, next);
        head.setNext(node);
        next.setPrev(node);
        size++;
    }

    public void removeFirst() {
        if (size <= 0) {
            return;
        }

        Node headNext = head.getNext().getNext();
        headNext.setPrev(head);
        head.setNext(headNext);
        size--;
    }

    public void addLast(T value) {
        Node prev = tail.getPrev();
        Node node = new Node(prev, value, tail);
        tail.setPrev(node);
        prev.setNext(node);
        size++;
    }

    public void removeLast() {
        if (size <= 0) {
            return;
        }

        Node tailPrev = tail.getPrev().getPrev();
        tailPrev.setNext(tail);
        tail.setPrev(tailPrev);
        size--;
    }

    private Node findNode(int index) {
        if (index < -1 || index > size) {
            throw new IllegalArgumentException("index 不合法");
        }
        int p = -1;
        for (Node current = head; current != tail; current = current.getNext(), p++) {
            if (p == index) {
                return current;
            }
        }

        return null;
    }

    public void insert(int index, T value) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index 不合法");
        }
        Node prev = findNode(index - 1);
        Node next = prev.getNext();
        Node node = new Node(prev, value, next);
        prev.setNext(node);
        next.setPrev(node);
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index 不合法");
        }
        Node prev = findNode(index - 1);
        Node next = prev.getNext().getNext();
        prev.setNext(next);
        next.setPrev(prev);
        size--;
    }

    public static void main(String[] args) {
        DoublyLinkedListSentinel<String> doublyLinkedListSentinel = new DoublyLinkedListSentinel<>();
        doublyLinkedListSentinel.addFirst("a");
        doublyLinkedListSentinel.addFirst("b");
        doublyLinkedListSentinel.addFirst("c");
        System.out.println(doublyLinkedListSentinel);

        doublyLinkedListSentinel.removeFirst();
        doublyLinkedListSentinel.removeFirst();
        doublyLinkedListSentinel.removeFirst();
        doublyLinkedListSentinel.removeFirst();
        System.out.println(doublyLinkedListSentinel);

        doublyLinkedListSentinel.addLast("1");
        doublyLinkedListSentinel.addLast("2");
        doublyLinkedListSentinel.addLast("3");
        System.out.println(doublyLinkedListSentinel);

        doublyLinkedListSentinel.removeLast();
        System.out.println(doublyLinkedListSentinel);

        System.out.println(doublyLinkedListSentinel.findNode(-1).getValue());
        System.out.println(doublyLinkedListSentinel.findNode(0).getValue());
        System.out.println(doublyLinkedListSentinel.findNode(1).getValue());

        doublyLinkedListSentinel.insert(0, "0");
        doublyLinkedListSentinel.insert(1, "a");
        doublyLinkedListSentinel.insert(2, "b");
        System.out.println(doublyLinkedListSentinel);

        doublyLinkedListSentinel.remove(0);
        doublyLinkedListSentinel.remove(1);
        doublyLinkedListSentinel.remove(2);
        System.out.println(doublyLinkedListSentinel);

    }

    @Override
    public String toString() {
        return head.nextToString() + " (" + size + ") " + tail.prevToString();
    }

    class DoublyIterator implements Iterator<T> {
        Node<T> current = head.getNext();
        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public T next() {
            T value = current.getValue();
            current = current.getNext();
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("不支持");
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Iterator.super.forEachRemaining(action);
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new DoublyIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }


    private static class Node<T> {
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

        public String nextToString() {
            if (next == null) {
                return "tail";
            }
            return value + " -> " + next.nextToString();
        }

        public String prevToString() {
            if (prev == null) {
                return "head";
            }
            return value + " -> " + prev.prevToString();
        }




    }
}
