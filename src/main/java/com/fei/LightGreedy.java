package com.fei;

import java.util.HashSet;

/**
 * @description: 贪心算法：放灯问题
 * @author: guyuefeng
 * @create: 2022-04-13 10:55
 **/
public class LightGreedy {

    /**
     * 有一排坑位，可以放墙、房子， 规则：放了墙的坑位不能放灯，灯只能放在房子，一盏灯只能照亮所在房子和左右两边的房子，不能穿过墙照
     * 求解怎么放灯，可以让灯的数量最少，但是照到所有的房子
     *
     * @param dbs 放0、1数据，0表示当前坑位是房子，1表示墙
     * @return
     */
    public static int planForceLight(int[] dbs){
        return forceLight(dbs, new HashSet<Integer>(), 0);
    }

    /**
     * 暴力求解
     * @param dbs 放0、1数据，0表示当前坑位是房子，1表示墙
     * @param lightSet 记录放灯的位置
     * @param index 表示当前安排的位置
     * @return
     */
    public static int forceLight(int[] dbs, HashSet<Integer> lightSet, int index){
        if (index == dbs.length){
            //每次放完所有坑位就检测一次
            for (int i=0,len=dbs.length; i<len ; i++){
                //如果当前是房子，那就检测一下，是否全部的房子都被照亮
                if (dbs[i] == 0){
                    //如果有任何一个房子没照亮，就不通过
                    if (!(lightSet.contains(i) || lightSet.contains(i-1) || lightSet.contains(i+1))){
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lightSet.size();
        } else {
            //不放灯：当前位置可能是房子，也可能是墙
            int no = forceLight(dbs, lightSet, index + 1);

            int yes = Integer.MAX_VALUE;
            if (dbs[index] == 0){
                //如果当前位置是房子，放灯
                lightSet.add(index);
                yes = forceLight(dbs, lightSet, index+1);
                //当处理完index+1的位置放灯的递归，就清除index，防止污染其他的位置放灯的递归操作
                lightSet.remove(index);
            }

            //1 2 3 4 5 6 7 8
            return Math.min(no, yes);
        }
    }

    /**
     * 有一排坑位，可以放墙、房子， 规则：放了墙的坑位不能放灯，灯只能放在房子，一盏灯只能照亮所在房子和左右两边的房子，不能穿过墙照
     * 求解怎么放灯，可以让灯的数量最少，但是照到所有的房子
     *
     * @param dbs 放0、1数据，0表示当前坑位是房子，1表示墙
     * @return
     */
    public static int planOptimumLight(int[] dbs){
        return optimumLight(dbs);
    }

    /**
     * 贪心策略：
     * 如果当前坑位是墙，跳过考察
     * 如果当前坑位是房子，先观察下一个坑位
     *   （1）如果是墙，当前坑位要放灯，跳过墙考察下一个坑位
     *   （2）如果是房子，当前坑位下一个坑位放灯，再下一个坑位是房子还是墙都没关系了，直接跳过再下一个坑位看之后的坑位
     * @param dbs
     * @return
     */
    public static int optimumLight(int[] dbs){
        int num =0;
        for (int i=0,len=dbs.length; i<len;){
            if (dbs[i] == 1){
                i++;
                continue;
            }
            if (i == len-1){
                num++;
                break;
            }
            if (dbs[i+1] == 1){
                num++;
                i+=2;
                continue;
            }
            num++;
            i+=3;
        }
        return num;
    }

    public static void main(String[] args) {
        int[] dbs = new int[]{0,1,0,0,0,0,1,0,0,0};
        int num = planForceLight(dbs);
        System.out.println("放灯数量: "+num);

//        int count = 1000000;
//        int arrSz = 10;
//        Random random = new Random();
//
//        for (int i=0; i<count; i++){
//            int[] db = new int[arrSz];
//            for (int j=0; j<arrSz; j++){
//                db[j] = random.nextInt(2);
//            }
//            int i1 = planForceLight(db);
//            int i2 = planOptimumLight(db);
//            if (i1 != i2){
//                System.out.println("error！ i1="+i1+", i2="+i2);
//                System.out.println("当前数组："+ Arrays.toString(db));
//                return;
//            }
//        }
//        System.out.println("Nice !");
    }
}
