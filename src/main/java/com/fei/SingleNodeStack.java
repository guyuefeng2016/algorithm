package com.fei;

import java.util.Random;
import java.util.Stack;

/**
 * @description: 单链表实现栈
 * @author: guyuefeng
 * @create: 2022-04-14 16:32
 **/
public class SingleNodeStack {

    private Node node;
    private int size =0;

    public static class Node{
        private Node next;
        private Integer value;
    }

    public SingleNodeStack(){
    }

    public SingleNodeStack(Node node){
        this.node = node;
        size =1;
    }

    public Boolean add(Node newNode){
        if (node == null){
            node = newNode;
            size++;
            return true;
        }

        newNode.next = node;
        node = newNode;
        size++;
        return true;
    }

    public Node pop(){
        if (node == null){
            return null;
        }

        Node newHead = node.next;
        node.next = null;
        Node returnNode = node;
        node = newHead;
        size--;
        return returnNode;
    }

    public Boolean isEmpty(){
        return size == 0? true: false;
    }

    public Boolean hasNext(){
        return size == 0? false: true;
    }

    public static void main(String[] args) {
        int count = 1000000;
        int nodeLen = 100;
        int maxValue = 100;
        Random random = new Random();

        for (int i=0; i<count; i++){
            SingleNodeStack singleNodeStack = new SingleNodeStack();
            Stack<Node> stack = new Stack<Node>();
            for (int j=0; j<nodeLen; j++){
                Node node = new Node();
                node.value = random.nextInt(maxValue);

                singleNodeStack.add(node);
                stack.add(node);

                int i1 = random.nextInt(1000);
                if (i1 % (random.nextInt(nodeLen)+1) == 0){
                    singleNodeStack.pop();
                    stack.pop();
                }

                if (i1 % (random.nextInt(nodeLen)+1) == 0){
                    Node node2 = new Node();
                    node2.value = random.nextInt(maxValue);

                    singleNodeStack.add(node2);
                    stack.add(node2);

                    singleNodeStack.pop();
                    stack.pop();
                }
            }

            while (!singleNodeStack.isEmpty()){
                if (!stack.pop().value.equals(singleNodeStack.pop().value)){
                    System.out.println("error !");
                    return;
                }
            }

            if (stack.size() != singleNodeStack.size){
                System.out.println("error2 !");
                return;
            }
        }

        System.out.println("Nice !");
    }
}
