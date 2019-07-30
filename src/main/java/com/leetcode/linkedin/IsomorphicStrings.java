package com.leetcode.linkedin;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.

 Two strings are isomorphic if the characters in s can be replaced to get t.

 All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

 Example 1:

 Input: s = "egg", t = "add"
 Output: true
 Example 2:

 Input: s = "foo", t = "bar"
 Output: false
 Example 3:

 Input: s = "paper", t = "title"
 Output: true
 Note:
 You may assume both s and t have the same length.

 * @author Santosh Manughala (SM030146).
 */
public class IsomorphicStrings {
    public static void main(String args[]) {
        System.out.println("First way: ");
        System.out.println(isIsomorphicStringsI("egg", "add"));
        System.out.println(isIsomorphicStringsI("foo", "bar"));
        System.out.println(isIsomorphicStringsI("paper", "title"));
        System.out.println(isIsomorphicStringsI("abc", "saa"));
        System.out.println(isIsomorphicStringsI("a", "a"));

        System.out.println("Second way: ");
        System.out.println(isIsomorphicStringsII("egg", "add"));
        System.out.println(isIsomorphicStringsII("foo", "bar"));
        System.out.println(isIsomorphicStringsII("paper", "title"));
        System.out.println(isIsomorphicStringsII("abc", "saa"));
        System.out.println(isIsomorphicStringsII("a", "a"));

    }

    // Time: O(n)
    // Space: O(1)
    private static boolean isIsomorphicStringsII(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }

        int[] i1 = new int[256];
        int[] i2 = new int[256];

        for(int i = 0; i < s1.length(); i++) {
            if(i1[s1.charAt(i)] != i2[s2.charAt(i)]) {
                return false;
            }

            i1[s1.charAt(i)] = i2[s2.charAt(i)] = i + 1;
        }
        return true;
    }


    // Time: O(n)
    // Space: O(n)
    private static boolean isIsomorphicStringsI(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }

        Map map = new HashMap<>();
        // leetcode fails on int -> has to be Integer
        for(int i = 0; i < s1.length(); i++) {
            if (map.put(s1.charAt(i), i) != map.put(s2.charAt(i) + "", i)) {
                return false;
            }
        }

        return true;
    }
}
