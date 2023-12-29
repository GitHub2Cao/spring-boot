package com.softnovo.algorithm.linklist;

import com.google.common.base.Preconditions;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * @author cgm
 * @date 2023-12-17 18:44
 */
public class SelfLink<E> implements Iterable {
	private Node<E> head;
	private Node<E> last;

	private int size;

	public SelfLink() {
		head = new Node<>();
		last = head;
	}

	public Node<E> first() {
		return head.getNext();
	}

	public <E> void add(E e) {
		Node node = new Node(e);
		last.setNext(node);
		size++;
		last = node;
	}

	public int indexOf(E e) {
		Objects.requireNonNull(e, "e is null");
		int index = 0;
		for (Node<E> x = head.getNext(); x != null; x = x.getNext()) {
			if (e.equals(x.getE())) {
				return index;
			}
			index++;
		}

		return -1;
	}

	public E indexOf(int index) {
		return node(index).getE();
	}

	private Node<E> node(int index) {
		Preconditions.checkArgument(index <= size && index >= 0);
		int cursor = 0;
		for (Node<E> x = head.getNext(); x != null; x = x.getNext()) {
			if (cursor == index) {
				return x;
			}
			cursor++;
		}
		throw new RuntimeException("no element in list");
	}

	public E remove(int index) {
		Preconditions.checkArgument(index <= size && index >= 0);
		Node<E> node;
		if (index == 0) {
			node = head.getNext();
			head.setNext(head.getNext().getNext());
		} else {
			Node<E> prev = node(index - 1);
			node = prev.getNext();
			prev.setNext(node.getNext());
		}
		size--;

		return node.getE();
	}


	public <E> SelfLink<E> reverseWithStack() {
		Stack<Node<E>> stack = new Stack<>();

		Node<E> node = (Node<E>) head.getNext();
		while (node != null && node.getE() != null) {
			stack.push(node);
			node = node.getNext();
		}

		head.setNext(null);
		last = head;

		while (!stack.isEmpty()) {
			add(stack.pop().getE());
		}
		return (SelfLink<E>) this;
	}

	public <E> SelfLink<E> reverseWithFor() {
		// 1 -> 4 -> 2 -> 3 -> 5 -> 6
		// 5 -> 3 -> 2 -> 4 -> 1
		Node newNode = null;
		Node currentNode = head.getNext();
		while (currentNode != null) {
			Node nextNode = currentNode.getNext();
			currentNode.setNext(newNode);
			newNode = currentNode;
			currentNode = nextNode;
		}

		Node temp = head;
		head.setNext(last);
		last = temp;

		return (SelfLink<E>) this;
	}

	public <E> SelfLink<E> reverseWithRecursion() {
		// 1 -> 4 -> 2 -> 3 -> 5 -> 6
		// 5 -> 3 -> 2 -> 4 -> 1
		Node newNode = null;
		Node currentNode = head.getNext();
		while (currentNode != null) {
			Node nextNode = currentNode.getNext();
			currentNode.setNext(newNode);
			newNode = currentNode;
			currentNode = nextNode;
		}

		Node temp = head;
		head.setNext(last);
		last = temp;

		return (SelfLink<E>) this;
	}


	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		Node current = head.getNext();
		while (current != null) {
			stringBuilder.append(current.getE());
			current = current.getNext();
			if (current != null) {
				stringBuilder.append(" -> ");
			}
		}
		return stringBuilder.toString();
	}

	public static void main(String[] args) {
		SelfLink<Integer> selfLink = new SelfLink<>();
		selfLink.add(1);
		selfLink.add(4);
		selfLink.add(2);
		selfLink.add(3);
		selfLink.add(5);
		selfLink.add(6);

		System.out.println(selfLink);
//		selfLink.reverseWithStack();
//		System.out.println(selfLink);
		selfLink.reverseWithFor();
		System.out.println(selfLink);



//		System.out.println(selfLink.first().getE());
//		System.out.println(selfLink.indexOf(3));
//
//		List<Integer> list = Lists.newLinkedList();
//		list.add(1);
//		list.add(4);
//		list.add(2);
//		list.add(3);
//
//		System.out.println(list.indexOf(3));
//
//		System.out.println(selfLink.indexOf(2));
//		System.out.println(selfLink.indexOf(3));
//
//		selfLink.remove(1);
//		System.out.println(selfLink);
//		selfLink.remove(0);
//		System.out.println(selfLink);
//
//		System.out.println(selfLink.getSize());

//		System.out.println(selfLink.find(2));
//		System.out.println(selfLink.find(1));
//		System.out.println(selfLink.find(3));
	}

	@Override
	public Iterator iterator() {
		return null;
	}

	@Override
	public void forEach(Consumer action) {
		Iterable.super.forEach(action);
	}

	@Override
	public Spliterator spliterator() {
		return Iterable.super.spliterator();
	}


	public int getSize() {
		return size;
	}
}
