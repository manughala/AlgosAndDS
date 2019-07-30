package com.leetcode.facebook.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Palindrome Pairs


 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

 Example 1:

 Input: ["abcd","dcba","lls","s","sssll"]
 Output: [[0,1],[1,0],[3,2],[2,4]]
 Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 Example 2:

 Input: ["bat","tab","cat"]
 Output: [[0,1],[1,0]]
 Explanation: The palindromes are ["battab","tabbat"]

 * @author Santosh Manughala (SM030146).
 */
public class PalindromePairs {

    public static void main(String args[]) {
        String[] words = new String[] {"abcd","dcba","lls","s","sssll"};
//        List<List<Integer>> result = palindromePairsBruteForce(words);
        List<List<Integer>> result = palindromePairsBestCase(words);

        for(List<Integer> list : result) {
            System.out.println(list);
        }
    }

    private static class TrieNode {
        TrieNode[] next;
        int index;
        List<Integer> list;

        TrieNode() {
            next = new TrieNode[26];
            index = -1;
            list = new ArrayList<>();
        }
    }

    // TODO did not understand this at all-> need to read before going
    private static List<List<Integer>> palindromePairsBestCase(String[] words) {
        List<List<Integer>> res = new ArrayList<>();

        TrieNode root = new TrieNode();

        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            search(words, i, root, res);
        }

        return res;
    }

    private static void addWord(TrieNode root, String word, int index) {
        for (int i = word.length() - 1; i >= 0; i--) {
            int j = word.charAt(i) - 'a';

            if (root.next[j] == null) {
                root.next[j] = new TrieNode();
            }

            if (isPalindrome(word, 0, i)) {
                root.list.add(index);
            }

            root = root.next[j];
        }

        root.list.add(index);
        root.index = index;
    }

    private static void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
        for (int j = 0; j < words[i].length(); j++) {
            if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
                res.add(Arrays.asList(i, root.index));
            }

            root = root.next[words[i].charAt(j) - 'a'];
            if (root == null) return;
        }

        for (int j : root.list) {
            if (i == j) continue;
            res.add(Arrays.asList(i, j));
        }
    }

    private static boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) return false;
        }

        return true;
    }

    // Time:O(n^2k) -> k being the max length to be checked for palindrome
    // Space: O(1)
    private static List<List<Integer>> palindromePairsBruteForce(String[] words) {
        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i < words.length; i++) {
            for(int j = 0;  j < words.length; j++) {
                if(i != j && isPalindrome(words[i] + words[j])) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    result.add(list);
                }
            }
        }
        return result;
    }

    private static boolean isPalindrome(String word) {
        int i = 0, j = word.length() - 1;
        while(i < j) {
            if(word.charAt(i) == word.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }

        return true;
    }
}
