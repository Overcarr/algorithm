package com.yinkai.atguigu.dac;

/**
 * 汉诺塔
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoitower(3,'A','B','C');
    }

    public static void hanoitower(int num,char a,char b,char c){
         if (num==1){
             System.out.println("第1个盘从 "+a+"=>"+c);
         }else {
             hanoitower(num - 1, a, c, b);//把n之前的盘 借助c 从a移动b
             System.out.println("第" + num + "个盘从 " + a + "=>" + c); //移动最后一个盘
             hanoitower(num - 1, b, a, c);//把n之前的盘 借助a 从b移动c
         }
    }
}
