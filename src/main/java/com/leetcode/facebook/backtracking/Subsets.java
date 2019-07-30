package com.leetcode.facebook.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).

 Note: The solution set must not contain duplicate subsets.

 Example:

 Input: nums = [1,2,3]
 Output:
 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]

 * @author Santosh Manughala (SM030146).
 */
public class Subsets {


    public static void main(String args[]) {
        int[] nums = new int[]{1, 2, 3};
        int[] nums1 = new int[]{1, 2, 3, 4};
        int[] nums2 = new int[]{1, 2, 3, 4, 5};
        int[] nums3 = new int[]{3, 1, 2};


        // DO NOT TEST THIS FOR getSubsetsBestCaseI as we do not anticipate duplicate numbers in the input
        int[] nums4 = new int[]{1, 1, 2, 2};

        // NOTE: Given input clearly mentioned that we will only get distinct nums, so we do not account for duplicates in the given array.
//        List<List<Integer>> result = getSubsetsBestCaseI(nums3);
//        List<List<Integer>> result = getSubsetsIterative(nums3);

        // NOTE: What if we can have duplicates?
        List<List<Integer>> result = getSubsetsRecursiveWithDuplicates(nums4);

        for(List<Integer> list : result) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> getSubsetsBestCaseI(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }

        // You can sort the given array so we get the numbers in certain order
        Arrays.sort(nums);

        getSubsetsRecurringI(result, new ArrayList<>(), nums, 0);

        return result;
    }

    private static void getSubsetsRecurringI(List<List<Integer>> result, List<Integer> currentSubset, int[] nums, int index) {
        result.add(new ArrayList<>(currentSubset));

        for(int i = index; i < nums.length; i++) {
            currentSubset.add(nums[i]);
            getSubsetsRecurringI(result, currentSubset, nums, i + 1);
            currentSubset.remove(currentSubset.size() - 1);
        }
    }


    private static List<List<Integer>> getSubsetsRecursiveWithDuplicates (int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();

        if(nums == null || nums.length == 0) {
            return subsets;
        }

        // we can sort to have them in increasing order
        Arrays.sort(nums);

        getSubsetsRecurringWithDuplicates(nums, subsets, 0, new ArrayList<>());

        return subsets;
    }

    private static void getSubsetsRecurringWithDuplicates (int[] nums, List<List<Integer>> subsets, int index, List<Integer> currentSubset) {
        if(index == nums.length) {
            subsets.add(new ArrayList<>(currentSubset));
            return;
        }

        currentSubset.add(nums[index]);
        getSubsetsRecurringWithDuplicates(nums, subsets, index + 1, currentSubset);
        currentSubset.remove(currentSubset.size() - 1);

        while (index < nums.length - 1 && nums[index] == nums[index+1]) {
            index++;
        }

        getSubsetsRecurringWithDuplicates(nums, subsets, index + 1, currentSubset);

    }


    private static List<List<Integer>> getSubsetsIterative (int[] nums) {
        if (nums == null)
            return null;

        Arrays.sort(nums);

        List<List<Integer>> subsets = new ArrayList<>();

        for(int i = 0; i < nums.length; i++) {
            List<List<Integer>> tempSubsets = new ArrayList<>();

            for(List<Integer> subset : subsets) {
                tempSubsets.add(new ArrayList<>(subset));
            }

            for(List<Integer> tempSubset : tempSubsets) {
                tempSubset.add(nums[i]);
            }

            List<Integer> newOne = new ArrayList<>();
            newOne.add(nums[i]);
            tempSubsets.add(newOne);

            subsets.addAll(tempSubsets);
        }

        subsets.add(new ArrayList<>());

        return subsets;
    }
}
