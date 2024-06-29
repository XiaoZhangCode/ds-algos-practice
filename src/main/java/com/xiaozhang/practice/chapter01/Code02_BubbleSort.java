package com.xiaozhang.practice.chapter01;

import java.util.Arrays;

import static com.xiaozhang.practice.utils.RandomArrayUtil.generateRandomArray;

/**
 * @Author code_zhang
 * @Date 2024/6/25 13:48
 * @Description 冒泡排序
 */
public class Code02_BubbleSort {

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j + 1]){
                    swap(arr, j, j + 1);
                }
            }
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
            bubbleSort(arr1);
            Arrays.sort(arr2);
            if (!Arrays.equals(arr1, arr2)) {
                System.out.println("Oops!");
                return;
            }
        }
        System.out.println("Nice!");
    }
}
