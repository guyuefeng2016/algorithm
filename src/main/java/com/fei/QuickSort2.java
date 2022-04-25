package com.fei;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 * @description:
 * 快排
 * @author: guyuefeng
 * @create: 2022-04-24 16:33
 **/
public class QuickSort2 {

    public static int[] sort(int[] arr){
        if (arr == null || arr.length == 0 || arr.length == 1){
            return arr;
        }

        partion(arr,0, arr.length-1);
        return arr;
    }

    public static void partion(int[] arr, int l, int r){
        if (l > r){
            return;
        }
        int monitor = arr[r];

        int left = l-1;

        for (int i=l; i<r; i++){
            if (arr[i] < monitor){
                swap(arr, i, left+1);
                left++;
            }
        }
        swap(arr, left+1, r);

        partion(arr, l, left);
        partion(arr, left+2, r);
    }

    public static int[] sort2(int[] arr){
        if (arr == null || arr.length == 0 || arr.length == 1){
            return arr;
        }

        partion2(arr,0, arr.length-1);
        return arr;
    }

    public static void partion2(int[] arr, int l, int r){
        if (l > r){
            return;
        }
        int monitor = arr[r];

        int left = l-1;
        int right = r;

        for (int i=l; i<right; ){
            if (arr[i] < monitor){
                swap(arr, i, left+1);
                left++;
                i++;
            } else if (arr[i] == monitor){
                i++;
            } else {
                swap(arr, i, right-1);
                right--;
            }
        }
        swap(arr, left+1, r);

        partion2(arr, l, left);
        partion2(arr, left+2, r);
    }

    /**
     * 不是快排的非递归写法 [非递归的插入排序]
     * @param arr
     * @return
     */
    public static int[] sort3(int[] arr){
        if (arr == null || arr.length == 0 || arr.length == 1){
            return arr;
        }

        Stack<int[]> stack1 = new Stack<>();
        Stack<int[]> stack2 = new Stack<>();

        int len = arr.length;
        for (int i=0; i<len; i++){
            int[] sarr = new int[1];
            sarr[0] = arr[i];
            stack1.add(sarr);
        }

        while (stack2.size() != 1){
            stack2.clear();
            while (!stack1.isEmpty()) {
                int[] arr1 = stack1.pop();
                int[] arr2 = new int[0];
                if (!stack1.isEmpty()) {
                    arr2 = stack1.pop();
                }

                int arr2Len = arr2.length;

                for (int p=0; p<arr2Len; p++){
                    int[] narr = new int[arr1.length + 1];
                    int m = 0;
                    for (int i = 0; i < arr1.length; i++) {
                        narr[m++] = arr1[i];
                    }
                    narr[m++] = arr2[p];

                    int length = narr.length;
                    int monitor = narr[length-1];

                    int right = length - 1;
                    int monitorIndex = length-1;

                    for (int i = right-1; i >= 0; i--) {
                        if (narr[i] > monitor) {
                            swap(narr, i, monitorIndex);
                            monitorIndex = i;
                        }
                    }

                    arr1 = narr;
                }
                stack2.add(arr1);
            }
            stack1.addAll(stack2);
        }

        int[] pop = stack2.pop();
        return pop;
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] copyArr(int[] arr){
        int[] res = null;
        if (arr == null || arr.length == 0){
            return arr;
        }
        int len = arr.length;
        int[] copy = new int[len];
        for (int i=0; i<len; i++){
            copy[i] = arr[i];
        }

        return copy;
    }

    public static Integer[] copyArr2(int[] arr){
        Integer[] res = null;
        if (arr == null || arr.length == 0){
            return arr == null ? null : new Integer[0];
        }
        int len = arr.length;
        Integer[] copy = new Integer[len];
        for (int i=0; i<len; i++){
            copy[i] = arr[i];
        }

        return copy;
    }

    public static void main(String[] args) {
        int count = 1000000;
        Random random = new Random();

        for (int i=0; i<count; i++){
            int maxValue = 0;
            while ((maxValue = random.nextInt(100)) == 0){
            }
            int arrLen = 10;
            int[] arr = new int[arrLen];
            for (int j=0; j<arrLen; j++){
                arr[j] = random.nextInt(maxValue);
            }

            Integer[] ints = copyArr2(arr);
            int[] ints2 = copyArr(arr);
            int[] ints3 = copyArr(arr);

            int[] sort = sort(arr);
            int[] sort2 = sort2(ints2);
            int[] sort3 = sort3(ints3);

            Collections.sort(Arrays.asList(ints));

            for (int m=0; m<arrLen; m++){
                if (sort[m] != ints[m]){
                    System.out.println("错误, 错误索引: "+m+" 原数组："+Arrays.toString(arr)+" , 系统排序数组: "+Arrays.toString(ints)+" , 快速排序数组: "+Arrays.toString(sort));
                    return;
                }
            }

            for (int m=0; m<arrLen; m++){
                if (sort2[m] != ints[m]){
                    System.out.println("错误2, 错误索引: "+m+" 原数组："+Arrays.toString(arr)+" , 系统排序数组: "+Arrays.toString(ints)+" , 快速排序数组: "+Arrays.toString(sort2));
                    return;
                }
            }

            for (int m=0; m<arrLen; m++){
                if (sort3[m] != ints[m]){
                    System.out.println("错误3, 错误索引: "+m+" 原数组："+Arrays.toString(arr)+" , 系统排序数组: "+Arrays.toString(ints)+" , 快速排序数组: "+Arrays.toString(sort3));
                    return;
                }
            }
        }


        System.out.println("Nice !");
    }
}
