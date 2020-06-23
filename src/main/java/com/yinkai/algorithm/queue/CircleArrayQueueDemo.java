package com.yinkai.algorithm.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);//队列有效大小为3 ，因为约定空出来一个空间
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
                    queue.addOne(value);
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
        System.out.println("程序退出~~");
    }
}

class CircleArrayQueue{
    private int maxSize;
    //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    private int rear;
    //front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    private int front;
    private int[] arr;


    public CircleArrayQueue(int msxSize){
        this.maxSize = msxSize;
        this.arr = new int[msxSize];
        this.rear = 0;
        this.front = 0;
    }

    //队列是否满
    public boolean isFull(){
         return (rear+1) % maxSize == front;
    }

    //队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }

    //向队列里添加数据
    public void addOne(int n){
        if (isFull()){
            System.out.println("队列已满，不能添加数据~~");
            return;
        }
        arr[rear] = n;
        rear = (rear+1)%maxSize;
    }

    //获取队列里的数据
    public int getOne(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，请添加数据");
        }
        int vaule = arr[front];
        front++;
        return vaule;
    }

    //显示队列中所有数据
    public void showAll(){
        if (isEmpty()){
            System.out.println("队列为空,请添加数据");
            return;
        }
        for (int i = front;i<front+size();i++){
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //显示队列有效个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }

    //显示队列头部的数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，请添加数据");
        }
        int vaule = arr[front];
        return vaule;
    }
}
