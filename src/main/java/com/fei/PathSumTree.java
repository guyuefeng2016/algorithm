package com.fei;

/**
 * @description:
 * 在二叉树上能否组成路径和
 * Leetcode原题，https://leetcode.com/problems/path-sum
 *
 * @author: guyuefeng
 * @create: 2022-04-22 15:41
 **/
public class PathSumTree {

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

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null){
            return false;
        }
        int recursion = recursion(root, targetSum, 0);
        return recursion == -1;
    }


    public int recursion(TreeNode root, int targetSum, int curSum){
        if(root == null){
            return 0;
        }
        int leftSum = recursion(root.left, targetSum, root.val + curSum);

        int rightSum = 0;
        if (leftSum != -1) {
            rightSum = recursion(root.right, targetSum, root.val + curSum);
        }

        if (root.left == null && root.right == null){
            if ((root.val + curSum) == targetSum){
                return -1;
            }
            return 0;
        }

        return Math.min(leftSum, rightSum);
    }
}
