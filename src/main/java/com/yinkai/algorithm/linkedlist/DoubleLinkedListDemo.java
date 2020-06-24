package com.yinkai.algorithm.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
         //进行测试
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        /*doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);*/

        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);

        doubleLinkedList.list();
        doubleLinkedList.del(3);
        System.out.println("删除以后~~~~~~~~~");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList{
    //头结点
    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

    public void list(){
        HeroNode2 tmp = head.next;
        if (head.next==null){
            return;
        }
        while (true){
            if (tmp==null){
                break;
            }
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }

    //插入
    public void add(HeroNode2 heroNode2){
        HeroNode2 tmp = head;
        while (true){
            if (tmp.next==null){
                break;
            }
            tmp = tmp.next;
        }
        tmp.next=heroNode2;
        heroNode2.pre = tmp;
    }

    //按编号插入
    public void addByOrder(HeroNode2 heroNode2){
        HeroNode2 temp = head;
        boolean flag = false;
        while (true){
            if (temp.next==null){
                break;
            }
            if (temp.next.no>heroNode2.no){
                break;
            }else if (temp.next.no==heroNode2.no){
                flag=false;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            System.out.printf("该%d编号的英雄已经存在~~\n",heroNode2.no);
        }else {
            if (temp.next==null){
                temp.next=heroNode2;
                heroNode2.pre=temp;
            }else {
                HeroNode2 nn = temp.next; //接受之前temp下一个节点
                temp.next=heroNode2;
                heroNode2.pre=temp;
                heroNode2.next=nn;
                nn.pre=heroNode2;
            }
        }
    }

    //删除
    public void del(int no){
        if (head.next==null){
            System.out.println("空链表~~");
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true){
            if (temp==null){
                break;
            }
            if (temp.no == no){
                flag=true;
                break;
            }
            temp = temp.next;
        }

        if (flag){
            temp.pre.next = temp.next;
            if (temp.next!=null) {
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("该%d编号的英雄不存在~~\n",no);
        }
    }
}

class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickName) {
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
