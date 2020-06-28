package com.yinkai.algorithm.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        String suffixExpression = "30 4 + 55 * 66 -";
        List<String> list = toList(suffixExpression);
        System.out.println(list);
        try {
            int calculate = calculate(list);
            System.out.println(suffixExpression+"的运算结果为："+calculate);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static List<String> toList(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    /**
     * 逆波兰表达式（后缀）
     * 是数则压入栈中，是运算符则出栈上面的两个数字计算结果在压入栈中，依此循环，最后栈中只有一个数即为计算结果
     * @param list
     * @return
     */
    public static int calculate(List<String> list){
        Stack<String> numStack = new Stack<>();
        for (String s : list) {
            if (s.matches("\\d+")){
                numStack.push(s);
            }else {
                Integer num1 = Integer.parseInt(numStack.pop());
                Integer num2 = Integer.parseInt(numStack.pop());
                int res=0;
                if (s.equals("*")){
                    res = num1 * num2;
                }
                else  if (s.equals("/")){
                    res = num2 / num1;
                }
                else if (s.equals("+")){
                    res = num1 + num2;
                }
                else if (s.equals("-")){
                    res = num2 - num1;
                }else {
                    throw new RuntimeException("输入的运算符有误，请重新输入");
                }
                numStack.push(""+res);
            }
        }
        int res = Integer.parseInt(numStack.pop());
        return res;
    }
}
