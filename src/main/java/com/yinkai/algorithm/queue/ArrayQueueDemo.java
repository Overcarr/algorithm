package com.yinkai.algorithm.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showAll();
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                case 'g':
                    try {
                        int one = queue.getOne();
                        System.out.printf("取出到的数据是：%d\n",one);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int i = queue.headQueue();
                        System.out.printf("头部的数据是：%d\n",i);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }

    }
}


class ArrayQueue{
    private int maxSize;
    private int rear;
    private int front;
    private int arr[];

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.rear = -1;
        this.front = -1;
    }

    //队列是否满
    public Boolean isFull(){
        return rear == maxSize-1;
    }

    //队列是否为空
    public Boolean isEmpty(){
        return rear == front;
    }

    //往队列里加数据
    public void add(int i){
        if (isFull()){
            System.out.println("队列已满，不能在加数据~~");
            return;
        }
        rear++;
        arr[rear] = i;
    }

    //获取队列中的数据
    public int getOne(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，请往里面添加数据");
        }
        front++;
        return arr[front];
    }

    //显示队列中的所有数据
    public void showAll(){
        if (isEmpty()){
            System.out.println("队列为空，无数据");
            return;
        }
        for (int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //显示头部的数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，请往里面添加数据");
        }
        return arr[front+1];
    }

}
