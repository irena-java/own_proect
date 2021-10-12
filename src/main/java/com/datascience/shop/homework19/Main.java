package com.datascience.shop.homework19;

import java.util.Stack;

public class Main {
    static int sum;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        TreeNode treeNode = getTreeNode();
        sum = calculateSum(treeNode);
        System.out.println("Результат: " + sum);
    }

    private static TreeNode getTreeNode() {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(0);
        TreeNode treeNode4 = new TreeNode(1);
        TreeNode treeNode5 = new TreeNode(0);
        TreeNode treeNode6 = new TreeNode(1);
        TreeNode treeNode7 = new TreeNode(1);
        TreeNode treeNode8 = new TreeNode(1);
        TreeNode treeNode9 = new TreeNode(1);
        TreeNode treeNode10 = new TreeNode(0);
        TreeNode treeNode11 = new TreeNode(1);
        TreeNode treeNode12 = new TreeNode(1);
        TreeNode treeNode13 = new TreeNode(0);
        TreeNode treeNode14 = new TreeNode(1);
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

    public static int calculateSum(TreeNode root) {
        if (root.left == null) {
            stack.push(root.val);
            String stackStroka = transformStackToString(stack);
            sum += Integer.parseInt(stackStroka, 2);
            stack.pop();
            return sum;
        } else {
            stack.push(root.val);
            calculateSum(root.left);
            calculateSum(root.right);
            stack.pop();
        }
        return sum;
    }

    private static String transformStackToString(Stack<Integer> stack) {
        String result = "";
        for (Integer i : stack) {
            result = result + i;
        }
        return result;
    }
}