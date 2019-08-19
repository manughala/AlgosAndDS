package com.leetcode.amazon.explore.arraysandstrings;

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
public class ValidParentheses {
    public static void main(String args[]) {
        System.out.println(validParentheses("()"));
        System.out.println(validParentheses("()[]{}"));
        System.out.println(validParentheses("(]"));
        System.out.println(validParentheses("([)]"));
        System.out.println(validParentheses("{[]}"));
    }

//    private static boolean validParenthesesRecur() {
//
//    }

    private static boolean validParentheses(String input) {
        if(input == null) {
            return false;
        }

        if(input.length() == 0 || input.trim().length() == 0) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '(' || input.charAt(i) == '{' || input.charAt(i) == '[') {
                stack.push(input.charAt(i));
            } else if(stack.isEmpty() || !isMatchingValid(stack.pop(), input.charAt(i))) {
                return false;
            }
        }

        if(!stack.isEmpty()) {
            return false;
        }

        return true;
    }

    private static boolean isMatchingValid(char c1, char c2) {
        if(c1 == '(' && c2 == ')') {
            return true;
        } else if (c1 == '{' && c2 == '}') {
            return true;
        } else if (c1 == '[' && c2 == ']') {
            return true;
        } else {
            return false;
        }
    }
}
