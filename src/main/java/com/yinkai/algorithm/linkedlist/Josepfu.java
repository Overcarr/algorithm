package com.yinkai.algorithm.linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(125);
        /*circleSingleLinkedList.show();*/
        circleSingleLinkedList.countBoy(8,8,125);
    }
}

class CircleSingleLinkedList{
    private Boy first=null;
    //添加元素
    public void add(int num){
        Boy cur = first;
        for(int i = 1;i<=num;i++){
            if (i==1){
                first=new Boy(i);
                cur = first;
                first.setNext(first);
            }else {
                Boy boy = new Boy(i);
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }
        }
    }

    //显示链表
    public void show(){
        if (first==null){
            System.out.println("环形链表为空～～");
            return;
        };
        Boy cur = first;
        while (true){
            System.out.printf("当前编号为%d\n",cur.getBo());
            if (cur.getNext()==first){
                break;
            };
            cur = cur.getNext();
        }
    }

    /**
     * 出圈
     * @param startNo 从第几个小孩开始数
     * @param countNum 数几下
     * @param nums  小孩的个数
     */
    public void countBoy(int startNo, int countNum, int nums){
        if (startNo<=0||startNo>nums||countNum>nums){
            System.out.println("输入的参数有误");
            return;
        }
        //创建一个heleper让它指向开始前小孩的后一个小孩
        Boy heleper = first;
        while (true){
            if (heleper.getNext() == first){
                break;
            }
            heleper = heleper.getNext();
        }
        //移动到开始报数到小孩到位置
        for (int i =0 ;i<startNo-1; i++){
            first = first.getNext();
            heleper = heleper.getNext();
        }
        //开始出圈
        while (true){
            if (heleper==first){
                break;
            }
            for (int i=0;i<countNum-1;i++){
                first = first.getNext();
                heleper = heleper.getNext();
            }
            System.out.printf("要出圈到小孩是%d\n",first.getBo());
            first = first.getNext();
            heleper.setNext(first);
        }
        System.out.println("最后留在圈中小孩到编号:"+first.getBo());

    }
}

class Boy{
    private int bo;
    private Boy next;

    public int getBo() {
        return bo;
    }

    public void setBo(int bo) {
        this.bo = bo;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy(int bo) {
        this.bo = bo;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "bo=" + bo +
                '}';
    }
}
