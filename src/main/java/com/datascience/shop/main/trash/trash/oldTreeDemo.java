package com.datascience.shop.main.trash.trash;

import com.datascience.shop.main.trash.Tree;

import java.util.ArrayList;
import java.util.List;

public class oldTreeDemo {
    private static int depthLeft;
    private static int depthRight;
    private List<Integer> linearTree = new ArrayList<>();


    public static void main(String[] args) {



        oldTreeDemo oldTreeDemo = new oldTreeDemo();
        Tree tree = new Tree();
        //Tree tree1 = treeDemo.initTree(tree);


//            List<Integer> listPlus = new ArrayList<>();
//            List<Integer> listPlus1 = listPlus1(listPlus);
//            //listPlus1 = listPlus1(treeInLine);
//            System.out.println(listPlus.toString());
    }

/*    public Tree initTree(Tree tree) {
        int randomTrueFalse;
        Random random = new Random();
        int randomWeight = random.nextInt(100);
        randomTrueFalse = random.nextInt(10);

        while (depthLeft < 5) {
            tree.setWeight(randomWeight);
            if (randomTrueFalse >= 0) {
                depthLeft++;
                tree.setLeftTree(new Tree());
                Tree tree777 = initTree(tree.getLeftTree());
                tree.setLeftTree(tree777);

            } else {
                tree.setLeftTree(null);
            }
        }
        tree.setWeight(randomWeight);

        while (depthRight < 5) {
            if(tree.getRightTree()==null) {

                break;
            } else {
            randomTrueFalse = random.nextInt(10);
            if (randomTrueFalse >= 0) {
                depthRight++;
                //           tree.setRightTree(new Tree());
                Tree tree888 = initTree(tree.getRightTree());
                tree.setRightTree(tree888);
            } else {
                tree.setRightTree(null);
            }}
        }

        return tree;
    }
*/
//    private static List<Integer> listPlus1(List<Integer> list) {
//        list.add(7);
//        return list;
//    }


    public void printTree() {
//            for (Tree tree : getRightTree())
//
//                int randomTrueFalse;
//            Random random = new Random();
//            int randomWeight = random.nextInt(100);
//            tree.setWeight(randomWeight);
//
//            randomTrueFalse = random.nextInt(2);
//            if (randomTrueFalse == 1) {
//                tree.setLeftTree(initTree());
//            } else {
//                tree.setLeftTree(null);
//            }
//
//            randomTrueFalse = random.nextInt(2);
//            if (randomTrueFalse == 1) {
//                tree.setRightTree(initTree());
//            } else {
//                tree.setRightTree(null);
//            }
//            return tree;
    }


    public boolean isLeaf(Tree tree) {
        return true;
    }

    public Tree TreeInLine(Tree tree) {
//            treeInLine.add(tree.weight);
//            if (tree.leftTree != null) {
//                return getTreeInLine(tree.leftTree);
//            }
//            if (tree.rightTree != null) {
//                return getTreeInLine(tree.leftTree);
//            }
//            if (tree.leftTree == null && tree.rightTree == null) {
//                return null;
//
//            }
        return null;
    }


//            if (isLeaf(tree.leftTree)) {
//                treeInLine.add(tree.leftTree.i);
//                return treeInLine;
//            } else {
//                return getTreeInLine(tree.leftTree, treeInLine);
//            }
//            if (isLeaf(tree.rightTree)) {
//                treeInLine.add(tree.rightTree.i);
//                return treeInLine;
//            } else {
//                return getTreeInLine(tree.rightTree, treeInLine);
//          }
//        }


}


