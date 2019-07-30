package com.leetcode.facebook.backtracking;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

 Note: The input string may contain letters other than the parentheses ( and ).

 Example 1:

 Input: "()())()"
 Output: ["()()()", "(())()"]
 Example 2:

 Input: "(a)())()"
 Output: ["(a)()()", "(a())()"]
 Example 3:

 Input: ")("
 Output: [""]

 * @author Santosh Manughala (SM030146).
 */
public class RemoveInvalidParentheses {

    public static void main(String args[]) {
        String input = "()())()";
        String input1 = "(a)())()";
        String input2 = ")a(";

        List<String> validParentheses = removeInvalidParenthesesI(input);
//        List<String> validParentheses = removeInvalidParenthesesII(input1);

        for(String validParenthesis : validParentheses) {
            System.out.println(validParenthesis.isEmpty() ? "EMPTY" : validParenthesis);
        }
    }



    private static Set<String> validExpressions = new HashSet<String>();
    private static int minRemoved = Integer.MAX_VALUE;

    /*
    Time Complexity : O(2^N) : since in the worst case we will have only left parentheses in the expression and for every bracket we will
    have two options i.e. whether to remove it or consider it. Considering that the expression has N parentheses, the time complexity will be O(2^N).

    Space complexity: Space Complexity : O(N) because we are resorting to a recursive solution and for a recursive solution there is
    always stack space used as internal function states are saved onto a stack during recursion. The maximum depth of recursion decides the stack
    space used. Since we process one character at a time and the base case for the recursion is when we have processed all of the characters of
    the expression string, the size of the stack would be O(N). Note that we are not considering the space required to store the valid expressions.
    We only count the intermediate space here.
     */
    public static List<String> removeInvalidParenthesesI(String s) {
        if(s == null || s.length() == 0) {
            validExpressions.add("");
            return new ArrayList(validExpressions);
        }

        performInvalidParenthesesI(s, 0, 0, 0, 0, new StringBuffer());

        return new ArrayList(validExpressions);
    }

    // In this approach we remove each & every possible parentheses and in the end we check: if the string is valid or if we found total min removals
    // One better way to do it find what is the left and right parentheses that we can remove upfront. That is approach 2.
    private static void performInvalidParenthesesI(String s, int removedCount, int index, int leftCount, int rightCount, StringBuffer currentExpression) {
        if(s.length() == index) {
            if(leftCount == rightCount) {
                if(minRemoved >= removedCount) {
                    if(minRemoved > removedCount) {
                        validExpressions.clear();
                        minRemoved = removedCount;
                    }
                    validExpressions.add(currentExpression.toString());
                }
            }
        } else {
            char currentChar = s.charAt(index);
            int length = currentExpression.length();

            if(currentChar != '(' && currentChar != ')') {
                currentExpression.append(currentChar);
                performInvalidParenthesesI(s, removedCount, index + 1, leftCount, rightCount, currentExpression);
                currentExpression.deleteCharAt(length);
            } else {
                performInvalidParenthesesI(s, removedCount + 1, index + 1, leftCount, rightCount, currentExpression);
                currentExpression.append(currentChar);

                if(currentChar == '(') {
                    performInvalidParenthesesI(s, removedCount, index + 1, leftCount + 1, rightCount, currentExpression);
                } else if (rightCount < leftCount) {
                    performInvalidParenthesesI(s, removedCount, index + 1, leftCount, rightCount + 1, currentExpression);
                }

                currentExpression.deleteCharAt(length);
            }
        }
    }


    /*
    Time Complexity : The optimization that we have performed is simply a better form of pruning. Pruning here is something that will vary from one test case to another. In the worst case, we can have something like ((((((((( and the left_rem = len(S) and in such a case we can discard all of the characters because all are misplaced. So, in the worst case we still have 2 options per parenthesis and that gives us a complexity of O(2^N)O(2
N
 ).
Space Complexity : The space complexity remains the same i.e. O(N)O(N) as previous solution. We have to go to a maximum recursion depth of NN before hitting the base case. Note that we are not considering the space required to store the valid expressions. We only count the intermediate space here.
     */

    private static List<String> removeInvalidParenthesesII(String s) {
        if(s == null || s.length() == 0) {
            validExpressions.add("");
        }


        int leftRem = 0, rightRem = 0;

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                leftRem++;
            } else if (s.charAt(i) == ')') {
                rightRem = leftRem == 0 ? rightRem + 1 : rightRem;

                leftRem = leftRem > 0 ? leftRem - 1 : leftRem;
            }
        }

        performInvalidParenthesesII(s, new StringBuffer(), 0, leftRem, rightRem, 0, 0);

        return new ArrayList<>(validExpressions);
    }

    private static void performInvalidParenthesesII(String s, StringBuffer currentExpression, int index, int leftRem, int rightRem, int leftCount, int rightCount) {
        if(s.length() == index) {
            if(leftRem == 0 && rightRem == 0) {
                validExpressions.add(currentExpression.toString());
            }
        } else {
            char currentChar = s.charAt(index);
            int length = currentExpression.length();

            if((currentChar == '(' && leftRem > 0) || (currentChar == ')' && rightRem > 0)) {
                performInvalidParenthesesII(s, currentExpression, index + 1, currentChar == '(' ? leftRem - 1 : leftRem, currentChar ==')' ? rightRem - 1 : rightRem, leftCount, rightCount);
            }

            currentExpression.append(currentChar);

            if(currentChar != '(' && currentChar != ')') {
                performInvalidParenthesesII(s, currentExpression, index + 1, leftRem, rightRem, leftCount, rightCount);
            } else if (currentChar == '(') {
                performInvalidParenthesesII(s, currentExpression, index + 1, leftRem, rightRem, leftCount + 1, rightCount);
            } else if (leftCount > rightCount) {
                performInvalidParenthesesII(s, currentExpression, index + 1, leftRem, rightRem, leftCount, rightCount + 1);
            }

            currentExpression.deleteCharAt(length);
        }

    }

}
