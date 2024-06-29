package com.xiaozhang.practice.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhang
 * @date 2023/11/21 13:43
 * @description 随机数组工具类
 */
public class RandomArrayUtil {
    /**
     * 生成一个随机数组
     *
     * @param maxSize  数组最大长度
     * @param maxValue 数组最大值
     * @return 数组
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * (Math.random() + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    /**
     * 生成一个有序的随机数组
     *
     * @param maxSize  数组最大长度
     * @param maxValue 数组最大值
     * @return 数组
     */
    public static int[] generateRandomSortedArray(int maxSize, int maxValue) {
        int[] arr = generateRandomArray(maxSize, maxValue);
        Arrays.sort(arr);
        return arr;
    }

    /**
     * 生成一个相邻元素不相等的随机数组
     *
     * @param maxSize  数组最大长度
     * @param maxValue 数组最大值
     * @return 数组
     */
    public static int[] generateRandomArrayWithNoAdjacent(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        for (int i = 0; i < arr.length - 1; i++) {
            while (arr[i] == arr[i + 1]) {
                arr[i + 1] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            }
        }
        return arr;
    }

    /**
     * 生成一个随机数组 一种数字出现奇数次 另外一种数出现偶数次
     *
     * @param maxSize  数组最大长度
     * @param maxValue 数组最大值
     * @param oddCount 奇数的个数
     * @return 数组
     */
    public static int[] generateRandomArrayWithOddAndEven(int maxSize, int maxValue, int oddCount) {
        // 数组插入元素的位置
        int index = 0;
        if (!(maxSize % 2 == 0 && oddCount % 2 == 0)) {
            maxSize += 1;
        }
        int[] arr = new int[maxSize];
        // 随机生成一个奇数数字 代表奇数次出现
        int count = 0;
        // 保证多个奇数不重复出现
        Set<Integer> set = new HashSet<>(oddCount);
        while (count < oddCount) {
            int oddNum;
            do {
                oddNum = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
            } while (set.contains(oddNum));
            set.add(oddNum);
            int oddNumCount;
            do {
                oddNumCount = (int) (maxSize * Math.random()) - (int) (maxSize * Math.random());
            } while (oddNumCount % 2 == 0 || oddNumCount <= 0 || oddNumCount >= maxSize || oddNumCount > maxSize - index);

            for (int k = 0; k < oddNumCount; k++) {
                arr[index++] = oddNum;
            }
            count++;
        }
        int evenNumCount;
        for (int i = index; i < maxSize; i += evenNumCount) {
            int evenNum = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
            do {
                evenNumCount = (int) (maxSize * Math.random()) - (int) (maxSize * Math.random());
            } while (evenNumCount % 2 == 1 || evenNumCount <= 0 || evenNumCount >= maxSize || evenNumCount > maxSize - index);
            for (int j = 0; j < evenNumCount; j++) {
                arr[index++] = evenNum;
            }
        }
        return arr;
    }

    /**
     * 随机数组
     *
     * @param maxKinds 最多有多少种数字
     * @param range    数字范围
     * @param k        一种数出现的次数
     * @param m        其余数字出现的次数
     * @return 数组
     */
    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        // k次出现的数字
        int kTimeNum = randomNumber(range);

        // 出现的数字种类 最少有两种
        int numKinds = (int) (Math.random() * maxKinds) + 2;

        int[] arr = new int[k + (numKinds - 1) * m];

        int index = 0;
        while (index < k) {
            arr[index++] = kTimeNum;
        }

        numKinds--;

        HashSet<Integer> set = new HashSet<>();
        set.add(kTimeNum);
        while (numKinds > 0) {
            int num;
            do {
                num = randomNumber(range);
            } while (set.contains(num));
            set.add(num);
            int numCount = m;
            while (numCount != 0) {
                arr[index++] = num;
                numCount--;
            }
            numKinds--;
        }
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数，我想随机和j位置的数做交换
            int j = (int) (Math.random() * arr.length);// 0 ~ N-1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }


    public static int[] randomArrayPlus(int maxKinds, int range, int k, int m) {
        // k次出现的数字
        int kTimeNum = randomNumber(range);

        int times = Math.random() < 0.5 ? k : ((int) (Math.random() * (m - 1)) + 1);

        // 出现的数字种类 最少有两种
        int numKinds = (int) (Math.random() * maxKinds) + 2;

        int[] arr = new int[times + (numKinds - 1) * m];

        int index = 0;
        while (index < times) {
            arr[index++] = kTimeNum;
        }

        numKinds--;

        HashSet<Integer> set = new HashSet<>();
        set.add(kTimeNum);
        while (numKinds > 0) {
            int num;
            do {
                num = randomNumber(range);
            } while (set.contains(num));
            set.add(num);
            int numCount = m;
            while (numCount != 0) {
                arr[index++] = num;
                numCount--;
            }
            numKinds--;
        }
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数，我想随机和j位置的数做交换
            int j = (int) (Math.random() * arr.length);// 0 ~ N-1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    /**
     * 随机数
     *
     * @param range 范围
     * @return 随机数
     */
    public static int randomNumber(int range) {
        return (int) (Math.random() * (range + 1)) - (int) (Math.random() * (range + 1));
    }

}
