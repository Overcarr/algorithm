package com.yinkai.algorithm.tree.huffmantree;

import org.springframework.util.CollectionUtils;

import javax.lang.model.element.VariableElement;
import java.util.*;

/**
 * 霍夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        List<Node> nodes = creatHuffmanTree(arr);
        infixOrder(nodes.get(0));
    }

    public static void preOrder(Node root){
        if (root==null){
            return;
        }
        root.preOrder();
    }

    public static void infixOrder(Node root){
        if (root==null){
            return;
        }
        root.infixOrder();
    }

    public static List<Node> creatHuffmanTree(int[] arr){
        List<Node> nodes = new ArrayList<>();
        for (int i=0;i<arr.length;i++){
            nodes.add(new Node(arr[i]));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node node1 = nodes.get(0);
            Node node2 = nodes.get(1);
            Node node = new Node(node1.getVaule() + node2.getVaule());
            node.setLeft(node1);
            node.setRight(node2);
            nodes.remove(node1);
            nodes.remove(node2);
            nodes.add(node);
        }
        return nodes;
    }
}

class Node implements Comparable<Node>{
    private int vaule;
    private Node left;
    private Node right;

    public Node(int vaule) {
        this.vaule = vaule;
    }

    public int getVaule() {
        return vaule;
    }

    public void setVaule(int vaule) {
        this.vaule = vaule;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "vaule=" + vaule +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.vaule-o.vaule;
    }

    public void preOrder(){

        System.out.println(this);
        if (this.getLeft()!=null) {
            this.getLeft().preOrder();
        }
        if (this.getRight()!=null) {
            this.getRight().preOrder();
        }
    }

    public void infixOrder(){
        if (this.getLeft()!=null){
            this.getLeft().infixOrder();
        }
        System.out.println(this);
        if (this.getRight()!=null){
            this.getRight().infixOrder();
        }
    }

}
