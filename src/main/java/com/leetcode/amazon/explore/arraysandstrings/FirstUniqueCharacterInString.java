package com.leetcode.amazon.explore.arraysandstrings;

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
public class FirstUniqueCharacterInString {
    public static void main(String args[]) {
        System.out.println("firstUniqueCharInStringBestCase: " + firstUniqueCharInStringBestCase("leetcode"));
        System.out.println("firstUniqueCharInStringBestCase: " + firstUniqueCharInStringBestCase("loveleetcode"));

        System.out.println("firstUniqueCharInStringIntermediateCase: " + firstUniqueCharInStringIntermediateCase("leetcode"));
        System.out.println("firstUniqueCharInStringIntermediateCase: " + firstUniqueCharInStringIntermediateCase("loveleetcode"));
    }

    // Time: O(n)
    // Space: O(1)
    private static int firstUniqueCharInStringBestCase(String input) {
        char[] string = new char[26];

        for(char c: input.toCharArray()) {
            string[c - 'a']++;
        }

        for(int i = 0; i < input.length(); i++) {
            if(string[input.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    private static int firstUniqueCharInStringIntermediateCase(String input) {
        Map<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < input.length(); i++) {
            map.put(input.charAt(i), map.getOrDefault(input.charAt(i), 0) + 1);
        }

        for(int i = 0; i < input.length(); i++) {
            if(map.get(input.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }

}
