package com.fei;

import java.util.Random;

/**
 * @description:
 *
 * 两个链表相加问题
 * 给定两个链表的头节点head1和head2，
 * 认为从左到右是某个数字从低位到高位，返回相加之后的链表
 * 例子     4 -> 3 -> 6        2 -> 5 -> 3
 * 返回     6 -> 8 -> 9
 * 解释     634 + 352 = 986
 *
 * @author: guyuefeng
 * @create: 2022-04-18 14:17
 **/
public class NodesAdd {


    public static class Node{
        private Node next;
        private Integer value;
    }

    public static Node add(Node node1, Node node2){
        if (node1 == null){
            return node2;
        }
        if (node2 == null){
            return node1;
        }

        Node head = null;
        Node prev = head;

        while (node1 != null){
            Integer nodeValue1 = node1.value;
            Integer nodeValue2 = 0;
            if (node2 != null){
                nodeValue2 = node2.value;
            }

            Node node = new Node();
            node.value = nodeValue1+nodeValue2;

            if (prev != null){
                prev.next = node;
            } else {
                head = node;
            }
            prev = node;

            node1 = node1.next;
            if (node2 != null){
                node2 = node2.next;
            }
        }

        while (node2 != null){
            Integer value = node2.value;
            Node node = new Node();
            node.value = value;
            prev.next = node;

            node2 = node2.next;
        }

        return head;
    }

    public static void main(String[] args) {
        int count = 1;
        int nodeLen1 = 3;
        int nodeLen2 = 4;
        int maxValue = 10;
        boolean print=true;

        Random random = new Random();

        for (int i=0; i<count; i++){
            Node head1 = new Node();
            Node head2 = new Node();
            head1.value = random.nextInt(maxValue);
            head2.value = random.nextInt(maxValue);

            if (print){
                System.out.print("head1: "+head1.value+" ");
            }

            Node tmp1 = head1;
            Node tmp2 = head2;
            for (int j=0; j<nodeLen1-1; j++){
                Node node1 = new Node();
                node1.value = random.nextInt(maxValue);

                if (print){
                    System.out.print(node1.value+" ");
                }
                tmp1.next = node1;
                tmp1 = node1;
            }

            System.out.println();

            if (print){
                System.out.print("head2: "+head2.value+" ");
            }
            for (int j=0; j<nodeLen2-1; j++){
                Node node2 = new Node();
                node2.value = random.nextInt(maxValue);

                if (print){
                    System.out.print(node2.value+" ");
                }

                tmp2.next = node2;
                tmp2 = node2;
            }

            System.out.println();
            Node newHead = add(head1, head2);
            do {
                System.out.print(newHead.value+" ");
                newHead = newHead.next;
            } while (newHead != null);
        }
    }
}
