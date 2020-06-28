package com.yinkai.algorithm.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示栈");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据");
            System.out.println("g(get): 从栈顶取出数据");
            System.out.println("h(head): 查看栈顶的数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayStack.show();
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case 'g':
                    try {
                        int one = arrayStack.pop();
                        System.out.printf("取出到的数据是：%d\n",one);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
               /* case 'h':
                    try {
                        int i = queue.headQueue();
                        System.out.printf("头部的数据是：%d\n",i);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;*/
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序结束～～");
    }
}

class ArrayStack{
    private int maxSize;
    private int top = -1;
    private int[] stack;

    public ArrayStack(int maxSize){
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


}
