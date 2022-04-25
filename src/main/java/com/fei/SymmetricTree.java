package com.fei;

/**
 * @description:
 * https://leetcode.com/problems/symmetric-tree/
 * 镜面树
 * @author: guyuefeng
 * @create: 2022-04-20 19:06
 **/
public class SymmetricTree {

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

    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }
        return recursion(root.left, root.right);
    }

    public boolean recursion(TreeNode left, TreeNode right){
        if (left == null && right == null){
            return true;
        } else if (left == null){
            return false;
        } else if (right == null){
            return false;
        }

        if (left.val != right.val){
            return false;
        }

        boolean recursion1 = recursion(left.left, right.right);
        boolean recursion2 = recursion(left.right, right.left);

        return recursion1 && recursion2;
    }
}
