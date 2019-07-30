package com.leetcode.microsoft.arraysandstrings;

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
        System.out.println("isValid: ");
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(}"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));
        System.out.println(isValid(" "));
        System.out.println(isValid(""));

        System.out.println("validParen: ");
        System.out.println(validParen("()"));
        System.out.println(validParen("()[]{}"));
        System.out.println(validParen("(}"));
        System.out.println(validParen("([)]"));
        System.out.println(validParen("{[]}"));
        System.out.println(validParen(" "));
        System.out.println(validParen(""));

        // TODO: would be nice to practice without stack - constant space
    }


    // this use recursion and is performing faster on leet code
    // understood the approach but did not practice
    public static boolean validParen(String s) {
        if(s == null || s.length() == 0 || s.trim().length() == 0) {
            return true;
        }

        return s.length() == isValidRecur(s, 0);
    }

    private static int isValidRecur(String s, int index) {
        if(index == s.length()) {
            return index;
        } else {
            char c = s.charAt(index);
            if ("]})".indexOf(c) >= 0) {
                return index;
            } else {
                int i = isValidRecur(s, index + 1);

                if(i == -1 || i == s.length()) {
                    return -1;
                } else {
                    char c2 = s.charAt(i);
                    if((c == '(' && c2 == ')') || (c == '{' && c2 == '}') || (c == '[' && c2 == ']')) {
                        return isValidRecur(s, i + 1);
                    } else {
                        return -1;
                    }
                }
            }
        }
    }

    // Time O(n)
    // Space O(n)
    private static boolean isValid(String s) {
        if(s == null || s.length() == 0 || s.trim().length() == 0) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray()) {
            if(c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if(stack.isEmpty() || !isMatchingParentheses(stack.pop(), c)) {
                return false;
            }
        }

        if(!stack.isEmpty()) {
            return false;
        }

        return true;
    }

    private static boolean isMatchingParentheses(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}') || (c1 == '[' && c2 == ']');
    }
}
