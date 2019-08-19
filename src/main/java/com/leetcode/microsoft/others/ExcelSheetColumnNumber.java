package com.leetcode.microsoft.others;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.

 For example:

 A -> 1
 B -> 2
 C -> 3
 ...
 Z -> 26
 AA -> 27
 AB -> 28
 ...
 Example 1:

 Input: "A"
 Output: 1
 Example 2:

 Input: "AB"
 Output: 28
 Example 3:

 Input: "ZY"
 Output: 701

 * @author Santosh Manughala (SM030146).
 */
public class ExcelSheetColumnNumber {
    public static void main(String args[]) {
        System.out.println("titleToNumberI, Expected: 1, actual: " + titleToNumberI("A"));
        System.out.println("titleToNumberI, Expected: 27, actual: " + titleToNumberI("AA"));
        System.out.println("titleToNumberI, Expected: 28, actual: " + titleToNumberI("AB"));
        System.out.println("titleToNumberI, Expected: 701, actual: " + titleToNumberI("ZY"));

        System.out.println("titleToNumberII, Expected: 1, actual: " + titleToNumberII("A"));
        System.out.println("titleToNumberII, Expected: 27, actual: " + titleToNumberII("AA"));
        System.out.println("titleToNumberII, Expected: 28, actual: " + titleToNumberII("AB"));
        System.out.println("titleToNumberII, Expected: 701, actual: " + titleToNumberII("ZY"));
    }

    // Time : O(n)
    // Space: O(1)
    public static int titleToNumberII(String s) {
        int columnNumber = 0;

        for(int i = 0; i < s.length(); i++) {
            //BBB = (2 * 26^2) + (2 * 26^1) + (2 * 26^0)
            // ZY = (26^1 * 26) + (26^0 * 25)
            columnNumber += Math.pow(26, s.length() - 1 - i) * (s.charAt(i) - 'A' + 1);
        }

        return columnNumber;
    }

    // Time : O(n)
    // Space: O(1)
    public static int titleToNumberI(String s) {
        char[] chars = s.toCharArray();

        int columnNumber = 0;

        for(char c : chars) {
            columnNumber *= 26;
            columnNumber += (c - 'A') + 1;
        }

        return columnNumber;
    }
}
