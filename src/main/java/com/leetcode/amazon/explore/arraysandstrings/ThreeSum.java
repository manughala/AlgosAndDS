package com.leetcode.amazon.explore.arraysandstrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

 Note:

 The solution set must not contain duplicate triplets.

 Example:

 Given array nums = [-1, 0, 1, 2, -1, -4],

 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]

 * @author Santosh Manughala (SM030146).
 */
public class ThreeSum {
    public static void main(String args[]) {
        List<List<Integer>> result = threeSum(new int[] {-1, 0, 1, 2, -1, -4}, 0);
        for(List<Integer> triplet : result) {
            System.out.println(triplet);
        }
    }

    // Time: O(n^2)
    // Space: O(1)
    private static List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int k = 0; k < nums.length; k++) {
                if(k > 0 && nums[k] == nums[k - 1]) {
                    continue;
                }

                int i = k + 1, j = nums.length - 1;

                while (i < j) {
                if (nums[i] + nums[j] + nums[k] == target) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[k]);
                    triplet.add(nums[i]);
                    triplet.add(nums[j]);
                    result.add(triplet);

                    i++;
                    j--;

                    while(i < j && nums[i] == nums[i - 1]) {
                        i++;
                    }

                    while(i < j && nums[j] == nums[j + 1]) {
                        j--;
                    }

                } else if (nums[i] + nums[j] + nums[k] > target) {
                    j--;
                } else {
                    i++;
                }
            }
        }

        return result;
    }

}
