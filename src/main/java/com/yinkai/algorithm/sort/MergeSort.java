package com.yinkai.algorithm.sort;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 归并排序
 */
public class MergeSort {
    static int count=0;
    public static void main(String[] args) {
        /*int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));*/

        int[] arrs = new int[8000000];
        for (int i=0;i<8000000;i++){
            arrs[i] = (int)(Math.random() * 8000000);
        }
        int[] temps = new int[arrs.length];

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        //测试希尔插入排序
        mergeSort(arrs,0,args.length,temps);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    /**
     * 分+合
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr,int left, int right,int[] temp){
        if(left < right) {
            int mid = (left + right) / 2; //中间索引
            //向左递归进行分解 递归到分得不能再分 在进行合并
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param temp 临时数组
     */
    public static void merge(int[] arr,int left,int mid, int right,int[] temp){
        int l = left;
        int j = mid + 1;
        int t =0;

        while (l<=mid && j <=right){
            if (arr[l] <= arr[j]){
                temp[t] = arr[l];
                l++;
                t++;
            }else {
                temp[t] = arr[j];
                j++;
                t++;
            }
        }

        while (l<=mid){ //左边还有余
            temp[t] = arr[l];
            t++;
            l++;
        }

        while (j<=right){ //右边还有余 与上面只有一个能存在
            temp[t] = arr[j];
            t++;
            j++;
        }

        //将临时数组的值赋给原来的数组
        t=0;
        int tempLeft =left;
        while (tempLeft<=right){
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
    }
}
