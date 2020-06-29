package com.yinkai.algorithm.recursion;

public class MiGong {
    public static void main(String[] args) {
        //地图 1表示为墙、0表示没走的路、2表示走通、3表示死路
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
             map[0][i] = 1;
             map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1]=1;
        map[3][2]=1;
        //map[1][2]=1;
        //map[2][2]=1;
        System.out.println("地图情况：");
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt+ "  ");
            }
            System.out.println();
        }
        System.out.println();
        // 走迷宫
        setWay(map,1,1);

        System.out.println("发现路线以后:");
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt+ "  ");
            }
            System.out.println();
        }
    }

    //模拟走的路线
    public static boolean setWay(int[][] map, int i,int j){
        if (map[6][5] == 2){
            return true;
        }else {
            if (map[i][j]==0){
                map[i][j] = 2;
                if (setWay(map,i+1,j)){
                    return true;
                }else if(setWay(map,i,j+1)) {
                    return true;
                }else if(setWay(map,i-1,j)) {
                    return true;
                }else if(setWay(map,i,j-1)) {
                    return true;
                }else {
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}