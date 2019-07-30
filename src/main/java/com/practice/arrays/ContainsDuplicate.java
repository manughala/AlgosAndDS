package com.practice.arrays;

import java.util.HashSet;

/**
 * Given an array of integers, find if the array contains any duplicates.
 Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

 Input: [1,2,3,1]
 Output: true


 Input: [1,2,3,4]
 Output: false

 Input: [1,1,1,3,3,4,3,2,4,2]
 Output: true

 * @author Santosh Manughala (SM030146).
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(containsDuplicate(nums));
    }

    private static boolean containsDuplicate(int[] nums) {
        if(nums.length <= 1) {
            return false;
        }

        HashSet<Integer> numsHash = new HashSet<> ();
        for(int i : nums) {
            if(numsHash.contains(i)) {
                return true;
            }
            numsHash.add(i);
        }

        return false;
    }
}
