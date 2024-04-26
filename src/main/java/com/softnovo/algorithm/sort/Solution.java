package com.softnovo.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Solution {

    public static void main(String[] args) {

        //int[] arr = new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        //int[] arr = new int[] {6, 7, 7, 7, 9, 10, 2, 4, 8, 1, 3, 5, 7, 0};
        int[] arr = new int[] {6, 7, 7, 7, 9, 10, 4, 8, -2, -3, -1};
        System.out.println(Arrays.toString(arr));
        countingSort(arr);
        System.out.println(Arrays.toString(arr));
        //System.out.println(Arrays.toString(arr));

//        String[] strArray = new String[]{"xj1","xj2","xj3","xj4","xj5"};
//        System.out.println(Arrays.toString(strArray));
//        String[] strArrayCopy = new String[5];
//        System.arraycopy(strArray,0,strArrayCopy,0,3);
//        System.out.println(Arrays.toString(strArrayCopy));
//        System.arraycopy(strArray,3,strArrayCopy,3,1);
//        System.out.println(Arrays.toString(strArrayCopy));
//        int[] arr2 = new int[] {9, 8, 4};
//        int[] arrmerge = new int[9];
//        mergeSortedArray(arr, 0, arr.length - 1, arr2, 0, arr2.length - 1, arrmerge);
//        System.out.println(Arrays.toString(arrmerge));
    }

    public static void countingSort(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }
        }
        int[] countArr = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i] - min]++;
        }
        // 前缀和
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] = countArr[i] + countArr[i - 1];
        }
        // 前缀和具有明确的意义，prefix[num] - 1 代表元素 num 在结果数组 res 中最后一次出现的索引
        int[] b = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int j = arr[i] - min;
            countArr[j]--;
            b[countArr[j]] = arr[i];
        }
        System.arraycopy(b, 0, arr, 0, arr.length);
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int partition = partitionDoubleSelfxiaodeng(arr, left, right); // p 索引值
        System.out.printf("===" + partition);
        quickSort(arr, left, partition - 1);
        quickSort(arr, partition + 1, right);
    }

    public static int partitionDoubleSelfxiaodeng(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left + 1;
        int j = right;
        while (i <= j) {
            while (i <= j && arr[j] > pivot) { // 必须 i <= j 要不然会越界，不用等于就不会出现不平衡，两边一起移动，保证公平.
                j--;
            }
            while (i <= j && arr[i] < pivot) { // 必须 i <= j 要不然会越界,
                i++;
            }

            if (i <= j) {
                if (arr[i] != arr[j]) { // 相等就不交换
                    swap(arr, i, j);
                }

                i++;
                j--;
            }
        }
        swap(arr, left, j);
        return j;
    }

    /**
     * 到中间应该就停，要不然重复计算., 不能让 pivot 提前交换。
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int partitionDoubleSelfxiao(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && arr[j] > pivot) { // 必须 i < j 要不然会越界
                j--;
            }
            while (i < j && arr[i] <= pivot) { // 必须 i < j 要不然会越界,
                i++;
            }
            swap(arr, i, j);
        }
        swap(arr, left, j);
        return j; // 这个小于 pivot
    }

    /**
     * i 是比 pivot 大的边界值, pivot 不参与交换，
     * 单边做不了平衡，就是值相等的时候.
     */
    public static int partitionLomutoFor(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                if (i != j) {
                    swap(arr, i, j);
                }
                i++;
            }
        }
        if (i != right) {
            swap(arr, i, right);
        }
        return i;
    }

    public static int partitionLomutoWhile(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        int j = left;
        while (j < right) {
            if (arr[j] < pivot) {
                if (i != j) {
                    swap(arr, i, j);
                }
                i++;
            }
            j++;
        }
        if (i != right) {
            swap(arr, i, right);
        }

        return i;
    }

    public static int partitionTianken(int[] arr, int left, int right) {
        int pivot = arr[left]; // pivot 不参与交换.
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
            }
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
            }
        }
        arr[left] = pivot;
        return left;
    }

    /**
     * 1. 选择最左侧元素作为基准点
     * 2. j 找比基准点小的，i 找比基准点大的，一旦找到，二者进行交换
     *      i 从左向右
     *      j 从右向左
     * 3. 最后基准点与 i 交换，i 即为基准点最终索引
     *
     * 基准点在左边，并且要先 j 后 i 不能反.
     *
     * @param arr
     * @param left
     * @param right
     */
    private static int partitionDouble(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left + 1;
        int j = right;
        while (i <= j) {
            while (i <= j && arr[i] <= pivot) {
                i++;
            }
            while (i <= j && arr[j] >= pivot) {
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        swap(arr, left, j);
        return j;
    }

    public static int partitionLast(int[] arr, int left, int right) {
//        int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
//        swap(arr, left, idx);
        int pivot = arr[left];
        int i = left + 1;
        int j = right;
        while (i <= j) {
            while (i <= j && arr[j] > pivot) {
                j--;
            }
            while (i <= j && arr[i] < pivot) {
                i++;
            }

            if (i <= j) {
                if (arr[i] != arr[j]) {
                    swap(arr, i, j);
                }
                i++;
                j--;
            }
        }

        swap(arr, j, left);
        return j;
    }

    /**
     * 选择最右元素作为基准点元素
     * j 指针负责找到比基准点小的元素，一旦找到则与 i 进行交换
     * i 指针维护小于基准点元素的边界，也是每次交换的目标索引
     * 最后基准点与 i 交换，i 即为分区位置
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partitionLomuto(int[] arr, int left, int right) {
        int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        if (idx != right) { // 或不等于右边.
            swap(arr, idx, right);
        }
        int pivot = arr[right];
        int bound = left;
        for (int j = left; j < right; j++) { // pivot 不参与 for 循环交换
            if (arr[j] <= pivot) {
                if (bound != j) {
                    swap(arr, bound, j);
                }
                bound++;
            }
        }
        if (bound != right) {
            swap(arr, right, bound);
        }
        return bound;
    }

    /**
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partitionLomuto1(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        int j = left;
        while (j < right) {
            if (arr[j] < pivot) {
                if (i != j) {
                    swap(arr, i, j);
                }
                i++;
            }
        }
        if (i != right) {
            swap(arr, right, i);
        }
        return i;
    }

    public static void mergeInsertionSort(int[] arr) {
        int[] mergeArr = new int[arr.length];
        split4MergeInsertion(arr, 0, arr.length - 1, mergeArr);
    }

    private static void split4MergeInsertion(int[] arr, int lo, int hi, int[] mergedArr) {
        if (hi - lo <= 10) {
            insertSortRang(arr, lo, hi);
            return;
        }
        int mid = (lo + hi) >>> 1;
        split4Merge(arr, lo, mid, mergedArr);
        split4Merge(arr, mid + 1, hi, mergedArr);
        mergeSortedArray(arr, lo, mid, mid + 1, hi, mergedArr);
    }

    private static void insertSortRang(int[] arr, int left, int right) {
        for (int low = left + 1; low <= right; low++) {
            int needInsert = arr[low];
            int sortedBound = low - 1;

            while (sortedBound >= left && needInsert < arr[sortedBound]) {
                arr[sortedBound + 1] = arr[sortedBound];
                sortedBound--;
            }
            if (sortedBound != low - 1) {
                arr[sortedBound + 1] = needInsert;
            }
        }
    }

    public static void mergeSortUp(int[] arr) {
        int length = arr.length;
        int[] mergeArr = new int[length];
        for (int step = 1; step < length; step = step << 1) {
            for (int left = 0; left < length; left += step << 1) {
                int right = Integer.min(left + (step << 1) - 1, length - 1);
                if (right - left <= 10) {
                    insertSortRang(arr, left, right);
                    continue;
                }
                int mid = Integer.min(left + step - 1, length - 1);
                mergeSortedArray(arr, left, mid, mid + 1, right, mergeArr);
            }
        }
    }


    public static void mergeSortDown(int[] arr) {
        int[] arrTo = new int[arr.length];
        //System.out.println("arrTo ==== " + Arrays.toString(arrTo));
        split4Merge(arr, 0, arr.length - 1, arrTo);
        System.out.println(Arrays.toString(arr));
    }

    private static void split4Merge(int[] arr, int lo, int hi, int[] mergedArr) {
        if (lo == hi) {
            return;
        }
        int mid = (lo + hi) >>> 1;
        split4Merge(arr, lo, mid, mergedArr);
        split4Merge(arr, mid + 1, hi, mergedArr);
        mergeSortedArray(arr, lo, mid, mid + 1, hi, mergedArr);
        //System.arraycopy(mergedArr, lo, arr, lo, hi - lo + 1);
    }

    private static void mergeSortedArray(int[] arr, int leftStart, int leftEnd, int rightStart, int rightEnd, int[] mergedArr) {
        // 这个是mergedArr 的指针.
        int k = leftStart;
        int i = leftStart;
        int j = rightStart;
        while (i <= leftEnd && j <= rightEnd) {
            if (arr[i] <= arr[j]) {
                mergedArr[k++] = arr[i++];
            } else {
                mergedArr[k++] = arr[j++];
            }
        }
        if (i > leftEnd) {
            System.arraycopy(arr, j, mergedArr, k, rightEnd - j + 1);
        }
        if (j > rightEnd) {
            System.arraycopy(arr, i, mergedArr, k, leftEnd - i + 1);
        }
        System.arraycopy(mergedArr, leftStart, arr, leftStart, rightEnd - leftStart + 1);
    }

    public static void shellSort(int[] arr) {
        for(int gap = arr.length >> 1; gap > 0; gap = gap >> 1) {
            for (int low = gap; low < arr.length; low++) {
                int needInsert = arr[low];
                int bound = low - gap;
                while (bound >= 0 && needInsert < arr[bound]) {
                    arr[bound + gap] = arr[bound];
                    bound = bound - gap;
                }
                if (bound != low - gap) {
                    arr[bound + gap] = needInsert;
                }
            }
        }
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int needInsert = arr[i];
            int sortedBound = i - 1;
            while (sortedBound >= 0 && needInsert < arr[sortedBound]) {
                arr[sortedBound + 1] = arr[sortedBound];
                sortedBound--;
            }
            if (sortedBound != i - 1) {
                arr[sortedBound + 1] = needInsert;
            }
        }
    }

    public static void insertSortRec(int[] arr, int bound) {
        if (bound >= arr.length) {
            return;
        }
        int needInsert = arr[bound];
        int sortedBound = bound - 1;
        while (sortedBound >= 0 && needInsert < arr[sortedBound]) {
            System.arraycopy(arr, sortedBound, arr, sortedBound + 1, 1);
            sortedBound--;
        }
        if (sortedBound != bound - 1) {
            arr[sortedBound + 1] = needInsert;
        }
        insertSortRec(arr, bound + 1);
    }

    /**
     * 数组第0开始.
     * @param arr
     */
    public static void heapSort(int[] arr) {
        int lenth = arr.length - 1;
        for (int i = (lenth - 1) >> 1; i >= 0; i--) {
            heapify(arr, lenth, i);
        }
        for (int i = lenth; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i - 1, 0);
        }
    }

    private static void heapify(int[] arr, int size, int index) {
        while (true) {
            int max = index;
            int leftChild = (index << 1) + 1;
            int rightChild = leftChild + 1;
            if (leftChild < size && arr[max] < arr[leftChild]) {
                max = leftChild;
            }
            if (rightChild < size && arr[max] < arr[rightChild]) {
                max = rightChild;
            }
            if (max != index) {
                swap(arr, index, max);
                index = max;
            } else {
                break;
            }
        }
    }

    private static void swap(int[] arr, int index, int max) {
        int temp = arr[max];
        arr[max] = arr[index];
        arr[index] = temp;
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr, min, i);
            }
        }
    }

    public static void selectSortRec(int[] arr, int bound) {
        if (bound >= arr.length - 1) {
            return;
        }
        int min = bound;
        for (int i = bound; i < arr.length; i++) {
            if (arr[i] < arr[bound]) {
                min = i;
            }
        }
        if (min != bound) {
            swap(arr, min, bound);
        }
        selectSortRec(arr, bound + 1);
    }

    public static void bubbleSort(int[] arr) {
        List<String> aa = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j + 1, j);
                    aa.add("(" + arr[j] + ", " + arr[j + 1] + ")");
                }
            }
        }
        System.out.println(aa);
        System.out.println(aa.size());
    }

    public static void bubbleSortPlus(int[] arr) {
        int bound = arr.length - 1;
        while (true) {
            int sentinel = 0;
            for (int i = 0; i < bound; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i + 1, i);
                    sentinel = i;
                }
            }
            bound = sentinel;
            if (bound == 0) {
                break;
            }
        }
    }

    /**
     * 递归
     * @param arr
     */
    public static void bubbleSortRec(int[] arr) {
        bubbleSortRec(arr, arr.length - 1);
    }

    private static void bubbleSortRec(int[] arr, int bound) {
        if (bound == 0) {
            return;
        }
        int j = 0;
        for (int i = 0; i < bound; i++) {
            if (arr[i] > arr[i + 1]) {
                swap(arr, i + 1, i);
                j = i;
            }
        }

        bubbleSortRec(arr, j);
    }
}
