package com.leetcode.facebook.sortingandsearching;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

 You are given a target value to search. If found in the array return true, otherwise return false.

 Example 1:

 Input: nums = [2,5,6,0,0,1,2], target = 0
 Output: true
 Example 2:

 Input: nums = [2,5,6,0,0,1,2], target = 3
 Output: false


 NOTE: THIS IS A FOLLOW UP OF SearchInRotatedSortedArray, and the diff is that, the other class will not have duplicates, this can have
 Follow up:

 This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 Would this affect the run-time complexity? How and why?

 * @author Santosh Manughala (SM030146).
 */
public class SearchInRotatedSortedArrayII {

    public static void main(String args[]) {
        int[] nums = new int[]{1,3,1,1,1};
        int target = 3;

        boolean result = searchTargetInArrayBestCaseIter(nums, target);
        System.out.println(result);
    }

    // time The worst case complexity will become O(n)
    // space O(1)
    private static boolean searchTargetInArrayBestCaseIter (int[] nums, int k) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int pivot = left + (right - left) / 2;

            if(nums[pivot] == k) {
                return true;
            }

            if(nums[left] < nums[pivot]) {
                if(nums[left] <= k && nums[pivot] > k) {
                    right = pivot - 1;
                } else {
                    left = pivot + 1;
                }
            } else if(nums[left] > nums[pivot]) {
                if(nums[right] >= k && nums[pivot] < k) {
                    left = pivot + 1;
                } else {
                    right = pivot - 1;
                }
            } else {
                left++;
            }
        }

        return false;
    }
}
