package com.leetcode.amazon.explore.dynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

 Note:

 The same word in the dictionary may be reused multiple times in the segmentation.
 You may assume the dictionary does not contain duplicate words.
 Example 1:

 Input:
 s = "catsanddog"
 wordDict = ["cat", "cats", "and", "sand", "dog"]
 Output:
 [
 "cats and dog",
 "cat sand dog"
 ]
 Example 2:

 Input:
 s = "pineapplepenapple"
 wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 Output:
 [
 "pine apple pen apple",
 "pineapple pen apple",
 "pine applepen apple"
 ]
 Explanation: Note that you are allowed to reuse a dictionary word.
 Example 3:

 Input:
 s = "catsandog"
 wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output:
 []

 * @author Santosh Manughala (SM030146).
 */
public class WordBreakII {
    public static void main(String args[]) {
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");

        System.out.println("getWordsBruteForce: " + getWordsBruteForce("catsanddog", wordDict));
        System.out.println("getWordsIntermediate: " + getWordsIntermediate("catsanddog", wordDict));
        System.out.println("getWordsIntermediate: " + getWordsDP("catsanddog", wordDict));
    }

    // Time complexity : O(n^3) Two loops are required to fill dp array and one loop for appending a list.
    // Space complexity : O(n^3) Length of dp array is n and each value of dp array contains a list of string i.e. n^2 space.
    private static List<String> getWordsDP(String word, List<String> wordDict) {
        List<String>[] dp = new ArrayList[word.length() + 1];
        List<String> initialString = new ArrayList<>();
        initialString.add("");
        dp[0] = initialString;

        for(int i = 1; i <= word.length(); i++) {
            List<String> wordsWithCurrSubstring = new ArrayList<>();
            for(int j = 0; j < i; j++) {
                if(dp[j].size() > 0 && wordDict.contains(word.substring(j, i))) {
                    for(String w : dp[j]) {
                        wordsWithCurrSubstring.add(w + (w.equals("") ? "" : " ") + word.substring(j, i));
                    }
                }
            }

            dp[i] = wordsWithCurrSubstring;
        }

        return dp[word.length()];
    }

    // Time: O(n^3)
    // Space: O(n^3)
    static Map<Integer, List<String>> indexToWords = new HashMap<>();
    private static List<String> getWordsIntermediate(String word, List<String> wordDict) {
        return getWordsIntermediate(word, wordDict, 0);
    }

    private static List<String> getWordsIntermediate(String word, List<String> wordDict, int start) {
        if(indexToWords.containsKey(start)) {
            return indexToWords.get(start);
        }

        List<String> result = new ArrayList<>();

        if(start == word.length()) {
            result.add("");
        }

        for(int end = start + 1; end <= word.length(); end++) {
            if(wordDict.contains(word.substring(start, end))) {
                List<String> wordsWithCurrPrefix = getWordsBruteForce(word, wordDict, end);

                for(String wordWithCurrPrefix : wordsWithCurrPrefix) {
                    result.add(word.substring(start, end) + (wordWithCurrPrefix.equals("") ? "" : " ") + wordWithCurrPrefix);
                }
            }
        }

        indexToWords.put(start, result);

        return result;
    }

    // Time: O(n^n)
    // Space: O(n^3)
    private static List<String> getWordsBruteForce(String word, List<String> wordDict) {
        return getWordsBruteForce(word, wordDict, 0);
    }

    private static List<String> getWordsBruteForce(String word, List<String> wordDict, int start) {
        List<String> result = new ArrayList<>();

        if(start == word.length()) {
            result.add("");
        }

        for(int end = start + 1; end <= word.length(); end++) {
            if(wordDict.contains(word.substring(start, end))) {
                List<String> wordsWithCurrPrefix = getWordsBruteForce(word, wordDict, end);

                for(String wordWithCurrPrefix : wordsWithCurrPrefix) {
                    result.add(word.substring(start, end) + (wordWithCurrPrefix.equals("") ? "" : " ") + wordWithCurrPrefix);
                }
            }
        }

        return result;
    }
}
