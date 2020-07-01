package com.yinkai.algorithm.sort;

import java.util.Arrays;

/**
 * 基数排序(按桶来进行操作的排序)
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};

        radixSort(arr);
        System.out.println(Arrays.toString(arr));

        /*//创建10个二维数组 作为桶
        int[][] bucket = new int[10][arr.length];
        //创建1个数组 记录放在哪个桶 放了多少个
        int[] bucketElementCounts = new int[10];
        //第一按个位数排序
        //放数据
        for (int i=0;i<arr.length;i++){
            int digitOfElement = arr[i] % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement]++;
        }
        //取数据
        int index =0;
        for (int i=0;i<bucketElementCounts.length;i++){
            if (bucketElementCounts[i] !=0){
                for (int j=0;j<bucketElementCounts[i];j++){
                    arr[index] = bucket[i][j];
                    index++;
                }
            }
            bucketElementCounts[i] = 0;
        }
        System.out.println("第1轮处理过后:"+ Arrays.toString(arr));


        //第二按十位数排序
        //放数据
        for (int i=0;i<arr.length;i++){
            int digitOfElement = arr[i] / 10 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement]++;
        }
        //取数据
        index =0;
        for (int i=0;i<bucketElementCounts.length;i++){
            if (bucketElementCounts[i] !=0){
                for (int j=0;j<bucketElementCounts[i];j++){
                    arr[index] = bucket[i][j];
                    index++;
                }
            }
            bucketElementCounts[i] = 0;
        }
        System.out.println("第2轮处理过后:"+ Arrays.toString(arr));

        //第三按百位数排序
        //放数据
        for (int i=0;i<arr.length;i++){
            int digitOfElement = arr[i] / 100 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement]++;
        }
        //取数据
        index =0;
        for (int i=0;i<bucketElementCounts.length;i++){
            if (bucketElementCounts[i] !=0){
                for (int j=0;j<bucketElementCounts[i];j++){
                    arr[index] = bucket[i][j];
                    index++;
                }
            }
            bucketElementCounts[i] = 0;
        }
        System.out.println("第3轮处理过后:"+ Arrays.toString(arr));*/
    }

    public static void radixSort(int[] arr){
        //找出最大的那个数
        int maxVaule = arr[0];
        for (int i=0;i<arr.length;i++){
            if (arr[i]>maxVaule){
                maxVaule = arr[i];
            }
        }
        //求出最大数的位数
        int length = (maxVaule +"").length();
        //创建10个二维数组 作为桶
        int[][] bucket = new int[10][arr.length];
        //创建1个数组 记录放在哪个桶 放了多少个
        int[] bucketElementCounts = new int[10];
        for (int k=0,n=1;k<length;k++,n *=10){
            for (int i=0;i<arr.length;i++){
                int digitOfElement = arr[i] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
                bucketElementCounts[digitOfElement]++;
            }
            //取数据
            int index =0;
            for (int i=0;i<bucketElementCounts.length;i++){
                if (bucketElementCounts[i] !=0){
                    for (int j=0;j<bucketElementCounts[i];j++){
                        arr[index] = bucket[i][j];
                        index++;
                    }
                }
                bucketElementCounts[i] = 0;
            }
            System.out.println("第"+(k+1)+"轮处理过后:"+Arrays.toString(arr));
        }
    }
}
