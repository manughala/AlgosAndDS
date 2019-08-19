package com.leetcode.microsoft.dynamicprogramming;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

 Example:

 Input: [-2,1,-3,4,-1,2,1,-5,4],
 Output: 6
 Explanation: [4,-1,2,1] has the largest sum = 6.
 Follow up:

 If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

 * @author Santosh Manughala (SM030146).
 */
public class MaximumSubarray {
    public static void main(String args[]) {
        System.out.println("Expected: 6, actual: " + getMaxSubArrayLength(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println("Expected: 6, actual: " + getMaxSubArrayLengthDivideAndConquer(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    // Time O(n)
    // Space O(1)
    // simple idea: find max sum till now, and current sum will be max of current item or current item + previous
    // explanation: kadanes algo: https://www.youtube.com/watch?v=86CQq3pKSUw
    private static int getMaxSubArrayLength(int[] nums) {
        int maxSum = nums[0], localSum = nums[0];

        for(int i = 1; i < nums.length; i++) {
            localSum = Math.max(nums[i] + localSum, nums[i]);

            if(localSum > maxSum) {
                maxSum = localSum;
            }
        }

        return maxSum;
    }

    private static int getMaxSubArrayLengthDivideAndConquer(int[] nums) {
        return getMaxSubArrayRecur(nums, 0, nums.length - 1);
    }

    private static int getMaxSubArrayRecur(int[] nums, int start, int end) {
        if(start == end) {
            return nums[start];
        }

        int mid = start + (end - start) / 2;

        return Math.max(Math.max(getMaxSubArrayRecur(nums, start, mid), getMaxSubArrayRecur(nums, mid + 1, end)), getSubArrayWithMid(nums, start, end, mid));
    }

    private static int getSubArrayWithMid(int[] nums, int start, int end, int mid) {
        int leftMax = Integer.MIN_VALUE, leftCount = 0;

        for(int i = mid; i >= start; i--) {
            leftCount += nums[i];

            if(leftCount > leftMax) {
                leftMax = leftCount;
            }
        }

        int rightMax = Integer.MIN_VALUE, rightCount = 0;

        for(int i = mid + 1; i <= end; i++) {
            rightCount += nums[i];

            if(rightCount > rightMax) {
                rightMax = rightCount;
            }
        }

        return rightMax + leftMax;
    }
}
