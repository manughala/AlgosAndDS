package com.leetcode.linkedin;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.

 Your algorithm's runtime complexity must be in the order of O(log n).

 Example 1:

 Input: nums = [4,5,6,7,0,1,2], target = 0
 Output: 4
 Example 2:

 Input: nums = [4,5,6,7,0,1,2], target = 3
 Output: -1

 * @author Santosh Manughala (SM030146).
 */
public class SearchInRotatedArray {

    public static void main(String args[]) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int target = 0;

        int indexI = searchIter(nums, target);
        int indexR = searchRecur(nums, target, 0 , nums.length - 1);
        System.out.println("index iterative : " + indexI);
        System.out.println("index recursive : " + indexR);
    }

    // Note: only doing the best case, for all cases refer: com.leetcode.facebook.sortingandsearching
    private static int searchIter(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2; // to not have overflow

            if(nums[mid] == target) {
                return mid;
            }

            if (nums[left] <= nums[mid]) {
                // this means the left array is sorted
                if(target >= nums[left] && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if(target >= nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    private static int searchRecur(int[] nums, int target, int left, int right) {
        if(left > right) {
            return  -1;
        }

        int mid = left + (right - left) / 2;

        if(nums[mid] == target) {
            return mid;
        }

        if(nums[left] <= nums[mid]) {
            if(nums[left] <= target && nums[mid] >= target)
                return searchRecur(nums, target, left, mid - 1);

            return searchRecur(nums, target, mid + 1, right);
        }

        if(nums[mid] <= target && nums[right] >= target)
            return searchRecur(nums, target, mid + 1, right);

        return searchRecur(nums, target, left, mid - 1);
    }
}
