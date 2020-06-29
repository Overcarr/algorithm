package com.yinkai.algorithm.recursion;

import java.util.Arrays;

/**
 * 八皇后问题
 */
public class Queue8 {

    int max = 8;
    int[] arr = new int[max];
    static int count =0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.place(0);
        System.out.printf("共有%d总解法",queue8.count);
    }

    /**
     *
     * @param n 放置第几个棋子
     */
    private void place(int n){
        if (n==max){
            print();
            return;
        }
        for (int i=0;i<max;i++){
            arr[n] = i;
            if (check(n)){ //如果为true就放下一个棋子，如果为false就继续本棋子的for循环
                place(n+1);
            }
        }
    }
    /**
     * 判断两个棋子之间是否在同一列或同一斜线上面
     * @param n 第几个棋子
     * @return
     */
    private boolean check(int n){
        for (int i=0;i<n;i++){
            if (arr[i] == arr[n] || Math.abs(arr[n] - arr[i]) == Math.abs(n-i)){
                return false;
            }// 这里不能 else 返回 true
        }
        return true;
    }

    /**
     * 输出结果
     */
    private void print(){
        count++;
        for (int i=0;i<arr.length;i++){
             System.out.print(arr[i]+" ");
         }
        System.out.println();
    }
}
