package com.softnovo.algorithm.sort;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;

/**
 * @author cgm
 * @date 2023-12-29 11:37
 */
public class Sort {
	public static void main(String[] args) {
//		int[] arr1 = new int[]{1, 2, 3, 4, 5, 6, 0, 0, 0};
//		System.out.println(Arrays.toString(arr1));
//		System.out.println(Arrays.toString(bubbleSort(arr1, 7)));
//		int[] arr2 = new int[]{6, 2, 3, 3, 5, 1, 0, 0, 0};
//		System.out.println(Arrays.toString(arr2));
//		System.out.println(Arrays.toString(bubbleSort(arr2, 9)));
		int[] arr1 = new int[]{4, 2};
		//System.out.println(orderedPair(arr1, 6));
		System.out.println(Arrays.toString(insertionSort(arr1, 2)));

	}

	public static List<Pair<Integer, Integer>> orderedPair(int[] arr, int n) {
		if (n <= 1) {
			return Lists.newArrayList();
		}

		List<Pair<Integer, Integer>> result = Lists.newArrayList();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] <= arr[j + 1]) {
					result.add(Pair.of(arr[j], arr[j + 1]));
				}
				if (arr[j] < arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}

		return  result;
	}

	public static int[] insertionSort(int[] arr, int n) {
		if (n <= 1) {
			return arr;
		}

		for (int i = 1; i < n; i++) {
			int current = arr[i];
			int j = i - 1;
			for (; j >= 0; j--) {
				if (arr[j] > current) {
					arr[j + 1] = arr[j];
				} else {
					break;
				}
			}

			arr[j + 1] = current;
		}

		return arr;
	}


	public static int[] bubbleSort(int[] arr, int n) {
		if (n <= 1) {
			return arr;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}

		return arr;
	}


	public static int[] bubbleSortPlus(int[] arr, int n) {
		if (n <= 1) {
			return arr;
		}

		for (int i = 0; i < n - 1; i++) {
			boolean swapFlag = false;
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapFlag = true;
				}
			}
			if (!swapFlag) {
				break;
			}
		}
		return arr;
	}
}
