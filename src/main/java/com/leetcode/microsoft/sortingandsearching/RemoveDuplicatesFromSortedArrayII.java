package com.leetcode.microsoft.sortingandsearching;

/**
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

 Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

 Example 1:

 Given nums = [1,1,1,2,2,3],

 Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

 It doesn't matter what you leave beyond the returned length.
 Example 2:

 Given nums = [0,0,1,1,1,1,2,3,3],

 Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

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
public class RemoveDuplicatesFromSortedArrayII {

    public static void main(String args[]) {
        int[] nums1 = new int[]{1, 1, 1, 2, 2, 3};
        int[] nums2 = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};

        int length1 = removeDuplicates(nums1);
        printNums(nums1, length1);

        int length2 = removeDuplicates(nums2);
        printNums(nums2, length2);

        int[] nums3 = new int[]{0, 1, 1, 1, 1, 2, 3, 3};
        int length6 = removeDuplicates(nums3);
        printNums(nums3, length6);

        nums1 = new int[]{1, 1, 1, 2, 2, 3};
        nums2 = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
        nums3 = new int[]{0, 1, 1, 1, 1, 2, 3, 3};

        int length3 = removeDuplicatesLeetCodeWay(nums1);
        printNums(nums1, length3);

        int length4 = removeDuplicatesLeetCodeWay(nums2);
        printNums(nums2, length4);

        int length5 = removeDuplicatesLeetCodeWay(nums3);
        printNums(nums3, length5);
    }

    // Time: O(n)
    // Space: O(1)
    private static int removeDuplicatesLeetCodeWay(int[] arr) {
        if (arr.length < 3) {
            return arr.length;
        }

        int last = 2;
        int next = 2;

        while (next < arr.length) {
            if (arr[last - 2] != arr[next]) {
                arr[last] = arr[next];
                last++;
            }
            next++;
        }
        return last;
    }

    // Time: O(n)
    // Space: O(1)
    private static int removeDuplicates(int[] nums) {
        if(nums == null) {
            return 0;
        }

        if(nums.length < 3) {
            return nums.length;
        }

        int index = 0, count = 1;

        for(int i = 1 ; i < nums.length; i++) {
            if(nums[index] != nums[i]) {
                count = 1;
                index++;
                nums[index] = nums[i];
            } else {
                count++;
                if(count > 2) {
                    continue;
                }
                index++;
                nums[index] = nums[i];
            }
        }

        return index + 1;
    }


    private static void printNums(int[] nums, int length) {
        System.out.println("NUMS LENGTH: " + length);
        for(int i = 0; i < length; i++) {
            System.out.println(nums[i]);
        }
    }
}
