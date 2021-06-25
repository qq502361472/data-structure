package com.hjrpc.tree;

/**
 * 二叉排序的实现
 */
public class BinarySortTreeMain {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree tree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            tree.add(arr[i]);
        }

//        tree.delete(2);
//        tree.delete(1);
//        tree.delete(5);
//        tree.delete(9);
//        tree.delete(12);
        tree.delete(3);
//        tree.delete(10);
//        tree.delete(7);
        tree.infixOrder();
    }

    static class BinarySortTree {
        Node root;

        public void delete(int delVal) {
            if (root == null) {
                System.out.println("delVal[" + delVal + "] is not exists.");
                return;
            }

            Node node = root.search(delVal);
            if (node == null) {
                System.out.println("delVal[" + delVal + "] is not exists.");
                return;
            }

            Node parent = root.searchParent(delVal);
            //如果要删除的节点是叶子节点
            if (node.left == null && node.right == null) {
                if (parent == null) {//说明要删除的节点是root节点
                    root = null;
                } else if (parent.left != null && parent.left.val == delVal) {//如果当前节点是父节点的左子节点
                    parent.left = null;
                } else {//当前节点是父节点的右子节点
                    parent.right = null;
                }
            } else if (node.left != null && node.right != null) {//有两个子节点
                int cur = delAndGetLowerNode(node);
                node.val = cur;
            } else {//如果当前节点只有一个子节点
                if (node.left != null) {
                    if (parent == null) {
                        root = node.left;
                    } else {//只有左节点
                        if (parent.left != null && parent.left.val == delVal) {
                            parent.left = node.left;
                        } else {
                            parent.right = node.left;
                        }
                    }
                } else {
                    if (parent == null) {
                        root = node.right;
                    } else {//只有右节点
                        if (parent.left != null && parent.left.val == delVal) {
                            parent.left = node.right;
                        } else {
                            parent.right = node.right;
                        }
                    }
                }
            }
        }

        private int delAndGetLowerNode(Node node) {
            Node parent = node;
            while (node.right != null) {
                parent = node;
                node = node.right;
            }
            parent.right = null;
            return node.val;
        }


        /**
         * 二叉排序树的创建
         *
         * @param addVal
         */
        public void add(int addVal) {
            if (root == null) {
                root = new Node(addVal);
            } else {
                root.add(addVal);
            }
        }

        public void infixOrder() {
            if (root == null) {
                System.out.println("tree is empty.");
                return;
            }
            root.infixOrder();
            System.out.println();
        }
    }

    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }

        public void infixOrder() {
            if (this.left != null) {
                this.left.infixOrder();
            }
            System.out.print(" " + val + " ");
            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        public void add(int addVal) {
            //如果当前节点大于 添加的值,说明因为添加在它的左边
            if (this.val > addVal) {
                //如果左子树为空,则当前值就为当前子树
                if (this.left == null) {
                    this.left = new Node(addVal);
                } else {//否则,继续进行添加逻辑
                    this.left.add(addVal);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(addVal);
                } else {
                    this.right.add(addVal);
                }
            }
        }

        public Node search(int delVal) {
            if (val == delVal) {
                return this;
            }
            if (this.left != null && val > delVal) {
                return this.left.search(delVal);
            } else if (this.right != null && val < delVal) {
                return this.right.search(delVal);
            } else {
                return null;
            }
        }

        public Node searchParent(int delVal) {
            if ((this.left != null && this.left.val == delVal)
                    || (this.right != null && this.right.val == delVal)) {
                return this;
            } else if (this.val > delVal && this.left != null) {
                return this.left.searchParent(delVal);
            } else if (this.val < delVal && this.right != null) {
                return this.right.searchParent(delVal);
            } else {
                return null;
            }
        }
    }
}
