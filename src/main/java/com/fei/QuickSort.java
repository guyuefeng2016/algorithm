package com.fei;

import java.util.Arrays;
import java.util.Random;

/**
 * @description: 快排
 * @author: guyuefeng
 * @create: 2022-04-04 18:34
 **/
public class QuickSort {

    private static Random random = random = new Random();

    public static void sort(int[] arr){
        recursion(arr, 0, arr.length-1);
    }

    public static void recursion(int[] arr, int l, int r){
        if (l >= r){
            return;
        }

        int[] mid = partion(arr, l, r);

        recursion(arr, l, mid[0]);
        recursion(arr, mid[1]+1, r);
    }

    public static int[] partion(int[] arr, int l, int r){
        //左扩区域
        int L=l-1;
        //右扩区域
        int R=r;
        //这里有个优化点，如果不加下面这行代码，时间复杂度最差是n^2[假如arr本身有序，那每次arr[R]这个划分值都是只划分了左边]，加了之后是n*logn 【将l～r之间某个数和r位置的数交换】
//        int randomIndex = random.nextInt(r-l+1)+l;
//        int temp1=arr[r];
//        arr[r] = arr[randomIndex];
//        arr[randomIndex] = temp1;

        for (int i=l; i<R; ){
            //如果当前观察的数 小于 划分值arr[r]
            if (arr[i] < arr[r]){
                //让当前数和左扩区域后一个数交换
                if (L+1 != i){
                    int temp = arr[i];
                    arr[i] = arr[L+1];
                    arr[L+1] = temp;
                }
                //左扩区域往后移动
                L++;
                //当前数往后移动
                i++;
            } else if (arr[i] > arr[r]){
                //如果当前观察的数 大于 划分值arr[r]
                //让当前数和右扩区域前一个数交换
                int temp = arr[R-1];
                arr[R-1] = arr[i];
                arr[i] = temp;

                //右扩区域左移
                R--;
                //当前数不动，因为是新的，没有观察
            } else {
                //如果当前观察的数 等于 划分值arr[r]， 直接让当前数往后移动
                i++;
            }
            if (i==R){
                //划分值arr[r]和右扩区域最左边那个数交换
                int temp = arr[r];
                arr[r] = arr[R];
                arr[R] = temp;
            }
        }
        int[] mid = new int[2];
        mid[0] = L;
        mid[1] = R;
        return mid;
    }

    public static void print(int[] arr2){
        System.out.println("arr sort: ");
        for (int j=0; j<arr2.length; j++){
            System.out.print(arr2[j]+" ");
        }
        System.out.println();
    }

    public static int[] copy(int[]arr){
        int[] arr1 = new int[arr.length];
        for (int i=0;i<arr.length;i++){
            arr1[i]=arr[i];
        }
        return arr1;
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
//            int[] copy = copy(arr1);
//            if (i<10){
//                print(copy);
//            }
            sort(arr2);
//            if (i<10){
//                System.out.println("-----------------");
//                print(copy);
//                System.out.println("==========");
//            }

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
