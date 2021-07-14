package com.hjrpc.algorithm;

import java.util.Arrays;
import java.util.Stack;

public class FloydAlgorithmMain {
    //图
    static Graph graph;
    //代表不通的路
    static final int INF = Byte.MAX_VALUE;

    public static void main(String[] args) {
        graph = new Graph(7);
        graph.addBorder('A', 'B', 5);
        graph.addBorder('A', 'G', 2);
        graph.addBorder('A', 'C', 7);
        graph.addBorder('B', 'G', 3);
        graph.addBorder('B', 'D', 9);
        graph.addBorder('C', 'E', 8);
        graph.addBorder('G', 'E', 4);
        graph.addBorder('G', 'F', 6);
        graph.addBorder('E', 'F', 5);
        graph.addBorder('D', 'F', 4);
        graph.updateNoBorderValue(INF);
        graph.show();

        /*
                   A ----5---- B
                  /  \       /   \
                7     2     3     9
               /       \  /        \
             C          G          D
              \        / \        /
               8     4    6     4
                \  /       \  /
                 E ----5---- F

     */
        floyd();
        System.out.println(dis[0][5]);
        showMinDisAndPath('C', 'D');
    }

    private static void showMinDisAndPath(char from, char to) {
        int f = graph.getIndex(from);
        int t = graph.getIndex(to);
        System.out.println("最短路径长度{" + dis[f][t] + "}");
        Stack<Integer> stack = new Stack<>();
        stack.add(t);
        int temp = t;
        while (temp != f) {
            temp = pre[f][temp];
            stack.add(temp);
        }
        while (!stack.isEmpty()){
            Integer pop = stack.pop();
            System.out.print(graph.vertexes[pop]+"->");
        }
        System.out.println();
    }

    private static void floyd() {
        //初始化前驱顶点和dis数组
        init();
        //k代表中间顶点
        for (int k = 0; k < graph.vertexes.length; k++) {
            //i代表起点
            for (int i = 0; i < dis.length; i++) {
                //j代表终点
                for (int j = 0; j < dis[i].length; j++) {
                    //如果i到j的距离 > i到k的距离 + k到j的距离
                    //更新i到j的最短距离,更新i到j的前驱节点为k节点,但是k节点有可能路径是通过别的节点到达,我们去k到j的前驱节点
                    if (dis[i][j] > dis[i][k] + dis[k][j]) {
                        dis[i][j] = dis[i][k] + dis[k][j];
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }

    //初始化前驱顶点
    private static void init() {
        pre = new int[graph.vertexes.length][graph.vertexes.length];
        for (int i = 0; i < pre.length; i++) {
            Arrays.fill(pre[i], i);
        }
        dis = graph.weight;
    }

    static int[][] dis;
    static int[][] pre;
}
