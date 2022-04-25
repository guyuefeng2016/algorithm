package com.fei;

/**
 * @description:
 * 怎么把一个int类型的数，提取出二进制中最右侧的1来
 * @author: guyuefeng
 * @create: 2022-04-25 10:47
 **/
public class ExtractBinaryRightOne {


    public static int extractRightOne(int num){
        int newNum = num & (~num + 1);
        System.out.println(newNum);
        return newNum;
    }

    public static void main(String[] args) {
        //00101 -> 1
        extractRightOne(5);
        //10100 -> 4
        extractRightOne(20);
        //01000 -> 8
        extractRightOne(8);
        //00011 -> 1
        extractRightOne(3);
        //11111 -> 1
        extractRightOne(31);
    }

}
