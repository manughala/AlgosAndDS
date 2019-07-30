package com.leetcode.microsoft.sortingandsearching;

/**
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

 Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

 Example 1:

 Given nums = [1,1,2],

 Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

 It doesn't matter what you leave beyond the returned length.
 Example 2:

 Given nums = [0,0,1,1,1,2,2,3,3,4],

 Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

 It doesn't matter what values are set beyond the returned length.
 Clarification:

 Confused why the returned value is an integer but your answer is an array?

 Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

 Internally you can think of this:

 // nums is passed in by reference. (i.e., without making a copy)
 int len = removeDuplicates(nums);

 // any modification to nums in your function would be known by the caller.
 // using the length returned by your function, it prints the first len elements.
 for (int i = 0; i < len; i++) {
 print(nums[i]);
 }

 * @author Santosh Manughala (SM030146).
 */
public class RemoveDuplicatesFromSortedArray {
    public static void main(String args[]) {
        int[] nums1 = new int[]{1, 1, 2};
        int[] nums2 = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        int length1 = removeDuplicates(nums1);
        printNums(nums1, length1);

        int length2 = removeDuplicates(nums2);
        printNums(nums2, length2);

        nums1 = new int[]{1, 1, 2};
        nums2 = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        int length3 = removeDuplicatesLeetCodeWay(nums1);
        printNums(nums1, length3);

        int length4 = removeDuplicatesLeetCodeWay(nums2);
        printNums(nums2, length4);
    }

    // Time: O(n)
    // Space: O(1)
    private static int removeDuplicatesLeetCodeWay(int[] nums) {
        if(nums == null || nums.length < 1 ) {
            return 0;
        }

        int index = 0;

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[index]) {
                index++;
                nums[index] = nums[i];
            }
        }
        return index + 1;
    }

    // Time: O(n)
    // Space: O(1)
    private static int removeDuplicates(int[] nums) {
        if(nums == null || nums.length < 1 ) {
            return 0;
        }

        int prevVal = nums[0], index = 1;

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != prevVal) {
                nums[index] = nums[i];
                prevVal = nums[index];
                index++;
            }
        }
        return index;
    }

    private static void printNums(int[] nums, int length) {
        System.out.println("NUMS LENGTH: " + length);
        for(int i = 0; i < length; i++) {
            System.out.println(nums[i]);
        }
    }
}
