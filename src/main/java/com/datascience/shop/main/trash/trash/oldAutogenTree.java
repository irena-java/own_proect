package com.datascience.shop.main.trash.trash;

import com.datascience.shop.main.trash.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Math.round;

public class oldAutogenTree {
            private final int number;
            private final oldAutogenTree left_tree;
            private final oldAutogenTree right_tree;

    public int getNumber() {
        return number;
    }

    public oldAutogenTree getLeft_tree() {
        return left_tree;
    }

    public oldAutogenTree getRight_tree() {
        return right_tree;
    }


    @Override
    public String toString() {
        return "AutogenTree{" +
                "number=" + number +
                ", left_tree=" + left_tree +
                ", right_tree=" + right_tree +
                '}';
    }


            oldAutogenTree(float chance, float chance_change) {
                Random rand = new Random();
                int randomWeight = rand.nextInt(100);
                this.number = randomWeight;

                if (chance >= 50) {
                    int a1 = rand.nextInt(round(100 / (100 - chance)));
                    int a2 = rand.nextInt(round(100 / (100 - chance)));
                    if (a1 != 0) {
                        this.left_tree = new oldAutogenTree(chance - chance_change, chance_change);
                    } else {
                        this.left_tree = null;
                    }
                    if (a2 != 0) {
                        this.right_tree = new oldAutogenTree(chance - chance_change, chance_change);
                    } else {
                        this.right_tree = null;
                    }
                } else {
                    int a1 = rand.nextInt(round(100 / chance));
                    int a2 = rand.nextInt(round(100 / chance));
                    if (a1 == 0) {
                        this.left_tree = new oldAutogenTree(chance - chance_change, chance_change);
                    } else {
                        this.left_tree = null;
                    }
                    if (a2 == 0) {
                        this.right_tree = new oldAutogenTree(chance - chance_change, chance_change);
                    } else {
                        this.right_tree = null;
                    }
                }
            }


    public static class HomeworkZigzag {
        public static void main(String[] args) {
            TreeNode nodes = getTreeNode();
            System.out.println(zigzagLevelOrder(nodes));
        }

        public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<TreeNode> trees = new ArrayList<>();
            if (root != null) {
                trees.add(root);
            }
            List<List<Integer>> final_values = new ArrayList<>();
            int turn = 0;
            while (trees.size() > 0) {
                turn = turn + 1;
                List<Integer> val_in_line = new ArrayList<>();
                for (TreeNode tree : trees) {
                    int num = tree.val;
                    val_in_line.add(num);
                }
                if (val_in_line.size() > 0) {
                    if (turn % 2 == 0) {
                        Collections.reverse(val_in_line);
                    }
                    final_values.add(val_in_line);
                }
                trees = iterate_trees(trees);
            }
            return final_values;
        }

        public static List<TreeNode> iterate_trees(List<TreeNode> trees) {
            List<TreeNode> new_trees = new ArrayList<>();
            for (TreeNode tree : trees) {
                if (tree.left != null) {
                    new_trees.add(tree.left);
                }
                if (tree.right != null) {
                    new_trees.add(tree.right);
                }
            }
            return new_trees;
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
}


