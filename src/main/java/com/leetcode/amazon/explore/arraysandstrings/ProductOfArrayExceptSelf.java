package com.leetcode.amazon.explore.arraysandstrings;

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
    public static void main(String args[]) {
        int[] output1 = productOfArrayExceptSelfIntermediateCase(new int[] {1, 2, 3 ,4});
        System.out.println("output1: ");
        for(int i: output1) {
            System.out.println(i);
        }

        int[] output2 = productOfArrayExceptSelfBestCase(new int[] {1, 2, 3, 4});
        System.out.println("output2: ");
        for(int i: output2) {
            System.out.println(i);
        }
    }

    // Time: O(n)
    // Space: O(1)
    private static int[] productOfArrayExceptSelfBestCase(int[] nums) {
        int[] output = new int[nums.length];

        int temp = 1;
        for(int i = 0; i < nums.length; i++) {
            output[i] = temp;
            temp *= nums[i];
        }

        temp = 1;
        for(int i = nums.length -1; i >= 0 ; i--) {
            output[i] *= temp;
            temp *= nums[i];
        }

        return output;
    }

    // Time: O(n)
    // Space: O(n)
    private static int[] productOfArrayExceptSelfIntermediateCase(int[] nums) {
        int leftProduct[] = new int[nums.length];
        leftProduct[0] = 1;
        for(int i = 1; i < nums.length; i++) {
            leftProduct[i] = leftProduct[i - 1] * nums[i - 1];
        }

        int rightProduct[] = new int[nums.length];
        rightProduct[nums.length - 1] = 1;
        for(int i = nums.length - 2; i >= 0; i--) {
            rightProduct[i] = rightProduct[i + 1] * nums[i + 1];
        }

        int output[] = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            output[i] = leftProduct[i] * rightProduct[i];
        }

        return output;
    }
}
