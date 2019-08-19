package com.leetcode.amazon.explore.others;

import java.util.ArrayList;
import java.util.List;

/**
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

 Example 1:
 Input: S = "ababcbacadefegdehijhklij"
 Output: [9,7,8]
 Explanation:
 The partition is "ababcbaca", "defegde", "hijhklij".
 This is a partition so that each letter appears in at most one part.
 A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 Note:

 S will have length in range [1, 500].
 S will consist of lowercase letters ('a' to 'z') only.

 * @author Santosh Manughala (SM030146).
 */
public class PartitionLabels {
    public static void main(String args[]) {
        System.out.println(getPartitionLabels("ababcbacadefegdehijhklij"));
    }

    private static List<Integer> getPartitionLabels(String input) {
        int[] lastIndexOf = new int[26];
        for(int i = 0; i < input.length(); i++) {
            lastIndexOf[input.charAt(i) - 'a'] = i;
        }
        List<Integer> result = new ArrayList<>();

        int startIdx = 0, endIdx = 0;
        for(int currIdx = 0; currIdx < input.length(); currIdx++) {
            endIdx = Math.max(endIdx, lastIndexOf[input.charAt(currIdx) - 'a']);
            if(endIdx == currIdx) {
                result.add(endIdx - startIdx + 1);
                startIdx = currIdx + 1;
            }
        }

        return result;
    }
}
