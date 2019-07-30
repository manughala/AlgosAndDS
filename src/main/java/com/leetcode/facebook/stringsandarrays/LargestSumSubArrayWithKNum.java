package com.leetcode.facebook.stringsandarrays;

import java.util.HashMap;

/**
 * Given an array, find the subarray (containing at least k numbers) which has the largest sum.

 Examples:

 Input : arr[] = {-4, -2, 1, -3}
 k = 2
 Output : -1
 The sub array is {-2, 1}

 Input : arr[] = {1, 1, 1, 1, 1, 1}
 k = 2
 Output : 6
 The sub array is {1, 1, 1, 1, 1, 1}


 * @author Santosh Manughala (SM030146).
 */
public class LargestSumSubArrayWithKNum {

    public static void main(String args[]) {
//        int[] nums = new int[] {-4, -2, 1, -3};
//        int k = 2;
        int[] nums = new int[] {1,1,1,1,1,1};
        int k = 2;

//        largestSumSubArrayWithKNumBruteForce(nums, k);
        largestSumSubArrayWithKNumBestCase(nums, k);
    }


    // Time complexity O(n^2)
    private static void largestSumSubArrayWithKNumBruteForce(int[] nums, int k) {
        int maxSum = nums[0], start = 0, end = 0;

        for(int i = 0; i < nums.length; i++) {
            int count = 0;
            for(int j = i; j < nums.length; j++) {
                count += nums[j];

                if(count > maxSum && i != j) {
                    maxSum = count;
                    start = i;
                    end = j;
                }
            }
        }

        if(end - start + 1 < k) {
            System.out.println("not found");
            return;
        }

        System.out.println(" found max length between: " + start + " and " + end + " with sum: " + maxSum);
    }

    //-4, -2, 1, -3
    private static void largestSumSubArrayWithKNumBestCase(int[] nums, int k) {
        // maxSum[i] is going to store maximum sum
        // till index i such that nums[i] is part of the
        // sum.
        int maxSum[] = new int [nums.length];
        maxSum[0] = nums[0];

        // We use Kadane's algorithm to fill maxSum[]
        // Below code is taken from method 3 of
        // https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
        int curr_max = nums[0];
        for (int i = 1; i < nums.length; i++)
        {
            curr_max = Math.max(nums[i], curr_max+nums[i]);
            maxSum[i] = curr_max;
        }
        // -4, -4, -3, -3

        // Sum of first k elements
        int sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i];
        // sum = -6

        // Use the concept of sliding window
        int result = sum; // -6
        for (int i = k; i < nums.length; i++)
        {
            // Compute sum of k elements ending
            // with a[i].
            sum = sum + nums[i] - nums[i-k]; //-2

            // Update result if required
            result = Math.max(result, sum); // -1

            // Include maximum sum till [i-k] also
            // if it increases overall max.
            result = Math.max(result, sum + maxSum[i-k]); // -1,
            System.out.println("i = " + i + " k = " + k);
        }
        System.out.println(result);
        return;
    }
}
