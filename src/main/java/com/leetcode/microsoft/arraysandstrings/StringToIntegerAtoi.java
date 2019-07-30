package com.leetcode.microsoft.arraysandstrings;

/**
 * Implement atoi which converts a string to an integer.

 The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

 The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

 If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

 If no valid conversion could be performed, a zero value is returned.

 Note:

 Only the space character ' ' is considered as whitespace character.
 Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 Example 1:

 Input: "42"
 Output: 42
 Example 2:

 Input: "   -42"
 Output: -42
 Explanation: The first non-whitespace character is '-', which is the minus sign.
 Then take as many numerical digits as possible, which gets 42.
 Example 3:

 Input: "4193 with words"
 Output: 4193
 Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 Example 4:

 Input: "words and 987"
 Output: 0
 Explanation: The first non-whitespace character is 'w', which is not a numerical
 digit or a +/- sign. Therefore no valid conversion could be performed.
 Example 5:

 Input: "-91283472332"
 Output: -2147483648
 Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 Thefore INT_MIN (−231) is returned.

 * @author Santosh Manughala (SM030146).
 */
public class StringToIntegerAtoi {

    public static void main(String[] args) {
        System.out.println(myAtoiBruteForce("42"));
        System.out.println(myAtoiBruteForce("-42"));
        System.out.println(myAtoiBruteForce("4193 with words"));
        System.out.println(myAtoiBruteForce("words and 987"));
        System.out.println(myAtoiBruteForce("-91283472332"));
        System.out.println(myAtoiBruteForce("-"));
        System.out.println(myAtoiBruteForce("  "));
        System.out.println(myAtoiBruteForce("20000000000000000000"));


        System.out.println(myAtoiBetter("42"));
        System.out.println(myAtoiBetter("-42"));
        System.out.println(myAtoiBetter("4193 with words"));
        System.out.println(myAtoiBetter("words and 987"));
        System.out.println(myAtoiBetter("-91283472332"));
        System.out.println(myAtoiBetter("-"));
        System.out.println(myAtoiBetter("  "));
        System.out.println(myAtoiBetter("20000000000000000000"));
        System.out.println(myAtoiBetter("-2147483647"));


        System.out.println(myAtoiMuchBetter("42"));
        System.out.println(myAtoiMuchBetter("-42"));
        System.out.println(myAtoiMuchBetter("4193 with words"));
        System.out.println(myAtoiMuchBetter("words and 987"));
        System.out.println(myAtoiMuchBetter("-91283472332"));
        System.out.println(myAtoiMuchBetter("-"));
        System.out.println(myAtoiMuchBetter("  "));
        System.out.println(myAtoiMuchBetter("20000000000000000000"));
        System.out.println(myAtoiMuchBetter("-2147483647"));
    }


    // Much faster because of the way we convert the number from string to long
    // Time: O(n)
    // Space: O(1)
    private static int myAtoiMuchBetter(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }

        str = str.trim();

        int i = 0;
        if(i == str.length()) {
            return 0;
        }

        int sign = 1;
        if(str.charAt(i) == '+' || str.charAt(i) == '-') {
            if(str.charAt(i) == '-') {
                sign = -1;
            }

            i++;
        }

        if(i == str.length() || !Character.isDigit(str.charAt(i))) {
            return 0;
        }

        long result = 0;
        while(i < str.length() && Character.isDigit(str.charAt(i))) {
            result = result * 10 + Character.getNumericValue(str.charAt(i++));

            if(result > Integer.MAX_VALUE) {
                if(sign > 0) {
                    return Integer.MAX_VALUE;
                }
                return Integer.MIN_VALUE;
            }
        }

        return (int) result * sign;
    }

    // Time: O(n)
    // Space: O(1)
    private static int myAtoiBetter(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }

        str = str.trim();

        int i = 0;
        if(i == str.length()) {
            return 0;
        }

        String number = "";
        if(str.charAt(i) == '+' || str.charAt(i) == '-') {
            number += str.charAt(i++);
        }

        if(i == str.length() || !Character.isDigit(str.charAt(i))) {
            return 0;
        }

        while(i < str.length() && Character.isDigit(str.charAt(i))) {
            number += str.charAt(i++);
        }

        double result = Double.parseDouble(number);

        if(result >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        if(result <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int) result;
    }

    // Time: O(n)
    // Space: O(1)
    private static int myAtoiBruteForce(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }

        // remove any white spaces
        int i = 0;
        while(i < str.length() && str.charAt(i) == ' ') {
            i++;
        }

        if(i == str.length()) {
            return 0;
        }

        boolean negative = false;
        if(str.charAt(i) == '+' || str.charAt(i) == '-') {
            if(str.charAt(i) == '-') {
                negative = true;
            }

            i++;
        }

        if(i == str.length() || !Character.isDigit(str.charAt(i))) {
            return 0;
        }

        String number = "";
        while(i < str.length()) {
            number += str.charAt(i++);

            if(i != str.length() && !Character.isDigit(str.charAt(i))) {
                break;
            }
        }

        double result = Double.parseDouble(number);
        if(negative) {
            result = -result;
        }

        if(result >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        if(result <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int) result;
    }
}
