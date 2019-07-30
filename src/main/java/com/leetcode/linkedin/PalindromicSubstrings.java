package com.leetcode.linkedin;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.

 The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

 Example 1:

 Input: "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".


 Example 2:

 Input: "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

 * @author Santosh Manughala (SM030146).
 */
public class PalindromicSubstrings {

    public static void main(String args[]) {
        System.out.println(countSubstrings("abc"));
        System.out.println(countSubstrings("aaaa"));
        System.out.println(countSubstringsBestCase("aaaa"));
    }

    // IDEAS:
    // 1. for each substring, check if it is a palindrome
    // to calculate substrings, it will be (1 * 2* .. n) -> n^2
    // to calculate palidrom , it will be logn -> (n^2logn)

    // 2. expanding center
    // for every string of length N,  the middle of the palindrome could be in one
    // of 2N - 1 positions: either at letter or between two letters.
    // get the center and expand across center to find all possible substrings.
    // O(n^2)

    // 3. dp appraoch : https://www.youtube.com/watch?v=omIP6yrVaJg O(n ^2)

    // 4.  Manacher's Algorithm
    // the best case in all these O(n) -> did not understand read it

    private static int countSubstrings(String s) {
        int n = s.length(), result = 0;
        for(int center = 0; center <= 2*n-1; center++) {
            int left = center / 2, right = left + center % 2;

            while(left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                result++;
                left--;
                right++;
            }
        }

        return result;
    }


    // Copied from leet code, did not understand yet
    // check description: https://leetcode.com/articles/palindromic-substrings/
    private static int countSubstringsBestCase(String S) {
        char[] A = new char[2 * S.length() + 3];
        A[0] = '@';
        A[1] = '#';
        A[A.length - 1] = '$';
        int t = 2;
        for (char c: S.toCharArray()) {
            A[t++] = c;
            A[t++] = '#';
        }

        int[] Z = new int[A.length];
        int center = 0, right = 0;
        for (int i = 1; i < Z.length - 1; ++i) {
            if (i < right)
                Z[i] = Math.min(right - i, Z[2 * center - i]);
            while (A[i + Z[i] + 1] == A[i - Z[i] - 1])
                Z[i]++;
            if (i + Z[i] > right) {
                center = i;
                right = i + Z[i];
            }
        }
        int ans = 0;
        for (int v: Z) ans += (v + 1) / 2;
        return ans;
    }
}
