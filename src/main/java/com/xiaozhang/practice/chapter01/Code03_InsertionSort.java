package com.xiaozhang.practice.chapter01;

import java.util.Arrays;

import static com.xiaozhang.practice.utils.RandomArrayUtil.generateRandomArray;

/**
 * @Author code_zhang
 * @Date 2024/6/25 14:27
 */
public class Code03_InsertionSort {

    /**
     * 插入排序
     *
     * @param arr 待排序数组
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 100;
        int testTime = 50000;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = arr1.clone();
            insertionSort(arr1);
            Arrays.sort(arr2);
            if (!Arrays.equals(arr1, arr2)) {
                System.out.println("Oops!");
                return;
            }
        }
        System.out.println("Nice!");

    }
}
