package com.leetcode.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters.

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
public class ShortestWordDistanceII {
    public static void main(String args[]) {
        String[] words = new String[] {"practice", "makes", "perfect", "coding", "makes"};

        ShortestWordDistanceII shortestWordDistanceII = new ShortestWordDistanceII(words);
        System.out.println(shortestWordDistanceII.shortest("coding", "practice"));
        System.out.println(shortestWordDistanceII.shortest("makes", "coding"));
    }

    HashMap<String, List<Integer>> map;

    // O(N) - time and space
    public ShortestWordDistanceII(String[] words) {
        map = new HashMap<>();

        for(int i = 0; i < words.length; i++) {
            List<Integer> temp = map.getOrDefault(words[i], new ArrayList<>());
            temp.add(i);
            map.put(words[i], temp);
        }
    }

    // O(max(K,L)) time K and L represent the number of occurrences of the two words.
    // O(n) for map
    public int shortest(String word1, String word2) {
        // the indices in the map will be in sorted order by default
        List<Integer> list1 = map.get(word1); // 1, 4
        List<Integer> list2 = map.get(word2); // 3

        int minDistance = Integer.MAX_VALUE;
        int l1 = 0, l2 = 0;

        while(l1 < list1.size() && l2 < list2.size()) {
            minDistance = Math.min(minDistance, Math.abs(list1.get(l1) - list2.get(l2)));

            if(list1.get(l1) < list2.get(l2)) {
                l1++;
            } else {
                l2++;
            }
        }

        return minDistance;
    }
}
