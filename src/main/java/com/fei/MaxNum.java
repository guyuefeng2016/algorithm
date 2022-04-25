package com.fei;

import java.util.Random;

/**
 * @description:
 * 不要用任何比较判断，返回两个数中较大的数
 *
 * @author: guyuefeng
 * @create: 2022-04-24 10:39
 **/
public class MaxNum {


    /**
     * 利用符号位：如果 a>b , 那么a-b的最高符号位32位上为0，如果 a<b，符号位为1
     * 如果a>b，a-b符号位为0，但是要返回a , 如果a<b, a-b符号位为1，但是要返回b
     * 要构造上面的需求， a-b获取符号位，并且要和1进行异或
     * @param a
     * @param b
     * @return
     */
    public static int maxNum(int a, int b){
        //如果 a<b , a-b符号位为1，右移31位之后，第一位上就是1，其余位为0 , 如果a>b，那第一位就是0 [必须要无符号右移，要不然如果是负数右移31位，最高位每次都用1来补，符号位永远都是负数]
        int fuhaowei = (a - b) >>> 31;
        //获取a>b时a， 左边的数 [如果 a<b , fuhaowei=1， 异或1，就变成0, left=0， 如果a>b，符号位为0，异或变成1，left=a]
        int left = (fuhaowei ^ 1) * a;
        //如果 a<b , fuhaowei=1 , right=b , 如果 a<b，fuhaowei=0，right=0
        int right = fuhaowei * b;

        return left+right;
    }

    public static void main(String[] args) {
        int count = 10000000;
        int maxValue = 100;
        Random random = new Random();

        for (int i=0; i<count; i++){
            int i1 = random.nextInt(maxValue);
            int i2 = random.nextInt(maxValue);

            int i3 = maxNum(i1, i2);
            if (i1 > i2 && i1 != i3){
                System.out.println("fuck you fuck ! a="+i1+", b="+i2+" , minNum="+i3);
                return;
            }
            if (i2 > i1 && i2 != i3){
                System.out.println("fuck you fuck ! a="+i1+", b="+i2+" , minNum="+i3);
                return;
            }
        }

        System.out.println("Nice !");
    }

}
