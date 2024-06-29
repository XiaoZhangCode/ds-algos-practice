package com.xiaozhang.practice.chapter01;

import java.util.Arrays;

import static com.xiaozhang.practice.utils.RandomArrayUtil.generateRandomArray;

/**
 * @Author code_zhang
 * @Date 2024/6/25 11:47
 * @Description 选择排序
 */
public class Code01_SelectionSort {
    /**
     * 选择排序
     *
     * @param arr 待排序数组
     *            时间复杂度 O(N^2)
     *            1. 假设当前数组最小值是arr[i]，记录索引为 minIndex，那么从i+1开始遍历，
     *            找到比最小值arr[minIndex]还小的元素的索引，将minIndex更新为j。然后将arr[i]和arr[minIndex]交换位置
     *            2. 重复步骤1，直到遍历完整个数组
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }

    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 100;
        int testTime = 50000;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = arr1.clone();
            selectionSort(arr1);
            Arrays.sort(arr2);
            if (!Arrays.equals(arr1, arr2)) {
                System.out.println("Oops!");
                return;
            }
        }
        System.out.println("Nice!");
    }


}
