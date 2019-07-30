package com.leetcode.facebook.DynamicProgramming;

/**
 * Maximum Sum of 3 Non-Overlapping Subarrays


 In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

 Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

 Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

 Example:
 Input: [1,2,1,2,6,7,5,1], 2
 Output: [0, 3, 5]
 Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 Note:
 nums.length will be between 1 and 20000.
 nums[i] will be between 1 and 65535.
 k will be between 1 and floor(nums.length / 3).

 * @author Santosh Manughala (SM030146).
 */
public class MaxSumOf3OverlappingSubArrays {

    public static void main(String args[]) {
        int nums[] = new int[] {1, 2, 1, 2, 6, 7, 5, 1};
        int subArraySize = 2;

        int[] result = maxSumOfThreeSubarrays(nums, subArraySize);

        for(int i : result) {
            System.out.println(i);
        }
    }


    // Time: O(n)
    // Space: O(n)
    private static int[] maxSumOfThreeSubarrays(int[] nums, int subArraySize) {
        //windows is an array of sums of windows
        int[] windows = new int[nums.length - subArraySize + 1];
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= subArraySize){
                sum -= nums[i - subArraySize];
            }

            if (i >= subArraySize - 1) {
                windows[i - subArraySize + 1] = sum;
            }
        }

        int[] left = new int[windows.length];
        int best = 0;
        for (int i = 0; i < windows.length; i++) {
            if (windows[i] > windows[best]) {
                best = i;
            }

            left[i] = best;
        }

        int[] right = new int[windows.length];
        best = windows.length - 1;
        for (int i = windows.length - 1; i >= 0; i--) {
            if (windows[i] >= windows[best]) {
                best = i;
            }

            right[i] = best;
        }

        int[] ans = new int[]{-1, -1, -1};
        for (int j = subArraySize; j < windows.length - subArraySize; j++) {
            int i = left[j - subArraySize], k = right[j + subArraySize];
            if (ans[0] == -1 || windows[i] + windows[j] + windows[k] > windows[ans[0]] + windows[ans[1]] + windows[ans[2]]) {
                ans[0] = i;
                ans[1] = j;
                ans[2] = k;
            }
        }

        return ans;
    }
}
