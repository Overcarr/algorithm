package com.yinkai.atguigu.dynamic;

/**
 * 动态规划（01问题 每个商品只能放一次）
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3}; //商品的重量
        int[] val = {1500,3000,2000}; //商品的价值
        int m = 4; //背包的容量
        int n = w.length; //物品的个数
        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n+1][m+1];

        int[][] v = new int[n+1][m+1]; //表示背包装下的最大价值
        for (int i=0;i<v.length;i++){
            v[i][0] = 0;
        }

        for (int i=0;i<v[0].length;i++){
            v[0][i] = 0;
        }


        for (int i=1;i<v.length;i++){
            for (int j=1;j<v[0].length;j++){
                if (w[i-1] > j){ //要添加的商品容量大于背包的容量 就用前面的同样容量的计划
                    v[i][j] = v[i-1][j];
                }else {
                    if (v[i-1][j] < (val[i-1] + v[i-1][j-w[i-1]])){ //每次进到这里的就是最优的解
                        v[i][j] = val[i-1] + v[i-1][j-w[i-1]]; // 因为每个商品只能添加一个 所以剩余到重量要去把前面一个到最佳解放进来
                        path[i][j] =1;
                    }else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }

        for (int i=0;i<v.length;i++){
            for (int j=0;j<v[0].length;j++){
                System.out.printf(v[i][j]+" ");
            }
            System.out.println();
        }

        int i = path.length - 1; //行的最大下标
        int j = path[0].length - 1; //列的最大下标
        while(i > 0 && j > 0 )  { //从 path 的最后开始找
            if(path[i][j] == 1){
               System.out.printf("第%d个商品放入到背包\n", i);
               j -= w[i-1];
            }
            i--; //查找下面的一个
        }
    }
}
