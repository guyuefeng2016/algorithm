package com.fei;

import java.util.*;

/**
 * @description:
 * 二叉搜索树染色
 * https://leetcode-cn.com/problems/QO5KpG/
 *
 * 二叉搜索树：所有左孩子节点值 小于父亲节点值 ， 所有右孩子节点值大于父亲节点值
 * @author: guyuefeng
 * @create: 2022-04-22 18:02
 **/
public class BinarySearchTreeDye {

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

    private int ri=0;

    public int getNumber(TreeNode root, int[][] ops) {
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i=0,len=ops.length; i<len ; i++){
            if (ops[i][0] == 1){
                Integer start = ops[i][1];
                Integer end = ops[i][2];

                Integer old = indexMap.get(start);
                if (old == null){
                    indexMap.put(start, end);
                } else {
                    if (end > old){
                        indexMap.put(start, end);
                    }
                }
            } else {
                if (indexMap.size() > 0){
                    Integer start = ops[i][1];
                    Integer end = ops[i][2];

                    List<Integer> toRemove = new ArrayList<>();
                    Map<Integer, Integer> toAdd = new HashMap<>();
                    for (Map.Entry<Integer, Integer> entry : indexMap.entrySet()){
                        Integer start1 = entry.getKey();
                        Integer end1 = entry.getValue();

                        if (start <= start1){
                            if (end >= end1){
                                toRemove.add(start1);
                            } else if (end>= start1){
                                Integer old = toAdd.get(end+1);
                                if (old == null){
                                    toAdd.put(end+1, end1);
                                } else {
                                    if (end1 > old){
                                        toAdd.put(end+1, end1);
                                    }
                                }
                                toRemove.add(start1);
                            }
                        } else if (start <= end1){
                            if (end >= end1){
                                //这里之所以要get 看有没有值相等再put，就是防止覆盖
                                Integer old = toAdd.get(start1);
                                if (old == null){
                                    toAdd.put(start1, start-1);
                                } else {
                                    if ((start-1) > old){
                                        toAdd.put(start1, start-1);
                                    }
                                }
                            } else {
                                Integer old1 = toAdd.get(start1);
                                if (old1 == null){
                                    toAdd.put(start1, start-1);
                                } else {
                                    if ((start-1) > old1){
                                        toAdd.put(start1, start-1);
                                    }
                                }

                                Integer old = toAdd.get(end+1);
                                if (old == null){
                                    toAdd.put(end+1, end1);
                                } else {
                                    if (end1 > old){
                                        toAdd.put(end+1, end1);
                                    }
                                }
                            }
                            toRemove.add(start1);
                        }
                    }
                    for (Integer toRmKey: toRemove){
                        indexMap.remove(toRmKey);
                    }
                    toRemove.clear();
                    for (Map.Entry<Integer, Integer> entry : toAdd.entrySet()){
                        Integer start1 = entry.getKey();
                        Integer end1 = entry.getValue();

                        Integer old = indexMap.get(start1);
                        if (old == null){
                            indexMap.put(start1, end1);
                        } else {
                            if (end1 > old){
                                indexMap.put(start1, end1);
                            }
                        }
                    }
                    toAdd.clear();
                }
            }
        }
        System.out.println("--->"+indexMap);
        if (indexMap.size() == 0){
            return 0;
        }
        recurison(root, ops, indexMap);
        return ri;
    }

    public void recurison(TreeNode root, int[][] ops, Map<Integer, Integer> indexMap){
        if (root == null){
            return;
        }

        if (indexMap.size() != 0) {
            recurison(root.left, ops, indexMap);

            int val = root.val;
            for (Map.Entry<Integer, Integer> entry : indexMap.entrySet()) {
                Integer start = entry.getKey();
                Integer end = entry.getValue();

                if (val >= start && val <= end) {
                    ri++;
                    if (val == end) {
                        indexMap.remove(start);
                    }
                    break;
                }
            }

            recurison(root.right, ops, indexMap);
        }
    }
}
