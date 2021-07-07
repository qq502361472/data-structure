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
     * @param args
     */
    public static void main(String[] args) {
        Graph graph = new Graph(7);
        graph.addBorder('A','B',5);
        graph.addBorder('A','G',2);
        graph.addBorder('A','C',7);
        graph.addBorder('B','G',3);
        graph.addBorder('B','D',9);
        graph.addBorder('C','E',8);
        graph.addBorder('G','E',4);
        graph.addBorder('G','F',6);
        graph.addBorder('E','F',5);
        graph.addBorder('D','F',4);

        graph.updateNoBorderValue(Integer.MAX_VALUE);
        graph.show();
    }


}
