package com.yinkai.atguigu.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


/**
 * 贪心算法
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcasts.put("k1",hashSet1);
        broadcasts.put("k2",hashSet2);
        broadcasts.put("k3",hashSet3);
        broadcasts.put("k4",hashSet4);
        broadcasts.put("k5",hashSet5);

        //未覆盖的地区
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //选择的电台
        ArrayList<String> selects = new ArrayList<>();
        
        HashSet<String> tempSet = new HashSet<>();
        String maxKey;
        while (allAreas.size()!=0){
            maxKey= null;
            for (String key:broadcasts.keySet()){
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                tempSet.retainAll(allAreas); //求tempSet的交集 并把交集赋给tempSet
                if (tempSet.size()>0 && (maxKey==null || tempSet.size() > broadcasts.get(maxKey).size())){ //选择与未覆盖地区交集最大的电台 作为key
                    maxKey = key;
                }
            }
            if (maxKey!=null){
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey)); //删除已经覆盖的地区
            }
        }
        System.out.println("得到的结果:"+selects);
    }
}
