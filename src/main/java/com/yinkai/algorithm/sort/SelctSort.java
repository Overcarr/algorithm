package com.yinkai.algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序(选择出最小值在进行交换)
 */
public class SelctSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1,-1,55};


        int[] arrs = new int[80000]; //2~3秒
        for (int i=0;i<80000;i++){
            arrs[i] = (int)(Math.random() * 8000000);
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        //测试选择排序
        selectSort(arrs);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);

        /*selectSort(arr);
        System.out.println("最终结果:"+Arrays.toString(arr));*/
    }

    public static void selectSort(int[] arr){
        for (int i=0;i<arr.length-1;i++){
            int minIndex = i;
            int min = arr[i];
            for (int j =i+1;j<arr.length;j++){
                if (min>arr[j]){
                    min = arr[j];
                    minIndex=j;
                }
            }
            //当下面那个值不同时才进行交换
            if (minIndex!=i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            /*System.out.printf("第%d趟排序下来:",i+1);
            System.out.println(Arrays.toString(arr));*/
        }
    }
}
