package com.leetcode.facebook.stringsandarrays;

import java.util.HashMap;

/**
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

 Note:
 The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

 Example 1:

 Input: nums = [1, -1, 5, -2, 3], k = 3
 Output: 4
 Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 Example 2:

 Input: nums = [-2, -1, 2, 1], k = 1
 Output: 2
 Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 Follow Up:
 Can you do it in O(n) time?

 * @author Santosh Manughala (SM030146).
 */
public class MaxSubArraySum {
    public static void main(String[] args) {
//        int[] nums = new int[] {1, -1, 5, -2, 3};
//        int sum = 3;
        int[] nums = new int[] {10, 3, 2, 7, 3, 9};
        int sum = 15;
//        int[] nums = new int[] {-5, 8, -14, 2, 4, 12};
//        int sum = -5;

        if(nums == null || nums.length == 0) {
            System.out.println("the max length: " + 0);
            return;
        }

//        int result = getMaxSubArraySumLengthBruteForce(nums, sum);
        int result = getMaxSubArraySumLengthBestCase(nums, sum);
        System.out.println("the max length: " + result);
    }

    // Time complexity: O(n^2)
    private static int getMaxSubArraySumLengthBruteForce(int[] nums, int sum) {

        int i = 0, maxLength = 0;
        boolean maxFound = false;

        while(i < nums.length) {
            int count = 0;
            for(int j = i; j < nums.length; j++) {
                count += nums[j];
                if(count == sum && j- i + 1 > maxLength) {
                    maxFound = true;
                    maxLength = j - i + 1;
                }
            }
            i++;
        }

        return maxFound ? maxLength : 0;
    }

    // Time complexity: O(n)
    private static int getMaxSubArraySumLengthBestCase (int[] nums, int sum) {
        int maxLength = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length ; i++) {
            count += nums[i];

            if (count == sum) {
                maxLength = i + 1;
            }

            if(!map.containsKey(count)) {
                map.put(count, i);
            }

            if(map.containsKey(count - sum) && maxLength < i - map.get(count-sum)) {
                maxLength = i - map.get(count-sum);
            }
        }

        return maxLength;
    }
}
