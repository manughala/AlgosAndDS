package com.leetcode.linkedin;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

 Example:

 Input: [-2,1,-3,4,-1,2,1,-5,4],
 Output: 6
 Explanation: [4,-1,2,1] has the largest sum = 6.
 Follow up:

 If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

 NOTE: THIS is different from max subarray length, as we are give a sum target, and asked to find a max subarray for that sum
 * @author Santosh Manughala (SM030146).
 */
public class MaxSubArray {

    public static void main(String args[]) {
//        int maxSum = maxSubArrayBestCaseLinear(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        int maxSum = maxSubArrayAverageCaseDivideAndConquer(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println("maxSum = " + maxSum);
    }

    // Time O(nlogn)
    // Space O(n)
    // NOTE: O(n) kadanes algorithm is the best one
    private static int maxSubArrayAverageCaseDivideAndConquer(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        return maxSubArray(nums, 0, nums.length - 1);
    }

    private static int maxSubArray(int[] nums, int left, int right) {
        if(left == right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;

        return Math.max(Math.max(maxSubArray(nums, left, mid), maxSubArray(nums, mid + 1, right)), sumCrossingMid(nums, left, mid, right));
    }

    private static int sumCrossingMid(int[] nums, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE, currentLeftCount = 0;
        for(int i = mid; i >= left; i--) {
            currentLeftCount += nums[i];
            if(currentLeftCount > leftSum) {
                leftSum = currentLeftCount;
            }
        }

        int rightSum = Integer.MIN_VALUE, currentRightCount = 0;
        for(int i = mid + 1; i <= right; i++) {
            currentRightCount += nums[i];
            if(currentRightCount > rightSum) {
                rightSum = currentRightCount;
            }
        }

        return leftSum + rightSum;
    }

    // Time O(n)
    // Space O(1)
    // simple idea: find max sum till now, and current sum will be max of current item or current item + previous
    // explanation: kadanes algo: https://www.youtube.com/watch?v=86CQq3pKSUw
    private static int maxSubArrayBestCaseLinear(int[] nums) {
        int maxGlobal, currentCount;
        maxGlobal = currentCount = nums[0];

        for(int i = 1; i < nums.length; i++) {
            currentCount = Math.max(nums[i], currentCount + nums[i]);

            if(maxGlobal < currentCount) {
                maxGlobal = currentCount;
            }
        }

        return maxGlobal;
    }
}
