package com.leetcode.linkedin;

import java.util.Arrays;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.



 Example 1:

 Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 Output: True
 Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.


 Note:

 1 <= k <= len(nums) <= 16.
 0 < nums[i] < 10000.

 * @author Santosh Manughala (SM030146).
 */
public class PartitionToKEqualSumSubsets {
    public static void main(String args[]) {
        System.out.println(canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
        System.out.println(canPartitionKSubsets(new int[]{10,10,10,7,7,7,7,7,7,6,6,6}, 3));
        System.out.println(canPartitionKSubsets(new int[]{2, 2, 2, 2, 3, 4, 5}, 4));
    }

    // refer: https://github.com/bephrem1/backtobackswe/blob/master/Dynamic%20Programming%2C%20Recursion%2C%20%26%20Backtracking/partitionIntoKEqualSumSubsets.java
    // Time :O(n!) -> i think but similar problem solution on leet code has O(k ^ n-k * n!)
    // space:  O(n)
    private static boolean canPartitionKSubsets(int[] nums, int k) {
        // 1.) k can not be negative or 0 because we can not fill 0 or negative buckets
        // 2.) k can not be greater than the length of the array, we can't partition
        // more buckets than there are elements in the array
        if(nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return false;
        }

        /*
        Get the sum of all items in the array. We will use this to
        divide by k to get the sum that each bucket needs to hit
        */
        int totalSum = 0;
        for(int i : nums) {
            totalSum += i;
        }

        // totalSum % k must be 0. If it is not then we would have to have to
        // fill buckets to a floating point sum which would be impossible with only integers
        if(totalSum % k != 0) {
            return false;
        }

        return canPartition(0, nums, k, 0, totalSum / k, new boolean[nums.length]);
    }

    private static boolean canPartition(int start, int[] nums, int k, int currentSum, int targetSum, boolean[] chosen) {
        /*
        If we have filled all k - 1 buckets up to this point and we are now on
        our last bucket, we can stop and be finished.

        Example:
        arr = [4, 3, 2, 3, 5, 2, 1]
        k = 4
        targetBucketSum = 5
        If we get to the point in our recursion that k = 1 that means we have filled
        k - 1 buckets (4 - 1 = 3). 3 buckets have been filled, each a value of 5 meaning
        we have "eaten" 15 "points" of value from an array that sums to 20.
        This means we have 5 "points" to extract from the array and that for sure will fill
        the last bucket. So at the point there is 1 bucket left, we know we can complete the
        partitioning (we don't have to though, we just want to know whether we can or not).
        */
        if(k == 1) {
            return true;
        }

        /*
        Bucket full. continue the recursion with k - 1 as the new k value, BUT the
        targetBucketSum stays the same. We just have 1 less bucket to fill.
        */
        if(currentSum == targetSum) {
            return canPartition(0, nums, k - 1, 0, targetSum, chosen);
        }

        /*
        Try all values from 'iterationStart' to the end of the array ONLY if:

        1.) They have not been used up to this point in the recursion's path
        2.) They do not overflow the current bucket we are filling
        */
        for(int i = start; i < nums.length; i++) {
            if(!chosen[i] && currentSum + nums[i] <= targetSum) {
                chosen[i] = true;

                /*
                See if we can partition from this point with the item added
                to the current bucket progress
                */
                if(canPartition(start + 1, nums, k, currentSum + nums[i], targetSum, chosen)) {
                    return true;
                }
                chosen[i] = false;
            }
        }

        /*
        Partitioning from this point is impossible. Whether we are at the
        top level of the recursion or deeper into it.
        */
        return false;
    }
}
