package com.xiaozhang.practice.chapter01;

import java.util.Arrays;

import static com.xiaozhang.practice.utils.RandomArrayUtil.generateRandomArrayWithNoAdjacent;

/**
 * @Author code_zhang
 * @Date 2024/6/25 16:25
 * 局部最小值问题定义:
 * 数组中(无论有序还是无序 ) 任意相邻两个数不相等, 数组下标 i 的元素 小于 i - 1 下标的元素 而且 小于 i + 1 下标的元素, 就称为局部最小值.
 * 特例：
 * 1. 数组中只有一个元素, 这个元素就是 局部最小值.
 * 2. 数组起始下标的元素 只要小于右边的元素, 就称为局部最小值.
 * 3. 数组最后一个元素 只要小于左边的元素, 就称为局部最小值.
 */
public class Code06_BSAwesome {
    /**
     * 局部最小值问题
     *
     * @param arr 数组
     * @return 局部最小值下标
     */
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int L = 1;
        int R = arr.length - 2;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 判断一个元素是否是局部最小值
     *
     * @param arr   数组
     * @param index 下标
     * @return 是否是局部最小值
     */
    public static boolean isRight(int[] arr, int index) {
        if (arr.length <= 1) {
            return true;
        }
        if (index == 0) {
            return arr[index] < arr[index + 1];
        }
        if (index == arr.length - 1) {
            return arr[index] < arr[index - 1];
        }
        return arr[index] < arr[index - 1] && arr[index] < arr[index + 1];
    }


    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 50;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArrayWithNoAdjacent(maxSize, maxValue);
            int ans = getLessIndex(arr);
            if (!isRight(arr, ans)) {
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("ans = " + ans);
                System.out.println("arr[ans] = " + arr[ans]);
                System.out.println("error !");
                return;
            }
        }
        System.out.println("Test ok!");
    }
}
