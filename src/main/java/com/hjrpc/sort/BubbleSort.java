package com.hjrpc.sort;

public class BubbleSort {
    public static void main(String[] args) {
        int[] simpleArray = ArrayDataUtil.getSimpleArray(10);
        bubbleSort(simpleArray);
        ArrayDataUtil.show(simpleArray);

        long l = System.currentTimeMillis();
        bubbleSort(ArrayDataUtil.getAdvanceArray(80000));
        System.out.println("冒泡排序,共耗时:" + (System.currentTimeMillis() - l));

        long l2 = System.currentTimeMillis();
        bubbleSortOptimized(ArrayDataUtil.getAdvanceArray(80000));
        System.out.println("冒泡排序优化的,共耗时:" + (System.currentTimeMillis() - l2));

    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    ArrayDataUtil.swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void bubbleSortOptimized(int[] arr) {
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    ArrayDataUtil.swap(arr, j, j + 1);
                }
            }
            if (flag) {//发生过交换,重置
                flag = false;
            } else {//没有发生过交换
                break;
            }
        }
    }

}
