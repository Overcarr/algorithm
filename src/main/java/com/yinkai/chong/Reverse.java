package com.yinkai.chong;

import java.util.Arrays;
import java.util.Stack;

/**
 * 整数反转
 */
public class Reverse {
    public static void main(String[] args) {
        int reverse = reverse2(9646);
        System.out.println(reverse);
    }

    public static int  reverse(int x){
        if(x ==Integer.MIN_VALUE || x ==Integer.MAX_VALUE) {
            return 0;
        }
        if(x == 0) {
            return 0;
        }
        Integer i = x;
        String s = i.toString();
        char[] ch = new char[s.length()];
        for (int j=0;j<s.length();j++){
            ch[j] = s.charAt(j);
        }
        Stack<String> stringStack = new Stack<>();
        for (int j=0;j<ch.length;j++){
            stringStack.add(ch[j]+"");
        }

        StringBuilder stringBuilder = new StringBuilder();
        char[] cc = new char[s.length()];
        int index = 0;
        while (!stringStack.isEmpty()){
            cc[index] = stringStack.pop().charAt(0);
            index++;
        }
        if (x<0){
            for(int j=cc.length-1;j>0;j--){
                char c = cc[j];
                cc[j] = cc[j-1];
                cc[j-1] = c;
            }
        }
        for (char c : cc) {
            stringBuilder.append(c+"");
        }
        int parseInt = Integer.parseInt(stringBuilder.toString());
        return parseInt;

    }

    public static int reverse2(int x) {//最大值和最小值 -2147483648  2147483647
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
