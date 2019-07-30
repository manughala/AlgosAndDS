package com.leetcode.facebook.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * NOTE THIS IS SAME AS PERMUTATIONS -> just that this can have duplicates vs distinct
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.

 Example:

 Input: [1,1,2]
 Output:
 [
 [1,1,2],
 [1,2,1],
 [2,1,1]
 ]


 * @author Santosh Manughala (SM030146).
 */
public class PermutationsII {

    public static void main(String args[]) {
        int[] nums = new int[] {1, 2, 1};

        Arrays.sort(nums);

        // Sorting will help here, as we want to make sure 2 same numbers are next to each other.
        List<List<Integer>> permutations = getPermutationsRecursiveI(nums);

        for(List<Integer> permutation : permutations) {
            System.out.println(permutation);
        }
    }

    private static List<List<Integer>> getPermutationsRecursiveI(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();

        if(nums == null || nums.length == 0) {
            return permutations;
        }
        performPermutationsRecursion(nums, permutations, 0);
        return permutations;
    }

    private static void performPermutationsRecursion(int[] nums, List<List<Integer>> permutations, int index) {
        if(index == nums.length) {
            List<Integer> list = new ArrayList<>();
            for(int num : nums) {
                list.add(num);
            }
            permutations.add(list);
            return;
        }

        HashSet<Integer> set = new HashSet<>();

        for(int i = index; i < nums.length; i++) {
            if(set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            swap(nums, index, i);
            performPermutationsRecursion(nums, permutations, index + 1);
            swap(nums, i, index);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
