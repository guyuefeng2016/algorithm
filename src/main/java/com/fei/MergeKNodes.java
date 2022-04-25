package com.fei;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description:
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * @author: guyuefeng
 * @create: 2022-04-20 12:17
 **/
public class MergeKNodes {

    public static class ListNode{
        private ListNode next;
        private int val;
    }

    public static class MyComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    private static PriorityQueue<ListNode> queue = new PriorityQueue<>(new MyComparator());

    public static ListNode mergeKLists(ListNode[] nodes){
        if (nodes == null || nodes.length == 0){
            return null;
        }
        if (nodes.length == 1){
            return nodes[0];
        }

        for (int i = 0, len = nodes.length; i < len; i++) {
            if (nodes[i] != null){
                queue.add(nodes[i]);
            }
        }

        if (queue.isEmpty()){
            return null;
        }

        ListNode head = queue.poll();
        if (head.next != null){
            queue.add(head.next);
        }

        ListNode tmp = head;

        while (!queue.isEmpty()) {
            ListNode poll = queue.poll();
            tmp.next = poll;
            tmp = poll;

            if (poll.next != null){
                queue.add(poll.next);
            }
        }

        return head;
    }

    /**
     *
     * @param nodes
     * @return
     */
    public static ListNode mergeKLists3(ListNode[] nodes){
        if (nodes == null || nodes.length == 0){
            return null;
        }
        if (nodes.length == 1){
            return nodes[0];
        }

        ListNode head = null;


        return head;
    }

    /**
     * 这种是最差的，引进了数据结构list，但是性能更差
     * @param nodes
     * @return
     */
    public static ListNode mergeKLists2(ListNode[] nodes){
        if (nodes == null || nodes.length == 0){
            return null;
        }
        if (nodes.length == 1){
            return nodes[0];
        }


        List<ListNode> listNodes = new ArrayList<>();
        for (int i=0,len=nodes.length; i<len; i++){
            ListNode node = nodes[i];
            if (node == null){
                continue;
            }
            listNodes.add(node);
        }

        if (listNodes.isEmpty()){
            return null;
        }

        ListNode head = null;
        ListNode tmp = null;

        while (!listNodes.isEmpty()){
            ListNode minNode = listNodes.get(0);
            for (int i=1,len=listNodes.size(); i<len; i++){
                ListNode node = listNodes.get(i);
                if (node.val < minNode.val){
                    minNode = node;
                }
            }
            if (tmp != null){
                tmp.next = minNode;
            } else {
                head = minNode;
            }
            tmp = minNode;
            listNodes.remove(minNode);
            if (minNode.next != null){
                listNodes.add(minNode.next);
            }
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode();
        node.val = 1;

        ListNode node11 = new ListNode();
        node11.val = 4;

        ListNode node22 = new ListNode();
        node22.val = 5;

        node.next = node11;
        node11.next = node22;

        ListNode node1 = new ListNode();
        node1.val = 1;

        ListNode node12 = new ListNode();
        node12.val = 3;

        ListNode node23 = new ListNode();
        node23.val = 4;

        node1.next = node12;
        node12.next = node23;


        ListNode node3 = new ListNode();
        node3.val = 2;

        ListNode node31 = new ListNode();
        node31.val = 6;

        node3.next = node31;


        ListNode[] listNodes = new ListNode[3];
        listNodes[0] = node;
        listNodes[1] = node1;
        listNodes[2] = node3;
        ListNode merge = mergeKLists(listNodes);

        while (merge != null){
            System.out.print(merge.val+" ");
            merge = merge.next;
        }
    }

}
