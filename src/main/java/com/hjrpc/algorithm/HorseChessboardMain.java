package com.hjrpc.algorithm;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 马踏棋盘
 */
public class HorseChessboardMain {
    //棋盘
    static int[][] chessboard;
    //当前位置是否访问过
    static boolean[][] visited;
    //行
    static int row;
    //列
    static int col;
    //是否已经找到路线了
    static boolean finished;

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        horseTravelMain(8, 8, new Point(3, 5));
        System.out.println("耗时:" + (System.currentTimeMillis() - l) + "ms");
    }

    private static void horseTravelMain(int row, int col, Point point) {
        //初始化
        init(row, col);
        //思路,使用深度遍历,从当前顶点开始
        int step = 0;
        horseTravel(point, step);
        for (int i = 0; i < chessboard.length; i++) {
            System.out.println(Arrays.toString(chessboard[i]));
        }

    }

    private static void horseTravel(Point point, int step) {
        //记录当前是第几步
        chessboard[point.x][point.y] = step;
        //设置为已访问
        visited[point.x][point.y] = true;
        List<Point> list = getNext(point);
        //使用贪心算法Greedy,递增排列集合,优先取到的就是后续节点可能性更少的方法
        list.sort((p1, p2) -> getNext(p1).size() - getNext(p2).size());
        while (!list.isEmpty()) {
            Point newPoint = list.remove(0);
            if (!visited[newPoint.x][newPoint.y]) {
                horseTravel(newPoint, step + 1);
                if (finished) {
                    break;
                }
            }
        }
        if (step == (row * col - 1) || finished) {
            finished = true;
        } else {
            //回退
            chessboard[point.x][point.y] = 0;
            //回溯设置为未访问
            visited[point.x][point.y] = false;
        }
    }

    //获取当前顶点可能的下一个顶点集合
    private static List<Point> getNext(Point point) {
        List<Point> list = new ArrayList<>();
        int x = point.x;
        int y = point.y;

        //左下
        if (x - 2 >= 0 && y - 1 >= 0) {
            list.add(new Point(x - 2, y - 1));
        }

        if (x - 1 >= 0 && y - 2 >= 0) {
            list.add(new Point(x - 1, y - 2));
        }

        //左上
        if (x - 2 >= 0 && y + 1 < col) {
            list.add(new Point(x - 2, y + 1));
        }

        if (x - 1 >= 0 && y + 2 < col) {
            list.add(new Point(x - 1, y + 2));
        }

        //右上
        if (x + 2 < row && y + 1 < col) {
            list.add(new Point(x + 2, y + 1));
        }

        if (x + 1 < row && y + 2 < col) {
            list.add(new Point(x + 1, y + 2));
        }

        //右下
        if (x + 2 < row && y - 1 >= 0) {
            list.add(new Point(x + 2, y - 1));
        }

        if (x + 1 < row && y - 2 >= 0) {
            list.add(new Point(x + 1, y - 2));
        }
        return list;
    }

    private static void init(int r, int c) {
        row = r;
        col = c;
        chessboard = new int[row][col];
        visited = new boolean[row][col];
    }

}
