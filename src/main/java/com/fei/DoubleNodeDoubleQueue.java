package com.fei;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 * @description: 用双链表实现双端队列
 * @author: guyuefeng
 * @create: 2022-04-14 16:50
 **/
public class DoubleNodeDoubleQueue {

    private Node head;
    private int size =0;

    public static class Node{
        private Node next;
        private Node parent;
        private Integer value;
    }

    public DoubleNodeDoubleQueue(){
    }

    /**
     * 做栈使用
     * @param node
     * @return
     */
    public Boolean push(Node node){
        if (head == null){
            head = node;
        } else {
            head.parent = node;
            node.next = head;
            head = node;
        }

        size++;
        return true;
    }

    /**
     * 做队列使用
     * @param node
     * @return
     */
    public Boolean offer(Node node){
        if (head == null){
            head = node;
        } else {
            Node temp = head;
            while (temp.next != null){
                temp = temp.next;
            }

            temp.next = node;
        }

        size++;
        return true;
    }

    public Node pop(){
        if (head == null){
            throw new RuntimeException("null...");
        }
        Node newHead = head.next;
        head.next = null;
        Node returnNode = head;
        head = newHead;

        size--;
        return returnNode;
    }

    public Node poll(){
        if (head == null){
            return null;
        }
        Node newHead = head.next;
        head.next = null;
        Node returnNode = head;
        head = newHead;

        size--;
        return returnNode;
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        int count = 1000000;
        int nodeLen = 100;
        int maxValue = 100;
        Random random = new Random();

        int iteratorPush1 = 0;
        int iteratorOffer1 = 0;
        int iteratorPop1 = 0;
        int iteratorPoll1 = 0;
        int iteratorComparePop1 = 0;
        int iteratorComparePoll1 = 0;

        for (int i=0; i<count; i++){
            DoubleNodeDoubleQueue dqueue = new DoubleNodeDoubleQueue();
            Deque<Node> queue = new ArrayDeque();

            for (int j=0; j<nodeLen; j++){
                if (random.nextInt(2) == 0){
                    Node node1 = new Node();
                    node1.value = random.nextInt(maxValue);

                    dqueue.push(node1);
                    queue.push(node1);

                    iteratorPush1++;
                } else {
                    Node node2 = new Node();
                    node2.value = random.nextInt(maxValue);

                    dqueue.offer(node2);
                    queue.offer(node2);

                    iteratorOffer1++;
                }

                if (random.nextInt(1000) % 2 == 0) {
                    Node node = new Node();
                    node.value = random.nextInt(maxValue);

                    dqueue.push(node);
                    queue.push(node);

                    iteratorPush1++;
                }

                if (random.nextInt(1000) % 3 == 0){
                    Node node = new Node();
                    node.value = random.nextInt(maxValue);

                    dqueue.offer(node);
                    queue.offer(node);

                    iteratorOffer1++;
                }

                if (random.nextInt(1000) % 25 == 0){
                    Node node = new Node();
                    node.value = random.nextInt(maxValue);

                    dqueue.pop();
                    queue.pop();

                    iteratorPop1++;
                }

                if (random.nextInt(1000) % 45 == 0){
                    dqueue.poll();
                    queue.poll();

                    iteratorPoll1++;
                }
            }

            while (!queue.isEmpty()){
                if (random.nextInt(2) == 0) {
                    iteratorComparePoll1++;
                    if (!queue.poll().value.equals(dqueue.poll().value)) {
                        System.out.println("error !");
                        return;
                    }
                } else {
                    iteratorComparePop1++;
                    if (!queue.pop().value.equals(dqueue.pop().value)) {
                        System.out.println("error2 !");
                        return;
                    }
                }
            }

            if (queue.size() != dqueue.size){
                System.out.println("error3 !");
                return;
            }
        }



        System.out.println("模拟push: "+iteratorPush1+" 次");
        System.out.println("模拟offer: "+iteratorOffer1+" 次");
        System.out.println("模拟pop: "+iteratorPop1+" 次");
        System.out.println("模拟poll: "+iteratorPoll1+" 次");
        System.out.println("模拟compre pop: "+iteratorComparePop1+" 次");
        System.out.println("模拟compre poll: "+iteratorComparePoll1+" 次");
        System.out.println("Nice !");
    }

}
