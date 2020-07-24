package com.yinkai.mianshi;

import com.yinkai.common.ListNode;

import java.util.HashMap;
import java.util.List;

public class Deno02 {

    public static ListNode head;

    public static void main(String[] args) {
        Deno02 deno02 = new Deno02();
        head = new ListNode(4);
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(9);
        head.next=node1;
        node1.next = node2;
        node2.next = node3;
        deno02.show(head);
        deno02.deleteNode(node1);
        System.out.println();
        deno02.show(head);
    }


    public void deleteNode(ListNode node) {
        if (head.val==node.val){
            head = head.next;
            return;
        }
        ListNode temp = head;
        while (temp!=null){
            if (temp.next.val==node.val){
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    public void show(ListNode head){
        ListNode temp = head;
        while (temp!=null){
            System.out.print(temp+"->");
            temp = temp.next;
        }
    }
}


