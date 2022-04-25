package com.fei;

/**
 * @description:
 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 * @author: guyuefeng
 * @create: 2022-04-25 11:23
 **/
public class FindNum2 {


    public static void findNum(int[] arr){
        if (arr == null || arr.length == 0 || arr.length == 1){
            return;
        }

        int num = arr[0];

        for (int i=1,len=arr.length; i<len; i++){
            num = num ^ arr[i];
        }

        //当前num必是一个这两个出现奇数次的数异或的数, 而且二进制出现的每一个1必是属于其中一个数【如果两个都有，那奇数次+奇数次=偶数次，当前不可能出现1】
        //将当前arr分成两波，一波是二进制当前位置有1，一波是当前位置没有1， 必然能将这两个奇数分开

        //提取两个奇数异或之后最右的那个1
        int rightOne = num & (~num+1);

        int one = -1;
        for (int j=0,len=arr.length; j<len; j++){
            if (one == -1 && (rightOne & arr[j]) == 0){
                one = arr[j];
            } else if ((rightOne & arr[j]) == 0){
                //最右二进制等于0的那一波, 直接跟两个奇数异或的那个数num进行异或，最后一定得到最右二进制不是0的那个奇数
                one = one ^ arr[j];
            }
        }

        // a^b = c =>> c^a = b  , c^b=a
        int otherOne = num ^ one;

        System.out.println("这两个奇数是: "+one+" , "+otherOne);
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3,5,1,4,6,6,3,1,5,5,7,5,7,7,4,1,3,1,6,8,8,7};
        findNum(arr);
    }

}
