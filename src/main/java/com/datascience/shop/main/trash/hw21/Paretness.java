package com.datascience.shop.main.trash.hw21;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Paretness {

    public static boolean isValid(String s) {
        Map<Character, Character> precetsMap = new HashMap<>();
        precetsMap.put(')', '(');
        precetsMap.put(']', '[');
        precetsMap.put('}', '{');
        if (s == null || s.length() < 2) {
            return false;
        } else {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char val = s.charAt(i);
                if (precetsMap.containsKey(val)) {
                    if (stack.empty() || precetsMap.get(val) != stack.peek()) {
                        return false;
                    } else {
                        stack.pop();
                    }
                } else {
                    stack.push(s.charAt(i));
                }
            }
            return stack.empty();
        }
    }
}