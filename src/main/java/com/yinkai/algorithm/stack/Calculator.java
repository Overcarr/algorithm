package com.yinkai.algorithm.stack;

/**
 *
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "39*56+93-55";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String key = "";
        while (true){
            //依次得到 expression 的每一个字符
            ch = expression.substring(index, index+1).charAt(0);
            if (operStack.isOper(ch)){
                //是运算符
                if (operStack.isEempty()) {
                    operStack.push(ch);
                }else {
                    if (operStack.priority(ch) > operStack.priority(operStack.getTop())) {
                        operStack.push(ch);
                    }else {
                        oper = operStack.pop();
                        num1= numStack.pop();
                        num2= numStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }
                }
            }else {
                //不是运算符
                key +=ch;
                if (index==expression.length()-1){
                    numStack.push(Integer.parseInt(key));
                }else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(key));
                        key ="";
                    }
                }
            }
            index++;
            //是否到末尾
            if (index>=expression.length()){
                break;
            }
        }

        while (true){
            if (operStack.isEempty()){
                break;
            }
            oper = operStack.pop();
            num1= numStack.pop();
            num2= numStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        int res2 = numStack.pop();
        System.out.println(expression + "的计算结果为："+res2);

    }
}

class ArrayStack2{
    private int maxSize;
    private int top = -1;
    private int[] stack;

    public ArrayStack2(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull(){
        return top == maxSize-1;
    }

    public boolean isEempty(){
        return top==-1;
    }

    public void push(int vaule){
        if (isFull()){
            System.out.println("栈已满～～");
            return;
        }
        top++;
        stack[top] = vaule;
    }

    public int pop(){
        if (isEempty()){
            throw new RuntimeException("栈空～～");
        }
        int i = stack[top];
        top--;
        return i;
    }

    public void show(){
        if (isEempty()){
            System.out.println("栈空，请添加元素");
            return;
        }
        for (int i = top;i>=0;i--){
            System.out.println(stack[i]);
        }
    }

    //返回运算到优先级
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {

            return 0;
        } else {

            return -1; // 假定目前的表达式只有 +, - , * , / }
        }
    }

    //判断是否是运算符
    public boolean isOper(int oper){
        return oper=='*' || oper=='/' || oper=='+' || oper=='-';
    }

    //计算
    public int cal(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
        }
        return res;
    }

    public int getTop(){
        return stack[top];
    }


}
