package com.fei;

import java.util.*;

/**
 * @description: 用环形数组实现栈和队列
 * @author: guyuefeng
 * @create: 2022-04-27 12:14
 **/
public class CircularArrQueueStack {

    private int[] arr;
    private int putIndex=0;
    private int consumeIndex=0;

    public CircularArrQueueStack(int size){
        arr = new int[size];
    }


    public boolean put(int num){
        if ((putIndex+1)% arr.length != consumeIndex){
            arr[putIndex] = num;
            putIndex = (putIndex+1)%arr.length;
            return true;
        }
        return false;
    }

    public int consume(){
        if (consumeIndex != putIndex){
            int res = arr[consumeIndex];
            consumeIndex = (consumeIndex+1) % arr.length;
            return res;
        }
        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        int count = 1000000;
        int nodeLen = 100;
        int maxValue = 1000;
        int iterator = 0;

        Random random = new Random();

        for (int i=0; i<count; i++) {
            int arrlen = random.nextInt(nodeLen);

            CircularArrQueueStack queueStack = new CircularArrQueueStack(arrlen);
            Queue<Integer> queue = new LinkedList<>();

            for (int j=0; j<arrlen; j++){
                int putFlag = random.nextInt(10);

                if (putFlag > 3) {
                    int num = random.nextInt(maxValue);

                    boolean put = queueStack.put(num);
                    if (put) {
                        queue.add(num);
                    }
                } else {
                    int consume = queueStack.consume();
                    if (consume != Integer.MIN_VALUE){

                        iterator++;
                        Integer poll = queue.poll();
                        if (consume != poll) {
                            System.out.println("fuck you fuck ! ");
                            return;
                        }
                    }
                }
            }
        }

        System.out.println("比对 "+iterator+" 次！");
        System.out.println("Nice !");
    }

    /**
     * 对数器验证
     * @param args
     */
    public static void main1(String[] args) {
        int count = 1000000;
        int nodeLen = 100;
        int maxValue = 1000;
        int iterator = 0;

        boolean printFlag = false;

        Random random = new Random();

        for (int i=0; i<count; i++){
            int arrlen = random.nextInt(nodeLen);
            CircularArrQueueStack circularArrQueueStack = new CircularArrQueueStack(arrlen);
            Queue<Integer> queueList = new LinkedList<>();
            List<Integer> numList = new LinkedList<>();

            int numLen = 0;
            while (numLen < arrlen){
                numLen = random.nextInt(nodeLen);
            }

            if (printFlag){
                System.out.println("$$$$$$$$ init arrSize = "+arrlen+"， numLen = "+numLen);
            }

            for (int j=0; j<numLen; j++){
                int putFlag = random.nextInt(10);

                if (putFlag > 3){
                    int num = random.nextInt(maxValue);

                    boolean put = circularArrQueueStack.put(num);
                    if (put){
                        if (printFlag){
                            System.out.println("------------- add num: "+num+" , 【 circular putInDex: "+circularArrQueueStack.putIndex+" , consumeIndex: "+circularArrQueueStack.consumeIndex+" ");
                        }
                        queueList.add(num);
                        numList.add(num);
                    }  else {
                        if (printFlag) {
                            System.out.println("put no more, putIndex=" + circularArrQueueStack.putIndex);
                        }
                    }
                } else {
                    int consume = circularArrQueueStack.consume();
                    if (consume == Integer.MIN_VALUE){
                        if (printFlag){
                            System.out.println("consume no more, consumeIndex="+circularArrQueueStack.consumeIndex);
                        }
                        if (!queueList.isEmpty()){
                            System.out.println("fuck you fuck2222 ! numList: " + Arrays.toString(numList.toArray()) + " , consume: " + consume);
                            return;
                        }
                    } else {
                        Integer poll = queueList.poll();
                        if (printFlag){
                            System.out.println("！！！！！ consume: "+consume+" , queueList poll: "+poll+" , 【 circular putInDex: "+circularArrQueueStack.putIndex+" , consumeIndex: "+circularArrQueueStack.consumeIndex+" ");
                        }
                        iterator++;
                        if (consume != poll) {
                            System.out.println("fuck you fuck ! numList: " + Arrays.toString(numList.toArray()) + " , consume: " + consume + " , poll: " + poll);
                            return;
                        }
                    }
                }
            }
        }

        System.out.println("比对 "+iterator+" 次！");
        System.out.println("Nice !");
    }
}
