package com.leetcode.microsoft.dynamicprogramming;

import com.leetcode.facebook.stringsandarrays.MatchingParanthesis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

 Example:

 Input: [10,9,2,5,3,7,101,18]
 Output: 4
 Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 Note:

 There may be more than one LIS combination, it is only necessary for you to return the length.
 Your algorithm should run in O(n2) complexity.
 Follow up: Could you improve it to O(n log n) time complexity?

 * @author Santosh Manughala (SM030146).
 */
public class LongestIncreasingSubsequence {
    public static void main(String args[]) {
        System.out.println("getLongestIncreasingSubSequenceBruteForce: Expected: 4, actual: " + getLongestIncreasingSubSequenceBruteForce(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println("getLongestIncreasingSubSequenceBruteForce: Expected: 3, actual: " + getLongestIncreasingSubSequenceBruteForce(new int[]{0, 8, 4, 12, 2}));

        System.out.println("getLongestIncreasingSubSequenceRecursive: Expected: 4, actual: " + getLongestIncreasingSubSequenceRecursive(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println("getLongestIncreasingSubSequenceRecursive: Expected: 3, actual: " + getLongestIncreasingSubSequenceRecursive(new int[]{0, 8, 4, 12, 2}));

        System.out.println("getLISDynamicProgramming: Expected: 4, actual: " + getLISDynamicProgramming(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println("getLISDynamicProgramming: Expected: 3, actual: " + getLISDynamicProgramming(new int[]{0, 8, 4, 12, 2}));

        System.out.println("getLISDynamicProgrammingBinarySearch: Expected: 4, actual: " + getLISDynamicProgrammingBinarySearch(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println("getLISDynamicProgrammingBinarySearch: Expected: 3, actual: " + getLISDynamicProgrammingBinarySearch(new int[]{0, 8, 4, 12, 2}));
    }

    // Time O(nlogn)
    // Space O(n)
    private static int getLISDynamicProgrammingBinarySearch(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int length = 0;
        for(int num : nums) {
            int i = Arrays.binarySearch(dp, 0, length, num);
            if(i < 0) {
                i = -(i + 1);
            }

            dp[i] = num;

            if(i == length) {
                length++;
            }
        }

        return length;
    }

    // Time O(n^2)
    // Space O(n)
    private static int getLISDynamicProgramming(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 0;

        for(int i = 1; i < nums.length; i++) {
            int maxVal = 0;
            for(int j = 0; j < i; j++) {
                if(nums[i] < nums[j]) {
                    maxVal = Math.max(dp[j], maxVal);
                }
            }

            dp[i] = maxVal + 1;
            res = Math.max(dp[i], res);
        }

        return res;
    }

    // Time O(n^2)
    // Space O(n^2)
    private static int getLongestIncreasingSubSequenceRecursive(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int[][] memo = new int[nums.length + 1][nums.length];
        for(int[] curr : memo) {
            Arrays.fill(curr, -1);
        }

        return performRecurII(nums, memo, -1, 0);
    }

    private static int performRecurII(int[] nums, int[][] memo, int prevIdx, int currPos) {
        if(currPos == nums.length) {
            return 0;
        }

        if(memo[prevIdx + 1][currPos] > 0 ) {
            return memo[prevIdx + 1][currPos];
        }

        int taken = 0;
        if(prevIdx < 0 || nums[prevIdx] > nums[currPos]) {
            taken = 1 + performRecurII(nums, memo, currPos, currPos + 1);
        }

        int notTaken = performRecurII(nums, memo, prevIdx, currPos + 1);
        memo[prevIdx + 1][currPos]  = Math.max(taken, notTaken);
        return memo[prevIdx + 1][currPos];
    }


    // Time O(2^n)
    // Space O(n^2)
    private static int getLongestIncreasingSubSequenceBruteForce(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        return performRecur(nums, Integer.MIN_VALUE, 0);
    }

    private static int performRecur(int[] nums, int prevVal, int currPos) {
        if(currPos == nums.length) {
            return 0;
        }

        int taken = 0;
        if(prevVal < nums[currPos]) {
            taken = 1 + performRecur(nums, nums[currPos], currPos + 1);
        }

        int notTaken = performRecur(nums, prevVal, currPos + 1);
        return Math.max(taken, notTaken);
    }
}
