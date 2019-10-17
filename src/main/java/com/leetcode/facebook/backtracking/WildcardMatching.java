package com.leetcode.facebook.backtracking;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).
 The matching should cover the entire input string (not partial).

 Note:

 s could be empty and contains only lowercase letters a-z.
 p could be empty and contains only lowercase letters a-z, and characters like ? or *.

 Example 1:

 Input:
 s = "aa"
 p = "a"
 Output: false
 Explanation: "a" does not match the entire string "aa".
 Example 2:

 Input:
 s = "aa"
 p = "*"
 Output: true
 Explanation: '*' matches any sequence.
 Example 3:

 Input:
 s = "cb"
 p = "?a"
 Output: false
 Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 Example 4:

 Input:
 s = "adceb"
 p = "*a*b"
 Output: true
 Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 Example 5:

 Input:
 s = "acdcb"
 p = "a*c?b"
 Output: false
 * @author Santosh Manughala (SM030146).
 */
public class WildcardMatching {
    public static void main(String args[]) {
        String input = "aaaabc";
        String pattern = "*b?";

        System.out.println(isMatchBestCaseII("adefghbsomethigdc", "******a*****b****c"));
//        System.out.println(isMatchBestCaseIII(input, pattern));
//        System.out.println(isMatchBestCaseI(input, pattern));
//        System.out.println(isMatchBestCaseI(input, null));
//        System.out.println(isMatchBestCaseI(null, null));
//        System.out.println(isMatchBestCaseI("", ""));
//        System.out.println(isMatchBestCaseI("", "a*"));
//        System.out.println(isMatchBestCaseI("", "*"));
//        System.out.println(isMatchBestCaseI("", "****"));
    }

    // Time : O(m+n) Space O(m+n)
    private static boolean isMatchBestCaseI(String text, String pattern) {
        if((text == null && pattern == null)) {
            return true;
        }
        // Assuming we can have null text and pattern
        if((text == null && pattern != null) || (pattern == null && text != null)) {
            return false;
        }

        if(text.isEmpty() && pattern.isEmpty()) {
            return true;
        }

        if (text == null) {
            return pattern.length() > 0;
        }

        if(pattern == null) {
            return text.length() > 0;
        }

        int i = 0, j = 0, starIndex = -1, iIndex = -1;

        // abcdea  a*a  -> stari = 2, iin = 2
        // i = 0 j = 0 s = -1 ii = -1  -> i = 1 j = 1 s = -1 ii = -1
        // i = 1 j = 1 s = -1 ii = -1  -> i = 1 j = 2 s = 1 ii = 1
        // i = 1 j = 2 s = 1 ii = 1  -> i = 2 j = 1 s = 2 ii = 2
        // i = 2 j = 1 s = 2 ii = 2 -> i = 2 j = 2 s = 1 ii = 2
        while(i < text.length()) {
            if (j < pattern.length() && (text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < pattern.length() && pattern.charAt(j) == '*') {
                starIndex = j;
                iIndex = i;
                j++;
            } else if (starIndex != -1){
                j = starIndex++;
                i = ++iIndex;
//                iIndex = i;
            } else {
                return false;
            }
        }

        while (j < pattern.length() && pattern.charAt(j) == '*') {
            j++;
        }

        return j == pattern.length();
    }

    private static boolean isMatchBestCaseII (String text, String pattern) {
        // handle the edge cases like above
        boolean[][] isMatchTillNow = new boolean[text.length() + 1][pattern.length() + 1];

        // empty text and pattern is true
        isMatchTillNow[0][0] = true;

        for(int j = 1; j < pattern.length(); j++) {
            if(pattern.charAt(j-1) == '*') {
                isMatchTillNow[0][j] = isMatchTillNow[0][j-1];
            }
        }

        for(int i = 1; i <= text.length(); i++) {
            for (int j = 1; j <= pattern.length(); j++) {
                if(pattern.charAt(j-1) == text.charAt(i-1) || pattern.charAt(j-1) == '?') {
                    isMatchTillNow[i][j] = isMatchTillNow[i-1][j-1];
                } else if (pattern.charAt(j-1) == '*') {
                    isMatchTillNow[i][j] = isMatchTillNow[i-1][j] || isMatchTillNow[i][j-1];
                }
            }
        }

        return isMatchTillNow[text.length()][pattern.length()];
    }

    private static boolean isMatchBestCaseIII (String text, String pattern) {
        // handle the edge cases like above

        boolean isFirst = true;
        int patternIndex = 0;

        char[] patternArray = pattern.toCharArray();

        // ******a******b*****c -> *a*b*c
        for(int j = 1; j <= pattern.length(); j++) {
            if(pattern.charAt(j-1) == '*') {
                if(isFirst) {
                    patternArray[patternIndex++] = patternArray[j-1];
                    isFirst = false;
                }
            } else {
                patternArray[patternIndex++] = patternArray[j-1];
                isFirst = true;
            }
        }

        boolean[][] isMatchTillNow = new boolean[text.length() + 1][patternIndex + 1];
        // empty text and pattern is true
        isMatchTillNow[0][0] = true;

        if(patternIndex > 0 && patternArray[0] == '*') {
            isMatchTillNow[0][1] = true;
        }

        for(int i = 1; i <= text.length(); i++) {
            for (int j = 1; j <= patternIndex; j++) {
                if(patternArray[j-1] == text.charAt(i-1) || patternArray[j-1] == '?') {
                    isMatchTillNow[i][j] = isMatchTillNow[i-1][j-1];
                } else if (patternArray[j-1] == '*') {
                    isMatchTillNow[i][j] = isMatchTillNow[i-1][j] || isMatchTillNow[i][j-1];
                }
            }
        }

        return isMatchTillNow[text.length()][patternIndex];
    }
}
