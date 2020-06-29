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

        String expression = "11+((2+3)*4)-5";
        List<String> infixExpression= toInfixExpressionList(expression);
        System.out.println("中缀表达式集合为："+infixExpression);  //  [1,+,(,(,2,+,3,),*,4,),-,5]
        System.out.println("中缀表达式转后缀表达式为："+parseSuffixExpreesionList(infixExpression));
    }

    /**
     * 中缀表达式字符串转为中缀表达式集合
     * @param s
     * @return
     */
    public static List<String> toInfixExpressionList(String s) {
        //定义一个 List,存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0; //这时是一个指针，用于遍历 中缀表达式字符串
        String str; // 对多位数的拼接
        char c; // 每遍历到一个字符，就放入到 c
        do {
            //如果 c 是一个非数字，我需要加入到 ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add(""+ c);
                i++; //i 需要后移
            } else { //如果是一个数，需要考虑多位数
                str = ""; //先将 str 置成"" '0'[48]->'9'[57]
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length()) ;
        return ls;//返回
    }

    /**
     * 中缀表达式转为后缀表达式
     * @param list
     * @return
     */
    public static List<String> parseSuffixExpreesionList(List<String> list){
        Stack<String> operStack = new Stack<>();
        List<String> strings = new ArrayList<>();
        for (String item : list) {
            //判断是否为数字 正则表达式
            if (item.matches("\\d+")){
                strings.add(item);
            }else {
                //符号栈为空 直接入栈
                if (operStack.isEmpty()){
                    operStack.push(item);
                    //
                }else if(item.equals("(")){
                    operStack.push(item);
                }else if (item.equals(")")){
                    while (!operStack.peek().equals("(")){
                        String pop = operStack.pop();
                        strings.add(pop);
                    }
                    //把 左括号 出栈
                    operStack.pop();
                    //栈顶为左括号直接压入
                }else if (operStack.peek().equals("(")){
                    operStack.push(item);
                    //优先级大于栈顶的优先级
                }else if (operation(item) > operation(operStack.peek())){
                    operStack.push(item);
                    //优先级小于等于栈顶的优先级  把栈顶的弹出到数字集合 在比较栈顶的优先级
                }else if (operation(item) <= operation(operStack.peek()) && operStack.size()!=0 ){
                    while ( operStack.size()!=0 && operation(item) <= operation(operStack.peek())){
                        String pop = operStack.pop();
                        strings.add(pop);
                    }
                    operStack.push(item);
                }
            }
        }
        while (!operStack.isEmpty()){
            String pop = operStack.pop();
            strings.add(pop);
        }
        return strings;
    }

    public static int operation(String item){
        int res = 0;
        switch (item){
            case "+":
                res=1;
                break;
            case "-":
                res=1;
                break;
            case "*":
                res=2;
                break;
            case "/":
                res=2;
                break;
            default:
                System.out.println("操作符有误");
                break;
        }
        return res;
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
