package com.fei;

/**
 * @description:
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * 返回一棵树的最大深度
 * @author: guyuefeng
 * @create: 2022-04-21 10:27
 **/
public class MaxDepthNode {


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


    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) +1;
    }
}
