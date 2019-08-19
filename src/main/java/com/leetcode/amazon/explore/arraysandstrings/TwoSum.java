package com.leetcode.amazon.explore.arraysandstrings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:

 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].

 * @author Santosh Manughala (SM030146).
 */
public class TwoSum {
    public static void main(String args[]) {
        int[] result = twoSum(new int[]{2, 7, 11, 15}, 9);

        System.out.println(result[0] + " and " + result[1]);
    }

    // Time: O(n)
    // Space: O(n)
    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }
        // or may throw not found
        return new int[]{-1, -1};
    }
}
