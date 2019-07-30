package com.leetcode.linkedin;

import java.util.Queue;
import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.

 Valid operators are +, -, *, /. Each operand may be an integer or another expression.

 Note:

 Division between two integers should truncate toward zero.
 The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
 Example 1:

 Input: ["2", "1", "+", "3", "*"]
 Output: 9
 Explanation: ((2 + 1) * 3) = 9
 Example 2:

 Input: ["4", "13", "5", "/", "+"]
 Output: 6
 Explanation: (4 + (13 / 5)) = 6
 Example 3:

 Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 Output: 22
 Explanation:
 ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 = ((10 * (6 / (12 * -11))) + 17) + 5
 = ((10 * (6 / -132)) + 17) + 5
 = ((10 * 0) + 17) + 5
 = (0 + 17) + 5
 = 17 + 5
 = 22

 * @author Santosh Manughala (SM030146).
 */
public class EvaluateReversePolishNotation {

    public static void main(String args[]) {
        String[] tokens1 = new String[]{"2", "1", "+", "3", "*"};
        String[] tokens2 = new String[]{"4", "13", "5", "/", "+"};
        String[] tokens3 = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};

        System.out.println("iter result 1 = " + evaluateReversePolishNotationIter(tokens1));
        System.out.println("iter result 2 = " + evaluateReversePolishNotationIter(tokens2));
        System.out.println("iter result 3 = " + evaluateReversePolishNotationIter(tokens3));
    }

    // Time O(n)
    // Space O(n)
    private static int evaluateReversePolishNotationIter(String[] tokens) {
        if(tokens == null || tokens.length == 0) {
            return 0;
        }

        if(tokens.length == 1) {
            return Integer.parseInt(tokens[0]);
        }

        Stack<Integer> stack = new Stack<>();
        for(String token : tokens) {
            if(!"+".equals(token) && !"-".equals(token) && !"*".equals(token) && !"/".equals(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int num1 = stack.pop();
                int num2 = stack.pop();

                switch (token) {
                    case "+" :
                        stack.push(num2 + num1);
                        break;
                    case "-":
                        stack.push(num2 - num1);
                        break;
                    case "*":
                        stack.push(num2 * num1);
                        break;
                    case "/":
                        stack.push(num2/num1);
                        break;
                    default:
                        throw new IllegalArgumentException("invalid token: " + token);
                }
            }

        }

        return stack.pop();
    }
}
