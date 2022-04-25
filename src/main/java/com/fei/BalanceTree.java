package com.fei;

/**
 * @description:
 * https://leetcode.com/problems/balanced-binary-tree/submissions/
 *
 * 判断是否是平衡搜索二叉树
 *
 * @author: guyuefeng
 * @create: 2022-04-22 14:18
 **/
public class BalanceTree {

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

    boolean flag = true;
    public boolean isBalanced(TreeNode root) {
        recursion(root, 0);
        return flag;
    }

    public int recursion(TreeNode root, int depth){
        if (root == null){
            return depth;
        }

        if (flag) {
            int left = recursion(root.left, depth + 1);
            int right = recursion(root.right, depth + 1);

            if (Math.abs(left-right) > 1){
                flag = false;

                return Integer.MIN_VALUE;
            }
            return Math.max(left, right);
        }

        return Integer.MIN_VALUE;
    }

}
