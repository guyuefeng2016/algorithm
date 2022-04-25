package com.fei;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @description:
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 *
 * 二叉树按层遍历并收集节点
 *
 * @author: guyuefeng
 * @create: 2022-04-22 10:30
 **/
public class LevelOrderBottom {

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

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> reslist = new ArrayList<>();
        if(root == null){
            return reslist;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        recursion(0, root, map);

        reslist.addAll(map.entrySet().stream().sorted((x1,x2)->x2.getKey()-x1.getKey()).map(x -> x.getValue()).collect(Collectors.toList()));

        return reslist;
    }



    public Integer recursion(Integer depth, TreeNode node, Map<Integer, List<Integer>> map){
        if (node == null){
            return 0;
        }

        if (map.containsKey(depth)){
            map.get(depth).add(node.val);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(node.val);
            map.put(depth, list);
        }

        recursion(depth+1, node.left, map);
        recursion(depth+1, node.right, map);

        return depth;
    }



    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        List<List<Integer>> reslist = new ArrayList<>();
        if(root == null){
            return reslist;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        Stack<List<Integer>> stack = new Stack<>();
        List<TreeNode> parentList = new ArrayList<>();
        List<TreeNode> childList = new ArrayList<>();
        childList.add(root);

//        Map<TreeNode, Integer> parentMap = new LinkedHashMap<>();
//        Map<TreeNode, Integer> childMap = new LinkedHashMap<>();
//        childMap.put(root, 1);

        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();

            if (!parentList.contains(poll)){
                parentList.clear();
                parentList.addAll(childList);
                childList.clear();

                stack.add(parentList.stream().map(x->x.val).collect(Collectors.toList()));
            }

//            if (!parentMap.containsKey(poll)){
//                parentMap.clear();
//                parentMap.putAll(childMap);
//                childMap.clear();
//
//                stack.add(parentMap.entrySet().stream().map(x->x.getKey().val).collect(Collectors.toList()));
//            }


            if (poll.left != null){
                if (parentList.contains(poll)){
                    childList.add(poll.left);
                }

//                if (parentMap.containsKey(poll)){
//                    childMap.put(poll.left, 1);
//                }

                queue.add(poll.left);
            }

            if (poll.right != null){
                if (parentList.contains(poll)){
                    childList.add(poll.right);
                }
//                if (parentMap.containsKey(poll)){
//                    childMap.put(poll.right, 1);
//                }
                queue.add(poll.right);
            }
        }

        while (!stack.isEmpty()){
            List<Integer> pop = stack.pop();
            reslist.add(pop);
        }

        return reslist;
    }

}
