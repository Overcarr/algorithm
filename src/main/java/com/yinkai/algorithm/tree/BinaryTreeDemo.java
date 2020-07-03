package com.yinkai.algorithm.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode nood1 = new HeroNode(2,"吴用");
        HeroNode nood2 = new HeroNode(3,"卢俊");
        HeroNode nood3 = new HeroNode(4,"林冲");
        HeroNode nood4 = new HeroNode(5,"关胜");
        BinaryTree binaryTree = new BinaryTree(root);
        root.setLeft(nood1);
        root.setRight(nood2);
        nood2.setRight(nood3);
        nood2.setLeft(nood4);


        //前序遍历
        System.out.println("前置遍历");
        binaryTree.perOrder(); //1、2、3、5、4
        System.out.println();
        //中序遍历
        System.out.println("中置遍历");
        binaryTree.infixOrder();//2、1、5、3、4
        System.out.println();
        //后序遍历
        binaryTree.postOrder();//2 、5 、4 、3、1
        System.out.println();
        //前序查找
        binaryTree.preOne(5);//4次
        System.out.println();
        //中序查找
        binaryTree.infixOne(5);//2次
        System.out.println();
        //后序查找
        binaryTree.postOne(5);
        System.out.println();
        binaryTree.del(3);
        binaryTree.perOrder();

    }
}

class BinaryTree{
    private HeroNode root;

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    public void perOrder(){
        if (root!=null){
            root.preOrder();
        }else {
            System.out.println("树为空");
        }
    }

    public void infixOrder(){
        if (root!=null){
            root.infixOrder();
        }else {
            System.out.println("树为空");
        }
    }

    public void postOrder(){
        if (root!=null){
            root.postOrder();
        }else {
            System.out.println("树为空");
        }
    }

    public void preOne(int id){
        HeroNode heroNode = root.preOne(id);
        if (heroNode!=null){
            System.out.println(heroNode);
        }else {
            System.out.println(id+"编号英雄不存在");
        }
    }

    public void infixOne(int id){
        HeroNode heroNode = root.infixOne(id);
        if (heroNode!=null){
            System.out.println(heroNode);
        }else {
            System.out.println(id+"编号英雄不存在");
        }
    }

    public void postOne(int id){
        HeroNode heroNode = root.postOne(id);
        if (heroNode!=null){
            System.out.println(heroNode);
        }else {
            System.out.println(id+"编号英雄不存在");
        }
    }

    public void del(int id){
        if (root!=null){
            if (root.getId()==id){
                root = null;
                return;
            }else {
                root.del(id);
            }
        }else {
            System.out.println("树为空");
        }
    }
}

class HeroNode{
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        //输出父节点
        System.out.println(this);
        //左遍历
        if (this.left!=null){
            this.left.preOrder();
        }
        //右遍历
        if (this.right !=null){
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        //左树遍历
        if (this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        //右树遍历
        if (this.right!=null){
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        if (this.left!=null){
            this.left.postOrder();
        }
        if (this.right!=null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找
     * @param id
     * @return
     */
    public HeroNode preOne(int id){
        System.out.println("前序查找");
        if (this.id == id){
            return this;
        }
        HeroNode heroNode = null;
        if (this.left!=null){
            heroNode = this.left.preOne(id);
        }
        if (heroNode!=null){
            return heroNode;
        }
        if (this.right!=null){
            heroNode = this.right.preOne(id);
        }
        return heroNode;
    }

    /**
     * 中序查找
     * @param id
     * @return
     */
    public HeroNode infixOne(int id){
        HeroNode heroNode = null;
        if (this.left!=null){
            heroNode = this.left.infixOne(id);
        }
        if (heroNode!=null){
            return heroNode;
        }
        System.out.println("中序查找");
        if (this.id ==id){
            return this;
        }
        if (this.right!=null){
            heroNode = this.right.infixOne(id);
        }
        return heroNode;
    }

    /**
     * 后序查找
     * @param id
     * @return
     */
    public HeroNode postOne(int id){
        HeroNode heroNode = null;
        if (this.left!=null){
            heroNode = this.left.postOne(id);
        }
        if (heroNode!=null){
            return heroNode;
        }
        if (this.right!=null){
            heroNode = this.right.postOne(id);
        }
        if (heroNode!=null){
            return heroNode;
        }
        System.out.println("后序查找");
        if (this.id ==id){
            return this;
        }
        return heroNode;
    }

    public void del(int id){
        if (this.left!=null && this.left.id == id){
            this.left =null;
            return;
        }

        if (this.right!=null && this.right.id == id){
            this.right =null;
            return;
        }
        if (this.left!=null){
            this.left.del(id);
        }
        if (this.right!=null){
            this.right.del(id);
        }


    }
}
