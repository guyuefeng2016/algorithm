package com.fei;

/**
 * @description: 单链表实现队列
 * @author: guyuefeng
 * @create: 2022-04-14 15:24
 **/
public class SingleNodeQueue {

    private Node head;

    public static class Node{
        private Node next;
        private Integer value;
    }

    public SingleNodeQueue(){
    }
    public SingleNodeQueue(Node node){
        this.head = node;
    }

    public Boolean add(Node node){
        if (node == null){
            return false;
        }
        if (head == null){
            head = node;
            return true;
        }

        Node headTmp = head;
        while (headTmp.next != null){
            headTmp = headTmp.next;
        }
        headTmp.next = node;
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

        return rNode;
    }

    public Boolean hasNext(){
        if (head == null){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SingleNodeQueue singleNodeQueue = new SingleNodeQueue();

        Node node = new Node();
        node.value = 5;

        Node node2 = new Node();
        node2.value = 3;

        Node node3 = new Node();
        node3.value = 4;

        Node node4 = new Node();
        node4.value = 7;

        singleNodeQueue.add(node);
        singleNodeQueue.add(node2);
        singleNodeQueue.add(node3);
        singleNodeQueue.add(node4);

        Node poll1 = singleNodeQueue.poll();
        System.out.println(poll1.next+" , "+poll1.value);

        Node node5 = new Node();
        node5.value = 2;
        singleNodeQueue.add(node5);

        while (singleNodeQueue.hasNext()){
            Node poll = singleNodeQueue.poll();
            System.out.println(poll.value);
        }
    }


}
