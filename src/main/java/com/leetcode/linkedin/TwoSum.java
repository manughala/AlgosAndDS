package com.leetcode.linkedin;

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

        int[] result = twoSumBestCase(new int[]{-1,0,1,2,-1,-4}, 3);
//        int[] result = twoSumBestCase(new int[]{2, 7, 11, 15}, 9);
//        int[] result = twoSumBestCase(new int[]{3, 2, 4}, 6);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
    // Brute force: 2 for loops -> easy to find the indices
    // Best case 1: first populate the map with all the values, then find the
    // compliment in another pass -> the best case combines these both in 1 pass

    //NOTE: this is not to return the values in the array, this is to return the indices so we should not follow the 3 sum way
    private static int[] twoSumBestCase (int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return  new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("Not found");
    }
}

