package com.yinkai.chong;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定 nums = [2, 7, 11, 15], target = 9
 * (两数之和)
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
class TwoPlus {
    public static void main(String[] args) {
         int[] nums = {2, 2, 11, 15,55,43,77};
        int[] ints = twoSum3(nums, 4);
        System.out.println(Arrays.toString(ints));
    }


    /**
     * 暴力匹配
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] sum = new  int[2];
        for (int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                if (nums[i] + nums[j] ==target){
                    sum[0] = i;
                    sum[1] = j;
                    return sum;
                }
            }
        }
        return null;
    }

    /**
     * 两遍哈希表
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target){
        HashMap<Integer, Integer> maps = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            maps.put(nums[i],i);
        }

        for (int i=0;i<nums.length;i++){
            int s = target - nums[i];
            if (maps.containsKey(s) && maps.get(s) !=i){
                return new int[] {i,maps.get(s)};
            }
        }
        throw new RuntimeException("null");
    }

    /**
     * 一遍哈希表
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum3(int[] nums, int target){
        HashMap<Integer, Integer> maps = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            int s = target - nums[i];
            if (maps.containsKey(s)){
                return new int[] {maps.get(s),i};
            }
            maps.put(nums[i],i);
        }
        throw new RuntimeException("null");
    }
}