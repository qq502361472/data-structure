package com.hjrpc.algorithm;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法,求最短距离
 */
public class DijkstraAlgorithmMain {
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

//        dijkstra('G');
        dijkstra('C');

    }

    //代表不通的路
    static final int INF = Byte.MAX_VALUE;
    //图
    static Graph graph;
    //节点是否被访问过
    private static boolean[] visited;
    //当前节点到各个顶点的最短距离
    private static int[] dis;
    //前驱节点,0代表没有前驱节点
    private static int[] preNode;

    private static void showDijkstra() {
        System.out.println(Arrays.toString(visited));
        System.out.println(Arrays.toString(dis));
        System.out.println(Arrays.toString(preNode));
    }

    private static void dijkstra(char v) {
        //初始化
        init(v);
        //获取出发顶点的下标
        int index = graph.getIndex(v);
        //设置出发顶点已经被访问
        visited[index] = true;
        //遍历顶点v到各个节点的边,即是graph.weight[index],更新dis和preNode
        for (int i = 0; i < graph.weight[index].length; i++) {
            int cur = i == index ? 0 : graph.weight[index][i];
            //初始化dis数组的值
            dis[i] = cur;
            //设置当前节点的前驱节点为节点v的下标
            preNode[i] = index;
        }
        showDijkstra();
        for (int i = 1; i < graph.weight[index].length; i++) {
            updateNode();
        }
        showDijkstra();


    }

    private static void updateNode() {
        //获取新的顶点
        int newIndex = getNewIndex();
        //设置当前顶点为已访问
        visited[newIndex] = true;
        //起点到前驱节点的距离
        int preDis = dis[newIndex];
        //遍历新顶点v到各个节点的边,即是graph.weight[index],更新dis和preNode
        for (int i = 0; i < graph.weight[newIndex].length; i++) {
            //newIndex到当前节点的距离
            int curDis = graph.weight[newIndex][i];
            //起点通过当前路线,到当前节点的距离
            int sumDis = preDis + curDis;
            //如果新路线的距离小于之前的路线,则更新
            if (sumDis < dis[i]) {
                //初始化dis数组的值
                dis[i] = sumDis;
                //设置当前节点的前驱节点为节点v的下标
                preNode[i] = newIndex;
            }
        }
    }

    //在未访问的节点中,选取一个距离最近的顶点作为新的顶点
    private static int getNewIndex() {
        int index = -1;
        int minDis = INF;
        for (int i = 0; i < dis.length; i++) {
            if (!visited[i] && dis[i] < minDis) {
                index = i;
                minDis = dis[i];
            }
        }
        return index;
    }

    private static void init(char v) {
        visited = new boolean[graph.vertexes.length];
        dis = new int[graph.vertexes.length];
        preNode = new int[graph.vertexes.length];
    }
}

