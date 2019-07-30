package com.leetcode.facebook.backtracking;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.
 The matching should cover the entire input string (not partial).

 Note:

 s could be empty and contains only lowercase letters a-z.
 p could be empty and contains only lowercase letters a-z, and characters like . or *.
 Example 1:

 Input:
 s = "aa"
 p = "a"
 Output: false
 Explanation: "a" does not match the entire string "aa".
 Example 2:

 Input:
 s = "aa"
 p = "a*"
 Output: true
 Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 Example 3:

 Input:
 s = "ab"
 p = ".*"
 Output: true
 Explanation: ".*" means "zero or more (*) of any character (.)".
 Example 4:

 Input:
 s = "aab"
 p = "c*a*b"
 Output: true
 Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
 Example 5:

 Input:
 s = "mississippi"
 p = "mis*is*p*."
 Output: false

 * @author Santosh Manughala (SM030146).
 */
public class RegularExpressionMatching {

    public static void main(String args[]) {
        String input = "aab";
        String pattern = "c*a*b";

        System.out.println(isRegExMatch(input, pattern));
    }

    private static boolean isRegExMatch(String input, String pattern) {
        if(pattern.charAt(0) == '*') {
            return false;
        }

        boolean[][] isMatchTillNow = new boolean[input.length() + 1][pattern.length() + 1];
        isMatchTillNow[0][0] = true;

        for(int j = 1; j <= pattern.length(); j++) {
            if(pattern.charAt(j-1) == '*') {
                isMatchTillNow[0][j] = isMatchTillNow[0][j-2];
            }
        }

        for(int i = 1; i <= input.length(); i++) {
            for(int j = 1; j <= pattern.length(); j++) {
                if (pattern.charAt(j-1) == '.' || input.charAt(i-1) == pattern.charAt(j-1)) {
                    isMatchTillNow[i][j] = isMatchTillNow[i-1][j-1];
                } else if (pattern.charAt(j-1) == '*') {
                    isMatchTillNow[i][j] = isMatchTillNow[i][j-2];

                    if(pattern.charAt(j-2) == '.' || pattern.charAt(j-2) == input.charAt(i-1)) {
                        isMatchTillNow[i][j] = isMatchTillNow[i][j] || isMatchTillNow[i-1][j];
                    }
                } else {
                    isMatchTillNow[i][j] = false;
                }
            }
        }

        return isMatchTillNow[input.length()][pattern.length()];
    }
}
