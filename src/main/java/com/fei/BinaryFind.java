package com.fei;

import java.util.Arrays;
import java.util.Random;

/**
 * @description: 二分查找
 * @author: guyuefeng
 * @create: 2022-04-12 11:03
 **/
public class BinaryFind {

    /**
     *
     * @param arr
     * @param num
     * @return
     */
    public static Integer findIndex(int[]arr, int num){
        Integer index = findRange(0, arr.length - 1, arr, num);
        return index;
    }

    /**
     * 有序数组中找到>=num最左的位置
     * 1 2 3 3 3 5 6
     * find 3 , index= 2
     * @param l
     * @param r
     * @param arr
     * @param num
     * @return
     */
    public static Integer findRange(int l,int r, int[] arr, int num){
        if (l == r && arr[l] >= num){
            return l;
        } else if (l == r){
            //找不到
            return -1;
        }
        int mid = l + ((r-l) >> 1);
        int index = -1;
        if (arr[mid] >= num){
            index = findRange(l, mid, arr, num);
        } else if (arr[mid] < num){
            index = findRange(mid+1, r, arr, num);
        }
        return index;
    }

    /**
     *
     * @param arr
     * @param num
     * @return
     */
    public static Integer findIndex2(int[]arr, int num){
        Integer index = findRange22(0, arr.length - 1, arr, num);
        return index;
    }

    /**
     * 有序数组中找到<=num最右的位置
     * 1 2 3 3 3 5 6
     * find 3 , index= 4
     * @param l
     * @param r
     * @param arr
     * @param num
     * @return
     */
    public static Integer findRange2(int l,int r, int[] arr, int num){
        if (r < 0){
            return -1;
        }
        if (l == r && arr[l] <= num){
            return l;
        }
        int mid = l + ((r-l) >> 1);
        int index = -1;
        if (arr[mid] > num){
            index = findRange2(l, mid-1, arr, num);
        } else if (arr[mid] <= num){
            if (r-l == 1 && arr[r] <= num){
                index = r;
            } else if (r - l == 1){
                index = l;
            } else {
                index = findRange2(mid, r, arr, num);
            }
        }
        return index;
    }

    /**
     * 有序数组中找到<=num最右的位置
     * 1 2 3 3 3 5 6
     * find 3 , index= 4
     * @param l
     * @param r
     * @param arr
     * @param num
     * @return
     */
    public static Integer findRange22(int l,int r, int[] arr, int num){
        if (l == r && arr[l] <= num){
            return l;
        }
        if (l > r || l<0|| r>arr.length){
            return -1;
        }
        int mid = l + ((r-l) >> 1);
        int index = -1;
        if (arr[mid] > num){
            index = findRange22(l, mid-1, arr, num);
        } else if (arr[mid] <= num){
            if (r-l == 1 && arr[r] <= num){
                index = r;
            } else if (r - l == 1){
                index = l;
            } else {
                index = findRange22(mid, r, arr, num);
            }
        }
        return index;
    }

//    public static void main(String[] args) {
//        int[]arr =new int[]{1,1,1,1,2};
//        Integer index2 = findIndex2(arr, 0);
//        System.out.println(index2);
//    }

    /**
     * 有序数组中找到<=num最右的位置
     * @param args
     */
    public static void main(String[] args) {
        int count = 1000000;
        int arrSize = 100;
        int maxValue = 100;
        Random random = new Random();

        for (int i=0; i<count; i++){
            //查找的数
            int num = random.nextInt(maxValue);
            int[] arr = new int[arrSize];
            for (int j=0,len=arrSize; j< len; j++){
                int i1 = random.nextInt(maxValue);
                arr[j] = i1;
            }

            //有序数组
            Arrays.sort(arr);
            //用二分查找一下
            Integer index = findIndex2(arr, num);
            //直接遍历保证一定正确
            //记录一下上一个位置
            int lastIndex = -1;
            for (int m=0,len=arrSize; m<len; m++){
                if (num >= arr[m]){
                    lastIndex = m;
                } else {
                    if (lastIndex != index){
                        System.out.println("错误");
                        System.out.println("数组: "+Arrays.toString(arr)+" , 二分查找index: "+index+" , 遍历for index: "+lastIndex+", num="+num);
                        return;
                    }
                }
            }
        }

        System.out.println("ok!");
    }

    /**
     * 有序数组中找到>=num最左的位置 对数器
     * @param args
     */
    public static void main2(String[] args) {
        int count = 10000000;
        int arrSize = 18;
        int maxValue = 100;
        Random random = new Random();

        for (int i=0; i<count; i++){
            //查找的数
            int num = random.nextInt()*maxValue;
            int[] arr = new int[arrSize];
            for (int j=0,len=arrSize; j< len; j++){
                int i1 = random.nextInt() * maxValue;
                arr[j] = i1;
            }

            //有序数组
            Arrays.sort(arr);
            //用二分查找一下
            Integer index = findIndex(arr, num);
            //直接遍历保证一定正确
            for (int m=0,len=arrSize; m<len; m++){
                if (arr[m] >= num){
                    if (m != index){
                        System.out.println("错误");
                        System.out.println("数组: "+arr.toString()+" , 二分查找index: "+index+" , 遍历for index: "+m);
                        return;
                    }
                    break;
                }
            }
        }

        System.out.println("ok!");
    }
}
