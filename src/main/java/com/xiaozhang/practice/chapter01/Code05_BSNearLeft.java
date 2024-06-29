package com.xiaozhang.practice.chapter01;

import static com.xiaozhang.practice.utils.RandomArrayUtil.generateRandomSortedArray;

/**
 * @Author code_zhang
 * @Date 2024/6/25 16:14
 * @description 二分查找法: 找满足 >=target 的最左位置
 */
public class Code05_BSNearLeft {

    /**
     * 二分查找法: 找满足 >=target 的最左位置
     *
     * @param arr    有序数组
     * @param target 目标值
     * @return 最左的元素的下标
     */
    public static int nearestIndex(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static int test(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 100;
        int testTime = 500;
        for (int i = 0; i < testTime; i++) {
            int[] sortedArray = generateRandomSortedArray(maxSize, maxValue);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            int nearestIndex = nearestIndex(sortedArray, value);
            int index = test(sortedArray, value);
            if (nearestIndex != index) {
                System.out.println("binarySearchNearLeft error!");
            }
        }
        System.out.println("binarySearchNearLeft is ok!");
    }
}
