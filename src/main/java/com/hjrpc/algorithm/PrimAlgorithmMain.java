package com.hjrpc.algorithm;

public class PrimAlgorithmMain {
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

        System.out.println("最短公里:" + getLowerWeight(graph));
    }

    private static int getLowerWeight(Graph graph) {
        int len = 0;
        boolean[] visited = new boolean[graph.vertexes.length];
        //默认从A开始找,设置A为已访问
        visited[graph.getIndex('A')] = true;

        //这里从第二个顶点开始遍历,找一个最短的路线
        for (int k = 1; k < graph.vertexes.length; k++) {
            int v1 = -1, v2 = -1;//最小的边对应的两个顶点的下标,这里初始化为-1
            int minWeight = Integer.MAX_VALUE;
            for (int i = 0; i < graph.vertexes.length; i++) {
                for (int j = 0; j < graph.vertexes.length; j++) {
                    if (visited[i] == true && visited[j] == false && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        v1 = i;
                        v2 = j;
                    }

                }
            }
            visited[v2] = true;
            System.out.println("边(" + graph.vertexes[v1] + "-" + graph.vertexes[v2] + ")权[" + minWeight + "]");
            len += minWeight;
        }

        return len;
    }


}
