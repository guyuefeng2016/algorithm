package com.fei;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * @description: 反转单链表
 * @author: guyuefeng
 * @create: 2022-04-13 16:19
 **/
public class ReverseSingleNodes {


    public static class Node{
        private Node next;
        private Integer value;
    }

    public static Node reverseNode(Node head){
        Node prev = null;
        while (head.next != null){
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        head.next = prev;
        return head;
    }

    public static void main(String[] args) {
        int count = 1000000;

        int nodeLen = 100;
        int maxValue = 100;

        Random random = new Random();

        for (int i=0; i<count; i++){
            Node node = new Node();
            node.value = random.nextInt(maxValue);

            Stack<Integer> stack = new Stack<Integer>();
            stack.add(node.value);

            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(node.value);

            Node head = node;
            for (int j=0; j<nodeLen-1; j++){
                Node node2 = new Node();
                node2.value = random.nextInt(maxValue);

                node.next = node2;
                node = node2;

                stack.add(node.value);
                queue.add(node.value);
            }

            Node head2 = head;
            do {
                if (!head.value.equals(queue.poll())){
                    System.out.println("fuck...queue");
                    return;
                }
                head = head.next;
            } while (head != null);

            Node node1 = reverseNode(head2);
            do {
                if (!node1.value.equals(stack.pop())){
                    System.out.println("fuck .. stack");
                    return;
                }
                node1 = node1.next;
            } while (node1 != null);
        }

        System.out.println("Nice !");
    }
}
