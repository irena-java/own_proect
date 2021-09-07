package com.datascience.shop.main.trash;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.round;

public class Tree {
    public int weight;
    public Tree leftTree;
    public Tree rightTree;

    public static List<Integer> listOfWeighth = new ArrayList<>();

    /**
     * chance - сила  ветвления дерева, вероятность, чем ближе к 100%, тем большье случ.дервое ветвится
     * chanceСhange - корректировка, кот. введена для снижения ветвления дерева к его разростанию,
     * чт.исключить случай слишком разросшихся деревьев
     * алгоритм мне за 20 минут написал на Питоне ребенок, беру его не меняя, т.к. задание не об этом,
     * короче поставила ему задачу создать случайное среднестатистическое дерево,
     * не сильно клубящееся ,а то не смогу его дебажить, но и чтобы пустых деревьев не кидало через раз
     **/
    public static Tree autogenTree(float chance, float chanceСhange) {
        int flagCreateLeftTree;
        int flagCreateRightTree;
        Tree tree = new Tree();
        Random random = new Random();
        int randomWeight = random.nextInt(100);
        tree.weight = randomWeight;

        if (chance >= 50) {
            flagCreateLeftTree = random.nextInt(round(100 / (100 - chance)));
            flagCreateRightTree = random.nextInt(round(100 / (100 - chance)));
            if (flagCreateLeftTree != 0) {
                tree.leftTree = autogenTree(chance - chanceСhange, chanceСhange);
            } else {
                tree.leftTree = null;
            }
            if (flagCreateRightTree != 0) {
                tree.rightTree = autogenTree(chance - chanceСhange, chanceСhange);
            } else {
                tree.rightTree = null;
            }
        } else {
            flagCreateLeftTree = random.nextInt(round(100 / chance));
            flagCreateRightTree = random.nextInt(round(100 / chance));
            if (flagCreateLeftTree == 0) {
                tree.leftTree = autogenTree(chance - chanceСhange, chanceСhange);
            } else {
                tree.leftTree = null;
            }
            if (flagCreateRightTree == 0) {
                tree.rightTree = autogenTree(chance - chanceСhange, chanceСhange);
            } else {
                tree.rightTree = null;
            }
        }
        return tree;
    }

    public static List<Tree> addTreesFromLevelLower(List<Tree> trees) {
        List<Tree> newTrees = new ArrayList<>();
        for (Tree tree : trees) {
            if (tree.leftTree != null) {
                newTrees.add(tree.leftTree);
            }
            if (tree.rightTree != null) {
                newTrees.add(tree.rightTree);
            }
        }
        return newTrees;
    }

    public static void fillListWithWeights(List<Tree> trees) {
        while (trees.size() > 0) {
            for (Tree tree : trees) {
                listOfWeighth.add(tree.weight);
            }
            trees = addTreesFromLevelLower(trees);
        }
    }
}