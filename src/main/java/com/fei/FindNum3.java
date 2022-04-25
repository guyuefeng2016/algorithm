package com.fei;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description:
 *
 * 一个数组中有一种数出现K次，其他数都出现了M次，
 * 已知M > 1，K < M，找到出现了K次的数
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 *
 * @author: guyuefeng
 * @create: 2022-04-25 14:39
 **/
public class FindNum3 {

    public static int findNum(int[] arr, int M){
        //用32位数组记录每一个数二进制在32位上的1的情况
        int[] barr = new int[32];

        for (int num: arr){
            for (int i=0; i<32; i++){
                if (((num>>i) & 1) != 0){
                    barr[i]++;
                }
            }
        }

        /**
         * 每一个数的二进制位都被记录起来了，如果记录的位置上出现1，如果只有 出现k次 的这种数，那他除M肯定除不尽，因为 K<M ，
         * 如果有1的位置 有 出现k次 的这种数，也有 出现M次 的这种数，那这个位置的1的累加和 为 k+n*M ，那他除M肯定也除不尽
         */
        int res = 0;
        for (int i=0; i<32; i++){
            if (barr[i] % M != 0){
                res |= (1<<i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int count = 1000000;
        int maxValue = 100;

        Random random = new Random();

        for (int i=0; i<count; i++){
            int numLen = random.nextInt(maxValue)+1;
            int K = 0;
            int M = 0;
            while (K >= M || K == 0){
                K = random.nextInt(maxValue);
                M = random.nextInt(maxValue);
            }

            //每个词出现的词频
            Map<Integer,Integer> frequency = new HashMap<>();

            int allLen = (numLen-1)*M+K;
            int[] arr = new int[allLen];
            int curNumLen = 0;
            while (curNumLen != numLen){
                int i1 = random.nextInt(maxValue);
                Integer integer = frequency.get(i1);
                if (integer == null){
                    arr[curNumLen++] = i1;
                    frequency.put(i1, 1);
                }
            }


            //出现k的那个数索引
            int kIndex = random.nextInt(numLen);
            int kNum = arr[kIndex];

            for (int j=numLen; j<allLen; j++){
                int addIndex = random.nextInt(numLen);
                int addNum = arr[addIndex];
                while ((addNum == kNum && frequency.get(addNum) >= K) || (addNum != kNum && frequency.get(addNum) >= M)){
                    addIndex = random.nextInt(numLen);
                    addNum = arr[addIndex];
                }

                frequency.put(addNum, frequency.get(addNum)+1);
                arr[j] = addNum;
            }

            int num = findNum(arr, M);
            if (num != kNum){
                System.out.println("出错了");
                System.out.println("allLen: "+allLen+", numLen: "+numLen+", arrLen: "+arr.length+" , K: "+K+", M:"+M+" , arr: "+ Arrays.toString(arr)+" , kNum: "+kNum+" , 方法寻找的kNum: "+num);
                return;
            }
        }

        System.out.println("Nice !");
    }
}
