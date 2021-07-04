package com.hjrpc.algorithm;

import java.util.Arrays;

/**
 * 查找字符串,KMP算法
 */
public class KMPMain {
    public static void main(String[] args) {
        //               012345678901234567890
        String source = "BBCABCDABABCDABCDABDE";
        String target = "ABCDABB";
//        int index = violenceMatch(source, target);
//        System.out.println(index);
        int[] ints = KMPNext(target);
        System.out.println(Arrays.toString(ints));
    }

    private static int violenceMatch(String source, String target) {
        for (int i = 0; i < source.length() - target.length() + 1; i++) {
            int s = i;
            int t = 0;
            while (s < source.length()) {
                if (source.charAt(s) == target.charAt(t)) {
                    if (t == target.length() - 1) {
                        //说明匹配完 找到了字符串
                        return s - t;
                    } else {
                        s++;
                        t++;
                    }
                } else {
                    t = 0;
                    break;
                }
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
        //i代表前缀的最后一个下标，因为是从1开始，所以前缀的长度也是i
        for (int i = 1,j = 0; i < chars.length; i++) {
            while (j > 0 && chars[i] != chars[j]) {
                //这里看不懂
                j = next[j - 1];
            }

            //当前缀的最后一个字母 = 后缀的最后一个字母
            if (chars[i] == chars[j]) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
