package com.yinkai.atguigu.binarysearchnorecursion;

/**
 * 二分查找算法 非递归
 */
public class BinarySearchNoRecu {
    public static void main(String[] args) {
        int[] arr = {1,5,7,8,33,88,99};
        System.out.println(binarySearchNoRecu(arr, 77));
    }

    public static int binarySearchNoRecu(int[] arr,int vaule){
        int left = 0;
        int right = arr.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if (arr[mid] == vaule){
                return mid;
            }else if (arr[mid] > vaule){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return -1;
    }
}
