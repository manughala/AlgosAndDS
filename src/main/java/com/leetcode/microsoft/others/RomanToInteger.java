package com.leetcode.microsoft.others;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

 Symbol       Value
 I             1
 V             5
 X             10
 L             50
 C             100
 D             500
 M             1000
 For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

 Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

 I can be placed before V (5) and X (10) to make 4 and 9.
 X can be placed before L (50) and C (100) to make 40 and 90.
 C can be placed before D (500) and M (1000) to make 400 and 900.
 Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

 Example 1:

 Input: "III"
 Output: 3
 Example 2:

 Input: "IV"
 Output: 4
 Example 3:

 Input: "IX"
 Output: 9
 Example 4:

 Input: "LVIII"
 Output: 58
 Explanation: L = 50, V= 5, III = 3.
 Example 5:

 Input: "MCMXCIV"
 Output: 1994
 Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

 * @author Santosh Manughala (SM030146).
 */
public class RomanToInteger {

    public static void main(String args[]) {
        System.out.println("getIntegerIII: Expected: 3, Actual: " + getIntegerIII("III"));
        System.out.println("getIntegerIII: Expected: 4, Actual: " + getIntegerIII("IV"));
        System.out.println("getIntegerIII: Expected: 9, Actual: " + getIntegerIII("IX"));
        System.out.println("getIntegerIII: Expected: 58, Actual: " + getIntegerIII("LVIII"));
        System.out.println("getIntegerIII: Expected: 1994, Actual: " + getIntegerIII("MCMXCIV"));

        System.out.println("getIntegerII: Expected: 3, Actual: " + getIntegerII("III"));
        System.out.println("getIntegerII: Expected: 4, Actual: " + getIntegerII("IV"));
        System.out.println("getIntegerII: Expected: 9, Actual: " + getIntegerII("IX"));
        System.out.println("getIntegerII: Expected: 58, Actual: " + getIntegerII("LVIII"));
        System.out.println("getIntegerII: Expected: 1994, Actual: " + getIntegerII("MCMXCIV"));

        System.out.println("getIntegerI: Expected: 3, Actual: " + getIntegerI("III"));
        System.out.println("getIntegerI: Expected: 4, Actual: " + getIntegerI("IV"));
        System.out.println("getIntegerI: Expected: 9, Actual: " + getIntegerI("XI"));
        System.out.println("getIntegerI: Expected: 58, Actual: " + getIntegerI("LVIII"));
        System.out.println("getIntegerI: Expected: 1994, Actual: " + getIntegerI("MCMXCIV"));
    }

    // Time: O(n)
    // Space: O(1)
    private static int getIntegerI(String roman) {
        if(roman == null || roman.length() == 0) {
            return 0;
        }

        int result = 0, currentCharVal = 0, nextCharVal = 0;

        for(int i = 0; i < roman.length() - 1; i++) {
            currentCharVal = romanToInt(roman.charAt(i));
            nextCharVal = romanToInt(roman.charAt(i + 1));

            if(nextCharVal > currentCharVal) {
                currentCharVal *= -1;
            }

            result += currentCharVal;
        }

        // add the last char val
        result += romanToInt(roman.charAt(roman.length() - 1));

        return result;
    }

    // Time: O(n)
    // Space: O(1)
    private static int getIntegerII(String roman) {
        if(roman == null || roman.length() == 0) {
            return 0;
        }

        int i = 0, result = 0;

        while(i < roman.length()) {
            char currentChar = roman.charAt(i);
            char nextChar = (i + 1) < roman.length() ? roman.charAt(i + 1) : currentChar;

            if((currentChar == 'I' && (nextChar == 'V' || nextChar == 'X')) ||
               (currentChar == 'X' && (nextChar == 'L' || nextChar == 'C')) ||
               (currentChar == 'C' && (nextChar == 'D' || nextChar == 'M'))) {
                result += romanToInt(nextChar) - romanToInt(currentChar);
                i++; // skip next char
            } else  {
                result += romanToInt(currentChar);
            }

            i++;
        }

        return result;
    }

    // Time: O(n)
    // Space: O(1)
    private static int getIntegerIII(String roman) {
        if(roman == null || roman.length() == 0) {
            return 0;
        }

        int result = 0;

        for(int i = 0; i < roman.length(); i++) {
            char currentChar = roman.charAt(i), nextChar = ' ';

            if(i + 1 < roman.length()) {
                nextChar = roman.charAt(i + 1);
            }

            switch (currentChar) {
                case 'I':
                    if(nextChar == 'V') {
                        result += 4;
                        i++; // skip next char
                    } else if(nextChar == 'X') {
                        result += 9;
                        i++; // skip next char
                    } else {
                        result += 1;
                    }
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'X' :
                    if(nextChar == 'L') {
                        result += 40;
                        i++; // skip next char
                    } else if(nextChar == 'C') {
                        result += 90;
                        i++; // skip next char
                    } else {
                        result += 10;
                    }
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'C':
                    if(nextChar == 'D') {
                        result += 400;
                        i++; // skip next char
                    } else if(nextChar == 'M') {
                        result += 900;
                        i++; // skip next char
                    } else {
                        result += 100;
                    }
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'M':
                    result += 1000;
                    break;
                default:
                    throw new IllegalArgumentException("Illegal argument passed: " + currentChar);
            }
        }

        return result;
    }

    private static int romanToInt(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw new IllegalArgumentException("Invalid roman char: " + c);
        }
    }
}
