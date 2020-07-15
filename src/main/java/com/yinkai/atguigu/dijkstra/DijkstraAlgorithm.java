package com.yinkai.atguigu.dijkstra;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.djs(6);
        graph.show();

    }
}



class Graph{
    public char[] vertex;//顶点的集合
    public int[][] matrix;//邻接矩阵
    public VisitedVertex vv;

    public Graph(char[] vertex,int[][] matrix){
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph(){
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public void show(){
        vv.show();
    }

    public void djs(int index){
        vv =  new VisitedVertex(index,vertex.length);
        update(index);
        for (int i=1;i<vertex.length;i++){
            index = vv.updateArr();
            update(index);
        }
    }

    public void update(int index){
        int len;
        for(int i=0;i<vertex.length;i++){
            // len 含义是 : 出发顶点到 index 顶点的距离 + 从 index 顶点到 j 顶点的距离的和
            len = vv.getLen(index) + matrix[index][i];
            // 如果 j 顶点没有被访问过，并且 len 小于出发顶点到 直接到 j 顶点的距离，就需要更新
            if (!vv.isVisited(i) && len < vv.getLen(i)){
                vv.updateDis(i,len); //更新出发顶点到 j 顶点的距离
                vv.updatePre(i,index); //更新 j 顶点的前驱为 index 顶点
            }
        }
    }
}

class VisitedVertex{
    // 记录各个顶点是否访问过 1 表示访问过,0 未访问,会动态更新
    public int[] already_arr;
    // 每个下标对应的值为前一个顶点下标, 会动态更新
    public int[] pre_visited;
    // 记录出发顶点到其他所有顶点的距离,比如 G 为出发顶点，就会记录 G 到其它顶点的距离，会动态更新，求的最短距离就会存放到 dis
    public int[] dis;

    public VisitedVertex(int index,int length){
        this.already_arr = new int[length];
        this.already_arr[index]=1; //设置出发点被访问过
        this.pre_visited = new int[length];
        this.dis = new int[length];
        Arrays.fill(dis,65535);
        dis[index] = 0;//设置出发点到自己的距离为0
    }

    /**
     * 返回顶点是否被访问过
     * @param i
     * @return
     */
    public boolean isVisited(int i){
        return already_arr[i] ==1;
    }

    /**
     * 更新出发点到顶点的距离
     * @param i
     * @param len
     */
    public void updateDis(int i,int len){
        dis[i] = len;
    }

    /**
     * 更新pre这个顶点的前驱节点为index
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 返回出发点到index顶点的距离
     * @param index
     * @return
     */
    public int getLen(int index){
        return dis[index];
    }

    /**
     * 返回下个要访问的顶点
     * @return
     */
    public int updateArr(){
        int min=65535,index=0;
        for (int i=0;i<already_arr.length;i++){
            if (already_arr[i]==0 && dis[i] < min){
                  min = dis[i];
                  index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }

    public void show(){
        System.out.println("==============");
        for (int i : already_arr) {
            System.out.print(i +"");
        }

        System.out.println();
        for (int i : pre_visited) {
            System.out.print(i +"");
        }

        System.out.println();
        for(int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
