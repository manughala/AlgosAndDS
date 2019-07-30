package com.leetcode.linkedin;

import com.sun.tools.corba.se.idl.InterfaceGen;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

 The same repeated number may be chosen from candidates unlimited number of times.

 Note:

 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 Example 1:

 Input: candidates = [2,3,6,7], target = 7,
 A solution set is:
 [
 [7],
 [2,2,3]
 ]
 Example 2:

 Input: candidates = [2,3,5], target = 8,
 A solution set is:
 [
 [2,2,2,2],
 [2,3,3],
 [3,5]
 ]

 * @author Santosh Manughala (SM030146).
 */
public class CombinationSum {
    public static void main(String args[]) {
        System.out.println("FIRST");
        List<List<Integer>> combinationSumListsI = combinationSum(new int[]{2, 3, 6, 7}, 7);
        for(List<Integer> combinationSumListI : combinationSumListsI) {
            System.out.println(combinationSumListI.toString());
        }

        System.out.println("SECOND");
        List<List<Integer>> combinationSumListsII = combinationSum(new int[]{2, 3, 5}, 8);
        for(List<Integer> combinationSumListII : combinationSumListsII) {
            System.out.println(combinationSumListII.toString());
        }
    }

    private static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }

        findCombinationSum(nums, target, 0, 0, new ArrayList<>(), result);
        return result;
    }

    private static void findCombinationSum(int[] nums, int targetSum, int currentSum, int startIndex, List<Integer> currentCombination, List<List<Integer>> result) {
        if(currentSum == targetSum) {
            result.add(new ArrayList<>(currentCombination));
        } else {
            for(int i = startIndex; i < nums.length; i++) {
                if(currentSum + nums[i] <= targetSum) {
                    currentCombination.add(nums[i]);
                    findCombinationSum(nums, targetSum, currentSum + nums[i], i, currentCombination, result);
                    currentCombination.remove(currentCombination.size() - 1);
                }
            }
        }
    }
 }
