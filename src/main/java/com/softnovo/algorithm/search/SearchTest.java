package com.softnovo.algorithm.search;

import java.util.Arrays;

public class SearchTest {

    static int left_bound(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length; // 注意

        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }

        return nums[left] == target ? left : -1;
    }

    static int right_bound(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left] == target ? left : -1;
    }

    static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // 注意 1

        while (left < right) { // 注意 2
            int mid = (right + left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1; // 注意 3
            } else if (nums[mid] > target) {
                right = mid + 1; // 注意 4
            }
        }
        return nums[left] == target ? left : -1;
    }

    public static int binarySearchLeftmost(int[] a, int target) {
        int i = 0, j = a.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target <= a[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i;
    }

    public static int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m + n];
        doMerge(nums1, 0, m - 1, nums2, 0, n - 1, 0, temp);
        System.arraycopy(temp, 0, nums1, 0, temp.length);
    }

    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m + n];
        int toLo = 0;
        int srcLo = 0;
        int index = 0;
        while (toLo < m && srcLo < n) {
            if (nums1[toLo] <= nums2[srcLo]) {
                temp[index++] = nums1[toLo++];
            } else {
                temp[index++] = nums2[srcLo++];
            }
            System.out.println("aaaa");
        }
        if (toLo >= m) {
            System.arraycopy(nums2, srcLo, temp, index, n - srcLo);
        }
        if (srcLo >= n) {
            System.arraycopy(nums1, toLo, temp, index, m - toLo);
        }
        System.arraycopy(temp, 0, nums1, 0, temp.length);
    }

    private static void doMerge(int[] arrTo, int toLo, int toHi, int[] arrSrc, int srcLo, int srcHi, int index, int[] tempArr) {
        if (srcLo > srcHi && toLo > toHi) {
            return;
        }

        if (srcLo > srcHi) {
            tempArr[index] = arrTo[toLo];
            // 可以直接拷贝
            doMerge(arrTo, toLo + 1, toHi, arrSrc, srcLo, srcHi, index + 1, tempArr);
        } else if (toLo > toHi) {
            tempArr[index] = arrSrc[srcLo];
            doMerge(arrTo, toLo, toHi, arrSrc, srcLo + 1, srcHi, index + 1, tempArr);
        } else if (arrTo[toLo] <= arrSrc[srcLo]) {
            tempArr[index] = arrTo[toLo];
            doMerge(arrTo, toLo + 1, toHi, arrSrc, srcLo, srcHi, index + 1, tempArr);
        } else if (arrSrc[srcLo] <= arrTo[toLo]) {
            tempArr[index] = arrSrc[srcLo];
            doMerge(arrTo, toLo, toHi, arrSrc, srcLo + 1, srcHi, index + 1, tempArr);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        merge1(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
