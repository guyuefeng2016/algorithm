package com.fei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 *
 * 在二叉树上收集所有达标的路径和
 * Leetcode原题，https://leetcode.com/problems/path-sum-ii
 *
 * @author: guyuefeng
 * @create: 2022-04-22 16:36
 **/
public class PathSumListTree {

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

    private List<List<Integer>> resList = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        recursion(root, targetSum, 0, new LinkedList(), new HashMap<TreeNode, Integer>());
        return resList;
    }


    public void recursion(TreeNode root, int targetSum, int curSum , List pathList, HashMap<TreeNode, Integer> indexMap){
        if (root == null){
            return;
        }

        pathList.add(root.val);
        indexMap.put(root, pathList.size()-1);

        int sum = root.val + curSum;
        recursion(root.left, targetSum, sum, pathList, indexMap);
        recursion(root.right, targetSum, sum, pathList, indexMap);

        if (root.left == null && root.right == null){
            if (sum == targetSum){
                List newList = new LinkedList();
                newList.addAll(pathList);
                resList.add(newList);
            }
        }

        pathList.remove(indexMap.get(root).intValue());
    }
}
