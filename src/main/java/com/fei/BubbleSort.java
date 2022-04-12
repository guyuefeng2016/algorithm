package com.fei;


import java.util.Arrays;
import java.util.Random;

/**
 * @description:
 * @author: guyuefeng
 * @create: 2022-04-04 16:28
 **/
public class BubbleSort {

    public static void sort(int[] arr){
        for (int len=arr.length-1; len>0; len--){
            for (int i=0; i<len; i++){
                if (arr[i] > arr[i+1]){
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int len = 10;
        int maxValue = 100;
        int count = 10000;

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

            if (i<20){
                System.out.println("arr2 sort: ");
                for (int j=0; j<len; j++){
                    System.out.print(arr2[j]+" ");
                }
                System.out.println();
            }

            for (int k=0; k<len; k++){
                if (arr1[k] != arr2[k]){
                    System.out.println(arr1);
                    System.out.println(arr2);
                    System.out.println("出错了！");
                    break;
                }
            }
        }

        System.out.println("nice!");
    }
}
