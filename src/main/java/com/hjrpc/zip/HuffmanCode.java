package com.hjrpc.zip;

import java.util.*;

/**
 * Huffman编码
 */
public class HuffmanCode {
    public static void main(String[] args) {
//        String source = "i like like like java do you like a java";
//        //统计所有char出现的次数
//        Map<Byte, Integer> map = countTimes(source);
//        //根据统计的出现次数构建一个Huffman树
//        Node root = generateHuffmanTreeNode(map);
//        //根据Huffman树获取不重复的路径编码  左为0  右为1,即是编码表
//        Map<Byte, String> huffmanCode = getHuffmanCode(root);
//        //按照Huffman编码进行压缩,生成编码后字符串
//        String code = zip(source, huffmanCode);
//        System.out.println(code);
//        System.out.println(code.length());
//        //将字符串截取存储到byte[],一个byte可以存储8位
//
//        Byte[] bytes = convert(code);
        System.out.println(Integer.parseInt("10011",2));
    }
    /*
   1.java中变量都是以补码的形式保存的
   2.int 在java中是32位， byte是8位。
   3.原码，反码，补码简介
       原码：就是二进制码，最高位为符号位，0表示正数，1表示负数，剩余部分表示真值。
       反码：在原码的基础上，正数反码就是他本身，负数除符号位之外全部按位取反。
       补码：正数的补码就是自己本身， 负数的补码是在自身反码的基础上加1.
   */
    private static Byte[] convert(String code) {
        //如果长度是9则,需要2位来存储,我们
        int len = (code.length() + (8 - 1)) / 8;
        Byte[] bytes = new Byte[len];
        int index = 0;
        for (int i = 0; i < code.length(); i+=8) {
            String substring = null;
            //如果当前下标,再往后推7个数,即是包括自己一共8个元素,得到的下标,超过字符串长度,说明已经到最后一个字节了
            if (i + 7 > code.length() - 1) {
                //最后一个字节,切割到最后
                substring = code.substring(i);
            } else {
                //不是最后一个字节,都是满8位的
                substring = code.substring(i, 8);
            }
            //将二进制字符串解析成Integer(32位,4个字节)
            int intVal = Integer.parseInt(substring, 2);
            //这里强转,直接舍弃前面3个字节,只留下最后一个字节
            bytes[index++] = (byte) intVal;
        }
        return bytes;
    }

    private static String zip(String source, Map<Byte, String> huffmanCode) {
        StringBuilder sb = new StringBuilder();
        byte[] bytes = source.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(huffmanCode.get(bytes[i]));
        }

        return sb.toString();
    }

    private static Map<Byte, String> huffmanCode = new HashMap<>();

    private static Map<Byte, String> getHuffmanCode(Node root) {
        //这里使用中序遍历遍历Huffman树
        if (root.left != null) {
            getCode(root.left, "0", "");
        }
        if (root.right != null) {
            getCode(root.right, "1", "");
        }
        return huffmanCode;
    }

    private static void getCode(Node node, String str, String source) {
        source += str;
        //如果是叶子节点
        if (node.val != null) {
            huffmanCode.put(node.val, source);
        }
        if (node.left != null) {
            getCode(node.left, "0", source);
        }

        if (node.right != null) {
            getCode(node.right, "1", source);
        }
    }

    private static Node generateHuffmanTreeNode(Map<Byte, Integer> map) {
        List<Node> list = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getValue(), entry.getKey());
            list.add(node);
        }
        return buildHuffmanTree(list);
    }


    public static Map<Byte, Integer> countTimes(String source) {
        //把字符串转为bytes数组,其中每个byte存储一个char,可以查询ASCII表
        byte[] bytes = source.getBytes();
        Map<Byte, Integer> map = new HashMap<>();
        for (int i = 0; i < bytes.length; i++) {
            Integer times = map.get(bytes[i]);
            //如果没有统计过,则初始化times为0次
            if (times == null) {
                times = 0;
            }
            //统计次数+1
            times++;
            //放入map,如果已经存在会覆盖
            map.put(bytes[i], times);
        }
        return map;
    }


    private static Node buildHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序List
            Collections.sort(nodes);

            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.weight + right.weight, null);
            parent.left = left;
            parent.right = right;

            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    static class Node implements Comparable<Node> {

        public int weight;
        public Byte val;
        public Node left;
        public Node right;

        public Node(int weight, Byte val) {
            this.weight = weight;
            this.val = val;
        }


        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "weight=" + weight +
                    '}';
        }

        public void preOrder() {
            System.out.print(" " + weight + "[" + val + "] ");
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }
    }
}
