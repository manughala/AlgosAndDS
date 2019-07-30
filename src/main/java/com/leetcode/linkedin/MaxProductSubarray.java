package com.leetcode.linkedin;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

 Example 1:

 Input: [2,3,-2,4]
 Output: 6
 Explanation: [2,3] has the largest product 6.
 Example 2:

 Input: [-2,0,-1]
 Output: 0
 Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

 * @author Santosh Manughala (SM030146).
 */
public class MaxProductSubarray {

    public static void main(String args[]) {
        int maxSum = maxProductBestCaseLinearI(new int[]{-4, -3, -2});
        int maxSum1 = maxProductBestCaseLinearII(new int[]{-4, -3, -2});
        System.out.println("maxSum = " + maxSum);
        System.out.println("maxSum = " + maxSum1);
    }

    // Time O(n)
    // Space O(1)
    private static int maxProductBestCaseLinearI(int[] a) {
        int min = a[0], max = a[0], res = a[0];

        for(int i = 1; i < a.length; i++) {
            if(a[i] < 0) {
                int t = min;
                min = max;
                max = t;
            }

            max = Math.max(a[i], max*a[i]);
            min = Math.min(a[i], min*a[i]);

            res = Math.max(max, res);
        }
        return res;
    }

    // Time O(n)
    // Space O(n) -> because we are storing all the results in intermediate array.
    private static int maxProductBestCaseLinearII(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];

        max[0] = min[0] = nums[0];
        int result = nums[0];

        for(int i=1; i<nums.length; i++){
            if(nums[i]>0){
                max[i]=Math.max(nums[i], max[i-1]*nums[i]);
                min[i]=Math.min(nums[i], min[i-1]*nums[i]);
            }else{
                max[i]=Math.max(nums[i], min[i-1]*nums[i]);
                min[i]=Math.min(nums[i], max[i-1]*nums[i]);
            }

            result = Math.max(result, max[i]);
        }

        return result;
    }
}
