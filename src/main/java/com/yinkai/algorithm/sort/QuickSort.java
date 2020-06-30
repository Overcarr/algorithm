package com.yinkai.algorithm.sort;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9,78,88,0,23,-567,70};
        quickSort(arr,0,6);

    }

    public static void quickSort(int[] arr,int left,int right){
        int l = left;
        int r = right;

        int pivot = arr[(right-left)/2];
        int temp;
        while (1<r){
            while (arr[l]<pivot){
                l +=1;
            }
            while (arr[r]>pivot){
                r -=1;
            }
            if (l>=r){
                break;
            }

            // 左右数交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个 arr[l] == pivot 值 相等 r--， 前移
            if (arr[l] ==pivot){
                r -=1;
            }

            //如果交换完后，发现这个 arr[r] == pivot 值 相等 r++， 前移
            if (arr[r] ==pivot){
                l +=1;
            }
        }

        // 如果 l == r, 必须 l++, r--, 否则为出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }

        //向左递归
        if(left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if(right > l) {
            quickSort(arr, l, right);
        }

    }
}
