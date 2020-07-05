package com.yinkai.algorithm.tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        /*threadedBinaryTree.threadedNodes(root);*/
        /*HeroNode left = node5.getLeft();
        System.out.println(left);
        System.out.println(node5.getRight());*/
        threadedBinaryTree.postThreadedNodes(root);
        threadedBinaryTree.post(root);
    }
}

class ThreadedBinaryTree {
    private HeroNode root;
    //为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
    // 在递归进行线索化时，pre 总是保留前一个结点
    private HeroNode pre = null;

    public ThreadedBinaryTree(HeroNode root) {
        this.root = root;
    }

    /**
     * 对二叉树进行中序线索化
     *
     * @param node
     */
    public void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        threadedNodes(node.getLeft());

        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        threadedNodes(node.getRight());
    }

    /**
     * 中序遍历
     */
    public void infix(HeroNode root){
        if (root==null){
            return;
        }
        HeroNode node = root;
        while (node!=null){
            while (node.getLeftType() ==0){
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType()==1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    /**
     * 前序线索化
     * @param node
     */
    public void preThreadedNodes(HeroNode node){
        if (node==null){
            return;
        }
        if (node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre!=null &&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        if (node.getLeftType()==0) {
            preThreadedNodes(node.getLeft());
        }
        if (node.getRightType()==0) {
            preThreadedNodes(node.getRight());
        }
    }

    /**
     * 前序遍历
     * @param heroNode
     */
    public void pre(HeroNode heroNode){
        while (heroNode!=null){
            while (heroNode.getLeftType()==0){
                System.out.println(heroNode);
                heroNode = heroNode.getLeft();
            }
            System.out.println(heroNode);
            heroNode = heroNode.getRight();
        }
    }

    /**
     * 后序线索化
     * @param node
     */
    public void postThreadedNodes(HeroNode node){
        if (node==null){
            return;
        }
        postThreadedNodes(node.getLeft());
        postThreadedNodes(node.getRight());
        if (node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
    }

    /**
     * 后序遍历
     * @param root
     */
    public void post(HeroNode root){
       HeroNode p = root;
       pre = null;
       while (p.getLeftType()==0){
           pre = p;
           p = p.getLeft();
       }
    }
}

class HeroNode {
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //1. 如果 leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
    // 2. 如果 rightType == 0 表示指向是右子树, 如果 1 表示指向后继结点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

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

}
