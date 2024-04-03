package com.softnovo.algorithm.linklist;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class CircleList implements Iterable<Integer> {
    class Node {
        Node prev;
        int value;
        Node next;

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        public String nextToString() {
            String result = "";
            Node current = sentinel;
            do {
                result += current.value + (current.next == sentinel ? "" : " -> ");
                current = current.next;
            } while (current != sentinel);
            return result;
        }

        public String prevToString() {
            if (prev == sentinel) {
                return "sentinel";
            }
            //System.out.println("prev to == " + value);
            String bb = value + " -> " + prev.prevToString();
            //System.out.println("prev back ---- " + bb) ;
            return bb;
        }
    }

    private final Node sentinel = new Node(null, -1, null); // 哨兵
    private int size;

    public CircleList() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public static void main(String[] args) {
        CircleList circleList = new CircleList();
        circleList.addFirst(1);
        circleList.addFirst(2);
        System.out.println(circleList);
    }

    public void addFirst(int value) {
        Node next = sentinel.next;
        Node prev = sentinel;
        Node added = new Node(prev, value, next);
        prev.next = added;
        next.prev = added;
        size++;
    }

    @Override
    public String toString() {
        return sentinel.nextToString() ;//+ " (" + size + ") " + sentinel.prevToString();
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return Iterable.super.spliterator();
    }
}
