package com.datascience.shop.main.trash.trash;

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



    }


