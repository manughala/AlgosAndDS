package com.leetcode.facebook.stringsandarrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        threeSum(new int[]{-1,0,1,2,-1,-4});
    }

    private static List<List<Integer>> threeSum(int[] nums) {
        if(nums == null) {
            throw new IllegalArgumentException("nums null");
        }

        if(nums.length == 0) {
            return new ArrayList<>();
        }

//        List<List<Integer>> result = bruteForceThreeSum(nums, 0);
        List<List<Integer>> result = bestCaseThreeSum(nums, 0);
        //time complexity is O(N^2) and space complexity is O(1).

        for (List<Integer> list : result) {
            System.out.println(list);
        }
        return result;
    }

    private static List<List<Integer>> bestCaseThreeSum(int[] nums, int sum) {
        List<List<Integer>> result = new ArrayList<>();
//        Arrays.sort(nums);


        for(int i = 0; i < nums.length-2; i++) {
            // Never let i refer to the same value twice to avoid duplicates.
            if(i == 0 || nums[i] > nums[i-1]) {
                int j = i + 1, k = nums.length - 1;

                while (j < k) {
                    if (nums[i] + nums[j] + nums[k] == sum) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        result.add(list);

                        j++;
                        k--;

                        // Never let j refer to the same value twice (in an output) to avoid duplicates
                        while (j < k && nums[j] == nums[j-1]) {
                            j++;
                        }

                        // Never let k refer to the same value twice (in an output) to avoid duplicates
                        while (j < k && nums[k] == nums[k+1]) {
                            k--;
                        }
                    } else if (nums[i] + nums[j] + nums[k] > sum) {
                        k--;
                    } else {
                        j++;
                    }
                }
            }
        }

        return result;

    }

    private static List<List<Integer>> bruteForceThreeSum(int[] nums, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);


        for(int i = 0; i < nums.length; i++) {
            if(i!=0 && nums[i] == nums[i-1]) {
                continue;
            }

            for(int j = i+1; j < nums.length; j++) {
                if(j!=i+1 && nums[j] == nums[j-1]) {
                    continue;
                }
                for(int k = j+1; k < nums.length; k++) {
                    if(k!=j+1 && nums[k] == nums[k-1]) {
                        continue;
                    }

                    if(nums[i] + nums[j] + nums[k] == sum) {
                        List<Integer> l = new ArrayList<>();
                        l.add(nums[i]);
                        l.add(nums[j]);
                        l.add(nums[k]);
                        result.add(l);
                    }
                }
            }
        }

        return result;
    }
}
