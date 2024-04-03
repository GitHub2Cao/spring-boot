package com.softnovo.algorithm.linklist;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SinglyLinkedListSentinel<T> implements Iterable<T> {
    private Node<T> head = new Node<>(null, null);
    private int size;

    public void addFirst(T value) {
        head.setNext(new Node<>(value, head.getNext()));
        size++;
    }

    public void addLast(T value) {
        Node<T> current = head;
        for (; current.getNext() != null; current = current.getNext()) {
        }
        Node<T> node = new Node<>(value, null);
        current.setNext(node);
        size++;
    }

    public void insert(int index, T value) {
        if (index < 0 || index >= size) {
            return;
        }
        if (index == 0) {
            addFirst(value);
        } else {
            Node<T> preNode = getNode(index - 1);
            Node<T> node = new Node<>(value, null);
            node.setNext(preNode.getNext());
            preNode.setNext(node);
        }

        size++;
    }

    private Node<T> getNode(int index) {
        int position = -1;
        for (Node<T> current = head; current != null; current = current.getNext(), position++) {
            if (position == index) {
                return current;
            }
        }
        return null;
    }


    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        return getNode(index).getValue();
    }

    public T removeFirst() {
       return remove(0);
    }

    public T removeLast() {
        return remove(size - 2);
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> preNode = getNode(index - 1);
        T value = preNode.getNext().getValue();
        preNode.setNext(preNode.getNext().getNext());
        size--;
        return value;
    }

    public void loop() {

    }

    public void loopWhile(Consumer<T> consumer) {

    }

    public void loopFor(Consumer<T> consumer) {

    }

    public void loopRecursion(Consumer<T> before, Consumer<T> after) {

    }

    public static void main(String[] args) {
        SinglyLinkedListSentinel singlyLinkedListSentinel = new SinglyLinkedListSentinel();
        singlyLinkedListSentinel.addFirst("a");
        singlyLinkedListSentinel.addFirst("b");
        singlyLinkedListSentinel.addFirst("c");
        System.out.println(singlyLinkedListSentinel);

        singlyLinkedListSentinel.addLast("1");
        singlyLinkedListSentinel.addLast("2");
        singlyLinkedListSentinel.addLast("3");
        System.out.println(singlyLinkedListSentinel);

        System.out.println(singlyLinkedListSentinel.get(0)); // c
        System.out.println(singlyLinkedListSentinel.get(2)); // a
        System.out.println(singlyLinkedListSentinel.get(3)); // 1
        System.out.println(singlyLinkedListSentinel.get(5)); // 3

        singlyLinkedListSentinel.insert(0, "0");
        System.out.println(singlyLinkedListSentinel);

        singlyLinkedListSentinel.insert(5, "11");
        System.out.println(singlyLinkedListSentinel);
        singlyLinkedListSentinel.insert(8, "last");
        System.out.println(singlyLinkedListSentinel);

        singlyLinkedListSentinel.remove(0);
        System.out.println(singlyLinkedListSentinel);

        singlyLinkedListSentinel.removeFirst();
        System.out.println(singlyLinkedListSentinel);

        singlyLinkedListSentinel.remove(3);
        System.out.println(singlyLinkedListSentinel);

        singlyLinkedListSentinel.removeLast();
        System.out.println(singlyLinkedListSentinel);


    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node current = this.head.getNext(); current != null; current = current.next) {
            // 做一些事
            stringBuilder.append(" -> ").append(current.getValue());
        }
        return stringBuilder.toString().replaceFirst(" -> ", "") + " (" + size + ")";
    }

    /**
     * NodeIterator 要定义为非 static 内部类，是因为它与 SinglyLinkedList 实例相关，是对某个 SinglyLinkedList 实例的迭代
     */
    private class NodeIterator implements Iterator<T> {
        Node<T> current = head;

        @Override
        public boolean hasNext() { // 是否有下一个元素
            return current != null;
        }

        @Override
        public T next() { // 返回当前值, 并指向下一个元素
            T value = current.getValue();
            current = current.getNext();
            return value;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
