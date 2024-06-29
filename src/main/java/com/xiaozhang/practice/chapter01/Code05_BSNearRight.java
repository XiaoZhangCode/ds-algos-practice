package com.xiaozhang.practice.chapter01;

import java.util.Arrays;

import static com.xiaozhang.practice.utils.RandomArrayUtil.generateRandomSortedArray;

/**
 * @Author code_zhang
 * @Date 2024/6/25 16:24
 * @description 二分查找: 找满足 <=target 的最右位置
 */
public class Code05_BSNearRight {

    /**
     * 二分查找: 找满足 <=target 的最右位置
     * @param arr 有序数组
     * @param target 目标值
     * @return 最右的元素的下标
     */
    public static int nearestIndex(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int index = -1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] <= target) {
                index = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return index;
    }

    public static int test(int[] arr, int value) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= value) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int maxSize = 10;
        int maxValue = 100;
        int testTime = 50000;
        for (int i = 0; i < testTime; i++) {
            int[] sortedArray = generateRandomSortedArray(maxSize, maxValue);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            int nearestIndex = nearestIndex(sortedArray, value);
            int index = test(sortedArray, value);
            if (nearestIndex != index) {
                System.out.println("sortedArray = " + Arrays.toString(sortedArray));
                System.out.println("value = " + value);
                System.out.println("binarySearchNearRight error! nearestIndex = " + nearestIndex + "  index = " + index);
                return;
            }
        }
        System.out.println("binarySearchNearRight is ok!");
    }

}
