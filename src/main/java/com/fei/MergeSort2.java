package com.fei;

import java.util.*;

/**
 * @description: 归并排序2
 * @author: guyuefeng
 * @create: 2022-04-24 11:36
 **/
public class MergeSort2 {

    public static int[] sort(int[] arr){
        if (arr == null || arr.length ==0 || arr.length== 1){
            return arr;
        }

        return merge(arr, 0, arr.length-1);
    }

    /**
     * 递归版本
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int[] merge(int[] arr, int l, int r){
        if (l == r){
            int[] t = new int[1];
            t[0]=arr[l];
            return t;
        }

        int mid = l + ((r - l) >> 1);
        int[] mergeLeft = merge(arr, l, mid);
        int[] mergeRight = merge(arr, mid+1, r);

        int rLen = mergeRight.length;
        int lLen = mergeLeft.length;
        int[] help = new int[rLen+lLen];
        int left=0;
        int right=0;
        int i=0;
        while (left<lLen && right<rLen){
            help[i++] = mergeLeft[left] < mergeRight[right] ? mergeLeft[left++] : mergeRight[right++];
        }

        while (left < lLen){
            help[i++] = mergeLeft[left++];
        }

        while (right < rLen){
            help[i++] = mergeRight[right++];
        }

        return help;
    }

    /**
     * 非递归版本
     * @param arr
     * @return
     */
    public static int[] sort2(int[] arr){
        if (arr == null || arr.length ==0 || arr.length== 1){
            return arr;
        }

        int len = arr.length;

        Stack<int[]> stack1 = new Stack<>();
        Stack<int[]> stack2 = new Stack<>();

        for (int i=0; i<len; i++){
            int[] sarr = new int[1];
            sarr[0] = arr[i];
            stack1.add(sarr);
        }

        while (stack2.size() != 1){
            stack2.clear();
            while (!stack1.isEmpty()){
                int[] mergeLeft = stack1.pop();

                int[] mergeRight = new int[0];
                if (!stack1.isEmpty()){
                    mergeRight = stack1.pop();
                }

                int rLen = mergeRight.length;
                int lLen = mergeLeft.length;
                int left=0;
                int right=0;
                int i=0;
                int[] help = new int[lLen+rLen];
                while (left<lLen && right<rLen && lLen>0 && rLen>0){
                    help[i++] = mergeLeft[left] < mergeRight[right] ? mergeLeft[left++] : mergeRight[right++];
                }

                while (left < lLen){
                    help[i++] = mergeLeft[left++];
                }

                while (right < rLen){
                    help[i++] = mergeRight[right++];
                }

                stack2.add(help);
            }
            stack1.addAll(stack2);
        }

        return stack2.pop();
    }


    public static Integer[] copyArr(int[] arr){
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

            Integer[] ints = copyArr(arr);
            int[] sort = sort(arr);
            int[] sort2 = sort2(arr);

            Collections.sort(Arrays.asList(ints));

            for (int m=0; m<arrLen; m++){
                if (sort[m] != ints[m]){
                    System.out.println("错误, 错误索引: "+m+" 原数组："+Arrays.toString(arr)+" , 系统排序数组: "+Arrays.toString(ints)+" , 归并排序数组: "+Arrays.toString(sort));
                    return;
                }
            }

            for (int m=0; m<arrLen; m++){
                if (sort2[m] != ints[m]){
                    System.out.println("错误2, 错误索引: "+m+" 原数组："+Arrays.toString(arr)+" , 系统排序数组: "+Arrays.toString(ints)+" , 归并排序数组: "+Arrays.toString(sort2));
                    return;
                }
            }
        }


        System.out.println("Nice !");
    }
}
