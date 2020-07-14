package com.yinkai.atguigu.kmp;

import javax.lang.model.element.VariableElement;
import java.lang.reflect.Array;
import java.time.temporal.ValueRange;
import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext("ABCDABD");
        System.out.println(Arrays.toString(next));
        int i = kmpSearch(str1, str2, next);
        System.out.println(i);
    }

    public static int kmpSearch(String str1, String str2, int[] next){
        for (int i=0,j=0;i<str1.length();i++){
            while(j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j-1];
            }
            if (str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if (j ==str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    private static int[] kmpNext(String str){
        int[] arr = new int[str.length()];
        arr[0] =0;
        for (int i=1, j=0;i<str.length();i++){
            while(j > 0 && str.charAt(i) != str.charAt(j)) {
                j = arr[j-1];
            }
            if (str.charAt(i) == str.charAt(j)){
                j++;
            }
            arr[i] = j;
        }
        return arr;
    }
}

