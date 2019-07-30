package com.leetcode.linkedin;

/**
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

 Example:
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Input: word1 = “coding”, word2 = “practice”
 Output: 3
 Input: word1 = "makes", word2 = "coding"
 Output: 1
 Note:
 You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

 * @author Santosh Manughala (SM030146).
 */
public class ShortestWordDistanceI {

    public static void main(String args[]) {
        String[] words = new String[] {"practice", "makes", "perfect", "coding", "makes"};

        System.out.println(shortestDistance(words, "coding", "practice"));
        System.out.println(shortestDistance(words, "makes", "practice"));
    }

    // NOTE: brute force is using 2 for loops to perform this but will take O(n^2) time

    // TIme O(n)
    // Space O(1)
    private static int shortestDistance(String[] words, String word1, String word2) {
        if(words == null || words.length == 0 || word1.equals(word2) || word1 == null || word2 == null) {
            return 0;
        }

        int minDistance = words.length;
        int i1 = -1, i2 = -1;

        for(int i = 0; i < words.length; i++) {
            if(word1.equals(words[i])) {
                i1 = i;
            }

            if(word2.equals(words[i])) {
                i2 = i;
            }

            if(i1 != -1 && i2 != -1) {
                minDistance = Math.min(minDistance, Math.abs(i1 - i2));
            }
        }

        return minDistance;

    }
}
