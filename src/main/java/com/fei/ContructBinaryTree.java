package com.fei;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @description:
 * 用先序数组和中序数组重建一棵树
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * @author: guyuefeng
 * @create: 2022-04-21 10:35
 **/
public class ContructBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i=0; i<preorder.length; i++){
            map.put(inorder[i], i);
        }
        return recursion(map, 0, preorder.length-1, preorder, inorder);
    }

    int i=0;

    public TreeNode recursion(Map<Integer, Integer> map, int l, int r,  int[] preorder, int[] inorder){
        if (l > r){
            return null;
        }

        int v = preorder[i++];
        TreeNode treeNode = new TreeNode(v);

        TreeNode left = recursion(map, l, map.get(v) - 1, preorder, inorder);
        System.out.println(i);
        treeNode.left = left;
        TreeNode right = recursion(map,map.get(v)+1, r, preorder, inorder);
        treeNode.right = right;

        return treeNode;
    }

    //[3,9,20,null,null,15,7]
    public static void main(String[] args) {
        int[] arr1 = new int[]{3,9,20,15,7};
        int[] arr2 = new int[]{9,3,15,20,7};

//        int[] arr1 = new int[]{1,2,3};
//        int[] arr2 = new int[]{2,3,1};

        TreeNode treeNode = new ContructBinaryTree().buildTree(arr1, arr2);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(treeNode);

        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            if (poll != null){
                System.out.print(poll.val+" ");
            }

            if (poll.left != null){
                queue.add(poll.left);
            }
            if (poll.right != null){
                queue.add(poll.right);
            }
        }

//        while (!queue.isEmpty()){
//            TreeNode poll = queue.peek();
//            if (poll != null){
//                System.out.print(poll.val+" ");
//            } else {
//                System.out.print("null ");
//            }
//
//            if (poll != null){
//                queue.add(poll.left);
//                queue.add(poll.right);
//            }
//            queue.remove(poll);
//        }
    }
}
