package com.softnovo.algorithm.array;

import com.google.common.base.Preconditions;

import java.util.Arrays;

/**
 * @author cgm
 * @date 2023-12-16 21:18
 */
public class SortedArray {
	private int[] array;
	private int size;

	public SortedArray(int size) {
		Preconditions.checkArgument(size >= 5);
		array = new int[size];
	}

	public void addOther(int value) {
		Preconditions.checkArgument(size <= array.length, "数组已经满");
		array[size++] = value;
		int i = size - 1;

		for (int j = i; j > 0; j--) {
			if (array[j - 1] > array[j]) {
				int temp = array[j - 1];
				array[j - 1] = array[j];
				array[j] = temp;
			}
		}
	}

	public void add(int value) {
		Preconditions.checkArgument(size <= array.length, "数组已经满");
		int findIndex = 0;
		for (int i = 0; i < size; i++) {
			if (array[i] >= value) {
				findIndex = i;
				break;
			}
			findIndex = size;
		}

		for (int i = size; i > findIndex; i--) {
			array[i] = array[i - 1];
		}

		array[findIndex] = value;
		size++;
	}

	public int find(int value) {
		return getResult(value);
	}

	private int getResult(int value) {
		Preconditions.checkArgument(size >= 1, "数组没有元素");
		int low = 0;
		int high = size - 1;
		int mid = low + (high - low) / 2;
		int result = -1;
		while (low <= high) {
			if (array[mid] == value) {
				result = mid;
				break;
			} else if (array[mid] > value) {
				high = mid - 1;
				mid = low + (high - low) / 2;
			} else if (array[mid] < value) {
				low = mid + 1;
				mid = low + (high - low) / 2;
			}
		}
		return result;
	}

	/**
	 * 返回位置
	 * @param value
	 * @return
	 */
	public int remove(int value) {
		int result = getResult(value);

		int i = result;
		for (; i < size - 1; i++) {
			array[i] = array[i + 1];
		}
		array[i] = 0;

		return result;
	}

	@Override
	public String toString() {
		return Arrays.toString(this.array);
	}

	public static void main(String[] args) {
		SortedArray sortedArray = new SortedArray(9);
		sortedArray.addOther(9);
		sortedArray.addOther(8);
		sortedArray.addOther(7);
		sortedArray.addOther(6);
		sortedArray.addOther(5);
		sortedArray.addOther(45);
		sortedArray.addOther(21);
		sortedArray.addOther(5);
		sortedArray.addOther(5);

		System.out.println(sortedArray);

		System.out.println(sortedArray.find(6));
		System.out.println(sortedArray.find(5));

		sortedArray.remove(6);
		sortedArray.remove(5);
		sortedArray.remove(45);
		System.out.println(sortedArray);

	}
}
