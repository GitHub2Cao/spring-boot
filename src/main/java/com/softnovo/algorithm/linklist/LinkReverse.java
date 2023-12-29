package com.softnovo.algorithm.linklist;

import java.util.Objects;
import java.util.Stack;

/**
 * @author cgm
 * @date 2023-12-19 15:27
 */
public class LinkReverse {
//	public static SelfLink reverseWithItor(SelfLink link) {
//		Objects.requireNonNull(link);
//
//		if (link.getSize() == 0 || link.getSize() == 1) {
//			return link;
//		}
//
//		if (link.getSize() = ) {
//
//		}
//
//		return link;
//	}

	public static <E> SelfLink<E> reverseWithStack(SelfLink<E> link) {
		Objects.requireNonNull(link);

		Stack<Node<E>> stack = new Stack<>();
		Node<E> node = link.first();
		while (node != null && node.getE() != null) {
			stack.push(node);
			node = node.getNext();
		}

		SelfLink<E> newLink = new SelfLink<>();
		while (!stack.isEmpty()) {
			newLink.add(stack.pop().getE());
		}

		return newLink;
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
		SelfLink<Integer> integerSelfLink = LinkReverse.reverseWithStack(selfLink);
		System.out.println(integerSelfLink);

	}
}
