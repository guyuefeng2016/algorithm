package com.fei;

import java.util.Random;

/**
 * @description: 反转双链表
 * @author: guyuefeng
 * @create: 2022-04-13 17:12
 **/
public class ReverseDoubleNodes {

    public static class Node{
        private Node next;
        private Node parent;
        private Integer value;
    }

    public static Node reverseNodes(Node head){
        Node cur = head;

        while (cur != null){
            Node next = cur.next;
            Node parent = cur.parent;

            cur.next = parent;
            cur.parent = next;

            cur = next;
        }

        return head;
    }

    public static Node copyNodes(Node node){
        Node head = null;
        Node prev = null;

        while (node != null){
            Node newNode = new Node();
            newNode.value = node.value;

            if (prev == null){
                head = newNode;
            }

            newNode.parent = prev;
            if (prev != null){
                prev.next = newNode;
            }

            prev = newNode;
            node = node.next;
        }

        return head;
    }

    public static void main(String[] args) {
        int count = 1000000;

        int nodeLen = 100;
        int maxValue = 100;

        int iterator1 = 0;
        int iterator2 = 0;

        Random random = new Random();


        for (int i=0; i<count; i++){
            Node node = new Node();
            node.value = random.nextInt(maxValue);
            node.parent = null;
            node.next = null;

//            System.out.println("生成的节点：");
//            System.out.print(node.value+" ");

            Node head = node;
            for (int j=0; j<nodeLen-1; j++){
                Node node2 = new Node();
                node2.value = random.nextInt(maxValue);

                node.next = node2;
                node2.parent = node;
                node = node2;

//                System.out.print(node.value+" ");
            }
//            System.out.println();
            //尾节点
//            Node tail = node;
            //头节点
            Node node3 = head;

            Node copyHead = copyNodes(node3);
//            System.out.println("顺序打印: ");
//            Node head2 = copyHead;
//            do {
//                System.out.print(head2.value+" ");
//                if (head2.next == null){
//                    break;
//                }
//                head2 = head2.next;
//            } while (head2 != null);
//            System.out.println();
//
//
//            System.out.println("逆序打印: ");
//            Node tail = head2;
//            do {
//                System.out.print(tail.value+" ");
//                if (tail.parent == null){
//                    break;
//                }
//                tail = tail.parent;
//            } while (tail != null);
//            System.out.println();


            Node rHead = head;
            Node reverse = copyNodes(rHead);
            Node reverseNode0 = reverseNodes(reverse);

            Node reverseNode = reverseNode0;

            //头遍历对比
            Node nHead = head;
            Node copyHead2 = copyHead;
            while (nHead != null){
                if (!nHead.value.equals(reverseNode.value) && !copyHead2.value.equals(reverseNode.value) && !nHead.value.equals(copyHead2.value)){
                    System.out.println("error !");
                    return;
                }
                if (reverseNode.parent == null){
                    iterator1++;
                    break;
                }
                reverseNode = reverseNode.parent;
                copyHead2 = copyHead2.next;
                nHead = nHead.next;
            }

            //尾遍历对比
            while (nHead != null){
                if (!nHead.value.equals(reverseNode.value) && !copyHead2.value.equals(reverseNode.value) && !nHead.value.equals(copyHead2.value)){
                    System.out.println("error2 !");
                    return;
                }

                if (reverseNode.next == null){
                    iterator2++;
                    break;
                }

                reverseNode = reverseNode.next;
                copyHead2 = copyHead2.parent;
                nHead = nHead.parent;
            }

        }

        System.out.println("iterator1="+iterator1+" , iterator2="+iterator2);
        System.out.println("Nice !");
    }

    public static void main2(String[] args) {
        int count = 1;

        int nodeLen = 10;
        int maxValue = 100;

        Random random = new Random();

        for (int i=0; i<count; i++){
            Node node = new Node();
            node.value = random.nextInt(maxValue);
            node.parent = null;
            node.next = null;

            System.out.println("生成的节点：");
            System.out.print(node.value+" ");
            Node head = node;
            for (int j=0; j<nodeLen-1; j++){
                Node node2 = new Node();
                node2.value = random.nextInt(maxValue);

                node.next = node2;
                node2.parent = node;
                node = node2;

                System.out.print(node.value+" ");
            }
            System.out.println();

            System.out.println("顺序打印: ");
            Node head2 = head;
            do {
                System.out.print(head2.value+" ");
                head2 = head2.next;
            } while (head2 != null);
            System.out.println();

            System.out.println("逆序打印: ");
            //追踪尾节点
            Node tail = node;
            do {
                System.out.print(tail.value+" ");
                tail = tail.parent;
            } while (tail != null);
            System.out.println();

            System.out.println("反转节点 【顺序打印】: ");
            Node tail2 = null;
            Node node3 = head;
            Node node1 = reverseNodes(node3);
            do {
                System.out.print(node1.value+" ");
                tail2 = node1;
                node1 = node1.parent;
            } while (node1 != null);
            System.out.println();

            System.out.println("反转节点 【逆序打印】: ");
            Node node4 = tail2;
            do {
                System.out.print(node4.value+" ");
                node4 = node4.next;
            } while (node4 != null);
            System.out.println();
        }

        System.out.println("Nice !");
    }
}
