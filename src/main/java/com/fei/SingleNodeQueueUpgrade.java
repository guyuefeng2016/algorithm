package com.fei;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @description: 单链表实现队列 升级版本
 * @author: guyuefeng
 * @create: 2022-04-14 15:24
 **/
public class SingleNodeQueueUpgrade {

    private Node head;
    private Node tail;
    private int size;

    public static class Node{
        private Node next;
        private Integer value;
    }

    public SingleNodeQueueUpgrade(){
    }
    public SingleNodeQueueUpgrade(Node node){
        this.head = node;
        this.tail = node;
        size=1;
    }

    public Boolean add(Node node){
        if (node == null){
            return false;
        }
        if (head == null){
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    public Node poll(){
        if (head == null){
            return head;
        }
        Node newHead = head.next;
        Node rNode = head;
        head.next = null;
        head = newHead;

        if (head == null){
            tail = null;
        }

        size--;
        return rNode;
    }

    public Boolean hasNext(){
        if (head == null){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int count = 1000000;
        int nodeLen = 10;
        int maxValue = 100;
        Random random = new Random();

        for (int i=0; i<count; i++){
            SingleNodeQueueUpgrade singleNodeQueue = new SingleNodeQueueUpgrade();
            Queue<Node> queue = new LinkedList<Node>();
            for (int j=0; j<nodeLen; j++){
                Node node = new Node();
                node.value = random.nextInt(maxValue);

                singleNodeQueue.add(node);
                queue.add(node);

                int i1 = random.nextInt(1000);
                if (i1 % 50 == 0){
                    singleNodeQueue.poll();
                    queue.poll();
                }

                if (i1 % 30 == 0){
                    singleNodeQueue.poll();
                    queue.poll();
                }
            }

            while (!queue.isEmpty()){
                if (!queue.poll().value.equals(singleNodeQueue.poll().value)){
                    System.out.println("error !");
                    return;
                }
            }

            if (queue.size() != singleNodeQueue.size){
                System.out.println("error2 !");
                return;
            }
        }

        System.out.println("Nice !");
    }


}
