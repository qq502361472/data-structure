package com.hjrpc.algorithm;

import java.util.Arrays;

/**
 * 查找字符串,KMP算法
 */
public class KMPMain {
    public static void main(String[] args) {
        //               012345678901234567890
        String source = "BBCABCDABABCDABCDABDE";
        String target = "ABABAA";
//        String target = "ABCDABD";
        int index = violenceMatch(source, target);
        System.out.println(index);
        int index2 = violenceMatchOptimized(source, target);
        System.out.println(index2);

        int index3 = KMPMatch(source, target);
        System.out.println(index3);
    }

    private static int KMPMatch(String source, String target) {
        int[] next = KMPNext(target);
        System.out.println(Arrays.toString(next));
        for (int i = 0, j = 0; i < source.length(); i++) {
            //B B C A B C D A B A B C D A B C D A B D E
            //                  i
            //                  j
            //      A B C D A B D
            //此时A 和D 不相等,我们让j=next[j-1]=2,也就是 子串的 A B  和  后面的 A B相同,因为当前串不相等,所以第一个 A B匹配失败
            // 现在我们从第二个 A B开始匹配,相同与子串移动到当前位置,如下图,其中i前2个位置 我们已经比较过就不用比较了
            //                  i
            //                  j
            //              A B C D A B D
            //此时A 和 C 还是不相等,那么j=next[j-1]=0,相当于子串右移到开始
            //B B C A B C D A B A B C D A B C D A B D E
            //                  A B C D A B D  这里就同上了
            //                          A B C D A B D
            while (j > 0 && source.charAt(i) != target.charAt(j)) {
                //B B C A B C D A B A B C D A B C D A B D E
                //                  i
                //                  j
                //      A B C D A B D
                //此时我们需要将子串向右移动 子串长度-公共前后缀长度=j-next[j-1]个位置
                // 即是j的下标=当前下标-移动的位置=j = j-(j-next[j-1]);
                j = next[j - 1];
            }

            //当前缀的最后一个字母 = 后缀的最后一个字母
            if (source.charAt(i) == target.charAt(j)) {
                j++;
            }
            if (j == target.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }


    //暴力匹配
    private static int violenceMatch(String source, String target) {
        for (int i = 0; i < source.length() - target.length() + 1; i++) {
            int s = i;
            int t = 0;
            while (s < source.length() && source.charAt(s) == target.charAt(t)) {
                if (t == target.length() - 1) {
                    //说明匹配完 找到了字符串
                    return s - t;
                } else {
                    s++;
                    t++;
                }
            }
        }
        return -1;
    }

    //暴力匹配优化版
    private static int violenceMatchOptimized(String source, String target) {
        int s = 0;
        int t = 0;
        while (s < source.length() && t < target.length()) {
            if (source.charAt(s) == target.charAt(t)) {
                if (t == target.length() - 1) {
                    //说明匹配完 找到了字符串
                    return s - t;
                } else {
                    s++;
                    t++;
                }
            } else {
                s++;
                t = 0;
            }
        }
        return -1;
    }


    //ABCDABD
    static public int[] KMPNext(String target) {
        char[] chars = target.toCharArray();
        int[] next = new int[chars.length];
        //初始化第一个位置为0，因为只有一个字母的时候，公共前后缀是0
        next[0] = 0;
        //最长公共前后坠
        //j代表后缀的坐标 i代表前缀表达式的
        for (int i = 1, j = 0; i < chars.length; i++) {
            //使用ABABAA来分析
            //发现规则,最长公共前后缀一定是至少有一个重复的字母才有的,我们先找到这第一个字母
            while (j > 0 && chars[i] != chars[j]) {//这里发现不相等 即是如下图
                //     最长公共前后缀 0  0  1  2  3  1
                //                  0  1  2  3  4  5
                //                  A  B  A  B  A  A
                //                     A  B  A  B  A  A
                //                     i
                //                     j
                //         i++   A  B  A  B  A  A
                //                     A  B  A  B  A  A
                //    j++ i++    A  B  A  B  A  A
                //                     A  B  A  B  A  A
                //                              i   后缀末尾
                //                              j   前缀末尾
                //               A  B  A  B  A  A            后缀
                //                     A  B  A  B  A  A      前缀
                //这里匹配是失败,当前长度是3,所以长度再增加肯定是没戏了,肯定是减小,也就是将下面的前缀串往后移动,相当于j减小
                //因为我们知道 前缀串的  A  B  A 的最长公共前后缀是1,所以我们前缀串后移动(j-next[j-1])个位置如下图
                //即是j = j-(j-next[j-1]),则此时第0个字符串我们已经比较过了相等,我们只要比较i和j的值,如果不相等继续前推
                //                              i   后缀末尾
                //                              j   前缀末尾
                //               A  B  A  B  A  A
                //                           A  B  A  B  A  A
                //但是,因为j-1的公共前缀是3,所以我们确定
                //
                //
                j = next[j - 1];
            }

            //当前缀的最后一个字母 = 后缀的最后一个字母,如果一直相等我们直接每次增加1就可以了
            if (chars[i] == chars[j]) {
                j++;

            }
            next[i] = j;
        }
        return next;
    }
}
