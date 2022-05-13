package com.fei;

import java.util.*;

/**
 * @description: 在链表中删除指定值的所有节点
 * @author: guyuefeng
 * @create: 2022-04-27 11:20
 **/
public class DeleteNode {


    public static class Node{
        private Node next;
        private Integer val;
    }

    /**
     * 删除链表中所有value的节点，并且返回此时头节点
     * @param head
     * @param value
     * @return
     */
    public static Node deleteNode(Node head, Integer value){
        if (head == null){
            return head;
        }

        Node newHead = head;
        Node prev = null;
        do {
            if (head.val.equals(value)){
                Node next = head.next;
                if (prev == null){
                    newHead = next;
                } else {
                    prev.next = next;
                }
                head = next;
            } else {
                prev = head;
                head = head.next;
            }
        } while (head != null);

        return newHead;
    }

    public static void main(String[] args) {
        int count = 1000000;
        int maxValue = 100;
        int nodeLen = 10;
        int iterator = 0;
        Random random = new Random();

        boolean print = false;

        for (int i=0; i<count; i++){
            Node head = new Node();
            head.val = random.nextInt(maxValue);
            if (print){
                System.out.print(head.val+" ");
            }
            Node curHead = head;

            int deleteIndex = random.nextInt(nodeLen);
            Integer deleteVal = head.val;

            List<Node> nodeList = new LinkedList<>();
            Queue<Node> nodeQueue = new LinkedList<>();
            nodeQueue.add(head);
            nodeList.add(head);

            for (int j=1; j<nodeLen; j++){
                Node cur = new Node();
                cur.val = random.nextInt(maxValue);
                nodeQueue.add(cur);
                nodeList.add(cur);
                if (print){
                    System.out.print(cur.val+" ");
                }
                if (deleteIndex == j){
                    deleteVal = cur.val;
                }
                curHead.next = cur;
                curHead = cur;
            }

            Node node = deleteNode(head, deleteVal);

            while (!nodeQueue.isEmpty()){
                Node poll = nodeQueue.poll();

                if (!poll.val.equals(deleteVal)){
                    iterator++;
                    if (!node.val.equals(poll.val)){
                        System.out.println("比对错误！nodeList: "+ Arrays.toString(nodeList.toArray())+" , nodeQueueVal: "+poll.val+" , nodeVal: "+node.val+" , deleteVal: "+deleteVal);
                        return;
                    }
                    node = node.next;
                }
            }
        }

        System.out.println();
        System.out.println("比对："+iterator+"次！");
        System.out.println("Nice !");
    }

}
