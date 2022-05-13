package com.fei;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * @description: 两个栈实现队列
 * @author: guyuefeng
 * @create: 2022-04-28 14:29
 **/
public class DoubleStackQueue {

    private Stack<Integer> pushStack = new Stack<>();
    private Stack<Integer> popStack = new Stack<>();

    public void push(Integer num){
        pushStack.push(num);
    }

    public Integer pop(){
        if (popStack.isEmpty() && pushStack.isEmpty()){
            return null;
        }

        if (popStack.isEmpty()){
            while (!pushStack.isEmpty()){
                popStack.push(pushStack.pop());
            }
        }

        if (popStack.isEmpty()){
            return null;
        }

        return popStack.pop();
    }

    public static void main(String[] args) {
        int count = 1000000;
        int maxValue = 1000;

        Random random = new Random();

        for (int i=0; i<count; i++){
            Queue<Integer> queue = new LinkedList<>();
            DoubleStackQueue doubleStackQueue = new DoubleStackQueue();
            int arrLen = random.nextInt(maxValue);
            for (int j=0; j<arrLen; j++){
                int i1 = random.nextInt(maxValue);

                int i2 = random.nextInt(10);
                if (i2 > random.nextInt(10)){
                    queue.add(i1);
                    doubleStackQueue.push(i1);
                } else {
                    Integer poll = queue.poll();
                    Integer pop = doubleStackQueue.pop();

                    if ((poll == null && pop != null) || (poll != null && pop == null)){
                        System.out.println("error ! poll: "+poll+" , pop: "+pop);
                        return;
                    }
                    if (poll != null && pop != null && !poll.equals(pop)){
                        System.out.println("error2 ! poll: "+poll+" , pop: "+pop);
                        return;
                    }
                }
            }
        }

        System.out.println("Nice !");
    }
}
