package com.softnovo.algorithm.linklist;

/**
 * @author cgm
 * @date 2023-12-17 19:14
 */
public class Node<E> {
	private Node<E> next;
	private E e;

	public Node(E element) {
		this.e = element;
	}

	public Node() {
	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	public E getE() {
		return e;
	}

	public void setE(E e) {
		this.e = e;
	}
}
