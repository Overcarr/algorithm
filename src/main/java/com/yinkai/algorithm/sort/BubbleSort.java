package com.yinkai.algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20};

        int[] arrs = new int[80000];
        for (int i=0;i<800000;i++){
            arrs[i] = (int)(Math.random() * 8000000);
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        //测试冒泡排序
        bubbleSort(arrs);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);

        /*bubbleSort(arr);
        System.out.printf("最终的冒泡排序结果为:");
        for (int i : arr) {
            System.out.print(i+" ");
        }*/

    }

    public static void bubbleSort(int[] arr){
           int temp;
           boolean flag = false;
           //冒泡循环的次数是 长度-1
           //比较的次数
           for (int i=0;i<arr.length-1;i++){
               for (int j=0;j<arr.length-i-1;j++){
                   if (arr[j]>arr[j+1]){
                       flag = true;
                       temp=arr[j];
                       arr[j] = arr[j+1];
                       arr[j+1] = temp;
                   }
               }
               /*System.out.printf("第%d趟后，数组的变化为:\n",i+1);
               System.out.println(Arrays.toString(arr));*/
               if (flag){
                   flag = false;
               }else {
                   break; //退出循环
               }
           }
    }
}
