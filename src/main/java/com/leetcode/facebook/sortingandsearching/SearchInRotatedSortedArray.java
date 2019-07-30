package com.leetcode.facebook.sortingandsearching;

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
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target = 3;

        // IDEAS:
        // We can do a for loop search for the string and return when found -> O(n) -- worst
        // int result = searchTargetInArrayBestCaseI(nums, target); // NOT ACCEPTED on leet code - time limit exceeded
//        int result = searchTargetInArrayBestCaseIIRecur(nums, 0, nums.length - 1, target);
        int result = searchTargetInArrayBestCaseIIIter(nums, target);
        System.out.println(result);
    }

    // time O(logn)
    // space O(1)
    // NOTE: ACCEPTED BY LEETCODE, the searchTargetInArrayBestCaseI is not with time limit exceeded exception
    private static int searchTargetInArrayBestCaseIIRecur(int[] arr, int l, int h, int key) {
        if (l > h)
            return -1;

        int mid = (l+h)/2;
        if (arr[mid] == key)
            return mid;

        /* If arr[l...mid] is sorted */
        if (arr[l] <= arr[mid])
        {
            /* As this subarray is sorted, we
               can quickly check if key lies in
               half or other half */
            if (key >= arr[l] && key <= arr[mid])
                return searchTargetInArrayBestCaseIIRecur(arr, l, mid-1, key);

            return searchTargetInArrayBestCaseIIRecur(arr, mid+1, h, key);
        }

        /* If arr[l..mid] is not sorted,
           then arr[mid... r] must be sorted*/
        if (key >= arr[mid] && key <= arr[h])
            return searchTargetInArrayBestCaseIIRecur(arr, mid+1, h, key);

        return searchTargetInArrayBestCaseIIRecur(arr, l, mid-1, key);
    }



    // Time: O(logn)
    // space: O(1)
    private static int searchTargetInArrayBestCaseIIIter (int[] nums, int k) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int pivot = left + (right - left) / 2;

            if(nums[pivot] == k) {
                return pivot;
            }

            if(nums[left] <= nums[pivot]) {
                if(nums[left] <= k && nums[pivot] >= k) {
                    right = pivot - 1;
                } else {
                    left = pivot + 1;
                }
            } else {
                if(nums[right] >= k && nums[pivot] <= k) {
                    left = pivot + 1;
                } else {
                    right = pivot - 1;
                }
            }
        }

        return -1;
    }

    // Time: O(logn)
    // space: O(1)
    private static int searchTargetInArrayBestCaseI(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        int rotatingIndex = getRotatingIndex(nums);

        if(nums[rotatingIndex] == k) {
            return rotatingIndex;
        }

        if(rotatingIndex == 0) {
            return search(nums, k, 0, nums.length - 1);
        }

        if(k > nums[0]) {
            return search(nums, k, 0, rotatingIndex);
        }

        return search(nums, k, rotatingIndex, nums.length - 1);
    }

    private static int search(int[] nums, int k, int left, int right) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        while (left < right) {
            int mid = left +  (right - left) / 2;

            if(nums[mid] == k) {
                return mid;
            } else if (nums[mid] > k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    private static int getRotatingIndex(int[] nums) {
        int rotatingIndex = 0, left = 0, right = nums.length - 1;

        if(nums == null || nums.length == 0) {
            return rotatingIndex;
        }

        while (left < right) {
            int pivot = left + (right - left) / 2;

            if(nums[pivot] > nums[pivot + 1]) {
                return pivot + 1;
            } else {
                if(nums[pivot] >= nums[left]) {
                    left = pivot + 1;
                } else {
                    right = pivot - 1;
                }
            }
        }

        return rotatingIndex;
    }
}
