package com.fei;

/**
 * @description:
 * morris实现 前序、中序、后序遍历
 * @author: guyuefeng
 * @create: 2022-05-13 10:46
 **/
public class Morris {

    public static class TreeNode {
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

    /**
     * morris先序遍历
     * @param head
     */
    public static void preTrversal(TreeNode head){
        System.out.println("morris先序遍历：");
        TreeNode cur = head;

        while (cur != null){
            TreeNode morrisRight = cur.left;
            if (morrisRight != null){
                while (morrisRight.right != null && morrisRight.right != cur){
                    morrisRight = morrisRight.right;
                }

                //第一次来到
                if (morrisRight.right == null){
                    System.out.print(cur.val+" ");
                    //cur左孩子最右节点指向cur自己
                    morrisRight.right = cur;
                    //cur来到左孩子节点
                    cur = cur.left;
                    continue;
                } else {
                    //第二次来到 morrisRight.right != null && morrisRight.right == cur
                    morrisRight.right = null;
                }
            }
            if (cur.left == null) {
                System.out.print(cur.val + " ");
            }
            cur = cur.right;
        }
    }

    public static void pre(TreeNode head){
        if (head == null){
            return;
        }
        System.out.print(head.val+" ");
        pre(head.left);
        pre(head.right);
    }


    /**
     * morris中序遍历
     * @param head
     */
    public static void inOrderTrversal(TreeNode head){
        System.out.println("morris中序遍历：");
        TreeNode cur = head;

        while (cur != null){
            TreeNode morrisRight = cur.left;
            if (morrisRight != null){
                while (morrisRight.right != null && morrisRight.right != cur){
                    morrisRight = morrisRight.right;
                }

                if (morrisRight.right == null){
                    morrisRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    morrisRight.right = null;
                    System.out.print(cur.val+" ");
                }
            }

            if (cur.left == null){
                System.out.print(cur.val+" ");
            }
            cur = cur.right;
        }
    }

    public static void inOrder(TreeNode head){
        if (head == null){
            return;
        }
        inOrder(head.left);
        System.out.print(head.val+" ");
        inOrder(head.right);
    }

    /**
     * morris中序遍历
     * @param head
     */
    public static void midTrversal(TreeNode head) {
        System.out.println("morris中序遍历：");
        TreeNode cur = head;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(2);
        TreeNode treeNode4 = new TreeNode(1);
        TreeNode treeNode5 = new TreeNode(6);
        TreeNode treeNode6 = new TreeNode(9);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        treeNode4.right = treeNode8;


        pre(treeNode1);
        System.out.println();
        preTrversal(treeNode1);

        System.out.println();
        System.out.println();

        inOrder(treeNode1);
        System.out.println();
        inOrderTrversal(treeNode1);
    }

}
