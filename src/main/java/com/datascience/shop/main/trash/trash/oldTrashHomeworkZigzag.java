package com.datascience.shop.main.trash.trash;

import com.datascience.shop.main.trash.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class oldTrashHomeworkZigzag {
    public static void main(String[] args) {
        TreeNode nodes = getTreeNode();
        System.out.println(zigzagLevelOrder(nodes));
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<List<Integer>> bigList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue.add(root);
        int level = 1;
        while (!queue.isEmpty() || !queue2.isEmpty()) {
            List<Integer> smallList = new ArrayList<>();
            if (level % 2 == 0) {
                while (!queue2.isEmpty()) {
                    root = queue2.poll();
                    smallList.add(root.val);
                    if (root.left != null) {
                        queue.add(root.left);
                    }
                    if (root.right != null) {
                        queue.add(root.right);
                    }
                }
            } else {
                while (!queue.isEmpty()) {
                    root = queue.poll();
                    smallList.add(root.val);
                    if (root.right != null) {
                        queue2.add(root.right);
                    }

                    if (root.left != null) {
                        queue2.add(root.left);
                    }
                }
            }
            level++;
            bigList.add(smallList);
        }
        return bigList;
    }


    public static TreeNode getTreeNode() {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(4);
        TreeNode treeNode4 = new TreeNode(5);
        TreeNode treeNode5 = new TreeNode(6);
        TreeNode treeNode6 = new TreeNode(7);
        TreeNode treeNode7 = new TreeNode(8);
        TreeNode treeNode8 = new TreeNode(9);
        TreeNode treeNode9 = new TreeNode(10);
        TreeNode treeNode10 = new TreeNode(11);
        TreeNode treeNode11 = new TreeNode(12);
        TreeNode treeNode12 = new TreeNode(13);
        TreeNode treeNode13 = new TreeNode(14);
        TreeNode treeNode14 = new TreeNode(15);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode2.left = treeNode5;
        treeNode2.right = treeNode6;
        treeNode3.left = treeNode7;
        treeNode3.right = treeNode8;
        treeNode4.left = treeNode9;
        treeNode4.right = treeNode10;
        treeNode5.left = treeNode11;
        treeNode5.right = treeNode12;
        treeNode6.left = treeNode13;
        treeNode6.right = treeNode14;
        return treeNode;
    }
}