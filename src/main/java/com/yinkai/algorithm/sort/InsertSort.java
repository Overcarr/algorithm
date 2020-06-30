package com.yinkai.algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arry = {101, 34, 119, 1};
        //inserSort(arry);

        int[] arrs = new int[800000]; //1秒左右
        for (int i=0;i<800000;i++){
            arrs[i] = (int)(Math.random() * 8000000);
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        //测试插入排序
        inserSort(arrs);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);

    }

    public static void inserSort(int[] arr){
        for (int i=1;i<arr.length;i++){
            int insertVal=arr[i];
            int insertIndex = i-1;
            //前面的数 一直大于我们需要插入的数时就一直往前面循环 直到找到小于需要插入数的位置
            while (insertIndex>=0 && insertVal<arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex+1 !=i) {
                arr[insertIndex + 1] = insertVal;
            }
            //System.out.printf("第%d次插入",i);
            //System.out.println(Arrays.toString(arr));
        }
    }
}
