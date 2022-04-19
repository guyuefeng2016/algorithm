package com.fei;

/**
 * @description: 位图
 * @author: guyuefeng
 * @create: 2022-04-18 15:53
 **/
public class BitMap {

    private long[] bitArr;

    public BitMap(int len){
        bitArr = new long[len];
    }

    public void add(Long v){
        Long arrSize = v >> 6;
        Long loc = v & 63;

        bitArr[arrSize.intValue()] |= 1 << loc;
    }

    public void del(Long v){
        Long arrSize = v >> 6;
        Long loc = v & 63;
        bitArr[arrSize.intValue()] &= (~(1 << loc));
    }

    public boolean query(Long v){
        Long arrSize = v >> 6;
        Long loc = v & 63;
        if ((bitArr[arrSize.intValue()] & (1 << loc)) != 0){
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(3);

        bitMap.add(12L);
        bitMap.add(65L);
        bitMap.add(28L);
        bitMap.add(128L);

        System.out.println(bitMap.query(12L));
        System.out.println(bitMap.query(65L));
        System.out.println(bitMap.query(28L));
        System.out.println(bitMap.query(38L));
        System.out.println(bitMap.query(128L));


        bitMap.del(65L);
        System.out.println(bitMap.query(65L));
    }

}
