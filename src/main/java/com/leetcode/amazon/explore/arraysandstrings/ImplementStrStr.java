package com.leetcode.amazon.explore.arraysandstrings;

/**
 * Implement strStr().

 Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

 Example 1:

 Input: haystack = "hello", needle = "ll"
 Output: 2
 Example 2:

 Input: haystack = "aaaaa", needle = "bba"
 Output: -1
 Clarification:

 What should we return when needle is an empty string? This is a great question to ask during an interview.

 For the purpose of this problem, we will return 0 when needle is an empty string.
 This is consistent to C's strstr() and Java's indexOf().

 * @author Santosh Manughala (SM030146).
 */
public class ImplementStrStr {
    public static void main(String args[]) {
        System.out.println("strStrBruteForce, expected: 4, actual: " + strStrBruteForce("mississippi", "issippi"));
        System.out.println("strStrOptimalCase, expected: 2, actual: " + strStrOptimalCase("hello", "ll"));
        System.out.println("strStrOptimalCase, expected: -1, actual: " + strStrOptimalCase("aaaaa", "bba"));
        System.out.println("strStrOptimalCase, expected: 4, actual: " + strStrOptimalCase("bacbabababcabab", "abababca"));

        System.out.println("strStrKMPBestCase, expected: 4, actual: " + strStrKMPBestCase("mississippi", "issippi"));
        System.out.println("strStrKMPBestCase, expected: 2, actual: " + strStrKMPBestCase("hello", "ll"));
        System.out.println("strStrKMPBestCase, expected: -1, actual: " + strStrKMPBestCase("aaaaa", "bba"));
        System.out.println("strStrKMPBestCase, expected: 4, actual: " + strStrKMPBestCase("bacbabababcabab", "abababca"));
    }

    // Time: O(m * n)
    // Space: O(1)
    private static int strStrOptimalCase(String haystack, String needle) {
        if(haystack == null || needle == null || needle.length() == 0) {
            return 0;
        }

        int haystackLen = haystack.length(), needleLen = needle.length();

        for(int i = 0; i <= haystackLen - needleLen; i++) {

            int j;
            for(j = 0; j < needleLen; j++) {
                if(needle.charAt(j) != haystack.charAt(i + j)) {
                    break;
                }
            }

            if(j == needleLen) {
                return i;
            }
        }

        return -1;
    }

    // Time: O(m * n)
    // Space: O(1)
    // abcdegh  deg
    private static int strStrBruteForce(String haystack, String needle) {
        if(haystack == null || needle == null || needle.length() == 0) {
            return 0;
        }

        int haystackLen = haystack.length(), needleLen = needle.length();
        if(needleLen > haystackLen) {
            return -1;
        }

        int i = 0, prevI = 0;
        while(i < haystackLen) {
            prevI = i;

            for(int j = 0; j < needleLen && i < haystackLen; j++) {
                if(needle.charAt(j) != haystack.charAt(i)) {
                    i = prevI++;
                    break;
                } else {
                    i++;
                }

                if(j == needleLen - 1) {
                    return prevI;
                }
            }

            i++;
        }

        return -1;
    }

    // TIme: O(m + n)
    // Space: O(n) -> needle length
    private static int strStrKMPBestCase(String haystack, String needle){
        if(haystack == null || needle == null) {
            return 0;
        }

        int haystackLen = haystack.length(), needleLen = needle.length();
        if(needleLen > haystackLen) {
            return -1;
        }

        if(needleLen == 0) {
            return 0;
        }

        int lps[] = computeTemporaryArray(needle);
        int i = 0, j = 0;

        while(i < haystackLen && j < needleLen) {
            if(haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                if(j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        if(j == needleLen) {
            return i - j;
        }

        return -1;
    }

    /**
     * Compute temporary array to maintain size of suffix which is same as prefix
     * Time/space complexity is O(size of pattern)
     */
    private static int[] computeTemporaryArray(String needle) {
        int i = 1, index = 0;
        int[] lps = new int[needle.length()];
        lps[0] = 0;

        while(i < needle.length()) {
            if(needle.charAt(i) == needle.charAt(index)) {
                lps[i] = lps[index] + 1;
                i++;
                index++;
            } else {
                if(index != 0) {
                    index = lps[index - 1];
                } else {
                    i++;
                }
            }
        }

        return lps;
    }
}
