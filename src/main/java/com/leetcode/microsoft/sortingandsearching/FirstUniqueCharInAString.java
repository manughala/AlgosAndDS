package com.leetcode.microsoft.sortingandsearching;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        System.out.println(firstUniqCharBestCase("@124asbc@24"));
        System.out.println(firstUniqCharBestCase("loveleetcode"));

        System.out.println(firstUniqCharSinglePass("leetcode"));
        System.out.println(firstUniqCharSinglePass("loveleetcode"));

        System.out.println(firstUniqCharIdxSinglePass("leetcode"));
        System.out.println(firstUniqCharIdxSinglePass("loveleetcode"));

//        System.out.println("stream, unique char: " + firstUniqCharBestCase('l'));
//        System.out.println("stream, unique char: " + firstUniqCharBestCase('e'));
//        System.out.println("stream, unique char: " + firstUniqCharBestCase('e'));
//        System.out.println("stream, unique char: " + firstUniqCharBestCase('t'));
//        System.out.println("stream, unique char: " + firstUniqCharBestCase('l'));
//        System.out.println("stream, unique char: " + firstUniqCharBestCase('c'));
//        System.out.println("stream, unique char: " + firstUniqCharBestCase('c'));
//        System.out.println("stream, unique char: " + firstUniqCharBestCase('t'));
//        System.out.println("stream, unique char: " + firstUniqCharBestCase('a'));


        System.out.println("stream, unique char idx: " + firstUniqCharIdxBestCase('l'));
        System.out.println("stream, unique char idx: " + firstUniqCharIdxBestCase('e'));
        System.out.println("stream, unique char idx: " + firstUniqCharIdxBestCase('e'));
        System.out.println("stream, unique char idx: " + firstUniqCharIdxBestCase('l'));
        System.out.println("stream, unique char idx: " + firstUniqCharIdxBestCase('e'));
        System.out.println("stream, unique char idx: " + firstUniqCharIdxBestCase('l'));
        System.out.println("stream, unique char idx: " + firstUniqCharIdxBestCase('c'));
        System.out.println("stream, unique char idx: " + firstUniqCharIdxBestCase('c'));
        System.out.println("stream, unique char idx: " + firstUniqCharIdxBestCase('t'));
        System.out.println("stream, unique char idx: " + firstUniqCharIdxBestCase('a'));
    }

    // iF a followup where this is a stream or we have to do it one pass, then:
    static Set<Character> repeating = new HashSet<>();
    static List<Character> nonRepeating = new ArrayList<>();
    private static char firstUniqCharBestCase(char c) {
        if(repeating.contains(c)) {
           return nonRepeating.isEmpty() ? ' ' : nonRepeating.get(0);
        }

        if(nonRepeating.contains(c)) {
            nonRepeating.remove((Character) c);
            repeating.add(c);
        } else {
            nonRepeating.add(c);
        }

        return nonRepeating.isEmpty() ? ' ' : nonRepeating.get(0);
    }

    // If we want index in case of stream processing, we may have to build the string as we go.
    static String input = "";
    private static int firstUniqCharIdxBestCase(char c) {
        input += c;

        if(repeating.contains(c)) {
            return nonRepeating.isEmpty() ? -1 : input.indexOf(nonRepeating.get(0));
        }

        if(nonRepeating.contains(c)) {
            nonRepeating.remove((Character) c);
            repeating.add(c);
        } else {
            nonRepeating.add(c);
        }

        return nonRepeating.isEmpty() ? -1 : input.indexOf(nonRepeating.get(0));
    }

    private static char firstUniqCharSinglePass(String s) {
        Set<Character> repeating = new HashSet<>();
        List<Character> nonRepeating = new ArrayList<>();

        for(char c : s.toCharArray()) {
            if(repeating.contains(c)) {
                continue;
            }

            if(nonRepeating.contains(c)) {
                // we cannot do c, as list.remove (index) can be triggered and we will get out of bound exception
                nonRepeating.remove((Character) c);
                repeating.add(c);
            } else {
                nonRepeating.add(c);
            }
        }

        return nonRepeating.isEmpty() ? ' ' : nonRepeating.get(0);
    }

    private static int firstUniqCharIdxSinglePass(String s) {
        Set<Character> repeating = new HashSet<>();
        List<Character> nonRepeating = new ArrayList<>();

        for(char c : s.toCharArray()) {
            if(repeating.contains(c)) {
                continue;
            }

            if(nonRepeating.contains(c)) {
                // we cannot do c, as list.remove (index) can be triggered and we will get out of bound exception
                nonRepeating.remove((Character) c);
                repeating.add(c);
            } else {
                nonRepeating.add(c);
            }
        }

        return nonRepeating.isEmpty() ? -1 : s.indexOf(nonRepeating.get(0));
    }

//    private static int firstUniqCharIdxSinglePass(String s) {
////        Set<Character> repeating = new HashSet<>();
//        List<Character> nonRepeating = new ArrayList<>();
//        Map<Character, Integer> charToIdx = new HashMap<>();
//        int idx = 0;
//
//        for(char c : s.toCharArray()) {
//            if(charToIdx.keySet().contains(c)) {
//                charToIdx.put(c, idx++);
//                continue;
//            }
//
//            if(nonRepeating.contains(c)) {
//                // we cannot do c, as list.remove (index) can be triggered and we will get out of bound exception
//                nonRepeating.remove((Character) c);
////                repeating.add(c);
//                charToIdx.put(c, idx++);
//            } else {
//                nonRepeating.add(c);
//            }
//        }
//
//        return nonRepeating.isEmpty() ? ' ' : nonRepeating.get(0);
//    }

    // SINCE THE CHAR CONTAINS ONLY LOWER CASE LETTERS: we can leverage alpha numeric value
    // Time O(n)
    // Space O(1)
    private static int firstUniqCharBestCase(String s) {
        int[] charsCount = new int[256];

        for(char c: s.toCharArray()) {
            charsCount[c]++;
        }

        for(char c: s.toCharArray()) {
            if(charsCount[c] == 1) {
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
