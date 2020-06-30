package com.yinkai.algorithm.search;

/**
 * 二分查找（必须是有序的）
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,8, 10, 89, 1000, 1000,1234};
        int i = binarySearch(arr, 0, arr.length - 1, 2);
        System.out.println(i);
    }

    public static int binarySearch(int[] arr,int left,int right,int vaule){
        if (left>right){ //当left = right时 检查最后一位数
            return -1;
        }
        int mid = (right+left)/2;
        int midVaule = arr[mid];

        if (vaule > midVaule) { // 向 右递归
            return binarySearch(arr, mid + 1, right, vaule);
        } else if (vaule < midVaule) { // 向左递归
            return binarySearch(arr, left, mid - 1, vaule);
        } else {
            return mid;
        }
    }
}
