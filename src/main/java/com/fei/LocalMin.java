package com.fei;

import java.util.Arrays;
import java.util.Random;

/**
 * @description: 局部最小值问题
 * @author: guyuefeng
 * @create: 2022-04-12 14:11
 **/
public class LocalMin {

    /**
     * 比如在两端，最左边<最左边+1 ， 最右边<最右边-1
     * 在内部 , x-1 < x <x+1
     * 就是局部最小值, 找到即可
     * 规则：任意相邻数不想等
     * @param arr
     * @return
     */
    public static int getLocalMin(int[] arr){
        return findIndex(0, arr.length-1, arr);
    }

    /**
     * 二分查找
     * @param l
     * @param r
     * @param arr
     * @return
     */
    public static int findIndex(int l, int r, int[] arr){
        int mid = l + ((r-l)>>1);

        if (mid == 0 && arr[mid] < arr[mid+1]){
            return mid;
        }
        if (mid == arr.length - 1 && arr[arr.length-1] < arr[mid-1]){
            return mid;
        }

        int index = -1;

        if (mid == 0 || mid == arr.length-1){
            return index;
        }
        if (arr[mid] < arr[mid-1] && arr[mid] < arr[mid+1]){
            return mid;
        }

        if (arr[mid] > arr[mid-1]){
            index = findIndex(l, mid, arr);
        }
        if (arr[mid] > arr[mid+1]){
            index = findIndex(mid+1, r, arr);
        }

        return index;
    }

    public static void main(String[] args) {
//        int[] arr1 = new int[]{5,4,3,2,7,4,6,7};
//        int localMin1 = getLocalMin(arr1);
//        System.out.println(localMin1);

        int count = 1000000;
        int arrSize = 100;
        int maxValue = 10;

        Random random = new Random();

        for (int i=0; i<count; i++){
            int[] arr = new int[arrSize];
            for (int j=0; j<arrSize; j++){
                int i1 = random.nextInt(maxValue);
                if (j>0){
                    while (i1 == arr[j-1]){
                        i1 = random.nextInt(maxValue);
                    }
                }
                arr[j] = i1;
            }
            int localMin = getLocalMin(arr);

            if (localMin == 0 && arr[localMin] > arr[localMin+1]){
                System.out.println("fail");
                System.out.println("数组: "+ Arrays.toString(arr)+" , localMin: "+localMin);
                return;
            } else if (localMin == arrSize-1 && arr[localMin] > arr[localMin-1]){
                System.out.println("fail");
                System.out.println("数组: "+ Arrays.toString(arr)+" , localMin: "+localMin);
                return;
            } else if(localMin != 0 && localMin != arrSize-1 && localMin != -1 && (arr[localMin] > arr[localMin-1] || arr[localMin] > arr[localMin+1])) {
                System.out.println("fail");
                System.out.println("数组: "+ Arrays.toString(arr)+" , localMin: "+localMin);
                return;
            } else if (localMin == -1){
                System.out.println("数组: "+ Arrays.toString(arr)+" , localMin: "+localMin);
            }
        }
        System.out.println("Nice!");
    }
}
