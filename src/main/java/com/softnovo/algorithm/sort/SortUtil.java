package com.softnovo.algorithm.sort;

/**
 * @author cgm
 * @date 2024-01-04 22:15
 */
public class SortUtil {
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	public static void exch(Comparable[] arr, int i, int j) {
		Comparable temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static boolean isSorted(Comparable[] arr) {
		for(int i = 1; i < arr.length; i++) {
			if (less(arr[i], arr[i - 1])) {
				return false;
			}
		}
		return true;
	}
}
