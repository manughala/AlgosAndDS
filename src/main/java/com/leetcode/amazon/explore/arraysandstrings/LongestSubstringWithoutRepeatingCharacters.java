package com.leetcode.amazon.explore.arraysandstrings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.

 Example 1:

 Input: "abcabcdefghibb"
 Output: 3
 Explanation: The answer is "abc", with the length of 3.
 Example 2:

 Input: "bbbbb"
 Output: 1
 Explanation: The answer is "b", with the length of 1.
 Example 3:

 Input: "pwwkew"
 Output: 3
 Explanation: The answer is "wke", with the length of 3.
 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

 * @author Santosh Manughala (SM030146).
 */
public class LongestSubstringWithoutRepeatingCharacters {
    // set does not contain item -> 3
    // set -> put this item
    // i++

    public static void main(String args[]) {
        System.out.println("getLenghtOfLongestSubstringBestCase: " + getLengthOFLSBestCaseII("abcabcdefghbb"));
        System.out.println("getLenghtOfLongestSubstringBestCase: " + getLengthOFLSBestCaseII("abcabcbb"));
        System.out.println("getLenghtOfLongestSubstringBestCase: " + getLengthOFLSBestCaseII("bbbb"));
        System.out.println("getLenghtOfLongestSubstringBestCase: " + getLengthOFLSBestCaseII("pwwkew"));

        System.out.println("getLenghtOfLongestSubstringBestCase: " + getLenghtOfLongestSubstringBestCase("abcabcdefghbb"));
        System.out.println("getLenghtOfLongestSubstringBestCase: " + getLenghtOfLongestSubstringBestCase("abcabcbb"));
        System.out.println("getLenghtOfLongestSubstringBestCase: " + getLenghtOfLongestSubstringBestCase("bbbb"));
        System.out.println("getLenghtOfLongestSubstringBestCase: " + getLenghtOfLongestSubstringBestCase("pwwkew"));

        System.out.println("getLengthOfLongestSubstringIntermediateCaseII: " + getLengthOfLongestSubstringIntermediateCaseII("abcabcdefghbb"));
        System.out.println("getLengthOfLongestSubstringIntermediateCaseII: " + getLengthOfLongestSubstringIntermediateCaseII("abcabcbb"));
        System.out.println("getLengthOfLongestSubstringIntermediateCaseII: " + getLengthOfLongestSubstringIntermediateCaseII("bbbb"));
        System.out.println("getLengthOfLongestSubstringIntermediateCaseII: " + getLengthOfLongestSubstringIntermediateCaseII("pwwkew"));

        System.out.println("getLengthOfLongestSubstringIntermediateCaseI: " + getLengthOfLongestSubstringIntermediateCaseI("abcabcdefghbb"));
        System.out.println("getLengthOfLongestSubstringIntermediateCaseI: " + getLengthOfLongestSubstringIntermediateCaseI("abcabcbb"));
        System.out.println("getLengthOfLongestSubstringIntermediateCaseI: " + getLengthOfLongestSubstringIntermediateCaseI("bbbb"));
        System.out.println("getLengthOfLongestSubstringIntermediateCaseI: " + getLengthOfLongestSubstringIntermediateCaseI("pwwkew"));

        System.out.println("getLengthOfLongestSubstringBruteForce: " + getLengthOfLongestSubstringBruteForce("abcabcdefghbb"));
        System.out.println("getLengthOfLongestSubstringBruteForce: " + getLengthOfLongestSubstringBruteForce("abcabcbb"));
        System.out.println("getLengthOfLongestSubstringBruteForce: " + getLengthOfLongestSubstringBruteForce("bbbb"));
        System.out.println("getLengthOfLongestSubstringBruteForce: " + getLengthOfLongestSubstringBruteForce("pwwkew"));
    }

    // int[26] for Letters 'a' - 'z' or 'A' - 'Z'
    // int[128] for ASCII
    // int[256] for Extended ASCII
    // Time: O(n)
    // Space: O(1)
    private static int getLenghtOfLongestSubstringBestCase(String input) {
        int[] chars = new int[256];
        int count = 0;

        for(int start, end = 0; end < input.length(); end++) {
            start = Math.max(chars[input.charAt(end)], end);
            count = Math.max(count, end + 1 - start);
            chars[input.charAt(end)] = end + 1;
        }

        return count;
    }

    // mayhave to optimize a little
    // Time: O(n)
    // Space: O(1)
    private static int getLengthOFLSBestCaseII(String input) {
        int[] chars = new int[256];

        int start = 0, end = 0, count = 0;
        while(start < input.length() && end < input.length()) {
            if(chars[input.charAt(end)] == 0) {
                chars[input.charAt(end)]++;
                end++;
                count = Math.max(count, end - start);
            } else {
                chars[input.charAt(start)]--;
                start++;
            }
        }

        return count;
    }

    // Time: O(n)
    // Space: O(n)
    private static int getLengthOfLongestSubstringIntermediateCaseII(String input) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;

        for(int start = 0, end = 0; end < input.length(); end++) {
            if(map.containsKey(input.charAt(end))) {
                start = Math.max(map.get(input.charAt(end)), start);
            }

            count = Math.max(count, end + 1 - start);
            map.put(input.charAt(end), end + 1); // store current index
        }

        return count;
    }

    // Time: O(n)
    // Space: O(n)
    private static int getLengthOfLongestSubstringIntermediateCaseI(String input) {
        Set<Character> set = new HashSet<>();

        int count = 0, start = 0, end = 0;

        while(start < input.length() && end < input.length()) {
            if(!set.contains(input.charAt(end))) {
                set.add(input.charAt(end));
                end++;
                count = Math.max(count, end - start);
            } else {
                set.remove(input.charAt(start++));
            }
        }

        return count;
    }

    // Time: O(n^3)
    // Space: O(n)
    private static int getLengthOfLongestSubstringBruteForce(String input) {
        int count = 0;

        for(int i = 0; i < input.length(); i++) {
            for(int j = i + 1; j < input.length(); j++) {
                if(isUnique(input, i, j)) {
                    count = Math.max(count, j - i);
                }
            }
        }

        return count;
    }

    private static boolean isUnique(String input, int start, int end) {
        Set<Character> set = new HashSet<>();
        for(int i = start; i < end; i++) {
            if(set.contains(input.charAt(i))) {
               return false;
            }
            set.add(input.charAt(i));
        }

        return true;
    }
}
