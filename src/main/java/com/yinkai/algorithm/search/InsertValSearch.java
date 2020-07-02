package com.yinkai.algorithm.search;

/**
 * 插值查找算法
 */
public class InsertValSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i=0;i<100;i++){
            arr[i] = i+1;
        }
        int i = insertValSearch(arr, 0, arr.length - 1, 100);
        System.out.println("index:"+i);
    }

    public static int insertValSearch(int[] arr,int left,int right,int findVal){
        if (left>right || findVal < arr[0] || findVal > arr[arr.length-1]){
            return -1;
        }

        int mid = left + (findVal-arr[left])/(arr[right]-arr[left]) *(right -left); //重点是这个公式
        int midVaule = arr[mid];
        if (findVal > midVaule){// 向右递归
            return insertValSearch(arr,mid+1,right,findVal);
        }else if (findVal < midVaule){// 向左递归
            return insertValSearch(arr,left,mid-1,findVal);
        }else {
            return mid; //递归的结束条件
        }
    }
}
