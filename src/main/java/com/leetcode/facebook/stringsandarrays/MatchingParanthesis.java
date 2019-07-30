package com.leetcode.facebook.stringsandarrays;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 An input string is valid if:

 Open brackets must be closed by the same type of brackets.
 Open brackets must be closed in the correct order.
 Note that an empty string is also considered valid.

 Example 1:

 Input: "()"
 Output: true
 Example 2:

 Input: "()[]{}"
 Output: true
 Example 3:

 Input: "(]"
 Output: false
 Example 4:

 Input: "([)]"
 Output: false
 Example 5:

 Input: "{[]}"
 Output: true

 * @author Santosh Manughala (SM030146).
 */
public class MatchingParanthesis {
    public static void main(String args[]) {
//        String input = "()";
//        String input = "([)]";
//        String input = "(]";
        String input = "([)]";
//        String input = "{[]}";

        if(input == null || input.length() <= 1) {
            System.out.println(false);
            return;
        }

//        boolean result = isMatchingParanthesisBruteForce(input);
        boolean result = isMatchingParanthesisBestCase(input);
        System.out.println(result);
    }

    private static boolean isMatchingParanthesisBruteForce(String string) {
       // TODO: did not understand
        // SKipped it for now: https://www.geeksforgeeks.org/check-balanced-parentheses-expression-o1-space/
        return false;
    }

    // O(n) time, O(n) space due to queue.. reduce space by using brute force -> O(n^3) accroding to above link, did not understand
    private static boolean isMatchingParanthesisBestCase(String string) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < string.length() ; i++) {
            char c = string.charAt(i);

            if(c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if(stack.isEmpty() || !isParanthesisMatching(stack.pop(), c)) {
                    return false;
                }
            }
        }

        if(stack.isEmpty()) {
            return true;
        }

        return false;
    }

    private static boolean isParanthesisMatching(char c1, char c2) {
        if(c1 == '(' && c2 == ')') {
            return true;
        } else if(c1 == '{' && c2 == '}') {
            return true;
        } else if(c1 == '[' && c2 == ']') {
            return true;
        }
        return false;
    }
}
