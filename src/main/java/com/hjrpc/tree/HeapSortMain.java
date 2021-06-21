package com.hjrpc.tree;

import com.hjrpc.sort.ArrayDataUtil;
import com.hjrpc.sort.QuickSort;

/**
 * 堆排序
 */
public class HeapSortMain {

    public static void main(String[] args) {
        int[] simpleArray = ArrayDataUtil.getSimpleArray(10);

        ArrayDataUtil.show(simpleArray);
        heapSort(simpleArray);
        ArrayDataUtil.show(simpleArray);

        long l = System.currentTimeMillis();
        int[] advanceArray1 = ArrayDataUtil.getAdvanceArray(8000000);
        heapSort(advanceArray1);
        System.out.println("堆排序,共耗时:" + (System.currentTimeMillis() - l));
        ArrayDataUtil.show(advanceArray1);

        long l2 = System.currentTimeMillis();
        int[] advanceArray2 = ArrayDataUtil.getAdvanceArray(8000000);
        QuickSort.quickSortFirst(advanceArray2, 0, advanceArray2.length - 1);
        System.out.println("快速排序交换,共耗时:" + (System.currentTimeMillis() - l2));
        ArrayDataUtil.show(advanceArray2);

        long l3 = System.currentTimeMillis();
        int[] advanceArray3 = ArrayDataUtil.getAdvanceArray(8000000);
        QuickSort.partitionSortOptimzed(advanceArray3, 0, advanceArray3.length - 1);
        System.out.println("快速排序优化版本,共耗时:" + (System.currentTimeMillis() - l3));
        ArrayDataUtil.show(advanceArray3);
    }


    private static void heapSort(int[] arr) {
        //构建一个大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        //找到最后一个非叶子节点
        for (int i = arr.length - 1; i > 0; i--) {
            //交换第一个节点和最后一个节点，那么此时这个大顶堆的第一个节点就不符合大顶堆规则了
            ArrayDataUtil.swap(arr, 0, i);
            //这里将第一个节点重新进行构建大顶堆，构建完成后还是大顶堆
            adjustHeap(arr, 0, i);
        }
    }

    //构建大顶堆
    private static void adjustHeap(int[] arr, int i, int length) {

        //k为当前节点的左子节点，一次循环后k指向自己的下一个左子节点
        for (int k = 2 * i + 1; k + 1 < length; k = 2 * k + 1) {
            if (arr[k] > arr[k + 1]) {//如果左子节点小于右子节点，因为是大顶堆，我们需要找到最大的，所以把k执行最大的那个节点
                ArrayDataUtil.swap(arr, k, k + 1);
            }
            if (arr[i] < arr[k + 1]) {//如果当前元素小于其子节点中较大的那个，则交换位置
                //交换节点
                ArrayDataUtil.swap(arr, i, k + 1);
            }
            //两个子节点互相比较，大的靠右
            if (arr[k] > arr[k + 1]) {//如果左子节点小于右子节点，因为是大顶堆，我们需要找到最大的，所以把k执行最大的那个节点
                ArrayDataUtil.swap(arr, k, k + 1);
            }
        }
    }
}
