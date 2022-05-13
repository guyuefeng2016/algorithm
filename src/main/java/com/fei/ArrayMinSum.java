package com.fei;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @description:
 *
 * 在一个数组中，一个数左边比它小的数的总和，叫该数的小和
 * 所有数的小和累加起来，叫数组小和
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16
 * 给定一个数组arr，求数组小和
 *
 *
 * [1,3,4]
 * [1,2]  queue=[3,4]
 * [1,2,5]
 * 5= 1 3 1 2 4
 *
 * @author: guyuefeng
 * @create: 2022-04-28 17:12
 **/
public class ArrayMinSum {

    //TODO: 未解决
    public static int getArrayMinSum(int[] arr){
        if (arr == null || arr.length < 2){
            return -1;
        }

        Stack<Integer> stack1 = new Stack<>();

        Map<Integer, Integer> indexSum = new HashMap<>();

        stack1.push(0);
        indexSum.put(0, 0);

        for (int i=1,len=arr.length; i<len; i++){
            if (arr[stack1.peek()] < arr[i]){
                stack1.push(i);
                indexSum.put(i, arr[stack1.peek()] + indexSum.get(stack1.peek()));
            } else if (arr[stack1.peek()] == arr[i]) {
                stack1.push(i);
                indexSum.put(i, indexSum.get(stack1.peek()));
            } else {
                while (!stack1.isEmpty()){
                    if (arr[stack1.peek()] < arr[i]){
                        indexSum.put(i, arr[stack1.peek()] + indexSum.get(stack1.peek()));
                        break;
                    } else if (arr[stack1.peek()] == arr[i]){
                        break;
                    }
                }
            }
        }

        return -1;
    }


}
