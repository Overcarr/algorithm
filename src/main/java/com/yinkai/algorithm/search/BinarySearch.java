package com.yinkai.algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找（必须是有序的）
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,8, 10, 89, 1000,1000, 1000,1000,1000,1234};
        /*int i = binarySearch(arr, 0, arr.length - 1, 2);
        System.out.println(i);*/

        List<Integer> list = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println(list);
    }

    public static int binarySearch(int[] arr,int left,int right,int vaule){
        if (left>right){ //当left = right时 检查最后一位数
            return -1;
        }
        int mid = (right+left)/2;
        int midVaule = arr[mid];

        if (vaule > midVaule) { // 向右递归
            return binarySearch(arr, mid + 1, right, vaule);
        } else if (vaule < midVaule) { // 向左递归
            return binarySearch(arr, left, mid - 1, vaule);
        } else {
            return mid;
        }
    }

    public static List<Integer> binarySearch2(int[] arr,int left,int right,int vaule){
        if (left>right){
            return new ArrayList<>();
        }
        int mid = (left+right)/2;
        int midValue = arr[mid];

        if (vaule > midValue){
           return binarySearch2(arr,mid+1,right,vaule);
        }else if (vaule<midValue){
            return binarySearch2(arr,left,mid-1,vaule);
        }else {
            List<Integer> resIndexlis= new ArrayList<>();
            int temp = mid-1;
            while (true){
                if (temp<0 || arr[temp] !=vaule){ //因为 集合是有序的 所以相等的数字 在其左右两侧
                    break;
                }
                resIndexlis.add(temp);
                temp -=1;
            }
            temp = mid;
            while (true){
                if (temp>arr.length-1 || arr[temp] !=vaule){
                    break;
                }
                resIndexlis.add(temp);
                temp +=1;
            }
            return resIndexlis;
        }
    }

}
