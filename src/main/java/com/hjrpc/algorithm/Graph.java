package com.hjrpc.algorithm;

import java.util.Arrays;

class Graph {
    //顶点
    char[] vertexes;
    //权重
    int[][] weight;
    //边的数量
    int borderSize;

    int noBorderVal;

    public Graph(int num) {
        borderSize = 0;
        vertexes = new char[num];
        weight = new int[num][num];
        for (int i = 0; i < num; i++) {
            vertexes[i] = (char) ('A' + i);
        }
    }

    public void addBorder(char a, char b, int w) {
        weight[getIndex(a)][getIndex(b)] = w;
        //因为是无向图
        weight[getIndex(b)][getIndex(a)] = w;
        borderSize++;
    }

    public int getIndex(char ch) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    public void show() {
        System.out.println("顶点:");
        System.out.println(Arrays.toString(vertexes));
        System.out.println("边:");
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight[i].length; j++) {
                String str = weight[i][j] + "";
                if (weight[i][j] == noBorderVal) {
                    str = "-";
                }
                System.out.print(" " + str + " ");
            }
            System.out.println();
        }
    }

    /**
     * 设置不连通的顶点权值为指定值,这里包含顶点和顶点自己的连线
     * @param maxValue
     */
    public void updateNoBorderValue(int maxValue) {
        this.noBorderVal = maxValue;
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight[i].length; j++) {
                if (weight[i][j] == 0) {
                    weight[i][j] = noBorderVal;
                }
            }
        }
    }
}