package com.hjrpc.tree;

/**
 * 二叉树的前序,中序,后序遍历
 */
public class BinaryTreeMain {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        /*
                 1
                / \
               2   3
                  / \
                 4   5
         */

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        BinaryTree binaryTree = new BinaryTree(node1);
        binaryTree.preOrder();   //1-2-3-4-5
        binaryTree.infixOrder(); //2-1-4-3-5
        binaryTree.postOrder();  //2-4-5-3-1
    }


    static class BinaryTree {
        public Node root;

        public BinaryTree(Node root) {
            this.root = root;
        }

        //前序遍历
        public void preOrder() {
            System.out.print("前序遍历:");
            if (root == null) {
                System.out.println("二叉树是空的...");
                return;
            }
            root.preOrder();
            System.out.println();
            System.out.println("--------------------");
        }

        //中序遍历
        public void infixOrder() {
            System.out.print("中序遍历:");
            if (root == null) {
                System.out.println("二叉树是空的...");
                return;
            }
            root.infixOrder();
            System.out.println();
            System.out.println("--------------------");
        }

        //后序遍历
        public void postOrder() {
            System.out.print("后序遍历:");
            if (root == null) {
                System.out.println("二叉树是空的...");
                return;
            }
            root.postOrder();
            System.out.println();
            System.out.println("--------------------");
        }
    }

    static class Node {
        public int id;
        public Node left;
        public Node right;

        public Node(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    '}';
        }

        //前序遍历
        public void preOrder() {
            System.out.print(" "+id+" ");
            if (left != null) {
                left.preOrder();
            }
            if (right != null) {
                right.preOrder();
            }
        }

        //中序遍历
        public void infixOrder() {
            if (left != null) {
                left.infixOrder();
            }
            System.out.print(" "+id+" ");
            if (right != null) {
                right.infixOrder();
            }
        }

        //后序遍历
        public void postOrder() {
            if (left != null) {
                left.postOrder();
            }
            if (right != null) {
                right.postOrder();
            }
            System.out.print(" "+id+" ");
        }
    }
}
