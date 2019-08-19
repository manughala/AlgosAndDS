package com.leetcode.linkedin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

 Only one letter can be changed at a time.
 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 Note:

 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 You may assume no duplicates in the word list.
 You may assume beginWord and endWord are non-empty and are not the same.
 Example 1:

 Input:
 beginWord = "hit",
 endWord = "cog",
 wordList = ["hot","dot","dog","lot","log","cog"]

 Output: 5

 Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.
 Example 2:

 Input:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]

 Output: 0

 Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

 * @author Santosh Manughala (SM030146).
 */
public class WordLadder {

    public static void main(String args[]) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = new ArrayList<String>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        System.out.println(ladderLengthI(beginWord, endWord, wordList));
        System.out.println(ladderLengthII(beginWord, endWord, wordList));
    }

    private static class Word {
        public String word;
        public int count;

        Word(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

    // Accepted leetcode
    // Time: O(M*N) -> m - number of words n is length of each word
    // Space: O(n)
    private static int ladderLengthII(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || !wordList.contains(endWord)) {
            return 0;
        }

        Queue<Word> queue = new LinkedList<>();
        queue.add(new Word(beginWord, 1));

        Set<String> wordSet = new HashSet<>(wordList);

        while (!queue.isEmpty()) {
            Word wordTemp = queue.remove();
            String word = wordTemp.word;
            int count = wordTemp.count;

            if(word.equals(endWord)) {
                return count;
            }

            StringBuilder builder = new StringBuilder(word);
            for(int i = 0; i < builder.length(); i++) {
                char temp = builder.charAt(i);

                for(char c = 'a'; c < 'z'; c++) {
                    if(c == temp) {
                        continue;
                    }

                    builder.setCharAt(i, c);
                    String newWord = builder.toString();
                    if(wordSet.remove(newWord)) {
                        queue.add(new Word(newWord, count + 1));
                    }
                }

                builder.setCharAt(i, temp);
            }

        }

        return 0;
    }

    // Works, but time limit exceeded on leetcode.
    // time: O(m*m*n) -> i think because worst case, while will go over all m words, the first for will go over each one again, and internally we go over n length words.
    // space: O(n)
    private static int ladderLengthI(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null || !wordList.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int count = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            count++;

            for(int i = 0; i < size; i++) {
                String word = queue.remove();
                Set<String> candidates = transform(wordList, word);
                for(String candidate : candidates) {
                    if(endWord.equals(candidate)) {
                        return count;
                    }
                    queue.add(candidate);
                }
            }

        }

        return 0;
    }

    private static Set<String> transform(List<String> words, String word) {
        Set<String> candidates = new HashSet<>();
        StringBuffer buffer = new StringBuffer(word);

        for(int i = 0; i < buffer.length(); i++) {
            char temp = buffer.charAt(i);

            for(char c = 'a'; c < 'z'; c++) {
                if(c == temp) {
                    continue;
                }

                buffer.setCharAt(i, c);
                String newWord = buffer.toString();
                if(words.contains(newWord)) {
                    candidates.add(newWord);
                }
            }

            buffer.setCharAt(i, temp);
        }

        return candidates;

    }
}
