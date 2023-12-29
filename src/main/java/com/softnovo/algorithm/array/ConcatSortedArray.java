package com.softnovo.algorithm.array;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * @author cgm
 * @date 2023-12-17 15:41
 */
public class ConcatSortedArray {
	public static int[] concat(int[] first, int[] second) {
		Preconditions.checkArgument(ArrayUtils.isNotEmpty(first) && ArrayUtils.isSorted(first), "first空或者没有排序");
		Preconditions.checkArgument(ArrayUtils.isNotEmpty(first) && ArrayUtils.isSorted(second), "second空或者没有排序");

		int[] result = Arrays.copyOf(first, first.length + second.length);

		for (int i = first.length; i < result.length; i++) {
			result[i] = second[second.length - (result.length - i)];
			for (int j = i; j > 0; j--) {
				if (result[j - 1] > result[j]) {
					int temp = result[j - 1];
					result[j - 1] = result[j];
					result[j] = temp;
				}
			}
		}

		return  result;
	}

	public static void main(String[] args) {
		int[] first = new int[]{0, 1, 2, 3, 4, 5, 6, 10};
		int[] second = new int[]{3, 4, 4, 4, 5, 6, 7, 11};
		System.out.println(Arrays.toString(concat(first, second)));
	}
}
