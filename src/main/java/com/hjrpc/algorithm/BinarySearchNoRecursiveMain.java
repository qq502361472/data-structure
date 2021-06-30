package com.hjrpc.algorithm;

/**
 * 二分查找 非递归
 */
public class BinarySearchNoRecursiveMain {

    public static void main(String[] args) {
        int[] arr = {-12, -7, -3, -2, 1, 3, 5, 9, 12, 16};
        System.out.println(binarySearchNoRecursive(arr, 2));
    }

    private static int binarySearchNoRecursive(int[] arr, int dst) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == dst) {
                return mid;
            }

            if (arr[mid] > dst) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
