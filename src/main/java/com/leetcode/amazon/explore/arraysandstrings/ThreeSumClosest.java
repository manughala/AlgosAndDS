package com.leetcode.amazon.explore.arraysandstrings;

import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.

 Example:

 Given array nums = [-1, 2, 1, -4], and target = 1.

 The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

 * @author Santosh Manughala (SM030146).
 */
public class ThreeSumClosest {
    public static void main(String args[]) {
        int result = threeSumClosest(new int[] {-1, 2, 1, -4}, 1);
        System.out.println(result);
    }

    // Time: O(n^2)
    // Space: O(1)
    private static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE, result = 0;

        for(int i = 0; i < nums.length; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1, k = nums.length - 1;

            while(j < k) {
                int currSum = nums[i] + nums[j] + nums[k];
                int diff = Math.abs(currSum - target);

                if (diff == 0) {
                    return currSum;
                }

                if(diff < min) {
                    min = diff;
                    result = currSum;
                }

                if(currSum <= target) {
                    j++;
                } else {
                    k--;
                }
            }
        }

        return result;
    }
}
