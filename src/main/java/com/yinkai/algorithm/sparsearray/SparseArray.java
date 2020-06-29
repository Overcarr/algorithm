package com.yinkai.algorithm.sparsearray;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个元素的二维数组
        //0 代表无棋子 1代表黑子 2代表蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][4] = 2;
        System.out.println("原始的二维数组");
        for (int[] ints : chessArr1) {
            for (int i : ints) {
                System.out.printf("%d\t",i);
            }
            System.out.println();
        }
        int sum = 0;
        for (int i= 0;i<11;i++){
            for (int j =0;j<11;j++){
                if (chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println();
        System.out.println(sum);

        //创建稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //将二维数组的有效值赋给稀疏数组
        int count = 1;
        for (int i= 0;i<11;i++){
            for (int j =0;j<11;j++){
                if (chessArr1[i][j]!=0){
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                    count++;
                }
            }
        }
        System.out.println();
        System.out.println("得到的稀疏数组~~~");
        for (int i = 0;i<sparseArr.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        //将稀疏数组转为二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println();
        System.out.println("恢复之后的二维数组");
        for (int[] ints : chessArr2) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }//
    }
}
