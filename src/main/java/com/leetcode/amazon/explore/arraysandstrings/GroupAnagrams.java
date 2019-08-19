package com.leetcode.amazon.explore.arraysandstrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static void main(String args[]) {
        String[] input1 = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] input2 = new String[]{"cab", "tin", "pew", "duh", "may", "ill", "buy", "bar", "max", "doc"};

        System.out.println("groupAnagramsBestCase: ");
        System.out.println(groupAnagramsBestCase(input1));
        System.out.println(groupAnagramsBestCase(input2));

// NOTE: for intermediate case, refer microsoft, but idea is to sort and store the sorted string as key instead of alphanumeric key
//        System.out.println("groupAnagramsIntermediateCase: ");
//        System.out.println(groupAnagramsIntermediateCase(input1));
//        System.out.println(groupAnagramsIntermediateCase(input2));
    }

    // brute force: another way is to just check for each anagram, but we need to keep track of all that we already looked at ->
    // https://www.geeksforgeeks.org/print-pairs-anagrams-given-array-strings/

    // Time: O(m * n) m string length, n max number of chars in string
    // Space: O(m * n)
    private static List<List<String>> groupAnagramsBestCase(String[] strings) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> keyToValues = new HashMap<>();

        for(String str : strings) {
            String key = getKey(str);
            List<String> anagrams = keyToValues.getOrDefault(key, new ArrayList<>());
            anagrams.add(str);

            keyToValues.put(key, anagrams);
        }

        result.addAll(keyToValues.values());
        return result;
    }

    private static String getKey(String str) {
        int[] key = new int[26];

        for(char c : str.toCharArray()) {
            key[c - 'a']++;
        }

        StringBuilder builder = new StringBuilder();
        for(int i : key) {
            builder.append(i);
        }

        return builder.toString();
    }
}
