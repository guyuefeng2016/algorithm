package com.fei;

import java.util.Arrays;
import java.util.Random;

/**
 * @description: 归并
 * @author: guyuefeng
 * @create: 2022-04-04 17:27
 **/
public class MergeSort {

    public static void sort(int[]arr){
        plan(arr, 0, arr.length-1);
    }

    public static void plan(int[] arr, int l, int r){
        if (l >= r){
            return;
        }

        int mid = l + ((r-l) >> 1);
        plan(arr, l, mid);
        plan(arr, mid+1, r);

        merge(arr, l, r);
    }

    public static void merge(int[] arr, int l, int r){
        if (l >= r){
            return;
        }
        int[] help = new int[r-l+1];

        int i=0;
        int mid = l + ((r-l) >> 1);
        int p1=l;
        int p2=mid+1;
        while (p1<=mid && p2<=r){
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1<=mid){
            help[i++] = arr[p1++];
        }

        while (p2<=r){
            help[i++] = arr[p2++];
        }

        int k=0;
        for (int j=l; j<=r; j++){
            arr[j] = help[k++];
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
