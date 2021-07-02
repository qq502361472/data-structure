package com.hjrpc.algorithm;

/**
 * 查找字符串,KMP算法
 */
public class KMPMain {
    public static void main(String[] args) {
        //               012345678901234567890
        String source = "BBCABCDABABCDABCDABDE";
        String target = "ABCDABD";
        int index = violenceMatch(source, target);
        System.out.println(index);
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
}
