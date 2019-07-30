package com.practice.arrays;

/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.

 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

 Input: [2,2,1]
 Output: 1

 Input: [4,1,2,1,2]
 Output: 4
 * @author Santosh Manughala (SM030146).
 */
public class SingleNumber {

    public static void main(String args[]) {
        System.out.println("find single number");
        int singleNum = singleNumber(new int[]{1,2,3,1,2});
        System.out.println("single number = " + singleNum);

    }

    private static int singleNumber(int[] nums) {
        int res = nums[0];
        for(int i = 1; i<nums.length ; i++) {
            res = res ^ nums[i];
        }

        return res;
    }
}
