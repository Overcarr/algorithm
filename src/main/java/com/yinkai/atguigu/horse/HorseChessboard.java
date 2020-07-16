package com.yinkai.atguigu.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 骑士周游问题
 */
public class HorseChessboard {
    static int X;
    static int Y;
    static boolean[] isVisited;
    static boolean isFinised;

    public static void main(String[] args) {
        System.out.println("骑士周游开始~~");
        X=9;
        Y=9;
        int row =1; //行
        int coulm = 1; //列
        int[][] chessboard = new int[X][Y]; //棋盘
        isVisited = new boolean[X*Y];
        tarvel(chessboard,row-1,coulm-1,1);
        for (int i=0;i<chessboard.length;i++){
            for (int j=0;j<Y;j++){
                System.out.print(chessboard[i][j]+"\t");
            }
            System.out.println();
        }
    }

    public static void tarvel(int[][] chessboard,int row,int coulm,int step){
        chessboard[row][coulm] = step;
        //标记为已访问
        isVisited[row*X+coulm] = true;
        Point point = new Point(coulm, row);
        ArrayList<Point> points = next(point);
        sort(points); //小心机 使用贪心算法 排序 从小到大
        while (!points.isEmpty()){
            Point p = points.remove(0);
            if (!isVisited[p.y*X+p.x]){
                tarvel(chessboard,p.y,p.x,step+1);
            }
        }

        if (step<X*Y && !isFinised){
            chessboard[row][coulm] = 0;
            isVisited[row*X+coulm] = false;
        }else {
            isFinised = true;
        }
    }

    public static void sort(List<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (next(o1).size() > next(o2).size()){
                    return 1;  //从低到高排序
                }else if (next(o1).size() == next(o2).size()) {
                    return 0; //默认位置不变
                }else {
                    return -1;
                }
            }
        });
    }

    /**
     * 下个点最多能走这个点
     * @param cutPoint
     * @return
     */
    public static ArrayList<Point> next(Point cutPoint){
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        if ((p1.x = cutPoint.x-2) >=0 && (p1.y = cutPoint.y-1) >=0){
            ps.add(new Point(p1));
        }
        if ((p1.x = cutPoint.x-1) >=0 && (p1.y = cutPoint.y-2) >=0){
            ps.add(new Point(p1));
        }
        if ((p1.x = cutPoint.x-2) >=0 && (p1.y = cutPoint.y+1) <Y){
            ps.add(new Point(p1));
        }
        if ((p1.x = cutPoint.x-1) >=0 && (p1.y = cutPoint.y+2) <Y){
            ps.add(new Point(p1));
        }
        if ((p1.x = cutPoint.x+2) <X && (p1.y = cutPoint.y-1) >=0){
            ps.add(new Point(p1));
        }
        if ((p1.x = cutPoint.x+1) <X && (p1.y = cutPoint.y-2) >=0){
            ps.add(new Point(p1));
        }
        if ((p1.x = cutPoint.x+1) <X && (p1.y = cutPoint.y+2) <Y){
            ps.add(new Point(p1));
        }
        if ((p1.x = cutPoint.x+2) <X && (p1.y = cutPoint.y+1) <Y){
            ps.add(new Point(p1));
        }
        return ps;
    }
}
