package com.leetcode.microsoft.sortingandsearching;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

 Examples:

 s = "leetcode"
 return 0.

 s = "loveleetcode",
 return 2.
 Note: You may assume the string contain only lowercase letters.

 * @author Santosh Manughala (SM030146).
 */
public class FirstUniqueCharInAString {
    public static void main(String args[]) {
        System.out.println(firstUniqChar("leetcode"));
        System.out.println(firstUniqChar("loveleetcode"));

        System.out.println(firstUniqCharBestCase("leetcode"));
        System.out.println(firstUniqCharBestCase("loveleetcode"));
    }


    // SINCE THE CHAR CONTAINS ONLY LOWER CASE LETTERS: we can leverage alpha numeric value
    // Time O(n)
    // Space O(1)
    private static int firstUniqCharBestCase(String s) {
        int[] charsCount = new int[26];

        for(char c: s.toCharArray()) {
            charsCount[c - 'a']++;
        }

        for(char c: s.toCharArray()) {
            if(charsCount[c - 'a'] == 1) {
                return s.indexOf(c);
            }
        }

        return -1;
    }

    // Time: O(n)
    // Space O(n)
    private static int firstUniqChar(String s) {
        Map<Character, Integer> charToCountMap = new HashMap<>();

        for(char c: s.toCharArray()) {
            charToCountMap.put(c, charToCountMap.getOrDefault(c, 0) + 1);
        }

        for(char c : s.toCharArray()) {
            if(charToCountMap.get(c) == 1) {
                return s.indexOf(c);
            }
        }

        return -1;
    }
}
