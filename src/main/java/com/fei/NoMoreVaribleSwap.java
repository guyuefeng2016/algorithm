package com.fei;

/**
 * @description: 不用额外变量交换两个数的值
 * @author: guyuefeng
 * @create: 2022-04-24 19:23
 **/
public class NoMoreVaribleSwap {

    public static void swap(int a, int b){
        System.out.println("交换前: a="+a+" , b="+b);

        //1110=14
        //0101=5

        //1011
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("交换后: a="+a+" , b="+b);
    }


    public static void swap2(int[] arr, int i, int j){
        System.out.println("交换前: arr[i]="+arr[i]+" , arr[j]="+arr[j]);
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
        System.out.println("交换后: arr[i]="+arr[i]+" , arr[j]="+arr[j]);
    }

    public static void main(String[] args) {
        swap(3,4);
        int[] arr = new int[]{1,2,3,4,5};
        swap2(arr, 1,2);
    }


}
