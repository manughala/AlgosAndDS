package com.leetcode.amazon.explore.others;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.

 Example 1:

 Input: 123
 Output: 321
 Example 2:

 Input: -123
 Output: -321
 Example 3:

 Input: 120
 Output: 21
 Note:
 Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
 For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 * @author Santosh Manughala (SM030146).
 */
public class ReverseInteger {
    public static void main(String args[]) {
        System.out.println("123: " + reverse(123));
        System.out.println("-123: " + reverse(-123));
        System.out.println("120: " + reverse(120));
        System.out.println("max: " + reverse(Integer.MAX_VALUE));
        System.out.println("min: " + reverse(Integer.MIN_VALUE));
        System.out.println("overflow: " + reverse(1534236469));
    }

    // time: O(logx)
    // space: O(1)
    public static int reverse(int x) {
        int reverse = 0;

        while(x != 0) {
            int temp  = x % 10;
            x = x / 10;

            if((reverse > Integer.MAX_VALUE / 10 || (reverse == Integer.MAX_VALUE / 10 && temp > 7)) ||
               (reverse < Integer.MIN_VALUE / 10 || (reverse == Integer.MIN_VALUE / 10 && temp < -8))) {
                return 0;
            }

            reverse = reverse * 10 + temp;
        }

        return reverse;
    }
}
