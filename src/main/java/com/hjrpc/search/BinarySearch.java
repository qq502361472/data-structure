package com.hjrpc.search;

/**
 * 二分查找
 */
public class BinarySearch {
    public static int times = 0;

    public static void main(String[] args) {
        int[] arr = {-12, -7, -3, -2, 1, 3, 5, 9, 12, 16};
        System.out.println(binarySearchMain(arr, -12));

    }

    public static int binarySearchMain(int[] arr, int val) {
        times = 0;
        int start = 0;
        int end = arr.length - 1;
        int res = binarySearch(arr, start, end, val);
        System.out.printf("binarySearchMain:times[%d],index[%d]\t\n", times, res);
        return res;
    }

    private static int binarySearch(int[] arr, int start, int end, int val) {
        times++;
        if (val < arr[start] || val > arr[end]) {
            return -1;
        }

        int middle = (start + end) / 2;
        if (arr[middle] == val) {
            return middle;
        }
        if (arr[middle] > val) {
            return binarySearch(arr, start, middle - 1, val);
        }
        return binarySearch(arr, middle + 1, end, val);
    }
}
