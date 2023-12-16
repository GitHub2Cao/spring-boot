package com.softnovo.algorithm.array;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author cgm
 * @date 2023-12-09 22:59
 */
public class SelfArray implements Iterable {
	private int[] array;
	private int size;

	public SelfArray(int size) {
		//Preconditions.checkArgument(size >= 0, "size 必须大于等于0");
		this.array = new int[size];
	}

	public SelfArray() {
		this(0);
	}

	public void add(int value) {
		resize();
		array[size++] = value;
	}

	public void add(int index, int value) {
		resize();
		for (int i = size; i > index; i--) {
			array[i] = array[i - 1];
		}
		array[index] = value;
		size = size + 1;
	}

	int get(int index) {
		Preconditions.checkArgument(index >= 0 && index <= size - 1);
		return array[index];
	}

	void set(int index, int e) {
		Preconditions.checkArgument(index >= 0 && index <= size - 1);
		array[index] = e;
	}

	int remove(int index) {
		Preconditions.checkArgument(index >= 0 && index <= size - 1);
		int result = array[index];

		int i = index;
		for (; i < size - 1; i++) {
			array[i] = array[i + 1];
		}
		array[i] = 0;
		size--;
		return result;
	}

	int size() {
		return size;
	}

	@Override
	public Iterator iterator() {
		return new Iterator() {
			int cursor = 0;

			@Override
			public boolean hasNext() {
				return cursor < size;
			}

			@Override
			public Object next() {
				return array[cursor++];
			}
		};
	}

	@Override
	public void forEach(Consumer action) {
		Iterable.super.forEach(action);
	}

	@Override
	public Spliterator spliterator() {
		return Iterable.super.spliterator();
	}

	public String toString() {
		return Arrays.toString(array);
	}

	private void resize() {
		if (array.length == size) {
			array = Arrays.copyOf(array, size * 2 + 1);
		}
	}

	public static void main(String[] args) {
		SelfArray selfArray = new SelfArray();

		testAdd(selfArray);

		selfArray.forEach(element -> System.out.println(element));

//		selfArray.set(0, 10);
//		selfArray.set(3, 234);
//		System.out.println(selfArray);
//		System.out.println(selfArray.get(0));
//		System.out.println(selfArray.get(3));
//		System.out.println(selfArray.get(5));
		System.out.println("---- " + selfArray.size);
		System.out.println(selfArray.remove(0));
		System.out.println("---- " + selfArray.size);
		System.out.println(selfArray);
		System.out.println(selfArray.remove(2));
		System.out.println("---- " + selfArray.size);
		System.out.println(selfArray);
		System.out.println(selfArray.remove(3));
		System.out.println("---- " + selfArray.size);
		System.out.println(selfArray);

	}

	private static void testAdd(SelfArray selfArray) {
		selfArray.add(0,12);
		selfArray.add(0,12);
		selfArray.add(0, 1);
		selfArray.add(0, 1);
		selfArray.add(selfArray.size, 5);
		selfArray.add(selfArray.size, 6);
		System.out.println(selfArray);
	}



}
