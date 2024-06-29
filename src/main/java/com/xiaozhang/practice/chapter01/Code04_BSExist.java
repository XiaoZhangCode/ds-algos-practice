package com.xiaozhang.practice.chapter01;

import java.util.Arrays;

import static com.xiaozhang.practice.utils.RandomArrayUtil.generateRandomSortedArray;

/**
 * @Author code_zhang
 * @Date 2024/6/25 15:41
 * @Description 二分查找某个数是否存在
 */
public class Code04_BSExist {
    /**
     * 二分查找
     *
     * @param sortedArr 有序数组
     * @param target    目标
     * @return true 存在 false 不存在
     */
    public static boolean binarySearch(int[] sortedArr, int target) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int left = 0;
        int right = sortedArr.length - 1;
        while (left <= right) {
            // 可能出现int溢出
            // int mid = (left + right) / 2;
            int mid = left + ((right - left) >> 1); // 右移运算符来代替除法运算符
            if (sortedArr[mid] == target) {
                return true;
            } else if (sortedArr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    /**
     * 二分查找
     *
     * @param sortedArr 有序数组
     * @param target    目标
     * @return true 存在 false 不存在
     */
    public static boolean binarySearch2(int[] sortedArr, int target) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int left = 0;
        int right = sortedArr.length - 1; // 初始化right为数组最后一个元素的索引
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (sortedArr[mid] == target) {
                return true;
            } else if (sortedArr[mid] < target) {
                left = mid + 1; // 如果中间值小于目标值，移动left到mid+1
            } else {
                // 在减小right之前，先检查是否已经指向了最后一个可能的索引
                right = mid - 1; // 确保right不会小于left
            }
        }
        // 循环结束时，left==right，检查这个位置是否为目标值
        return sortedArr[left] == target;
    }

    public static void main(String[] args) {
        int maxSize = 1000;
        int maxValue = 100;
        int testTime = 50000;
        for (int i = 0; i < testTime; i++) {
            int[] sortedArray = generateRandomSortedArray(maxSize, maxValue);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            boolean result = binarySearch(sortedArray, value);
            boolean result2 = binarySearch2(sortedArray, value);
            if (result != result2) {
                System.out.println("sortedArr:" + Arrays.toString(sortedArray) + "target:" + value);
                System.out.println("binarySearch error!");
            }
        }
        System.out.println("binarySearch is ok!");
    }
}
