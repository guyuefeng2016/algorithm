package com.fei;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @description:
 *
 * 两个有序链表的合并
 * 给定两个有序链表的头节点head1和head2，
 * 返回合并之后的大链表，要求依然有序
 * 例子     1 -> 3 -> 3 -> 5 -> 7   2 -> 2 -> 3 -> 3-> 7
 * 返回     1 -> 2 -> 2 -> 3 -> 3 -> 3 -> 3 -> 5 -> 7
 *
 * @author: guyuefeng
 * @create: 2022-04-18 14:17
 **/
public class NodesMerge {


    public static class Node{
        private Node next;
        private Integer value;
    }

    public static Node combine(Node node1, Node node2){
        Node head = null;

        Node tmp = head;
        while (node1 != null){
            Integer v1 = node1.value;
            Integer v2 = Integer.MAX_VALUE;

            if (node2 != null){
                v2 = node2.value;
            }

            int min = Math.min(v1, v2);

            Node node = new Node();
            node.value = min;

            if (tmp != null){
                tmp.next = node;
            } else {
                head = node;
            }
            tmp = node;

            if (min == v1){
                node1 = node1.next;
            } else {
                node2 = node2.next;
            }
        }

        while (node2 != null){
            Integer v2 = node2.value;

            Node node = new Node();
            node.value = v2;

            tmp.next = node;
            tmp = node;

            node2 = node2.next;
        }

        return head;
    }


    public static void main(String[] args) {
        int count = 1000000;
        int maxValue = 100;
        boolean print=false;

        Random random = new Random();

        for (int i=0; i<count; i++){
            int nodeLen1 = random.nextInt(maxValue);
            int nodeLen2 = random.nextInt(maxValue);

            Node head1 = new Node();
            Node head2 = new Node();
            head1.value = random.nextInt(maxValue);
            head2.value = random.nextInt(maxValue);

            PriorityQueue<Integer> queue = new PriorityQueue<>();

            queue.add(head1.value);
            queue.add(head2.value);

            if (print){
                System.out.print("head1: "+head1.value+" ");
            }

            Node tmp1 = head1;
            Node prevNode1 = head1;
            for (int j=0; j<nodeLen1-1; j++){
                Node node1 = new Node();
                while ((node1.value = random.nextInt(maxValue)) < prevNode1.value){
                }

                queue.add(node1.value);

                prevNode1 = node1;

                if (print){
                    System.out.print(node1.value+" ");
                }
                tmp1.next = node1;
                tmp1 = node1;
            }

            if (print){
                System.out.println();
            }

            if (print){
                System.out.print("head2: "+head2.value+" ");
            }

            Node tmp2 = head2;
            Node prevNode2 = head2;
            for (int j=0; j<nodeLen2-1; j++){
                Node node2 = new Node();

                while ((node2.value = random.nextInt(maxValue)) < prevNode2.value){
                }

                queue.add(node2.value);

                prevNode2 = node2;

                if (print){
                    System.out.print(node2.value+" ");
                }

                tmp2.next = node2;
                tmp2 = node2;
            }

            if (print){
                System.out.println();
            }

            Node newHead = combine(head1, head2);
            do {
                if (print){
                    System.out.print(newHead.value+" ");
                }
                if (!queue.poll().equals(newHead.value)){
                    System.out.println("ERROR !");
                    return;
                }
                newHead = newHead.next;
            } while (newHead != null);
        }

        System.out.println("\r\nNice !");
    }
}
