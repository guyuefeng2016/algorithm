package com.fei;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description:
 * @author: guyuefeng
 * @create: 2022-04-11 15:20
 **/
public class RandomUtil {

    /**
     * 产生1-5等概率随机
     * @return
     */
    public static Integer random1(){
        Random random = new Random();
        int i = random.nextInt(5)+1;
        return i;
    }

    /**
     * 产生不等概率
     * 0: 0.4
     * 1: 0.6
     * @return
     */
    public static Integer random3(){
        Integer integer = random1();
        if (integer>3){
            return 0;
        }
        return 1;
    }

    /**
     * 通过不等概率，产生等概率
     * @return
     */
    public static Integer getRand2(){
        int num;
        do {
            num = random3();
        } while (num == random3());
        return num;
    }

    /**
     * 1-5 等概率模拟 二分等概率
     * @return
     */
    public static Integer getRand(){
        do {
            Integer a = random1();
            Integer b = random1();
            if (a <3 && b<3){
                return 0;
            } else if (a>3 && b>3){
                return 1;
            }
        } while (true);
    }

    /**
     * a-b 等概率
     * @return
     */
    public static Integer random2(int a, int b){
        Integer num = 0;

        int one=-1;
        int sum=0;
        int round = b-a;

        do {
            one=-1;
            sum=0;
            for (int i=0; i<=round; i++){
                if (getRand() == 1){
                    one = i;
                    sum++;
                }
            }
        } while (sum != 1 || one == -1);

        num = one+a;
        return num;
    }

    public static void main(String[] args) {
        Map<Integer, Double> map = new HashMap<Integer, Double>();
        double count = 10000000d;

        for (int i=0; i<count; i++){
//            Integer integer = random2(20,29);
//            Integer integer = random3();
            Integer integer = getRand2();

            Double v = map.get(integer);
            if (map.get(integer) == null){
                map.put(integer, 1d);
            } else {
                map.put(integer, v+1);
            }
        }

        for (Map.Entry<Integer,Double> entry : map.entrySet()){
            System.out.println(entry.getKey()+" 出现 "+entry.getValue()+" , 概率："+(entry.getValue()/count));
        }
    }
}
