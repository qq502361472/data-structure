package com.hjrpc.algorithm;

import java.util.Arrays;

public class KruskalAlgorithmMain {

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

    /**
     * 假设有7个村庄,要在这7个村庄之间修路,求连通所有村庄的最短的公里数
     * 按边的长度进行排序,先添加小的边,保证每次添加的边的终点不同
     *
     * @param args
     */
    public static void main(String[] args) {
        Graph graph = new Graph(7);
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
        graph.updateNoBorderValue(Integer.MAX_VALUE);
        graph.show();

        System.out.println(Arrays.toString(kruskalLowerWeight(graph)));

    }

    private static Border[] kruskalLowerWeight(Graph graph) {
        //获取所有的边
        Border[] borders = getBorders(graph);
        System.out.println("排序前:" + Arrays.toString(borders));
        //排序
        Arrays.sort(borders, (x, y) -> x.weight - y.weight);
        System.out.println("排序后:" + Arrays.toString(borders));

        //记录各个顶点的终点,如果值为0表示顶点是自己,其他值表示终点是graph.vertexes[value],默认顶点都是自己,不用从初始化
        int[] ends = new int[graph.vertexes.length];
        //定义一个容器存储我们的最小生成树,这里还不确定需要多少条边,先给容器留上所有边的长度
        Border[] lowerTree = new Border[graph.borderSize];

        int index = 0;
        for (Border border : borders) {
            //开始顶点
            char p1 = border.from;
            //结束顶点
            char p2 = border.to;

            int end1 = getEnd(p1, ends, graph);
            int end2 = getEnd(p2, ends, graph);
            //如果两个终点不相等,说明没有构成通路,则可以添加边
            if (end1 != end2) {
                lowerTree[index++] = border;
                //修改较小的顶点对应的终点为较大的终点值
                ends[end1] = end2;
            }
        }
        return lowerTree;
    }

    /**
     * 获取顶点对应的终点下标
     *
     * @param p
     * @param ends
     * @param graph
     * @return
     */
    private static int getEnd(char p, int[] ends, Graph graph) {
        int index = graph.getIndex(p);
        while (ends[index] != 0) {
            index = ends[index];
        }
        return index;
    }

    static public Border[] getBorders(Graph graph) {
        Border[] borders = new Border[graph.borderSize];
        int index = 0;
        for (int i = 0; i < graph.weight.length; i++) {
            //无向图,不包含反方向的
            for (int j = i + 1; j < graph.weight[i].length; j++) {
                if (graph.weight[i][j] != Integer.MAX_VALUE) {
                    borders[index++] = new Border(graph.vertexes[i], graph.vertexes[j], graph.weight[i][j]);
                }
            }
        }

        return borders;
    }

    static class Border {
        char from;
        char to;
        int weight;

        public Border(char from, char to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "<" + from +
                    ", " + to +
                    "> =" + weight;
        }
    }
}
