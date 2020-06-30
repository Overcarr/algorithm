package com.yinkai.algorithm.sort;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, 5);
        System.out.println(Arrays.toString(arr));

    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        // pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0; //临时变量，作为交换时使用
        // while 循环的目的是让比 pivot 值小放到左边 //比 pivot 值大放到右边
        while (l < r) {
            //在 pivot 的左边一直找,找到大于等于 pivot 值,才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在 pivot 的右边一直找,找到小于等于 pivot 值,才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果 l >= r 说明 pivot 的左右两的值，已经按照左边全部是
            // 小于等于 pivot 值，右边全部是大于等于 pivot 值
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个 arr[l] == pivot 值 相等 r--， 前移
            if (arr[l] == pivot) {
                r -= 1;
            } //如果交换完后，发现这个 arr[r] == pivot 值 相等 l++， 后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        // 如果 l == r, 必须 l++, r--, 否则为出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
