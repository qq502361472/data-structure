package com.hjrpc.dynamic;

import java.util.Arrays;

public class KnapsackProblem {
    public static void main(String[] args) {
        //物品的重量
        int[] weight = {1, 3, 4};
        //物品的单价
        int[] price = {1500, 2000, 3000};
        String[] names = {"吉他", "音响", "电脑"};
        //物品的重量
//        int[] weight = {1, 4, 3};
//        //物品的单价
//        int[] price = {1500, 3000, 2000};
        //背包的容量
        int knapsackCapacity = 4;
        //物品的数量
        int num = price.length;

        //获取具体放入了哪个物品
        int[][] path = new int[price.length + 1][knapsackCapacity + 1];

        int[][] v = new int[price.length + 1][knapsackCapacity + 1];
        //默认第0行第0列都为0
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[i].length; j++) {
                //当前行物品的重量
                int w = weight[i - 1];
                //j代表容量,如果当前物品重量大于容量,则去我们上一个物品放入的值给当前值
                if (w > j) {
                    v[i][j] = v[i - 1][j];
                } else {//此时容量大于等于重量
                    //当前物品的单价
                    int p = price[i - 1];
                    //上一个物品放入的最大价格  <  当前物品价格,加上上一个物品放入,剩余的容量最大值之和
                    //这里取较大者
                    if (v[i - 1][j] < p + v[i - 1][j - w]) {
                        v[i][j] = p + v[i - 1][j - w];
                        //此处等于放入了新的物品
                        System.out.println("放入物品:" + names[i - 1]);
                        path[i][j] = 1;
                    } else {
                        //复制上一个物品的值
                        v[i][j] = v[i - 1][j];
                    }
                }

            }
        }

        for (int i = 0; i < v.length; i++) {
            System.out.println(Arrays.toString(v[i]));
        }
        for (int i = 0; i < path.length; i++) {
            System.out.println(Arrays.toString(path[i]));
        }

        int i = path.length - 1;
        int j = path[i].length - 1;
        System.out.println(v[i][j]);
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("放入物品" + names[i - 1] + ",价值[%d]重量[%d]\n", price[i - 1], weight[i - 1]);
                j = j - weight[i - 1];
            }
            //切记放在if外面,因为,有可能没有找到放入物品,那么我们就往上看放入物品的记录
            i--;
        }
    }
}
