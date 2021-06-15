package com.hjrpc.search;

import com.hjrpc.sort.ArrayDataUtil;
import com.hjrpc.sort.QuickSort;

/**
 * 插入查找
 */
public class InsertSearch {
    public static int times = 0;

    public static void main(String[] args) {
        int[] arr = {-12, -7, -3, -2, 1, 3, 5, 9, 12, 16};
//        System.out.println(insertSearchMain(arr, -12));

        int[] advanceArray = ArrayDataUtil.getAdvanceArray(80000);
        QuickSort.quickSortOptimized(advanceArray,0,advanceArray.length-1);
        System.out.println("insertSearchMain:"+insertSearchMain(advanceArray, 10086));
        System.out.println("----------------------------------------");

        System.out.println("binarySearchMain:"+BinarySearch.binarySearchMain(advanceArray, 10086));
    }

    public static int insertSearchMain(int[] arr, int val) {
        times = 0;
        int start = 0;
        int end = arr.length - 1;
        int res = insertSearch(arr, start, end, val);
        System.out.printf("insertSearchMain:times[%d],index[%d]\t\n", times, res);
        return res;
    }

    private static int insertSearch(int[] arr, int start, int end, int val) {
        times++;
        if (val < arr[start] || val > arr[end]) {
            return -1;
        }
        //插入排序
        int middle = start + (int)((Double.valueOf(val - arr[start]) / Double.valueOf(arr[end] - arr[start])) * (end - start));
        if (arr[middle] == val) {
            return middle;
        }
        if (arr[middle] > val) {
            return insertSearch(arr, start, middle - 1, val);
        }
        return insertSearch(arr, middle + 1, end, val);
    }
}
