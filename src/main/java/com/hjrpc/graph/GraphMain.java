package com.hjrpc.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphMain {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] vertexes = {"A", "B", "C", "D", "E"};
        //添加顶点
        for (String vertex : vertexes) {
            graph.addVertex(vertex);
        }

        /*
            添加边
                  A     E
                /   \ /
               C --- B --- D
         */
        graph.addBorder(0, 1, 1);//A-B
        graph.addBorder(1, 2, 1);//B-C
        graph.addBorder(2, 0, 1);//C-A
        graph.addBorder(1, 3, 1);//B-D
        graph.addBorder(1, 4, 1);//B-E

        graph.showVertexes();
        graph.showBorders();
        System.out.print("图的深度遍历:");
        graph.dfs();
    }

    static class Graph {
        List<String> vertexes;//所有的顶点
        int[][] borders;//边
        int borderSize;//边的数量
        boolean[] visited;

        /**
         * @param n 顶点的数量
         */
        public Graph(int n) {
            this.vertexes = new ArrayList<>(n);
            borders = new int[n][n];
            this.borderSize = 0;
            visited = new boolean[n];
        }

        //边的数量
        private int borderSize() {
            return borderSize;
        }

        //顶点的数量
        private int vertexSize() {
            return vertexes.size();
        }

        //新增顶点
        private void addVertex(String vertex) {
            vertexes.add(vertex);
        }

        private void showBorders() {
            for (int[] border : borders) {
                System.out.println(Arrays.toString(border));
            }
        }

        private void showVertexes() {
            System.out.println(vertexes);
        }

        /**
         * 新增边
         *
         * @param from   开始顶点下标
         * @param to     结束顶点下标
         * @param weight 权重
         */
        private void addBorder(int from, int to, int weight) {
            borders[from][to] = weight;
            //因为我们这里是无向图,所以反过来的值也添加上
            borders[to][from] = weight;
            borderSize++;
        }

        //获取下标对应的值
        private String getValueByIndex(int index) {
            return vertexes.get(index);
        }

        /**
         * 图的深度遍历 depth first search
         */
        private void dfs() {
            visited = new boolean[vertexSize()];
            for (int i = 0; i < vertexSize(); i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }
        }

        /**
         * 图的深度遍历 depth first search
         *
         * @param i 当前节点
         */
        private void dfs(int i) {
            //输出当前节点
            System.out.print(getValueByIndex(i) + "->");
            //设置当前节点为已访问
            visited[i] = true;
            //根据当前节点获取它的邻接结点
            int w = getNeighbor(i);
            while (w != -1) {//存在这样的边,进入while
                if (!visited[w]) {
                    dfs(w);
                }
                //从w开始,获取i的邻接结点,然后继续进入循环
                w = getNextNeighbour(i, w);
            }
        }

        /**
         * 从w开始,获取i的邻接结点
         *
         * @param i
         * @param w
         * @return
         */
        private int getNextNeighbour(int i, int w) {
            //w和w之前的下标已经处理过了
            for (int j = w + 1; j < vertexSize(); j++) {
                if (borders[i][j] == 1) {
                    return j;
                }
            }
            return -1;
        }


        /**
         * 首次获取它的邻接结点
         *
         * @param i 当前节点下标
         * @return
         */
        private int getNeighbor(int i) {
            for (int j = 0; j < vertexSize(); j++) {
                if (borders[i][j] == 1) {//获取它的邻接结点
                    return j;
                }
            }
            return -1;
        }

        /**
         * 图的广度遍历 breadth first search
         */
        public void bfs() {
            visited = new boolean[vertexSize()];
            for (int i = 0; i < vertexSize(); i++) {
                if (!visited[i]) {
                    bfs(i);
                }
            }
        }

        /**
         * 图的广度遍历 breadth first search
         */
        private void bfs(int i) {

        }
    }
}
