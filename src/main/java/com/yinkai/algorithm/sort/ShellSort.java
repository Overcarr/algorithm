package com.yinkai.algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        //shellSort2(arr);


        int[] arrs = new int[800000];
        for (int i=0;i<800000;i++){
            arrs[i] = (int)(Math.random() * 8000000);
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        //测试希尔插入排序
        shellSort2(arrs);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);

        /*//第一次分组
        for (int i=5;i<arr.length;i++){
            for (int j=i-5;j>=0;j-=5){
                if (arr[j]>arr[j+5]){
                    int temp = arr[j];
                    arr[j] = arr[j+5];
                    arr[j+5] = temp;
                }
            }
        }
        System.out.println("第一次希尔排序后："+ Arrays.toString(arr));

        //第二次分组
        for (int i=2;i<arr.length;i++){
            for(int j=i-2;j>=0;j -=2){
                if (arr[j]>arr[j+2]){
                    int temp = arr[j];
                    arr[j] = arr[j+2];
                    arr[j+2] = temp;
                }
            }
        }
        System.out.println("第二次希尔排序后："+ Arrays.toString(arr));

        //第三次分组
        for (int i=1;i<arr.length;i++){
            for(int j=i-1;j>=0;j -=1){
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("第二次希尔排序后："+ Arrays.toString(arr));*/

    }


    //相邻步数的两个数 判断 在交换(一个一个交换)
    public static void shellSort(int[] arr){
        int count=0;
        for (int gap=arr.length/2;gap>0;gap /=2){
            for (int i=gap;i<arr.length;i++){
                for(int j=i-gap;j>=0;j -=gap){
                    if (arr[j]>arr[j+gap]){
                        int temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
            System.out.println("第"+(++count)+"次希尔排序后："+ Arrays.toString(arr));
        }
    }

    //移位法 找到合适的位置再把数字插入进去
    public static void shellSort2(int[] arr){
        for (int gap=arr.length/2;gap>0;gap /=2){
            for (int i=gap;i<arr.length;i++){
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j-gap]){
                    while (j-gap>=0 && temp<arr[j-gap]){
                        arr[j] = arr[j-gap];
                        j -=gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }



}
