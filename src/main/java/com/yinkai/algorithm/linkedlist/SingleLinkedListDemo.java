package com.yinkai.algorithm.linkedlist;

import java.util.HashMap;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        HeroNode hero5 = new HeroNode(7, "宋江", "及时雨");
        HeroNode hero6 = new HeroNode(8, "卢俊义", "玉麒麟");
        HeroNode hero7 = new HeroNode(9, "吴用", "智多星");
        HeroNode hero8 = new HeroNode(10, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();

        /*singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        singleLinkedList.list();*/

        System.out.println();

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.list();
        System.out.println();
        singleLinkedList2.addByOrder(hero5);
        singleLinkedList2.addByOrder(hero6);
        singleLinkedList2.addByOrder(hero7);
        singleLinkedList2.addByOrder(hero8);
        singleLinkedList2.list();

        /*HeroNode hero5= new HeroNode(5, "林冲", "狮子头");
        singleLinkedList.update(hero5);*/

        /*singleLinkedList.del(hero4);
        singleLinkedList.list();
        System.out.println(getLength(singleLinkedList.getHead()));
        System.out.println(getOne(singleLinkedList.getHead(),1));*/

        /*System.out.println("//单链表的反转");
        reversetList(singleLinkedList.getHead());
        singleLinkedList.list();*/

        /*System.out.println("从尾到头打印单链表");
        reversePrint(singleLinkedList.getHead());
        singleLinkedList.list();*/
        System.out.println();
        HeroNode heroNode = mergeHero(singleLinkedList.getHead(), singleLinkedList2.getHead());
        singleLinkedList.show(heroNode);

    }

    //获取有效个数
    public static int getLength(HeroNode head){
        HeroNode temp = head.next;
        int count = 0;
        while (true){
            if (temp ==null){
                break;
            }
            temp = temp.next;
            count++;
        }
        return count;
    }

    //查询倒数第k个的节点
    public static HeroNode getOne(HeroNode head,int index){
        if (head.next==null){
            return null;
        }
        HeroNode cur = head.next;
        int length = getLength(head);
        if (length<=0 ||index>length ){
            return null;
        }
        for (int i = 0;i<length - index;i++){
            cur = cur.next;
        }
        return cur;
    }

    //单链表的反转
    public static void reversetList(HeroNode head){
        if (head.next==null){
            return;
        }
        HeroNode cur = head.next;
        HeroNode reverseHead = new HeroNode(0,"","");
        HeroNode next = null;
        while (cur!=null){
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    //从尾到头打印单链表
    public static void reversePrint(HeroNode head){
        if (head.next==null){
            return;
        }
        HeroNode cur = head.next;
        Stack<HeroNode> nodeStack = new Stack<>();
        while (cur!=null){
            nodeStack.push(cur);
            cur = cur.next;
        }
        while (nodeStack.size()>0){
            System.out.println(nodeStack.pop());
        }
    }

    //合并两个有序的单链表，合并之后的链表依然有序
    public static HeroNode mergeHero(HeroNode head1,HeroNode head2){
        HeroNode merge1 = new HeroNode(0,"","");
        HeroNode merge = merge1;
        HeroNode tep1 = head1.next;
        HeroNode tep2 = head2.next;

        while (tep1!=null && tep2!=null){
            if (tep1.no <= tep2.no){
                merge.next = tep1;
                //赋值以后 后移一位
                merge = merge.next;
                tep1 = tep1.next;
            }else {
                merge.next = tep2;
                //赋值以后 后移一位
                merge = merge.next;
                tep2 = tep2.next;
            }
        }
        if (tep1 == null) {
            merge.next = tep2;
        }
        if (tep2 == null) {
            merge.next = tep1;
        }

        return merge1;
    }
}


class SingleLinkedList{
    //头结点
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //直接插入
    public void add(HeroNode heroNode){
         HeroNode temp = head;
         while (true){
             if (temp.next==null){
                 break;
             }
             temp = temp.next;
         }
         temp.next=heroNode;
    }

    //按编号插入
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next==null){
                break;
            }
            if(temp.next.no>heroNode.no){
                break;
            }else if (temp.next.no == heroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            System.out.printf("编号%d英雄已经存在了，不能插入",heroNode.no);
        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点
    public void update(HeroNode heroNode){
        HeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if (temp==null){
                break;
            }
            if (temp.no==heroNode.no){
                flag=true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        }else {
            System.out.printf("编号%d的英雄不存在\n",heroNode.no);
        }
    }

    //删除节点
    public void del(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next==null){
                break;
            }
            if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.printf("编号%d的英雄不存在\n",heroNode.no);
        }

    }

    public void list(){
        if (head.next==null){
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if (temp==null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void show(HeroNode head){
        if (head.next==null){
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if (temp==null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
