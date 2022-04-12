package com.fei;

import java.util.Arrays;
import java.util.Random;

/**
 * @description: 插入
 * @author: guyuefeng
 * @create: 2022-04-04 17:16
 **/
public class InsertSort {

    public static void sort(int[] arr){
        for (int i=0,len=arr.length; i<len; i++){
            int j=i;
            while (j-1>=0){
                if (arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    j--;
                } else {
                    break;
                }
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
