package com.leetcode.amazon.explore.dynamicprogramming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.

 Note:

 The same word in the dictionary may be reused multiple times in the segmentation.
 You may assume the dictionary does not contain duplicate words.
 Example 1:

 Input: s = "leetcode", wordDict = ["leet", "code"]
 Output: true
 Explanation: Return true because "leetcode" can be segmented as "leet code".
 Example 2:

 Input: s = "applepenapple", wordDict = ["apple", "pen"]
 Output: true
 Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 Note that you are allowed to reuse a dictionary word.
 Example 3:

 Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output: false

 * @author Santosh Manughala (SM030146).
 */
public class WordBreak {
    public static void main(String args[]) {
        // BEST CASE III  DP
        List<String> wordDict = new ArrayList<>();wordDict.add("leet");wordDict.add("code");
        System.out.println("isSpaceSeperatedSequenceDPBestCaseIII: Expected: true, actual: " + isSpaceSeperatedSequenceDPBestCaseIII("leetcode", wordDict));

        wordDict = new ArrayList<>();wordDict.add("apple");wordDict.add("pen");
        System.out.println("isSpaceSeperatedSequenceDPBestCaseIII: Expected: true, actual: " + isSpaceSeperatedSequenceDPBestCaseIII("applepenapple", wordDict));

        wordDict = new ArrayList<>();wordDict.add("cats");wordDict.add("dog");wordDict.add("sand");wordDict.add("and");wordDict.add("cat");
        System.out.println("isSpaceSeperatedSequenceDPBestCaseIII: Expected: false, actual: " + isSpaceSeperatedSequenceDPBestCaseIII("catsandog", wordDict));

        wordDict = new ArrayList<>();wordDict.add("a");wordDict.add("abc");wordDict.add("b");wordDict.add("cd");
        System.out.println("isSpaceSeperatedSequenceDPBestCaseIII: Expected: true, actual: " + isSpaceSeperatedSequenceDPBestCaseIII("abcd", wordDict));

        // BEST CASE II  BFS
        wordDict = new ArrayList<>();wordDict.add("leet");wordDict.add("code");
        System.out.println("isSpaceSeperatedSequenceBestCaseII: Expected: true, actual: " + isSpaceSeperatedSequenceBestCaseII("leetcode", wordDict));

        wordDict = new ArrayList<>();wordDict.add("apple");wordDict.add("pen");
        System.out.println("isSpaceSeperatedSequenceBestCaseII: Expected: true, actual: " + isSpaceSeperatedSequenceBestCaseII("applepenapple", wordDict));

        wordDict = new ArrayList<>();wordDict.add("cats");wordDict.add("dog");wordDict.add("sand");wordDict.add("and");wordDict.add("cat");
        System.out.println("isSpaceSeperatedSequenceBestCaseII: Expected: false, actual: " + isSpaceSeperatedSequenceBestCaseII("catsandog", wordDict));

        wordDict = new ArrayList<>();wordDict.add("a");wordDict.add("abc");wordDict.add("b");wordDict.add("cd");
        System.out.println("isSpaceSeperatedSequenceBestCaseII: Expected: true, actual: " + isSpaceSeperatedSequenceBestCaseII("abcd", wordDict));

        // BEST CASE I BETTER BRUTE FORCE (RECUR AND MEMORY)
        wordDict = new ArrayList<>();wordDict.add("leet");wordDict.add("code");
        System.out.println("isSpaceSeperatedSequenceBestCaseI: Expected: true, actual: " + isSpaceSeperatedSequenceBestCaseI("leetcode", wordDict));

        wordDict = new ArrayList<>();wordDict.add("apple");wordDict.add("pen");
        System.out.println("isSpaceSeperatedSequenceBestCaseI: Expected: true, actual: " + isSpaceSeperatedSequenceBestCaseI("applepenapple", wordDict));

        wordDict = new ArrayList<>();wordDict.add("cats");wordDict.add("dog");wordDict.add("sand");wordDict.add("and");wordDict.add("cat");
        System.out.println("isSpaceSeperatedSequenceBestCaseI: Expected: false, actual: " + isSpaceSeperatedSequenceBestCaseI("catsandog", wordDict));

        wordDict = new ArrayList<>();wordDict.add("a");wordDict.add("abc");wordDict.add("b");wordDict.add("cd");
        System.out.println("isSpaceSeperatedSequenceBestCaseI: Expected: true, actual: " + isSpaceSeperatedSequenceBestCaseI("abcd", wordDict));

        // BRUTE FORCE
        wordDict = new ArrayList<>();wordDict.add("leet");wordDict.add("code");
        System.out.println("isSpaceSeperatedSequenceBruteForce: Expected: true, actual: " + isSpaceSeperatedSequenceBruteForce("leetcode", wordDict));

        wordDict = new ArrayList<>();wordDict.add("apple");wordDict.add("pen");
        System.out.println("isSpaceSeperatedSequenceBruteForce: Expected: true, actual: " + isSpaceSeperatedSequenceBruteForce("applepenapple", wordDict));

        wordDict = new ArrayList<>();wordDict.add("cats");wordDict.add("dog");wordDict.add("sand");wordDict.add("and");wordDict.add("cat");
        System.out.println("isSpaceSeperatedSequenceBruteForce: Expected: false, actual: " + isSpaceSeperatedSequenceBruteForce("catsandog", wordDict));

        wordDict = new ArrayList<>();wordDict.add("a");wordDict.add("abc");wordDict.add("b");wordDict.add("cd");
        System.out.println("isSpaceSeperatedSequenceBruteForce: Expected: true, actual: " + isSpaceSeperatedSequenceBruteForce("abcd", wordDict));

        // OWN IDEA DID NOT WORK
        List<String> wordDict4 = new ArrayList<>();wordDict4.add("a");wordDict4.add("abc");wordDict4.add("b");wordDict4.add("cd");
        System.out.println("isSpaceSeperatedSequenceDIDNOTWORK: Expected: true, actual: " + isSpaceSeperatedSequenceDIDNOTWORK("abcd", wordDict4));
    }

    // DP
    // Time O(n^2)
    // Space O(n)
    private static boolean isSpaceSeperatedSequenceDPBestCaseIII(String word, List<String> wordDict) {
        boolean dp[] = new boolean[word.length() + 1];
        dp[0] = true;

        for(int i = 1; i <= word.length(); i++) {
            for(int j = 0; j < i; j++) {
                if(dp[j] && wordDict.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[word.length()];

    }

    // BFS
    // Time O(n^2)
    // Space O(n)
    private static boolean isSpaceSeperatedSequenceBestCaseII(String word, List<String> wordDict) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        int[] visited = new int[word.length()];

        while(!queue.isEmpty()) {
            int start = queue.poll();

            if(visited[start] == 0) {

                for(int end = start + 1; end <= word.length(); end++) {
                    if(wordDict.contains(word.substring(start, end))) {
                        queue.add(end);

                        if(end == word.length()) {
                            return true;
                        }
                    }
                }

                visited[start] = 1;
            }
        }

        return false;
    }

    // Time O(n^2)
    // Space O(n)
    private static boolean isSpaceSeperatedSequenceBestCaseI(String word, List<String> wordDict) {
        return isSpaceSeperatedSequenceBestCaseIRecur(word, wordDict, 0, new Boolean[word.length()]);
    }

    private static boolean isSpaceSeperatedSequenceBestCaseIRecur(String word, List<String> wordDict, int start, Boolean[] visited) {
        if(start == word.length()) {
            return true;
        }

        if(visited[start] != null) {
            return visited[start];
        }

        for(int end = start + 1; end <= word.length(); end++) {
            if(wordDict.contains(word.substring(start, end)) && isSpaceSeperatedSequenceBestCaseIRecur(word, wordDict, end, visited)) {
                return visited[start] = true;
            }
        }

        return visited[start] = false;
    }

    // Time O(n^n)
    // Space O(n)
    private static boolean isSpaceSeperatedSequenceBruteForce(String word, List<String> wordDict) {
        return isSpaceSeperatedSequenceRecur(word, wordDict, 0);
    }

    private static boolean isSpaceSeperatedSequenceRecur(String word, List<String> wordDict, int start) {
        if(start == word.length()) {
            return true;
        }

        for(int end = start + 1; end <= word.length(); end++) {
            if(wordDict.contains(word.substring(start, end)) && isSpaceSeperatedSequenceRecur(word, wordDict, end)) {
                return true;
            }
        }

        return false;
    }

    // own idea: will not work for example wordDict4
    // Time: O(m * n) - m is number of words, n is max length of the words
    // Space: O(1)
    private static boolean isSpaceSeperatedSequenceDIDNOTWORK(String word, List<String> wordDict) {
        Trie trie = new Trie();
        buildTrie(trie, wordDict);

        Trie temp = trie;
        for(char c : word.toCharArray()) {
            int idx = c - 'a';
            if(temp.children[idx] == null) {
                return false;
            }

            if(temp.word != null) {
                temp = trie;
            } else {
                temp = temp.children[idx];
            }
        }

        return true;
    }

    private static void buildTrie(Trie trie, List<String> wordDict) {
        for(String word : wordDict) {
            Trie temp = trie;

            for (char c : word.toCharArray()) {
                int idx = c - 'a';

                if (temp.children[idx] == null) {
                    temp.children[idx] = new Trie();
                }

                if (word.indexOf(c) == word.length() - 1) {
                    temp.word = word;
                }

                temp = temp.children[idx];
            }
        }
    }

}

class Trie {
    Trie[] children;
    String word;

    Trie() {
        children = new Trie[26];
    }
}