package com.fei;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * https://leetcode-cn.com/problems/same-tree/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 *
 * @author: guyuefeng
 * @create: 2022-04-20 18:33
 **/
public class SameTree {

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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null){
            return true;
        } else if (p == null){
            return false;
        } else if (q == null){
            return false;
        }

        if (p.val != q.val){
            return false;
        }

        boolean sameTreeLeft = isSameTree(p.left, q.left);

        boolean sameTreeRight = false;
        if (sameTreeLeft) {
            sameTreeRight = isSameTree(p.right, q.right);
        } else {
            return false;
        }

        return sameTreeLeft && sameTreeRight;
    }

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null){
            return true;
        } else if (p == null){
            return false;
        } else if (q == null){
            return false;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        queue1.add(p);
        queue2.add(q);

        while (!queue1.isEmpty()){
            TreeNode poll1 = queue1.poll();
            TreeNode poll2 = queue2.poll();

            if (poll2 == null){
                return false;
            }

            if (poll1.val != poll2.val){
                return false;
            }

            if (poll1.left == null && poll2.left != null){
                return false;
            }

            if (poll1.right == null && poll2.right != null){
                return false;
            }

            if (poll2.left == null && poll1.left != null){
                return false;
            }

            if (poll2.right == null && poll1.right != null){
                return false;
            }

            if (poll1.left != null){
                queue1.add(poll1.left);
            }
            if (poll1.right != null){
                queue1.add(poll1.right);
            }

            if (poll2.left != null){
                queue2.add(poll2.left);
            }
            if (poll2.right != null){
                queue2.add(poll2.right);
            }
        }

        if (!queue2.isEmpty()){
            return false;
        }

        return true;
    }
}
