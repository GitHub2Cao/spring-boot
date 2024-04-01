package com.softnovo.algorithm.linklist;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 单向链表. 不带哨兵.
 */
public class SinglyLinkedList<T>  implements Iterable<T> {
    private Node<T> head;
    private int size;

    /**
     * 定义为 static 内部类，是因为 Node 不需要与 SinglyLinkedList 实例相关，多个 SinglyLinkedList实例能共用 Node 类定义
     * @param <T>
     */
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

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
    }

    public void addLast(T value) {
        Node<T> node = new Node<>(value, null);
        Node<T> current = head;
        Node<T> pre = null;
        while (current != null) {
            pre = current;
            current = current.getNext();
        }
        if (pre == null) {
            head = node;
        } else {
            pre.setNext(node);
        }
        size++;
    }

    public void insert(int index, T value) {
        Node<T> node = new Node<>(value, null);
        Node<T> current = head;
        Node<T> pre = null;
        int position = 0;
        if (position == index) {
            add(pre, node);
        } else {
            while (current != null) {
                pre = current;
                if (++position >= index) {
                    break;
                }
                current = current.getNext();
            }
            add(pre, node);
        }
    }

    private void add(Node<T> pre, Node<T> node) {
        if (pre == null) {
            node.setNext(head);
            head = node;
        } else {
            node.setNext(pre.getNext());
            pre.setNext(node);
        }

        size++;
    }

    public T get(int index) {
        if (index < 1 || index > size) {
            System.out.println("非法参数");
            return null;
        }
        Node<T> current = head;
        int position = 1;
        if (position == index) {
            return head.getValue();
        }

        while (current != null) {
            current = current.getNext();
            if (++position == index) {
                break;
            }
        }

        return current.getValue();
    }

    public T removeFirst() {
        if (head == null) {
            return null;
        }
        T value = head.getValue();
        head = head.getNext();
        size--;
        return value;
    }

    public T removeLast() {
        if (head == null) {
            return null;
        }
        Node<T> current = head;
        Node<T> pre = null;
        while (current.getNext() != null) {
            pre = current;
            current = current.getNext();
        }
        T value = null;
        if (pre == null) {
            value = head.getValue();
            head = null;
        } else {
            value = current.getValue();
            pre.setNext(null);
        }
        size--;

        return value;
    }

    public T remove(int index) {
        if (index < 1 || index > size) {
            return null;
        }
        T value = null;
        if (index == 1) {
            value = head.getValue();
            head = head.getNext();
        } else {
            int position = 1;
            Node<T> current = head;
            Node<T> pre = null;
            while (current != null) {
                pre = current;
                current = current.getNext();
                if (++position == index) {
                    break;
                }
            }
            if (pre != null) {
                value = current.getValue();
                pre.setNext(current.getNext());
            }
        }

        size--;
        return value;
    }

    public void loop() {
        Node<T> current = head;
        StringBuilder stringBuilder = new StringBuilder();
        while (current != null) {
            stringBuilder.append(" -> ").append(current.getValue());
            current = current.getNext();
        }
        System.out.println(stringBuilder.toString().replaceFirst(" -> ", ""));
    }

    public void loop(Consumer<T> consumer) {
        Node<T> current = head;
        while (current != null) {
            consumer.accept(current.getValue());
            current = current.getNext();
        }
    }

    public void loop1(Consumer<T> consumer) {
        for (Node<T> current = head; current != null; current = current.getNext()) {
            consumer.accept(current.getValue());
        }
    }

    public void loop2(Consumer<T> before, Consumer<T> after) {
        recursion(head, before, after);
    }

    private void recursion(Node<T> head, Consumer<T> before, Consumer<T> after) {
        if (head == null) {
            return;
        }
        before.accept(head.getValue());
        recursion(head.getNext(), before, after);
        after.accept(head.getValue());
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = getStringBuilder();
        return stringBuilder.toString().replaceFirst(" -> ", "") + " (" + size + ")";
    }

    private StringBuilder getStringBuilder() {
        Node<T> current = head;
        StringBuilder stringBuilder = new StringBuilder();
        while (current != null) {
            stringBuilder.append(" -> ").append(current.getValue());
            current = current.getNext();
        }
        return stringBuilder;
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
        return new NodeIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        StringBuilder stringBuilder = getStringBuilder();
        System.out.println(stringBuilder.toString().replaceFirst(" -> ", ""));
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    public static void main(String[] args) {
        SinglyLinkedList<String> singlyLinkedList = new SinglyLinkedList<>();
        System.out.println(singlyLinkedList.removeFirst());
        System.out.println(singlyLinkedList);
        System.out.println(singlyLinkedList.removeLast());
        System.out.println(singlyLinkedList);
        singlyLinkedList.addFirst("a");
        System.out.println(singlyLinkedList.removeLast());
        System.out.println(singlyLinkedList);
        singlyLinkedList.addFirst("b");
        singlyLinkedList.addFirst("c");
        System.out.println(singlyLinkedList);

        singlyLinkedList.addLast("d");
        singlyLinkedList.addLast("e");
        singlyLinkedList.addFirst("1");
        System.out.println(singlyLinkedList);

        singlyLinkedList.insert(0, "0");
        singlyLinkedList.insert(1, "00");
        singlyLinkedList.insert(10, "10");
        singlyLinkedList.insert(5, "5");

        System.out.println(singlyLinkedList);

        System.out.println(singlyLinkedList.get(0)); // null
        System.out.println(singlyLinkedList.get(1)); // 0
        System.out.println(singlyLinkedList.get(5)); // b
        System.out.println(singlyLinkedList.get(10)); // 10

        System.out.println(singlyLinkedList.removeFirst());
        System.out.println(singlyLinkedList);

        System.out.println(singlyLinkedList.removeFirst());
        System.out.println(singlyLinkedList);

        System.out.println(singlyLinkedList.removeLast());
        System.out.println(singlyLinkedList);

        System.out.println(singlyLinkedList.remove(1));
        System.out.println(singlyLinkedList);

        System.out.println(singlyLinkedList.remove(3));
        System.out.println(singlyLinkedList);

        System.out.println(singlyLinkedList.remove(4));
        System.out.println(singlyLinkedList);

        System.out.println(singlyLinkedList.remove(4));
        System.out.println(singlyLinkedList);

        singlyLinkedList.loop();
        Iterator<String> iterator = singlyLinkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        singlyLinkedList.loop(System.out::print);
        System.out.println();
        singlyLinkedList.loop1(System.out::print);
        System.out.println();

        singlyLinkedList.loop2(value -> {
            value = value + value;
            System.out.println(value);
        }, value -> {
            System.out.println(value.toUpperCase());
        });

    }

}
