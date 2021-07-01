package com.hjrpc.algorithm;

/**
 * 汉诺塔问题解决,使用分而治之的思想
 */
public class HanoiTowerProblemMain {
    static int times=0;
    public static void main(String[] args) {
        playHanoiTower(26, 'A', 'B', 'C');
        System.out.println(times);
    }

    /**
     * 开始玩汉诺塔
     *
     * @param num 汉罗塔的层数
     * @param a   塔A
     * @param b   塔B
     * @param c   塔C
     */
    private static void playHanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            times++;
            System.out.println("从塔" + a + "->" + c);
            return;
        }

        if (num >= 2) {//都看成是2个,最下面的一个,和上面的部分
            //把上面的部分,从a移动到b塔,借助c塔
            playHanoiTower(num - 1, a, c, b);
            times++;
            //把最后一个从a移动到c塔
            System.out.println("从塔" + a + "->" + c);
            //把上面的部分从b移动到c塔,借助a塔
            playHanoiTower(num - 1, b, a, c);
        }
    }
}
