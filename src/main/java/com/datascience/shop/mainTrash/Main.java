package com.datascience.shop.mainTrash;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Tree tree = Tree.autogenTree(80, 10);
        List<Tree> trees = new ArrayList<>();
        trees.add(tree);
        Tree.fillListWithWeights(trees);
        System.out.println("Дерево в виде списка - " + Tree.listOfWeighth.toString());
    }
}