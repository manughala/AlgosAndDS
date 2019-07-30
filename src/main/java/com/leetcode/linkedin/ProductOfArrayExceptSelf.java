package com.leetcode.linkedin;

import java.util.Arrays;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

 Example:

 Input:  [1,2,3,4]
 Output: [24,12,8,6]
 Note: Please solve it without division and in O(n).

 Follow up:
 Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

 * @author Santosh Manughala (SM030146).
 */
public class ProductOfArrayExceptSelf {

    // Ideas:
    // 1. brute force: 2 for loops -> inner for loop will run with equals check and update output array -> O(n^2)
    // 2. find the overall product, and divide by itself for each number to populate the output array -> O(n) but cannot use division : productOfArrayExceptSelfI
    // 3.

    public static void main(String args[]) {
        // Do not use per note in the question to not use division
//        int[] output = productOfArrayExceptSelfI(new int[] {1, 2, 3 ,4});
//        int[] output = productOfArrayExceptSelfII(new int[] {1, 2, 3, 4});
        int[] output = productOfArrayExceptSelfIII(new int[] {1, 2, 3, 4});

        for(int i : output) {
            System.out.println(i);
        }
    }

    // Time O(n)
    //SpaceO(1)
    private static int[] productOfArrayExceptSelfIII(int[] nums) {
        int[] output = new int[nums.length];

        Arrays.fill(output, 1);

        int temp = 1;
        for(int i = 0; i < nums.length; i++) {
            output[i] = temp;
            temp *= nums[i];
        }

        temp = 1;
        for(int i = nums.length - 1; i >= 0; i--) {
            output[i] *= temp;
            temp *= nums[i];
        }

        return output;
    }

    // Time O(n)
    //SpaceO(n) -> optimized above
    private static int[] productOfArrayExceptSelfII(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        int[] output = new int[nums.length];

        left[0] = 1;

        for(int i = 1; i < nums.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        right[nums.length - 1] = 1;
        for(int i = nums.length - 2; i >= 0; i--) {
            right[i] = right[i+1] * nums[i+1];
        }

        for(int i = 0; i < nums.length; i++) {
            output[i] = left[i] * right[i];
        }

        return output;
    }



    //Time O(n)
    //Space O(1)
    // wont work if there are 0s
    private static int[] productOfArrayExceptSelfI(int[] nums) {
        int[] output = new int[nums.length];

        int overAllProduct = 1;
        for(int i : nums) {
            overAllProduct *= i;
        }

        for (int i = 0; i < nums.length; i++) {
            output[i] = overAllProduct / nums[i];
        }

        return output;
    }
}
