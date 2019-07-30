package com.leetcode.linkedin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
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
//        int[] nums = new int[] {1, 2, 3};
        int[] nums = new int[] {3, 1, 2};


        List<List<Integer>> permutations = getPermutationsRecursive(nums);

        for(List<Integer> permutation : permutations) {
            System.out.println(permutation);
        }
    }

    // Time: O(n*n!)
    //Space: O(n!)
    // NOTE: Only best case here, refer: com.leetcode.facebook.backtracking
    private static List<List<Integer>> getPermutationsRecursive(int[] nums) {
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

        Set<Integer> trackDuplicates = new HashSet<>();
        for(int i = index; i < nums.length; i++) {
            if(trackDuplicates.contains(nums[i])) {
                continue;
            }

            trackDuplicates.add(nums[i]);
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
