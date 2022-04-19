package com.fei;

import java.util.Random;

/**
 * @description: 位运算的加减乘除
 * @author: guyuefeng
 * @create: 2022-04-18 17:26
 **/
public class BitCalculate {


    public static int add(int a, int b){
        int sum = a+b;
        return sum;
    }

    public static int sub(int a, int b){
        int sub = a-b;
        return sub;
    }

    public static int multi(int a, int b){
        int sub = a*b;
        return sub;
    }

    public static int div(int a, int b){
        if (b == 0){
            return 0;
        }
        int div = a/b;
        return div;
    }

    /**
     * 位运算除
     * @param a
     * @param b
     * @return
     */
    public static int divBinary(int a, int b){
        //13 / 4 = 3       =>     13 = 4*2^1 + 4*2^0 => 1位置1个1，0位置1个1： 最终3 = 011
        //13=1101  4=0100  3=0011
        //1101右移1位 =》 0110 1bit 【先讲1101右移30位-> 0位，找到第一个刚好大于等于0100的，就是右移1位的时候刚刚好大于0100】
        // 1101 - 1000 = 0101  【然后将4=0100左移1位，让13=1101 减去这个数】（右移13靠近4其实等价于4左移靠近13，只不过右移更安全，因为左移可能会碰到符号位置）
        //0101 0bit  【13=1101 减去之前的数之后，被更新成为现在的数，然后拿着这个数再去重复上面的流程，直到被更新成为不大于4=0100的数】
        //0101右移0位 =》 0101 0bit 【右移1位小于4,只有右移0位会大于4】
        //0101 - 0100 = 0001 【0100左移0位】
        int tempa = a;
        int tempb = b;

        a = isNegative(a) ? -a : a;
        b = isNegative(b) ? -b : b;

        if (b == 0){
            return 0;
        }
        int div = 0;

        while (a >= b){
            for (int i=30; i>=0; i--){
                if (a >>> i >= b){
                    a = sub(a, b << i);
                    div ^= (1 << i);
                }
            }
        }
        //符号位如果一样，就是0
        return (tempa>>30 ^ tempb>>30) == 0 ? div : -div;
    }

    /**
     * 位运算除 【考虑系统最小】
     * @param a
     * @param b
     * @return
     */
    public static int divBinaryUpgrade(int a, int b){
        //系统最小值，绝对值比系统最大值会多1，所以没办法得到系统最小值的直接正数
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE){
            return 1;
        } else if (b == Integer.MIN_VALUE){
            return 0;
        } else if (a == Integer.MIN_VALUE){

            //-9 8
            // 8/3=2 ...2
            //先让系统最小加上1，然后这个数是可以转成正数进行运算的
            int i1 = addBinary(a, 1);
            //计算得到一个数
            int num = divBinary(i1, b);
            //计算i1/b的余数，这个余数和之前的1加起来，看看能不能除b
            int yushu = sub(i1, multiBinaryUpgrade(num, b));

            int num2 = divBinaryUpgrade(addBinaryUpgrade(yushu, 1), b);

            return addBinaryUpgrade(num, num2);
        } else {
            return divBinary(a, b);
        }
    }

    public static Boolean isNegative(int num){
        return num < 0;
    }

    /**
     * 位运算乘
     * @param a
     * @param b
     * @return
     */
    public static int multiBinary(int a, int b){
        int tempa = a;
        int tempb = b;

        a = isNegative(a) ? -a : a;
        b = isNegative(b) ? -b : b;

        int temp = a;
        int temp2 = a;
        int sum =0;

        while (temp2 != 0){
            int i=0;
            temp = temp2;
            if (temp2 == 1){
                sum = addBinary(sum, b);
                break;
            }
            while (temp != 1){
                temp = temp >> 1;
                i = addBinary(i, 1);
            }
            int curent = i;

            sum = addBinary((b << curent), sum);
            temp2 = (~(1<<curent)) & temp2;
        }

        return (tempa>>30 ^ tempb>>30) == 0 ? sum : -sum;
    }

    /**
     * 位运算乘 升级版本
     * @param a
     * @param b
     * @return
     */
    public static int multiBinaryUpgrade(int a, int b){
        int tempa = a;
        int tempb = b;

        a = isNegative(a) ? -a : a;
        b = isNegative(b) ? -b : b;

        int sum =0;

        while (b != 0){
            if ((b & 1) == 1){
                sum = addBinary(sum, a);
            }
            a =  a << 1;
            b = b >>> 1;
        }

        return (tempa>>30 ^ tempb>>30) == 0 ? sum : -sum;
    }

    /**
     * 位运算相减
     * @param a
     * @param b
     * @return
     */
    public static int subBinary(int a, int b){
        return addBinary(a, addBinary(~b, 1));
    }

    /**
     * 位运算相加
     * @param a
     * @param b
     * @return
     */
    public static int addBinary(int a, int b){
        //进位信息
        int jinwei = 0;
        //无进位相加
        int sum = a ^ b;
        //进位信息
        jinwei = (a & b) << 1;

        while (jinwei != 0){
            //计算无进位相加前，把原来的数保存一下
            int sum1 = sum;
            //sum和进位信息 再进行无进位相加
            sum ^=  jinwei;
            //继续算进位信息
            jinwei = (sum1 & jinwei) << 1;
        }


        return sum;
    }

    /**
     * 位运算相加
     * @param a
     * @param b
     * @return
     */
    public static int addBinaryUpgrade(int a, int b){
        int sum = a;

        if (a == 0){
            return b;
        }

        while (b != 0){
            //无进位相加
            sum = a ^ b;
            //进位信息
            b = (a & b) << 1;
            a = sum;
        }

        return sum;
    }

    public static void mai1n(String[] args) {
        int count = 10000000;
        int maxValue = 10000;
        Random random = new Random();

        long startTime = System.currentTimeMillis();
        for (int i=0; i<count; i++) {
            int i1 = random.nextInt(maxValue);
            int i2 = random.nextInt(maxValue);

//            int add = add(i1, i2);//200毫秒左右
//            int add2 = addBinary(i1, i2); //300毫秒左右
//            int add3 = addBinaryUpgrade(i1, i2); //280毫秒左右

//            int mul = multi(i1, i2); //200毫秒左右
//            int mul2 = multiBinary(i1, i2); //1300毫秒左右
//            int mul3 = multiBinaryUpgrade(i1, i2); //1200ms

//            int div = div(i1, i2); //210毫秒
            int div2 = divBinary(i1, i2); //400ms
        }
        long startTime2 = System.currentTimeMillis();
        System.out.println("耗时："+(startTime2-startTime));
    }

    public static void main(String[] args) {
        int count = 1000000;
        int maxValue = 10000;
        int iterator = 0;
        Random random = new Random();


        for (int i=0; i<count; i++){
            int i1 = random.nextInt(maxValue);
            int i2 = random.nextInt(maxValue);
            int i3 = random.nextInt(maxValue);

            i1 = i1-i3;
            i2 = i2-i3;

            int add = add(i1, i2);
            int add2 = addBinary(i1, i2);
            int add3 = addBinaryUpgrade(i1, i2);

            int sub = sub(i1, i2);
            int sub2 = subBinary(i1, i2);

            int mul = multi(i1, i2);
            int mul2 = multiBinary(i1, i2);
            int mul3 = multiBinaryUpgrade(i1, i2);

            int div = div(i1, i2);
            int div2 = divBinary(i1, i2);
            int div3 = divBinaryUpgrade(i1, i2);

            iterator++;
            if (add != add2){
                System.out.println("ERROR add !");
                System.out.println("num1="+i1+", num2="+i2+" , add="+add+" , add2="+add2);
                return;
            }

            if (add2 != add3){
                System.out.println("ERROR add2 !");
                System.out.println("num1="+i1+", num2="+i2+" , add="+add+" , add3="+add3);
                return;
            }

            if (sub != sub2){
                System.out.println("ERROR sub !");
                System.out.println("num1="+i1+", num2="+i2+" , sub="+sub+" , sub2="+sub2);
                return;
            }

            if (mul != mul2){
                System.out.println("ERROR multi !");
                System.out.println("num1="+i1+", num2="+i2+" , mul="+mul+" , mul2="+mul2);
                return;
            }

            if (mul2 != mul3){
                System.out.println("ERROR multi2 !");
                System.out.println("num1="+i1+", num2="+i2+" , mul2="+mul2+" , mul3="+mul3);
                return;
            }

            if (div != div2){
                System.out.println("ERROR div !");
                System.out.println("num1="+i1+", num2="+i2+" , div="+div+" , div2="+div2);
                return;
            }

            if (div != div3){
                System.out.println("ERROR div !");
                System.out.println("num1="+i1+", num2="+i2+" , div="+div+" , div3="+div3);
                return;
            }
        }

        System.out.println("比对"+iterator+"次结束！");
        System.out.println("Nice !");
    }

}
