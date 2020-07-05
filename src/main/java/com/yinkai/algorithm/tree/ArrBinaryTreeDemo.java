package com.yinkai.algorithm.tree;

/**
 * 顺序储存二叉树
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
         int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        /*arrBinaryTree.perOrder(0);*/
        arrBinaryTree.infixOrder(0);

    }
}

class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 前序遍历
     * @param index
     */
    public void perOrder(int index){
        if (arr.length<0 || arr==null){
            System.out.println("二叉树为空");
        }else {
            //一进入就把当前节点输出
            System.out.println(arr[index]);
            if ((index*2+1) < arr.length){
                perOrder(index*2+1);
            }
            if ((index*2+2) < arr.length){
                perOrder(index*2+2);
            }
        }
    }

    /**
     * 中序遍历
     * @param index
     */
    public void infixOrder(int index){
        if (arr.length<0 || arr==null){
            System.out.println("二叉树为空");
        };
        if ((index*2+1)<arr.length){
            infixOrder((index*2+1));
        }
        System.out.println(arr[index]);
        if ((index*2+2)<arr.length){
            infixOrder(index*2+2);
        }
    }
}
