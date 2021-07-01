package com.hjrpc.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 图的优化版深度遍历和广度遍历
 */
public class GraphOptimizedMain {
    public static void main(String[] args) {
        GraphOptimized graph = new GraphOptimized(5);
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
        System.out.println("深度遍历");
        graph.dfs();
        System.out.println("广度遍历");
        graph.bfs();

        System.out.println(graph.borderSize());
    }

    static class GraphOptimized {
        private List<String> vertexes;//所有的顶点
        private List<List<Integer>> borders;//边
        private int borderSize;//边的数量

        /**
         * @param n 顶点的数量
         */
        public GraphOptimized(int n) {
            this.vertexes = new ArrayList<>(n);
            this.borders = initBorders(n);
            this.borderSize = 0;
        }

        private List<List<Integer>> initBorders(int n) {
            List<List<Integer>> result = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                result.add(new ArrayList<>());
            }
            return result;
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
            borders.get(from).add(to);
            borders.get(to).add(from);
            borderSize++;
        }

        //获取下标对应的值
        private String getValueByIndex(int index) {
            return vertexes.get(index);
        }

        public void showBorders() {
            for (int i = 0; i < borders.size(); i++) {
                List<Integer> list = borders.get(i);
                System.out.println("元素:" + getValueByIndex(i) + "相连的节点:"
                        + list.stream().map(x -> getValueByIndex(x)).collect(Collectors.toList()));
            }
        }

        //深度遍历
        private void dfs() {
            boolean[] isVisited = new boolean[vertexSize()];
            dfs(isVisited, 0);
        }

        /**
         * 元素:A相连的节点:[B, C]
         * 元素:B相连的节点:[A, C, D, E]
         * 元素:C相连的节点:[B, A]
         * 元素:D相连的节点:[B]
         * 元素:E相连的节点:[B]
         *
         * @param isVisited
         * @param index
         */
        private void dfs(boolean[] isVisited, int index) {
            System.out.println("访问节点:" + getValueByIndex(index));
            isVisited[index] = true;
            List<Integer> integers = borders.get(index);
            for (int i = 0; i < integers.size(); i++) {
                Integer pre = integers.get(i);
                if (!isVisited[pre]) {
                    dfs(isVisited, pre);
                }
            }
        }

        /**
         * 广度遍历
         * 元素:A相连的节点:[B, C]
         * 元素:B相连的节点:[A, C, D, E]
         * 元素:C相连的节点:[B, A]
         * 元素:D相连的节点:[B]
         * 元素:E相连的节点:[B]
         */
        private void bfs() {
            boolean[] isVisited = new boolean[vertexSize()];
            LinkedList<Integer> queue = new LinkedList();
            System.out.println("访问节点:" + getValueByIndex(0));
            isVisited[0] = true;
            queue.addLast(0);
            while (!queue.isEmpty()) {
                Integer integer = queue.removeFirst();
                List<Integer> list = borders.get(integer);
                for (int i = 0; i < list.size(); i++) {
                    //获取对应的顶点下标
                    Integer v = list.get(i);
                    if (!isVisited[v]) {
                        isVisited[v] = true;
                        System.out.println("访问节点:" + getValueByIndex(v));
                        queue.addLast(v);
                    }
                }
            }
        }
    }
}
