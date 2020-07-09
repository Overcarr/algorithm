package com.yinkai.algorithm.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr ={10,12,8,9,7,6};
        AVLTree avlTree = new AVLTree();
        for (int i=0;i<arr.length;i++){
            avlTree.add(new Node(arr[i]));
        }
        avlTree.infix();
        System.out.println();
        if (avlTree.diffHeight() >=2) {
            avlTree.rightRotate();
        }
        avlTree.infix();
        System.out.println(avlTree.diffHeight());
    }
}

class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int delRightTreeMin(Node node){
        Node temp = node;
        while (temp.left!=null){
            temp = temp.left;
        }
        del(temp.vaule);
        return temp.vaule;

    }

    /**
     * 右子树高 左旋转
     */
    public void leftRotate(){
        root.leftRotate();
    }

    /**
     * 左子树高 右旋转
     */
    public void rightRotate(){
        root.rightRotate();
    }

    public int hegiht(){
        if (root==null){
            return 0;
        }else {
            return root.height();
        }
    }

    public int diffHeight(){
        if (root==null){
            return 0;
        }else {
            return Math.abs(root.leftHeight()-root.rightHeight());
        }
    }

    /**
     * 删除节点
     * @param vaule
     */
    public void del(int vaule){
        if (root==null){
            return;
        }else {
            Node targetNode = root.findTarget(vaule);
            if (targetNode==null){
                return;
            }
            if (root.left==null&&root.right==null){
                root = null;
                return;
            }
            Node parentNode = root.findParent(vaule);
            if (targetNode.left == null && targetNode.right == null) { //删除的节点为叶子节点
                if (parentNode.left == targetNode) {
                    parentNode.left = null;
                } else if (parentNode.right == targetNode) {
                    parentNode.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                int minVaule = delRightTreeMin(targetNode.left);
                targetNode.vaule = minVaule;
            } else { //只有一个子节点的 子树
                if (targetNode.left != null) {
                    if (parentNode != null) {
                        if (parentNode.left.vaule == targetNode.vaule) {
                            parentNode.left = targetNode.left;
                        } else if (parentNode.right.vaule == targetNode.vaule) {
                            parentNode.right = targetNode.left;
                        }
                    }else { //没有父节点了 删除的是根节点
                        root = targetNode.left;
                    }
                } else if (targetNode.right != null) {
                    if (parentNode != null) {
                        if (parentNode.left.vaule == targetNode.vaule) {
                            parentNode.left = targetNode.right;
                        } else if (parentNode.right.vaule == targetNode.vaule) {
                            parentNode.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    /**
     * 删除节点的父节点
     * @param vaule
     * @return
     */
    public Node findParent(int vaule){
        if (root==null){
            return null;
        }
        return root.findParent(vaule);
    }

    /**
     * 删除当前节点
     * @param vaule
     * @return
     */
    public Node findTarget(int vaule){
        if (root==null){
            return null;
        }
        return root.findTarget(vaule);
    }

    public void add(Node node){
        if (root==null){
            root =node;
            return;
        }else {
            root.add(node);
        }
    }

    public void infix(){
        if (root==null){
            System.out.println("树为空");
        }else {
            root.infix();
        }
    }

}

class Node{
    int vaule;
    Node left;
    Node right;

    public Node(int vaule) {
        this.vaule = vaule;
    }

    @Override
    public String toString() {
        return "Node{" +
                "vaule=" + vaule +
                '}';
    }

    /**
     * 左自旋
     */
    public void leftRotate(){
        Node newNode = new Node(vaule);
        newNode.left = left;
        newNode.right = right.left;
        vaule = right.vaule;
        right = right.right;
        left = newNode;
    }

    public void rightRotate(){
        Node newNode = new Node(vaule);
        newNode.right = right;
        newNode.left = left.right;
        vaule = left.vaule;
        left = left.left;
        right = newNode;
    }

    /**
     * 左子树高度
     * @return
     */
    public int leftHeight(){
        if (left!=null){
            return left.height();
        }else{
            return 0;
        }
    }

    /**
     * 右子树高度
     * @return
     */
    public int rightHeight(){
        if (right!=null){
            return right.height();
        }else{
            return 0;
        }
    }

    /**
     * 树的高度
     * @return
     */
    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }

    /**
     * 添加
     * @param node
     */
    public void add(Node node){
        if (node ==null){
            return;
        }
        if (node.vaule<this.vaule){
            if (this.left!=null){
                this.left.add(node);
            }else {
                this.left = node;
            }
        }else {
            if (this.right!=null){
                this.right.add(node);
            }else {
                this.right = node;
            }
        }

        if (rightHeight() - leftHeight() >1){
            if (right!=null&&right.leftHeight() -right.rightHeight() >1){
                rightHeight();
                leftHeight();
            }else {
                leftHeight();
            }
            return;
        }
        if (leftHeight() - rightHeight() >1){
            if (left!=null&&left.rightHeight() -left.leftHeight() >1){
                leftHeight();
                rightHeight();
            }else {
                rightHeight();
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infix(){
        if (this.left!=null){
            this.left.infix();
        }
        System.out.println(this);
        if (this.right!=null){
            this.right.infix();
        }
    }

    /**
     * 查找要删除的节点
     * @param vaule
     * @return
     */
    public Node findTarget(int vaule){
        if (this.vaule==vaule){
            return this;
        }
        if (vaule<this.vaule){
            if (this.left!=null){
                return this.left.findTarget(vaule);
            }else {
                return null;
            }
        }else {
            if (this.right!=null){
                return this.right.findTarget(vaule);
            }else {
                return null;
            }
        }
    }

    /**
     * 查找要删除的父节点
     * @param vaule
     * @return
     */
    public Node findParent(int vaule){
        if ((this.left!=null && this.left.vaule==vaule) || (this.right!=null && this.right.vaule==vaule)){
            return this;
        }else {
            if (this.left != null && this.vaule > vaule) {
                return this.left.findParent(vaule);
            }else if ((this.right!=null && this.vaule<=vaule)){
                return this.right.findParent(vaule);
            }else {
                return null;
            }
        }
    }
}
