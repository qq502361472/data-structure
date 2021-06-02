package com.hjrpc.array;

public class SparseArray {
    static int col = 11;
    static int row = 11;

    public static void main(String[] args) {
        int[][] arr = getArrayData(row, col);
        show(arr);

        int[][] sparseArray = generateSparseArray(arr);
        show(sparseArray);
    }

    private static int[][] generateSparseArray(int[][] arr) {
        int count = 0;
        for (int[] tempArray : arr) {
            for (int item : tempArray) {
                if (item != 0) {
                    count++;
                }
            }
        }

        int[][] sparseArray = new int[count + 1][3];
        sparseArray[0][0] = row;
        sparseArray[0][1] = col;
        sparseArray[0][2] = count;

        int temp = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    sparseArray[temp][0] = i;
                    sparseArray[temp][1] = j;
                    sparseArray[temp][2] = arr[i][j];
                    temp++;
                }
            }
        }
        return sparseArray;
    }

    private static void show(int[][] arr) {
        for (int[] tempArray : arr) {
            for (int item : tempArray) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }
    }

    private static int[][] getArrayData(int row, int col) {
        int[][] ints = new int[row][col];
        ints[2][2] = 1;
        ints[3][3] = 2;
        return ints;
    }

}
