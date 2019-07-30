package com.leetcode.microsoft.sortingandsearching;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

 Find the minimum element.

 You may assume no duplicate exists in the array.

 Example 1:

 Input: [3,4,5,1,2]
 Output: 1
 Example 2:

 Input: [4,5,6,7,0,1,2]
 Output: 0

 * @author Santosh Manughala (SM030146).
 */
public class FindMinInRotatedSortedArray {
    public static void main(String args[]) {
        System.out.println("findMin: " + findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println("findMin: " + findMin(new int[]{4, 5, 6, 7, 8, 0, 1, 2}));
        System.out.println("findMin: " + findMin(new int[]{2, 1}));
        System.out.println("findMin: " + findMin(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));

        System.out.println("findMinLeetCodeWay: " + findMinLeetCodeWay(new int[]{3, 4, 5, 1, 2}));
        System.out.println("findMinLeetCodeWay: " + findMinLeetCodeWay(new int[]{4, 5, 6, 7, 8, 0, 1, 2}));
        System.out.println("findMinLeetCodeWay: " + findMinLeetCodeWay(new int[]{2, 1}));
        System.out.println("findMinLeetCodeWay: " + findMinLeetCodeWay(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

    // Time: O(logn)
    // Space: O(1)
    private static int findMinLeetCodeWay(int[] nums) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        if(nums.length < 2) {
            return nums[0];
        }

        int left = 0, right = nums.length - 1;

        if(nums[left] < nums[right]) {
            return nums[0];
        }

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }

            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }

            if(nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    // Time: O(logn)
    // Space: O(1)
    private static int findMin(int[] nums) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        if(nums.length < 2) {
            return nums[0];
        }

        int left = 0, right = nums.length - 1;

        if(nums[left] < nums[right]) {
            return nums[0];
        }

        while(left < right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            } else if (nums[mid] >= nums[0]) {
                // because now you know that the left side is sorted,
                // you also know that mid > mid+1 value -> meaning you are looking for rotating index on the right
                left = mid + 1;
            } else  {
                right = mid;
            }
        }

        return -1;
    }
}
