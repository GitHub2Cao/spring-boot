package com.softnovo.algorithm.sort;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cgm
 * @date 2023-12-29 11:37
 */
public class Sort {
	private static final int size = 10;
	private static final Comparable[] aux = new Comparable[size];


	public static void main(String[] args) {
//		int[] arr1 = new int[]{1, 2, 3, 4, 5, 6, 0, 0, 0};
//		System.out.println(Arrays.toString(arr1));
//		System.out.println(Arrays.toString(bubbleSort(arr1, 7)));
//		int[] arr2 = new int[]{6, 2, 3, 3, 5, 1, 0, 0, 0};
//		System.out.println(Arrays.toString(arr2));
//		System.out.println(Arrays.toString(bubbleSort(arr2, 9)));
		//int[] arr1 = new int[]{4, 2};
		//System.out.println(orderedPair(arr1, 6));
		//System.out.println(Arrays.toString(insertionSort(arr1, 2)));
		//Integer[] arr = new Integer[][]{6, 2, 3, 3, 5, 1};
		Random seed = new Random();
		Supplier<Integer> random = seed::nextInt;
		Integer[] arr = Stream.generate(random).limit(size).collect(Collectors.toList()).toArray(new Integer[0]);
		if (ArrayUtils.isSorted(arr)) {
			System.out.println("YYYYYY");
		}
		//System.out.println(Arrays.toString(arr));
		mergeSort(arr, size);
		if (ArrayUtils.isSorted(arr)) {
			System.out.println("YYYYYY");
		}
	}

	public static Comparable[] mergeSort(Comparable[] arr, int n) {
		if (n <= 1) {
			return arr;
		}
		doMergeSort(arr, 0, n - 1);
		return arr;
	}

	private static void doMergeSort(Comparable[] arr, int lo, int hi) {
		if (hi <= lo) {
			return;
		}

		int mid = lo + (hi - lo) / 2;
		doMergeSort(arr, lo, mid);
		doMergeSort(arr, mid + 1, hi);
		merge(arr, lo, mid, hi);
	}

	private static void merge(Comparable[] arr, int lo, int mid, int hi) {
		int i = lo;
		int j = mid + 1;

		for (int k = lo; k <= hi; k++) {
			aux[k] = arr[k];
		}

		for (int k = lo; k <= hi; k++) {

			// 左边用完
			if (i > mid) {
				arr[k] = aux[j++];
			// 右边用完
			} else if (j > hi) {
				arr[k] = aux[i++];
			} else if (SortUtil.less(aux[i], aux[j])) {
				arr[k] = aux[i++];
			} else {
				arr[k] = aux[j++];
			}
		}
	}

	public static Integer[] shellSort(Integer[] arr, int n) {
		if (n <= 1) {
			return arr;
		}

		int h = n / 2;
		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h && SortUtil.less(arr[j], arr[j - h]); j = j - h) {
					SortUtil.exch(arr, j, j - h);
				}
			}
			h = h / 2;
		}
		return arr;
	}

	public static Integer[] selectionSort(Integer[] arr, int n) {
		if (n <= 1) {
			return arr;
		}

		for (int i = 0; i < n; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			SortUtil.exch(arr, i, minIndex);
//			int temp = arr[i];
//			arr[i] = arr[minIndex];
//			arr[minIndex] = temp;
		}

		assert SortUtil.isSorted(arr);

		return arr;
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

	public static Integer[] insertionSortOld(Integer[] arr, int n) {
		if (n <= 1) {
			return arr;
		}

		for (int i = 1; i < n; i++) {
			for (int j = i; j > 0 && SortUtil.less(arr[j], arr[j - 1]); j--) {
				SortUtil.exch(arr, j, j - 1);
			}
		}

		return arr;
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
