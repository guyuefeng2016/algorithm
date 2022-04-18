package com.fei;

import java.util.*;

/**
 * @description:
 *
 * K个节点的组内逆序调整问题：
 * 给定一个单链表的头节点head，和一个正数k
 * 实现k个节点的小组内部逆序，如果最后一组不够k个就不调整
 * 例子:
 * 调整前：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8，k = 3
 * 调整后：3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 7 -> 8
 *
 * @author: guyuefeng
 * @create: 2022-04-15 12:13
 **/
public class KReverseNode {

    private Node head;

    public static class Node{
        private Node next;
        private Integer value;

        public Node(){

        }
        public Node(Integer value){
            this.value = value;
        }

        @Override
        public String toString() {
            return value+" ";
        }
    }

    public KReverseNode(){

    }

    public void add(Node node){
        if (head == null){
            head = node;
            return;
        }
        Node tmp = head;
        while (tmp.next != null){
            tmp = tmp.next;
        }
        tmp.next = node;
    }

    public void reverseK(int k){
        Node tmpNode = head;
        Node prev = null;
        Node prevTail = null;
        Node curHead = head;
        Node cur = head;
        Node newHead = null;
        while (true){
            Boolean flag = false;
            for (int i=0; i<k-1; i++){
                if (tmpNode == null || tmpNode.next == null){
                    break;
                }
                if (i == k-2){
                    flag = true;
                }
                tmpNode = tmpNode.next;
            }

            if (!flag){
                while (curHead != null){
                    prevTail.next = curHead;
                    prevTail = curHead;
                    curHead = curHead.next;
                }
                break;
            }
            if (newHead == null){
                newHead = tmpNode;
            }
            Node tmpNodeNext = tmpNode.next;
            do {
                Node next =  cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            } while (cur != tmpNodeNext);
            if (prevTail != null){
                prevTail.next = prev;
            }
            prevTail = curHead;
            prev = null;

            curHead = cur;
            tmpNode = cur;
        }

        head = newHead;
    }


    public static void main(String[] args) {
        int count = 100000;
        int nodeLen = 100;
        int maxValue = 100;
        boolean print = false;

        int iteratorComp = 0;

        Random random = new Random();

        for (int i=0; i<count; i++){
            int randomK = random.nextInt(nodeLen-2)+2;
            KReverseNode kReverseNode = new KReverseNode();
            Queue<Stack<Node>> stackQueue = new LinkedList<Stack<Node>>();
            Stack<Node> stack = null;
            List<Node> nodeList = new ArrayList<Node>();
            if (print){
                System.out.println("生成单链表：");
            }
            //left表示 "如果最后一组不够k个就不调整" 的那些数的 个数
            int left = nodeLen - nodeLen % randomK;
            for (int j=0; j<nodeLen; j++){
                Node node = new Node();
                node.value = random.nextInt(maxValue);

                if (j < left){
                    if (j % randomK == 0){
                        stack = new Stack<Node>();
                        stackQueue.add(stack);
                    }
                } else {
                    stack = new Stack<Node>();
                    stackQueue.add(stack);
                }
                stack.push(node);

                if (print){
                    System.out.print(node.value + " ");
                }
                kReverseNode.add(node);
                nodeList.add(node);
            }

            kReverseNode.reverseK(randomK);

            String stackQueueStr = stackQueue.toString();

            while (kReverseNode.head != null){
                while (!stackQueue.isEmpty()){
                    Stack<Node> poll = stackQueue.poll();

                    while (!poll.isEmpty()){
                        iteratorComp++;
                        Node pop = poll.pop();
                        if (kReverseNode.head != pop){
                            System.out.println("\r\nError !");
                            System.out.println("k: "+randomK);
                            System.out.println("原链表: "+nodeList.toString());

                            System.out.println("queue: "+stackQueueStr);
                            return;
                        }
                        kReverseNode.head = kReverseNode.head.next;
                    }
                }
            }

            if (kReverseNode.head != null || !stackQueue.isEmpty()){
                System.out.println("Error2 !");
                break;
            }

//            if (print){
//                System.out.println();
//                System.out.println("reverse之后的单链表：");
//                while (kReverseNode.head != null){
//                    System.out.print(kReverseNode.head.value+" ");
//                    kReverseNode.head = kReverseNode.head.next;
//                }
//            }

        }

        System.out.println("\r\n比对: "+iteratorComp+" 次");
        System.out.println("Nice !");
    }

}
