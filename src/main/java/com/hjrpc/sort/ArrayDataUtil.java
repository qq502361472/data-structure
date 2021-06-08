package com.hjrpc.sort;

import java.util.Arrays;

public class ArrayDataUtil {

    public static int[] getSimpleArray(int length){
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[i] = (int)(Math.random()*100);
        }
        return res;
    }

    public static int[] getAdvanceArray(int length){
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[i] = (int)(Math.random()*80000);
        }
        return res;
    }

    public static void show(int[] arr) {
        Arrays.stream(arr).forEach((x)->{
            System.out.print(" "+x+" ");
        });
        System.out.println();
    }

    public static void swap(int[] arr,int index1,int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
