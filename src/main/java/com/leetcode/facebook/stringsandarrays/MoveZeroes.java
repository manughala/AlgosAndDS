package com.leetcode.facebook.stringsandarrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

 Example:

 Input: [0,1,0,3,12]
 Output: [1,3,12,0,0]

 Input: [0, 9, 1, 8, 7, 0 , 12, 2]
 Output: [9, 1, 8, 7, 12, 2, 0, 0]
 Note:

 You must do this in-place without making a copy of the array.
 Minimize the total number of operations.

 * @author Santosh Manughala (SM030146).
 */
public class MoveZeroes {
    public static void moveZeroes(int[] nums) {
        if(nums.length <= 0) {
            System.out.println("No elements in the array, cannot perform operation");
            return;
        }

        if(nums.length == 1) {
            System.out.println("Array after move: " + nums);
            return;
        }

        int count = 0;
        for(int num : nums) {
            if(num != 0) {
                nums[count++] = num;
            }
        }

        while(count < nums.length) {
            nums[count++] = 0;
        }

        System.out.println("output: ");
        for(int num: nums) {
            System.out.println(num);
        }
    }

    private static int valueIndex(int[] x, int value){
        //enter your code here
        int left = 0, right = x.length -1;

        while(left < right) {
            int mid = (left + right) / 2;
            if(x[mid] == value) {
                return mid;
            } else if (x[mid] > value) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return -1;

    }




    public static void main(String args[]) {
        System.out.println(valueIndex(new int[]{0, 9, 1, 8, 7, 0 , 12, 2}, 12));

        // Time complexity: O(n)
        // Space complexity: O(1)
    }



}
