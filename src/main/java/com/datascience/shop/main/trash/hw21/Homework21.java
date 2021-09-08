package com.datascience.shop.main.trash.hw21;

public class Homework21 {
    public static void main(String[] args) {
        System.out.println(Paretness.isValid("(){]"));
        System.out.println(Paretness.isValid("()"));
        System.out.println(Paretness.isValid("(()]"));

        System.out.println(UniqSymbol.getFirstUniqSymbolIndex(("dg r a g add")));
        System.out.println(UniqSymbol.getFirstUniqSymbolIndex(("dkurbg;")));

    }
}
