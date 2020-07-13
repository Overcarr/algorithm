package com.yinkai.atguigu.prim;

import java.util.Arrays;

/**
 * 普林姆算法(最小树)
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A','B','C','D','E','F','G'};
        int verts = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int[][] weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},};
        Graph graph = new Graph(verts);
        MinTree minTree = new MinTree();
        minTree.creatGraph(graph,data,verts,weight);
        minTree.prim(graph,0);
    }
}

class MinTree{
    /**
     *
     * @param graph 矩阵
     * @param v 开始的起点
     */
    public void prim(Graph graph,int v){
        int[] visited = new int[graph.vertex];
        visited[v] = 1; //标记该点被访问
        int minWeight = 10000;
        int h1=-1;
        int h2=-1;

        for (int k=1;k<graph.vertex;k++){
            for (int i=0;i<graph.vertex;i++){
                for (int j=0;j<graph.vertex;j++){
                    if (visited[i] ==1 && visited[j]==0 && graph.weight[i][j] < minWeight){ //找到 i是被访问 j是未访问的 且是连接的边 进行比较
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            visited[h2] = 1; //把找出来的点 设为已经被访问 再继续循环直到找到 n-1条边
            minWeight = 10000;
        }

    }

    public void creatGraph(Graph graph,char[] data,int vertex,int[][] weight){
        for (int i=0;i<vertex;i++){
            graph.data[i] = data[i];
            for (int j=0;j<vertex;j++){
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void show(Graph graph){
        int[][] weight = graph.weight;
        for (int[] ints : weight) {
            System.out.println(Arrays.toString(ints));
        }
    }
}

class Graph{
    int vertex; //顶点个数
    char[] data; //存放节点的数据
    int[][] weight; //存放边

    public Graph(int vertex){
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }
}
