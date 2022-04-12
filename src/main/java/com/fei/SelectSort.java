package com.fei;

import java.util.Arrays;
import java.util.Random;

/**
 * @description: 选择
 * @author: guyuefeng
 * @create: 2022-04-04 16:50
 **/
public class SelectSort {

    public static void sort(int[] arr){
        int len=arr.length;

        for (int i=0; i<len-1; i++){
            int minIndex = i;
            for (int j=i; j<len-1; j++){
                minIndex = arr[minIndex] < arr[j+1] ? minIndex : j+1;
            }
            if (minIndex != i){
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }

    public static void print(int[] arr2){
        System.out.println("arr sort: ");
        for (int j=0; j<arr2.length; j++){
            System.out.print(arr2[j]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 10;
        int maxValue = 100;
        int count = 1000000;
        long start = System.currentTimeMillis();

        Random random = new Random();
        for (int i=0; i<count; i++){
            int [] arr1 = new int[len];
            int [] arr2 = new int[len];

            for (int j=0; j<len; j++){
                int i1 = random.nextInt(maxValue);
                arr1[j] = i1;
                arr2[j] = i1;
            }

            Arrays.sort(arr1);
            sort(arr2);

            for (int k=0; k<len; k++){
                if (arr1[k] != arr2[k]){
                    print(arr1);
                    print(arr2);
                    System.out.println("出错了！");
                    return;
                }
            }
        }
        long end = System.currentTimeMillis();


        System.out.println("nice! "+(end-start)*1.0/1000);
    }

}
