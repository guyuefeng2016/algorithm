package com.fei;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * @description: 两个队列实现栈
 * @author: guyuefeng
 * @create: 2022-04-28 14:49
 **/
public class DoubleQueueStack {

    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();

    public void push(Integer num){
        if (queue1.isEmpty() && queue2.isEmpty()){
            queue1.add(num);
        } else if (queue1.isEmpty()){
            queue2.add(num);
        } else if (queue2.isEmpty()){
            queue1.add(num);
        }
    }

    public Integer pop(){
        if (queue1.isEmpty() && queue2.isEmpty()){
            return null;
        } else if (queue1.isEmpty()){
            while (queue2.size() != 1){
                queue1.add(queue2.poll());
            }
            return queue2.poll();
        } else if (queue2.isEmpty()){
            while (queue1.size() != 1){
                queue2.add(queue1.poll());
            }
            return queue1.poll();
        }

        return null;
    }

    public Boolean isEmpty(){
        return queue1.isEmpty() && queue2.isEmpty();
    }

    public static void main(String[] args) {
        int count = 1000000;
        int maxValue = 1000;

        Random random = new Random();

        for (int i=0; i<count; i++){
            Stack<Integer> stack = new Stack<>();
            DoubleQueueStack doubleQueueStack = new DoubleQueueStack();
            int arrLen = random.nextInt(maxValue);
            for (int j=0; j<arrLen; j++){
                int i1 = random.nextInt(maxValue);

                int i2 = random.nextInt(10);
                if (i2 > random.nextInt(10)){
                    stack.add(i1);
                    doubleQueueStack.push(i1);
                } else {
                    if (stack.isEmpty() && doubleQueueStack.isEmpty()){
                        continue;
                    } else if (stack.isEmpty()){
                        System.out.println("error ! stack empty but doubleQueueStack not empty");
                        return;
                    } else if (doubleQueueStack.isEmpty()){
                        System.out.println("error ! stack not empty but doubleQueueStack empty");
                        return;
                    }
                    Integer poll = stack.pop();
                    Integer pop = doubleQueueStack.pop();

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
