package com.yinkai.atguigu.floyd;



import lombok.Data;

import java.util.Arrays;

/**
 * 弗罗伊德算法(选择中间点和直连比较)
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };
        Graph graph = new Graph(vertex,matrix);
        floyd(graph);
        graph.show();
    }

    public static void floyd(Graph graph){
        int length = graph.getVertex().length;
        int[][] dis = graph.getDis();
        int[][] pre = graph.getPre();
        for (int i=0;i<length;i++){  //作为中间点
            for (int j=0;j<length;j++){//出发点
                for (int k=0;k<length;k++){//终点
                    if (dis[j][k] > (dis[i][j] + dis[i][k])){ //直连的距离大于使用中间点的距离
                        dis[j][k] = dis[i][j] + dis[i][k];
                        pre[j][k] = pre[i][k];
                    }
                }
            }
        }
    }
}

@Data
class Graph{
    private char[] vertex;
    private int[][] dis;//邻接矩阵 出发点进过中间点到达顶点的距离 最终结果也保存在这里
    private int[][] pre;// 保存到达目标顶点的前驱顶点

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[vertex.length][vertex.length];
        for (int i=0;i<pre.length;i++){
            Arrays.fill(pre[i],i);
        }
    }

    public void show(){
        char[] xx = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        for (int i=0;i<vertex.length;i++){
            for (int j=0;j<vertex.length;j++){
                System.out.print("从"+xx[i]+"到"+xx[j]+"最短距离为:"+dis[i][j]+"\t");
            }
            System.out.println();
            for (int j=0;j<vertex.length;j++){
                System.out.print(pre[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
