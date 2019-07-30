package com.leetcode.microsoft.sortingandsearching;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

 Note:

 The number of elements initialized in nums1 and nums2 are m and n respectively.
 You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 Example:

 Input:
 nums1 = [1,2,3,0,0,0], m = 3
 nums2 = [2,5,6],       n = 3

 Output: [1,2,2,3,5,6]

 * @author Santosh Manughala (SM030146).
 */
public class MergeSortedArray {

    public static void main(String args[]) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6, 7, 8};
        int[] result = mergeSortedArrayBruteForce(nums1, 3, nums2, nums2.length);
        printNums(result);

        nums1 = new int[]{0, 0, 0 , 0};
        nums2 = new int[]{1, 2, 3, 4};
        result = mergeSortedArrayBruteForce(nums1, 0, nums2, nums2.length);
        printNums(result);

        nums1 = new int[]{1, 2, 3, 0, 0, 0, 0, 0};
        nums2 = new int[]{2, 5, 6, 7, 8};
        result = mergeSortedArrayIntermediateCase(nums1, 3, nums2, nums2.length);
        printNums(result);

        nums1 = new int[]{0, 0, 0 , 0};
        nums2 = new int[]{1, 2, 3, 4};
        result = mergeSortedArrayIntermediateCase(nums1, 0, nums2, nums2.length);
        printNums(result);

        nums1 = new int[]{1, 2, 3, 0, 0, 0, 0, 0};
        nums2 = new int[]{2, 5, 6, 7, 8};
        result = mergeSortedArrayBestCase(nums1, 3, nums2, nums2.length);
        printNums(result);

        nums1 = new int[]{0, 0, 0 , 0};
        nums2 = new int[]{1, 2, 3, 4};
        result = mergeSortedArrayBestCase(nums1, 0, nums2, nums2.length);
        printNums(result);
    }

    // Time O(m+n)
    // Space O(1)
    private static int[] mergeSortedArrayBestCase(int[] nums1, int m, int[] nums2, int n) {
        if(nums1 == null || nums2 == null) {
            return null;
        }

        if(nums2.length == 0) {
            return nums1;
        }

        int r = m + n - 1;
        int i = m - 1, j = n - 1;

        while(i >= 0 && j >= 0) {
            nums1[r--] = nums1[i] < nums2[j] ? nums2[j--] : nums1[i--];
        }

        while(i >= 0) {
            nums1[r--] = nums1[i--];
        }

        while(j >= 0) {
            nums1[r--] = nums2[j--];
        }

        return nums1;
    }

    // Time O(m+n)
    // Space O(m)
    private static int[] mergeSortedArrayIntermediateCase(int[] nums1, int m, int[] nums2, int n) {
        if(nums1 == null || nums2 == null) {
            return null;
        }

        if(nums2.length == 0) {
            return nums1;
        }

        int[] nums1Copy = new int[m];
        for(int i = 0; i < m; i++) {
            nums1Copy[i] = nums1[i];
        }

        int i = 0, j = 0, r = 0;

        while(i < m && j < n) {
            nums1[r++] = nums1Copy[i] < nums2[j] ? nums1Copy[i++] : nums2[j++];
        }

        // add remaining items
        while(i < m) {
            nums1[r++] = nums1Copy[i++];
        }

        while(j < n) {
            nums1[r++] = nums2[j++];
        }

        return nums1;
    }


    //IDEA: merge first, sort then
    // Time O(m+n)log(m+n)
    // Space O(1)
    private static int[] mergeSortedArrayBruteForce(int[] nums1, int m, int[] nums2, int n) {
        if(nums1 == null || nums2 == null) {
            return null;
        }

        if(nums2.length == 0) {
            return nums1;
        }

        int count = m;
        for(int i : nums2) {
            nums1[count++] = i;
        }

        Arrays.sort(nums1);
        return nums1;
    }

    private static void printNums(int[] nums) {
        System.out.println("NUMS LENGTH: " + nums.length);
        for(int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
