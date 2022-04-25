package com.fei;

/**
 * @description:
 * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 * @author: guyuefeng
 * @create: 2022-04-25 10:37
 **/
public class FindNum {

    /**
     * 一个数，经过偶数次异或一定为0，经过奇数次异或为他本身
     * @param arr
     * @return
     */
    public static int findNum(int[] arr){
        if (arr == null || arr.length ==0){
            return Integer.MIN_VALUE;
        }
        int num = arr[0];
        if (arr.length == 1){
            return num;
        }
        for (int i=1,len=arr.length; i<len; i++){
            num = arr[i] ^ num;
        }

        return num;
    }

    public static void main(String[] args) {
        int[] arr = new int[9];
        arr[0] = 3;
        arr[1] = 2;
        arr[2] = 2;
        arr[3] = 5;
        arr[4] = 5;
        arr[5] = 3;
        arr[6] = 3;
        arr[7] = 3;
        arr[8] = 3;

        System.out.println(findNum(arr));
    }
}
