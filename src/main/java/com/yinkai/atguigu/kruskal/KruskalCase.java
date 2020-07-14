package com.yinkai.atguigu.kruskal;

import lombok.Data;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法(最小生成树)
 */
public class KruskalCase {
    private int numofEdges; //边的条数
    private char[] veertexs; //顶点的值
    private int[][] matrix; //邻接矩阵
    private static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] veertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G',};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        //大家可以在去测试其它的邻接矩阵，结果都可以得到最小生成树.
        KruskalCase kruskalCase = new KruskalCase(veertexs, matrix);
        kruskalCase.kruskal();
    }

    public void kruskal(){
        //存放各个顶点的 连接顶点
        int[] ends = new int[veertexs.length];

        EDate[] edges = getEdges();
        bubbleSort(edges);

        //存放选择的边
        int index=0;
        EDate[] res = new EDate[numofEdges];
        //依次从小到大选择边
        for (int i=0;i<numofEdges;i++){
            char p1 = edges[i].getStart();
            char p2 = edges[i].getEnd();
            int m = getEnd(ends, getVeetex(p1));
            int n = getEnd(ends, getVeetex(p2));
            if (m!=n){
                ends[m] = n;
                res[index++] = edges[i];

            }
        }
        System.out.println("最小生成树为：");
        for (int i=index-1;i>=0;i--){
            System.out.println(res[i]);
        }
    }

    /**
     * 功能: 获取下标为i的顶点的终点(), 用于后面判断两个顶点的终点是否相同
     * @param ends ： 数组就是记录了各个顶点对应的终点是哪个,ends 数组是在遍历过程中，逐步形成
     * @param i : 表示传入的顶点对应的下标
     * @return 返回的就是 下标为i的这个顶点对应的终点的下标, 一会回头还有来理解
     */
    private int getEnd(int[] ends, int i) { // i = 4 [0,0,0,0,5,0,0,0,0,0,0,0]
        while(ends[i] != 0) {  //找到end[i]=0的顶点 此顶点就为 要求顶点的顶点
            i = ends[i];
        }
        return i;
    }

    /**
     * 冒泡排序边的长度
     * @param dates
     */
    public void bubbleSort(EDate[] dates){
        for (int i=0;i<dates.length-1;i++){
            for (int j=0;j<dates.length-1-i;j++){
                if (dates[j].getWeight() > dates[j+1].getWeight()){
                    EDate date = dates[j];
                    dates[j] = dates[j+1];
                    dates[j+1] = date;
                }
            }
        }
        System.out.println(Arrays.toString(dates));
    }

    /**
     * 根据顶点返回对应的下标
     * @param c
     * @return
     */
    public int getVeetex(char c){
        for (int j=0;j<veertexs.length;j++){
             if (veertexs[j] ==c){
                 return j;
             }
        }
        return -1;
    }

    /**
     * 获取图中的边放入数组中
     * @return
     */
    public EDate[] getEdges(){
        int index=0;
        EDate[] eDates = new EDate[numofEdges];
        for (int i=0;i<veertexs.length;i++){
            for (int j=i+1;j<veertexs.length;j++){
                if (matrix[i][j]!=INF){
                    eDates[index] = new EDate(veertexs[i],veertexs[j],matrix[i][j]);
                    index++;
                }
            }
        }
        return eDates;
    }

    /**
     * 创建图的邻接矩阵
     * @param veertexs
     * @param matrix
     */
    public KruskalCase(char[] veertexs,int[][] matrix){
        this.veertexs = new char[veertexs.length];
        for (int i=0;i<veertexs.length;i++){
            this.veertexs[i] = veertexs[i];
        }

        this.matrix = new int[veertexs.length][veertexs.length];
        for (int i=0;i<veertexs.length;i++){
            for (int j=0;j<veertexs.length;j++){
                this.matrix[i][j] = matrix[i][j];
            }
        }
        for (int i=0;i<veertexs.length;i++){
            for (int j=i+1;j<veertexs.length;j++){  // j = i+1 为排除重复的和为0的边
                if (this.matrix[i][j] !=INF){
                    numofEdges++;
                }
            }
        }

    }

    /**
     * 打印邻接矩阵
     */
    public void printf(){
        System.out.println("邻接矩阵为：\n");
        for (int i=0;i<veertexs.length;i++){
            for (int j=0;j<veertexs.length;j++){
                System.out.printf("%12d",matrix[i][j]);
            }
            System.out.println();
        }
    }


}

@Data
class EDate{
    private char start;
    private char end;
    private int weight;

    public EDate(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}
