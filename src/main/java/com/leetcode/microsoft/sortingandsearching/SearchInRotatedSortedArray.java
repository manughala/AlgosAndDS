package com.leetcode.microsoft.sortingandsearching;

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
public class SearchInRotatedSortedArray {
    public static void main(String args[]) {
        System.out.println("find, index: " + find(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println("find, index: " + find(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));

//        System.out.println("findMinRecur: " + findMinRecur(new int[]{3, 4, 5, 1, 2}));
//        System.out.println("findMinRecur: " + findMinRecur(new int[]{4, 5, 6, 7, 8, 0, 1, 2}));
//        System.out.println("findMinRecur: " + findMinRecur(new int[]{1, 2}));
//        System.out.println("findMinRecur: " + findMinRecur(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

    // Time: O(logn)
    // Space: O(1)
    private static int find(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] == target) {
                return mid;
            }

            if(nums[mid] >= nums[left]) {
                //left side is sorted
                if(target >= nums[left] && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                //right side is sorted
                if(target >= nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
