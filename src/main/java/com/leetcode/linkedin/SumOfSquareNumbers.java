package com.leetcode.linkedin;

/**
 * Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

 Example 1:

 Input: 5
 Output: True
 Explanation: 1 * 1 + 2 * 2 = 5


 Example 2:

 Input: 3
 Output: False


 * @author Santosh Manughala (SM030146).
 */
public class SumOfSquareNumbers {
    public static void main(String args[]) {
        System.out.println(isSumOfSquaresBruteForce(5));
        System.out.println(isSumOfSquaresBruteForce(3));

        System.out.println(isSumOfSquaresBruteForceBetter(5));
        System.out.println(isSumOfSquaresBruteForceBetter(3));

        System.out.println(isSumOfSquareBetter(5));
        System.out.println(isSumOfSquareBetter(3));


        System.out.println(isSumOfSquaresBest(5));
        System.out.println(isSumOfSquaresBest(3));

    }

    private static boolean isSumOfSquaresBest(int c) {
        for(long a = 0; a * a <= c; a++) {
            int b = c - (int) (a * a);
            if(findB(0, b, b)) {
                return true;
            }
        }
        return false;
    }

    private static boolean findB(long left, long right, int b) {
        if(left > right) {
            return false;
        }

        long mid = right + (left - right) / 2;
        if(mid * mid == b) {
            return true;
        } else if (mid * mid > b) {
            return findB(left, mid - 1, b);
        } else {
            return findB(mid + 1, right, b);
        }
    }

    // accepted when a is long only
    // Time: O(sqrt of C * logn) -> only one loop till sqrt of c, and log n to calculate the sqrt of c- a*a
    // space O(1)
    private static boolean isSumOfSquareBetter(int c) {
        // a has to be long otherwise time limit exceeded, i think the overflow
        for(long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if((int) b == b) {
                return true;
            }
        }

        return false;
    }

    // time limit exceeded
    // Time O(c) sum is calculated O(c) times
    // space O(1)
    private static boolean isSumOfSquaresBruteForceBetter(int c) {
        for(long a = 0; a * a <= c; a++) {
            int b = c - (int) (a * a);

            for(int i = 1, sum = 0; sum < b; i += 2) {
                sum += i;
                if(sum == b) {
                    return true;
                }
            }
        }

        return false;
    }

    // time limit exceeded
    // Time: O(c) Two loops upto sqrt{c}. Here, c refers to the given integer(sum of squares).
    // Space:O(1)
    private static boolean isSumOfSquaresBruteForce(int c) {
        for(long a = 0; a * a <= c; a++) {
            for(long b = 0; b * b <= c; b++) {
                if(a * a + b * b == c) {
                    return true;
                }
            }
        }

        return false;
    }
}
