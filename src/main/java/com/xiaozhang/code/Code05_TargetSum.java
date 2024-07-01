package com.xiaozhang.code;

import java.util.HashMap;

/**
 * @link <a href="https://leetcode.cn/problems/target-sum/leetcode">leetcode 494</a>
 */
public class Code05_TargetSum {

    public int findTargetSumWays1(int[] nums, int target) {
        return process(nums, 0, target);
    }

    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }

        int p1 = process(arr, index + 1, rest - arr[index]);
        int p2 = process(arr, index + 1, rest + arr[index]);
        return p1 + p2;
    }

    public static int findTargetSumWays2(int[] nums, int target) {
        return process2(nums, 0, target, new HashMap<Integer, HashMap<Integer, Integer>>());
    }

    private static int process2(int[] nums, int index, int target, HashMap<Integer, HashMap<Integer, Integer>> dp) {
        if (index == nums.length) {
            return target == 0 ? 1 : 0;
        }
        if (dp.containsKey(index) && dp.get(index).containsKey(target)) {
            return dp.get(index).get(target);
        }
        int ans = 0;
        int p1 = process2(nums, index + 1, target - nums[index], dp);
        int p2 = process2(nums, index + 1, target + nums[index], dp);
        ans = p1 + p2;
        HashMap<Integer, Integer> map = new HashMap<>();
        if (!dp.containsKey(index)) {
            dp.put(index, map);
        }
        dp.get(index).put(target, ans);
        return ans;
    }

    // 优化点一 :
    // 你可以认为arr中都是非负数
    // 因为即便是arr中有负数，比如[3,-4,2]
    // 因为你能在每个数前面用+或者-号
    // 所以[3,-4,2]其实和[3,4,2]达成一样的效果
    // 那么我们就全把arr变成非负数，不会影响结果的
    // 优化点二 :
    // 如果arr都是非负数，并且所有数的累加和是sum
    // 那么如果target<sum，很明显没有任何方法可以达到target，可以直接返回0
    // 优化点三 :
    // arr内部的数组，不管怎么+和-，最终的结果都一定不会改变奇偶性
    // 所以，如果所有数的累加和是sum，
    // 并且与target的奇偶性不一样，没有任何方法可以达到target，可以直接返回0
    // 优化点四 :
    // 比如说给定一个数组, arr = [1, 2, 3, 4, 5] 并且 target = 3
    // 其中一个方案是 : +1 -2 +3 -4 +5 = 3
    // 该方案中取了正的集合为P = {1，3，5}
    // 该方案中取了负的集合为N = {2，4}
    // 所以任何一种方案，都一定有 sum(P) - sum(N) = target
    // 现在我们来处理一下这个等式，把左右两边都加上sum(P) + sum(N)，那么就会变成如下：
    // sum(P) - sum(N) + sum(P) + sum(N) = target + sum(P) + sum(N)
    // 2 * sum(P) = target + 数组所有数的累加和
    // sum(P) = (target + 数组所有数的累加和) / 2
    // 也就是说，任何一个集合，只要累加和是(target + 数组所有数的累加和) / 2
    // 那么就一定对应一种target的方式
    // 也就是说，比如非负数组arr，target = 7, 而所有数累加和是11
    // 求有多少方法组成7，其实就是求有多少种达到累加和(7+11)/2=9的方法
    // 优化点五 :
    // 二维动态规划的空间压缩技巧
    public int findTargetSumWays3(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (target > sum || ((target & 1) ^ (sum & 1)) != 0) {
            return 0;
        }
        int bag = (target + sum) >> 1;
        if (bag < 0) return 0;
        int[] dp = new int[bag + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = bag; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[bag];
    }


}
