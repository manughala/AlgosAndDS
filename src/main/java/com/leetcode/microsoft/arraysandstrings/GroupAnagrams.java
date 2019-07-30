package com.leetcode.microsoft.arraysandstrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of strings, group anagrams together.

 Example:

 Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 Output:
 [
 ["ate","eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 Note:

 All inputs will be in lowercase.
 The order of your output does not matter.

 * @author Santosh Manughala (SM030146).
 */
public class GroupAnagrams {
    // IDEAS:
    // 1. brute force: O(n^2) -> 2 for loops, check if all the chars is Si are in Sj
    // 2. THIS IDEA DID NOT WORK : If the alpha numeric value of all chars in a string is same, they are anagrams ->
    // REASON: we could have 2 strings like duh and ill will always lead up to the same value, and they will be returned as anagram
    // 3. TWEAKED IDEA2, a bit (copied from leetcode solution) -> instead of using alpha numeric val as key, come up with a key,
    // the key is just an int array of size 26 representing the chars in the string. Convert this to string key, and group anagrams.
    // Time O(nm) -> n string len, m max char len in the string. space O(nm)
    // 4. Another solution from leetcode, sort the string, and use as key to a map -> O(nklogk) O(nm)
    public static void main(String args[]) {
        String[] input1 = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] input2 = new String[]{"cab", "tin", "pew", "duh", "may", "ill", "buy", "bar", "max", "doc"};

        System.out.println("groupAnagramsBestCase: ");
        System.out.println(groupAnagramsBestCase(input1));
        System.out.println(groupAnagramsBestCase(input2));


        System.out.println("groupAnagramsIntermediateCase: ");
        System.out.println(groupAnagramsIntermediateCase(input1));
        System.out.println(groupAnagramsIntermediateCase(input2));
    }


    // The difference here is the key stored is sorted array, because of which the time goes up y mlogm
    // Time O(nmlogm)
    // space O(nm)
    private static List<List<String>> groupAnagramsIntermediateCase(String[] strs) {
        List<List<String>> result = new ArrayList<>();

        if(strs == null || strs.length == 0) {
            return result;
        }

        Map<String, List<String>> sortedStringKeyToStrs = new HashMap<>();
        for(String str : strs) {
            char[] key = str.toCharArray();
            Arrays.sort(key);
            String stringKey = new String(key);

            List<String> anagrams = sortedStringKeyToStrs.getOrDefault(stringKey, new ArrayList<>());
            anagrams.add(str);

            sortedStringKeyToStrs.put(stringKey, anagrams);
        }

        result.addAll(sortedStringKeyToStrs.values());
        return result;
    }

    // we could have 2 strings like duh and ill will always lead up to the same value if we used alpha numeric val as key, and they will be returned as anagram
    // use a key that uniquely identifies each anagram set, so used an int array of size 26 representing the chars in the string. Convert this to string key
    // Time O(n*m) -> n string length, m max number of chars in string
    // Space O(n*m)
    private static List<List<String>> groupAnagramsBestCase(String[] strs) {
        List<List<String>> result = new ArrayList<>();

        if(strs == null || strs.length == 0) {
            return result;
        }

        Map<String, List<String>> countKeyToStrs = new HashMap<>();

        for(String str : strs) {
            String key = getKey(str);
            List<String> anagrams = countKeyToStrs.getOrDefault(key, new ArrayList<>());
            anagrams.add(str);

            countKeyToStrs.put(key, anagrams);
        }

        result.addAll(countKeyToStrs.values());
        return result;
    }

    private static String getKey(String s) {
        // create the key
        int[] key = new int[26];
        for(char c : s.toCharArray()) {
            key[c - 'a']++;
        }

        // convert the key to string
        StringBuilder stringKeyBuilder = new StringBuilder();
        for(int k : key) {
            stringKeyBuilder.append(k);
        }

        return stringKeyBuilder.toString();
    }

}
