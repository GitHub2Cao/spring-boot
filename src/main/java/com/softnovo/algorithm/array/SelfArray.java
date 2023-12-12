package com.softnovo.algorithm.array;

import com.google.common.base.Preconditions;

import java.util.Arrays;

/**
 * @author cgm
 * @date 2023-12-09 22:59
 */
public class SelfArray {
	private int[] array;
	private int size;

	public SelfArray(int size) {
		Preconditions.checkArgument(size >= 0, "size 必须大于等于0");
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
		selfArray.add(0,12);
		selfArray.add(0,12);
		selfArray.add(1, 1);
		selfArray.add(2, 1);
		selfArray.add(5);
		System.out.println(selfArray);
	}





}
