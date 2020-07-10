package com.yinkai.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private List<String> vertexList;//参数
    private int[][] edges;//矩阵
    private int numOfEdges; //边
    private boolean isVisited[];

    public static void main(String[] args) {

        //String[] vertexs = {"A","B","C","D","E"};
        String[] vertexs = {"1","2","3","4","5","6","7","8"};
        Graph graph = new Graph(vertexs.length);
        for (String vertex : vertexs) {
            graph.addVertex(vertex);
        }
        graph.addNumOfEdges(0,1,1);
        graph.addNumOfEdges(0,2,1);
        graph.addNumOfEdges(1,3,1);
        graph.addNumOfEdges(1,4,1);
        graph.addNumOfEdges(3,7,1);
        graph.addNumOfEdges(4,7,1);
        graph.addNumOfEdges(2,5,1);
        graph.addNumOfEdges(2,6,1);
        graph.addNumOfEdges(5,6,1);
        graph.showGraph();
        graph.dfs();
        System.out.println();
        graph.bfs();

    }

    public Graph(int n){
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
    }

    /**
     * 广度优先算法
     * @param isVisited
     * @param index
     */
    public void bfs(boolean[] isVisited,int index){
        int u;//头结点的下标
        int w;//相邻顶点
        System.out.printf(getVauleByIndex(index)+"=>");
        isVisited[index] = true;
        LinkedList queue = new LinkedList();
        queue.add(index);
        while (!queue.isEmpty()){
            u = (Integer) queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w!=-1){
                if (!isVisited[w]) {
                    System.out.printf(getVauleByIndex(w) + "=>");
                    isVisited[w] = true;
                    queue.add(w);
                }
                w = getNextNeighbor(u,w);
            }
        }
    }

    public void bfs(){
        isVisited = new boolean[getNumofVe()];
        for (int i=0;i<getNumofVe()-1;i++){
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    /**
     * 深度优先算法
     * @param isVisited
     * @param index
     */
    public void dfs(boolean[] isVisited,int index){
        String vauleByIndex = getVauleByIndex(index);
        System.out.printf(vauleByIndex+"->");
        isVisited[index] =true;
        int w = getFirstNeighbor(index);
        while (w!=-1){
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            w = getNextNeighbor(index,w);
        }
    }

    public void dfs(){
        isVisited = new boolean[getNumofVe()];
        for (int i=0;i<getNumofVe()-1;i++){
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    public int getFirstNeighbor(int index){
        for(int i=0;i<vertexList.size();i++){
            if (edges[index][i]>0){
                return i;
            }
        }
        return -1;
    }

    public int getNextNeighbor(int v1,int v2){
        for(int i=v2+1;i<vertexList.size();i++){
            if (edges[v1][i]>0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 顶点的值
     * @param i
     * @return
     */
    public String getVauleByIndex(int i){
        return vertexList.get(i);
    }

    /**
     * 顶点的个数
     * @return
     */
    public int getNumofVe(){
        return vertexList.size();
    }

    /**
     * 显示图
     */
    public void showGraph(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * 添加参数
     * @param s
     */
    public void addVertex(String s){
        vertexList.add(s);
    }

    /**
     * 添加边
     * @param v1
     * @param v2
     * @param wight
     */
    public void addNumOfEdges(int v1,int v2,int wight){
        edges[v1][v2] = wight;
        edges[v2][v1] = wight;
        numOfEdges++;
    }
}
