package com.leetcode.amazon.explore.arraysandstrings;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

 Example 1:

 Input: [3,0,1]
 Output: 2
 Example 2:

 Input: [9,6,4,2,3,5,7,0,1]
 Output: 8
 Note:
 Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

 * @author Santosh Manughala (SM030146).
 */
public class MissingNumber {

    public static void main(String args[]) {
        System.out.println("missingNumberBestCaseDoNotWorkForLargeN: " + missingNumberBestCaseDoNotWorkForLargeN(new int[]{9,6,4,2,3,5,7,0,1}));
        System.out.println("missingNumberBestCaseDoNotWorkForLargeN: " + missingNumberBestCaseDoNotWorkForLargeN(new int[]{3,0,1}));

        System.out.println("missingNumberIntermediateCase: " + missingNumberIntermediateCase(new int[]{9,6,4,2,3,5,7,0,1}));
        System.out.println("missingNumberIntermediateCase: " + missingNumberIntermediateCase(new int[]{3,0,1}));

        System.out.println("missingNumberBestCase: " + missingNumberBestCase(new int[]{9,6,4,2,3,5,7,0,1}));
        System.out.println("missingNumberBestCase: " + missingNumberBestCase(new int[]{3,0,1}));
    }


    private static int missingNumberBestCase(int[] nums) {
        int missing = nums.length;

        // (3 ^ 0 ^ 3) ^ (1 ^ 0) ^ (2 ^ 1)
        for(int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }

        return missing;
    }

    // Time: O(n)
    // Space: O(n)
    private static int missingNumberIntermediateCase(int[] nums) {
        boolean[] visited = new boolean[nums.length + 1];

        for(int i = 0; i < nums.length; i++) {
            visited[nums[i]] = true;
        }

        for(int i = 0; i < nums.length + 1; i++) {
            if(!visited[i]) {
                return i;
            }
        }

        return -1;
    }

    // Time : O(n)
    // Space : O(1)
    // NOTE: DO NOT DO THIS, this will work for small n, but when the n is Integer.MAXVALUE, it overflows -> long /double also cannot hold those values.
    private static int missingNumberBestCaseDoNotWorkForLargeN(int[] nums) {
        int n = nums.length;

        long overallSum = n * (n + 1) / 2;
        long currentSum = 0;

        for(int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
        }

        return (int) (overallSum - currentSum);
    }
}
