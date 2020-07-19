package com.yinkai.chong;

/**
 * 回文数
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome2(123221));
    }

    public static boolean isPalindrome(int x){
        int temp = x;
        if (x<0){
            return false;
        }
        int res = 0;
        while (x!=0){
            int pop = x%10;
            x = x/10;
            res = res*10+pop;
        }
        if (res==temp){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isPalindrome2(int x){
       if (x<0 || (x%10==0&&x!=0)){
           return false;
       }
       int res=0;
       while (x>res){
           res = res*10+x%10;
           x = x/10;
       }
       return x == res || (x==res/10);
    }
}
