package com.leetcode.facebook.sortingandsearching;

import java.util.Comparator;
import java.util.PriorityQueue;

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
//        int[] nums1 = new int[]{1, 2, 3, 0 ,0, 0};
//        int[] nums1 = new int[]{-1,0,0,3,3,3,0,0,0};
        int[] nums1 = new int[]{-1,3,0,0,0,0,0};
//        int[] nums2 = new int[]{2, 5, 6};
//        int[] nums2 = new int[]{1, 2, 2};
        int[] nums2 = new int[]{0, 0, 1, 2, 3};

//       nums1 = merge2SortedArraysBestCaseI(nums1, nums2, 2, 5);
//        merge2SortedArraysBestCaseII(nums1, nums2, 2, 5);
//        merge2SortedArraysBestCaseIII(nums1, nums2, 2, 5); // -> less space compared to I and II
        merge2SortedArraysBestCaseULTIMATE(nums1, nums2, 2, 5); // -> BEST SPACE AND SAME TIME

        for(int num : nums1) {
            System.out.println(num);
        }
    }

    // Time: O(m+n) space O(m+n)
    private static void merge2SortedArraysBestCaseII(int[] nums1, int[] nums2, int m, int n) {
        if(nums1 == null || nums2 == null) {
            return;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(nums1.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i1 - i2;
            }
        });

        for(int i = 0; i < m; i++) {
            queue.add(nums1[i]);
        }

        for(int i = 0; i < n; i++) {
            queue.add(nums2[i]);
        }

        for(int i = 0; i < m+n; i++) {
            nums1[i] = queue.poll();
        }
    }

    // Time: O(m+n) space O(m+n)
    private static int[] merge2SortedArraysBestCaseI(int[] nums1, int[] nums2, int m, int n) {
        int[] output = new int[m+n];
        int i = 0, j = 0, k = 0;

        while(i < m && j < n) {
            if(nums1[i] < nums2[j]) {
                output[k++] = nums1[i++];
            } else {
                output[k++] = nums2[j++];
            }
        }

        while(i < m) {
            output[k++] = nums1[i++];
        }

        while(j < n) {
            output[k++] = nums2[j++];
        }

        return output;
    }

    // Time: O(m+n) space O(m) - advantage less space
    private static void merge2SortedArraysBestCaseIII(int[] nums1, int[] nums2, int m, int n) {
        int[] nums1Copy = new int[m];
        System.arraycopy(nums1, 0, nums1Copy, 0, m);

        int i = 0, j = 0, k = 0;

        while(i < m && j < n) {
            if(nums1Copy[i] < nums2[j]) {
                nums1[k++] = nums1[i++];
            } else {
                nums1[k++] = nums2[j++];
            }
        }

        while(i < m) {
            nums1[k++] = nums1Copy[i++];
        }

        while(j < n) {
            nums1[k++] = nums2[j++];
        }
    }

    private static void merge2SortedArraysBestCaseULTIMATE(int[] nums1, int nums2[], int m, int n) {
        int i = m-1, j = n-1, k = m+n-1;

        while(i >= 0 && j >= 0) {
            nums1[k--] = nums1[i] < nums2[j] ? nums2[j--] : nums1[i--];
        }

        // can have a case where there are no elements in nums1 but only in nums2
        while(j>=0) {
            nums1[k--] = nums2[j--];
        }
    }

}
