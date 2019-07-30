package com.practice.arrays;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

 The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

 You may assume the integer does not contain any leading zero, except the number 0 itself.

 Example 1:

 Input: [1,2,3]
 Output: [1,2,4]
 Explanation: The array represents the integer 123.
 Example 2:

 Input: [4,3,2,1]
 Output: [4,3,2,2]
 Explanation: The array represents the integer 4321.

 * @author Santosh Manughala (SM030146).
 */
public class PlusOne {

    public static void main(String[] args) {
        int[] nums = new int[]{4,3,2,1};
        plusOne(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    private static void plusOne(int[] nums) {
        nums[nums.length-1] = nums[nums.length-1]+1;
    }



}
