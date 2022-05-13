package com.fei;

import java.util.Arrays;
import java.util.Random;

/**
 * @description: 用递归行为得到数组中的最大值，并用master公式来估计时间复杂度
 * @author: guyuefeng
 * @create: 2022-04-28 15:08
 **/
public class RecurisonMax {

    public static Integer getMax(int[] arr){
        Integer max = recursion(arr, 0, arr.length - 1);
        return max;
    }

    public static Integer recursion(int[] arr, int left, int right){
        if (left > right){
            return Integer.MIN_VALUE;
        }
        if (left == right){
            return arr[left];
        }

        int mid = left + ((right-left) >> 1);

        Integer recursionLeft = recursion(arr, left, mid);
        Integer recursionRight = recursion(arr, mid+1, right);

        return Math.max(recursionLeft, recursionRight);
    }

    public static void main(String[] args) {
        int count = 1000000;
        int maxValue = 100;

        Random random = new Random();

        for (int i=0; i<count; i++) {
            int arrLen = random.nextInt(maxValue);
            if (arrLen == 0){
                continue;
            }
            int[] arr = new int[arrLen];

            int max = -1;
            for (int j = 0; j < arrLen; j++) {
                int i1 = random.nextInt(maxValue);
                arr[j] = i1;

                max = Math.max(max, i1);
            }

            Integer max1 = getMax(arr);
            if (max != max1){
                System.out.println("error ! max: "+max+" , getMax: "+max1+" , arr="+ Arrays.toString(arr));
                return;
            }
        }

        System.out.println("Nice !");
    }

}
