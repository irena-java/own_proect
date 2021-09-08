package com.datascience.shop.main.trash.hw21;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Paretness {

    public static boolean isValid(String s) {
        Map<Character, Integer> precetsMap = new HashMap<>();
        precetsMap.put('(', 1);
        precetsMap.put('[', 2);
        precetsMap.put('{', 3);
        precetsMap.put(')', -1);
        precetsMap.put(']', -2);
        precetsMap.put('}', -3);

        Stack<Character> stack = new Stack<>();
        if (s == null || s.length() < 2 || precetsMap.get(s.charAt(0)) < 0) {
            return false;
        } else {
            stack.push(s.charAt(0));
            for (int i = 1; i < s.length(); i++) {
                if (stack.empty() || precetsMap.get(s.charAt(i)) + precetsMap.get(stack.pop()) != 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
