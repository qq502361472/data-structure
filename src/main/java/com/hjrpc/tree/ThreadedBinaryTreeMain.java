package com.hjrpc.tree;

/**
 * 线索化的二叉树
 */
public class ThreadedBinaryTreeMain {

    public static void main(String[] args) {
        //      1
        //  3       6
        //8  10   14


    }
    static class Node {
        public int id;
        public Node left;
        public Node right;
        //0-普通左节点 1-线索化的节点
        public int leftType;
        //0-普通右节点 1-线索化的节点
        public int rightType;

        public Node(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    '}';
        }
    }
}
