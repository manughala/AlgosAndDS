package com.leetcode.microsoft.sortingandsearching;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

 Find the minimum element.

 The array may contain duplicates.

 Example 1:

 Input: [1,3,5]
 Output: 1
 Example 2:

 Input: [2,2,2,0,1]
 Output: 0
 Note:

 This is a follow up problem to Find Minimum in Rotated Sorted Array.
 Would allow duplicates affect the run-time complexity? How and why?

 * @author Santosh Manughala (SM030146).
 */
public class FindMinInRotatedSortedArrayII {
    public static void main(String args[]) {
        System.out.println("findMin: " + findMin(new int[]{1, 3, 5}));
        System.out.println("findMin: " + findMin(new int[]{2, 2, 2, 0, 1}));
        System.out.println("findMin: " + findMin(new int[]{1, 1}));
        System.out.println("findMin: " + findMin(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));

        System.out.println("findMinRecur: " + findMinRecur(new int[]{3, 4, 5, 1, 2}));
        System.out.println("findMinRecur: " + findMinRecur(new int[]{4, 5, 6, 7, 8, 0, 1, 2}));
        System.out.println("findMinRecur: " + findMinRecur(new int[]{1, 2}));
        System.out.println("findMinRecur: " + findMinRecur(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

    // Time: O(logn)
    // Space: O(1)
    private static int findMin(int[] nums) {
        if(nums == null) {
            return -1;
        }

        if(nums.length < 2) {
            return nums[0];
        }

        int left = 0, right = nums.length - 1;

        if(nums[left] < nums[right]) {
            return nums[0];
        }

        while(left + 1 < right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] == nums[left]) {
                left++;
            } else if (nums[mid] == nums[right]) {
                right--;
            } else if (nums[left] < nums[right]) {
                return nums[left];
            } else if (nums[left] < nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return Math.min(nums[left], nums[right]);
    }

    // Time: O(n)
    // Space: O(n)
    private static int findMinRecur(int[] nums) {
        return performRecur(nums, 0, nums.length - 1);
    }

    private static int performRecur(int[] nums, int left, int right) {
        if(left == right) {
            return nums[left];
        }

        if(nums[left] < nums[right]) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;
        return Math.min(performRecur(nums, left, mid), performRecur(nums, mid + 1, right));
    }

}
