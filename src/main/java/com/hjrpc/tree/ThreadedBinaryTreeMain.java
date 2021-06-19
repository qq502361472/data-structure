package com.hjrpc.tree;

/**
 * 线索化的二叉树
 */
public class ThreadedBinaryTreeMain {

    public static void main(String[] args) {
        //      1
        //  3       6
        //8  10   14
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(6);
        Node node4 = new Node(8);
        Node node5 = new Node(10);
        Node node6 = new Node(14);
        Node root = node1;
        ThreadedBinaryTree tree = new ThreadedBinaryTree(root);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
    }

    static class ThreadedBinaryTree {

        Node root;

        public ThreadedBinaryTree(Node root) {
            this.root = root;
        }
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
