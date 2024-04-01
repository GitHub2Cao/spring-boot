package com.softnovo.algorithm.search;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SearchUtil {
    private SearchUtil() {}

    public static <T extends Comparable<? super T>> int binarySearch(T[] sortedArray, T searchKey) {
        if (Objects.isNull(sortedArray) || sortedArray.length == 0) {
            return -1;
        }
        if (Objects.isNull(searchKey)) {
            return -1;
        }
        int lo = 0;
        int hi = sortedArray.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            // 倾斜的情况.
            if (sortedArray[mid].compareTo(searchKey) < 0) {
                hi = mid - 1;
            } else if (sortedArray[mid].compareTo(searchKey) > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static <T extends Comparable<? super T>> int binarySearch1(T[] sortedArray, T searchKey) {
        if (Objects.isNull(sortedArray) || sortedArray.length == 0) {
            return -1;
        }
        if (Objects.isNull(searchKey)) {
            return -1;
        }
        int lo = 0;
        int hi = sortedArray.length;

        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            // 倾斜的情况.
            if (sortedArray[mid].compareTo(searchKey) < 0) {
                hi = mid;
            } else if (sortedArray[mid].compareTo(searchKey) > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static <T extends Comparable<? super T>> int binarySearchBalance(T[] sortedArray, T searchKey) {
        int lo = 0;
        int hi = sortedArray.length;
        while (1 < hi - lo) {
            int mid = (lo + hi) >>> 1;
            if (searchKey.compareTo(sortedArray[mid]) < 0) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return (sortedArray[lo].compareTo(searchKey)) == 0 ? lo : -1;
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

    /**
     * 这样不行
     * @param sortedArray
     * @param searchKey
     * @return
     * @param <T>
     */
    public static <T extends Comparable<? super T>> int binarySearchBalance1(T[] sortedArray, T searchKey) {
        int lo = 0;
        int hi = sortedArray.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (searchKey.compareTo(sortedArray[mid]) < 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return (sortedArray[lo].compareTo(searchKey)) == 0 ? lo : -1;
    }

    public static <T extends Comparable<? super T>> int binarySearchJDK(T[] sortedArray, T searchKey) {
        if (Objects.isNull(sortedArray) || sortedArray.length == 0) {
            return -1;
        }
        if (Objects.isNull(searchKey)) {
            return -1;
        }
        int lo = 0;
        int hi = sortedArray.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            // 倾斜的情况.
            if (sortedArray[mid].compareTo(searchKey) < 0) {
                lo = mid + 1;
            } else if (sortedArray[mid].compareTo(searchKey) > 0) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }

        return -(lo + 1);
    }

    public static <T extends Comparable<? super T>> int binarySearchLeftmost(T[] sortedArray, T searchKey) {
        if (Objects.isNull(sortedArray) || sortedArray.length == 0) {
            return -1;
        }
        if (Objects.isNull(searchKey)) {
            return -1;
        }
        int lo = 0;
        int hi = sortedArray.length - 1;
        int candidate = -1;

        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            // 倾斜的情况.
            if (sortedArray[mid].compareTo(searchKey) < 0) {
                lo = mid + 1;
            } else if (sortedArray[mid].compareTo(searchKey) > 0) {
                hi = mid - 1;
            } else {
                hi = mid - 1;
                candidate = mid;
            }
        }

        return candidate;
    }

    public static <T extends Comparable<? super T>> int binarySearchRightmost(T[] sortedArray, T searchKey) {
        int lo = 0;
        int hi = sortedArray.length - 1;
        int candidate = -1;

        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (sortedArray[mid].compareTo(searchKey) < 0) {
                lo = mid + 1;
            } else if (sortedArray[mid].compareTo(searchKey) > 0) {
                hi = mid - 1;
            } else {
                candidate = mid;
                lo = mid + 1;
            }
        }

        return candidate;
    }

    public static <T extends Comparable<? super T>> int binarySearchBalanceInsertIndex(T[] sortedArray, T searchKey) {
        int lo = 0;
        int hi = sortedArray.length;
        while (hi - lo > 1) {
            int mid = (lo + hi) >>> 1;
            if (searchKey.compareTo(sortedArray[mid]) < 0) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        return (sortedArray[lo].compareTo(searchKey)) == 0 ? lo : -(lo + 1);
    }



    public static void main(String[] args) {
        // binarySearch
//        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        //Integer[] arr = new Integer[]{1, 2, 3, 4, 4, 5, 6, 7};
        // 9 == -1, 2 == -10

        Integer[] arr = new Integer[]{1, 3, 4, 4, 4, 4, 5, 6, 7};
//        System.out.println(binarySearchRightmost(arr, 4));
        Integer searchKey = 2;
        int i = binarySearchBalanceInsertIndex(arr, searchKey);
        System.out.println(i);

        List<Integer> list = Lists.newArrayList(arr);
        if (i < 0) {
            list.add(Math.abs(i + 1), searchKey);
        } else {
            System.out.println(i);
        }
        System.out.println(list.toString());
        


        //对于数组 $[1, 2, 4, 4, 4, 5, 6, 7]$，查找元素4，结果也是索引3，并不是


    }

}
