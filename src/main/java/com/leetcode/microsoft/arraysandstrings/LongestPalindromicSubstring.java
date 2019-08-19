package com.leetcode.microsoft.arraysandstrings;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example 1:

 Input: "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.
 Example 2:

 Input: "cbbd"
 Output: "bb"

 * @author Santosh Manughala (SM030146).
 */
public class LongestPalindromicSubstring {
    public static void main(String args[]) {
        // IDEAS:
        // 1. brute force -> >O(n^3) or O(n^2 logn) space O(1)
        // 2. Expand around center -> O(n^2) space O(1)
        // 3. DP -> O(n^2) space O(n^2)  -> did not practice https://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
        // 4. Manacher's Algorithm -> O(n) -> did not practice

        System.out.println("longestPalindromeBruteForce: ");
        System.out.println(longestPalindromeBruteForce("babad"));
        System.out.println(longestPalindromeBruteForce("babab"));
        System.out.println(longestPalindromeBruteForce("cbbd"));
        System.out.println(longestPalindromeBruteForce("a"));
        System.out.println(longestPalindromeBruteForce("ac"));
        System.out.println(longestPalindromeBruteForce("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"));


        System.out.println("longestPalindromeExpandAroundPivot: ");
        System.out.println(longestPalindromeExpandAroundPivot("babad"));
        System.out.println(longestPalindromeExpandAroundPivot("babab"));
        System.out.println(longestPalindromeExpandAroundPivot("cbbd"));
        System.out.println(longestPalindromeExpandAroundPivot("a"));
        System.out.println(longestPalindromeExpandAroundPivot("ac"));
        System.out.println(longestPalindromeExpandAroundPivot("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"));
    }

    // IDEA: for every i, find all the palindromes (even and odd), meaning to find palindromes for all odd length strings, pick i as the
    // pivot and start expanding as long as string is palindrome. Same with even length, except that we have 2 pivots i, i+1
    //
    // idea from https://www.geeksforgeeks.org/longest-palindromic-substring-set-2/
    // implementation from https://www.baeldung.com/java-palindrome-substrings
    private static String currentLongestPalindrome = "";

    // Time: O(n^2)
    // Space: O(1)
    private static String longestPalindromeExpandAroundPivot(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }

        currentLongestPalindrome = s.substring(0, 1);
        for(int i = 0; i < s.length(); i++) {
            getPalindrome(s, i, i);
            getPalindrome(s, i, i + 1);
        }

        return currentLongestPalindrome;
    }

    private static String getPalindrome(String s, int low, int high) {
        while(low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
            if(currentLongestPalindrome.length() < high - low + 1) {
                currentLongestPalindrome = s.substring(low, high + 1);
            }
            low--;
            high++;
        }

        return currentLongestPalindrome;
    }



    // NOTE: this is brute force algorithm, here the palindrome check could have taken up till O(n) -> so n^3 solution is possible too.
    // Time: O(n^2 * logn)
    // Space: O(1)
    private static String longestPalindromeBruteForce(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }

        String currentLongestPalindrome = s.substring(0, 1);
        for(int i = 0; i  < s.length(); i++) {
            for(int j = i + 1; j < s.length(); j++) {
                if(isPalindrome(s.substring(i, j + 1)) && currentLongestPalindrome.length() < j - i + 1) {
                    currentLongestPalindrome = s.substring(i, j + 1);
                }
            }
        }

        return currentLongestPalindrome;
    }

    private static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;

        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }
}
