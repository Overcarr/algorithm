package com.yinkai.algorithm.hashtable;

import java.util.Scanner;

/**
 * 哈希表
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        Scanner scanner = new Scanner(System.in);
        String key ="";
        boolean loop =true;
        while (loop){
            System.out.println("add: 添加雇员");
            System.out.println("show: 显示所有雇员");
            System.out.println("find: 根据id查找雇员");
            System.out.println("del: 根据id删除雇员");
            System.out.println("exit: 退出程序");

            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("请输入id");
                    int id = scanner.nextInt();
                    System.out.println("请输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id,name);
                    hashTable.add(emp);
                    break;
                case "show":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    int findId = scanner.nextInt();
                    hashTable.findOne(findId);
                    break;
                case "del":
                    System.out.println("请输入要删除的id");
                    int deldId = scanner.nextInt();
                    hashTable.del(deldId);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    loop =false;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}

class HashTable{
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTable(int size){
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        for (int i=0;i<size;i++){
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加雇员
     * @param emp
     */
    public void add(Emp emp){
        int empLinkedListsNo = hasFun(emp.id);
        empLinkedLists[empLinkedListsNo].add(emp);

    }

    /**
     * 根据雇员id找到放在数组的哪个索引
     * @param id
     * @return
     */
    private int hasFun(int id){
        return id % size;
    }

    /**
     * 遍历哈希表
     */
    public void list(){
        for (int i=0;i<empLinkedLists.length;i++){
            empLinkedLists[i].list(i);
        }
    }

    public void findOne(int id){
        int i = hasFun(id);
        Emp one = empLinkedLists[i].findOne(id);
        if (one!=null){
            System.out.printf("在第%d条链表上找到id为%d的雇员",(i+1),one.id);
        }else {
            System.out.println("未找到%d雇员"+id);
        }
        System.out.println();
    }

    public void del(int id){
        int i = hasFun(id);
        empLinkedLists[i].del(id);
    }
}

class EmpLinkedList{
    private Emp head;

    //添加雇员
    public void add(Emp emp){
        if (head==null){
            head = emp;
            return;
        }
         Emp temp = head;
         while (true){
             if (temp.next==null){
                 break;
             }
             temp = temp.next;
         }
         temp.next =emp;
    }

    //显示所有雇员
    public void list(int no){
        if (head==null){
            System.out.println("第"+(no+1)+"链表为空~~");
            return;
        }
        Emp temp = head;
        System.out.printf("第"+(no+1)+"链表");
        while (true){
            System.out.printf("=>id:%d name:%s\t",temp.id,temp.name);
            if (temp.next==null){
                break;
            }
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * 根据id 查找雇员
     * @param id
     * @return
     */
    public Emp findOne(int id){
        if (head==null){
            return null;
        }
        Emp temp = head;
        while (true){
            if (temp.id==id){
                return temp;
            }
            if (temp.next==null){
                break;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * 根据id删除雇员
     * @param id
     */
    public void del(int id){
        Emp temp = head;
        boolean flag = false;
        if (head.id==id){
            if (head.next!=null){
                head = head.next;
                return;
            }else {
                head=null;
                return;
            }
        }
        while (true){
            if (temp.next==null){
               break;
            }
            if (temp.next.id ==id){
                flag=true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
                temp.next = temp.next.next;
        }else {
            System.out.printf("不存在id为%d的雇员",id);
        }
    }
}

class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
