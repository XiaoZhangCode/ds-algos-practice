package com.xiaozhang.code;

import java.util.Arrays;

/**
 * 给定一个有序数组arr , 代表坐落在X轴上的点、给定一个整数K,代表绳子的长度。返回绳子最多压中几个点？即使绳子边缘处盖住点也算盖住
 */
public class Code01_MaxPoint {
    /**
     * 滑动窗口
     * @param arr 数组
     * @param n 线段长度
     * @return 返回最大点数
     */
    public static int maxPoint(int[] arr, int n) {
        int left = 0;
        int right = 0;
        int res = 0;
        while (left < arr.length) {
            while (right < arr.length && arr[right] - arr[left] <= n) {
                right++;
            }
            res = Math.max(res, right - left);
            left++;
        }
        return res;
    }

    /**
     * 二分查找
     * @param arr 数组
     * @param n 线段长度
     * @return 返回最大点数
     */
    public static int maxPoint1(int[] arr, int n) {
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int index = nearestIndex(arr, i, arr[i] - n);
            res = Math.max(res, i - index + 1);
        }
        return res;
    }

    /**
     * 找到 0 - right 中 arr[i] >= value 的最大下标
     * @param arr 数组
     * @param right 右区间
     * @param value 值
     * @return 大于等于 value 的最左下标
     */
    private static int nearestIndex(int[] arr, int right, int value) {
        int l = 0;
        int r = right;
        int index = right;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return index;
    }

    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint(arr, L);
            int ans2 = maxPoint1(arr, L);
            int ans3 = test(arr, L);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("oops!");
                break;
            }
        }
        System.out.println("测试结束");


    }


}
