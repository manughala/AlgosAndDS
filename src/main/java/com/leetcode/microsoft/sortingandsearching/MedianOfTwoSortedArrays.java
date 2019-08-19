package com.leetcode.microsoft.sortingandsearching;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.

 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

 You may assume nums1 and nums2 cannot be both empty.

 Example 1:

 nums1 = [1, 3]
 nums2 = [2]

 The median is 2.0
 Example 2:

 nums1 = [1, 2]
 nums2 = [3, 4]

 The median is (2 + 3)/2 = 2.5

 * @author Santosh Manughala (SM030146).
 */
public class MedianOfTwoSortedArrays {

    public static void main(String args[]) {
        System.out.println("getMedianBruteForce : " + getMedianBruteForce(new int[]{1, 3}, new int[]{2}));
        System.out.println("getMedianBruteForce : " + getMedianBruteForce(new int[]{1, 2}, new int[]{3, 4}));

        System.out.println("findMedianBestCase : " + findMedianBestCase(new int[]{1, 3}, new int[]{2}));
        System.out.println("findMedianBestCase : " + findMedianBestCase(new int[]{1, 2}, new int[]{3, 4}));
    }

//    Time O(log(min(m,n)))
//    Space complexity: O(1)O(1).
    public static double findMedianBestCase(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) {
            return findMedianBestCase(nums2, nums1);
        }

        int x = nums1.length;
        int y = nums2.length;

        int low = 0, high = x;

        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            int maxLeftX = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = partitionX == x ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = partitionY == 0 ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = partitionY == y ? Integer.MAX_VALUE : nums2[partitionY];

            if(maxLeftX <= minRightY && minRightX >= maxLeftY) {
                if((x + y) % 2 == 0) {
                    return (double) (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                }
                return (double) Math.max(maxLeftX, maxLeftY);
            } else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("error");
    }


    // Does not work for all cases, own idea -> might work for equal lenght sorted arrays
//    private static float getMedian(int[] nums1, int[] nums2) {
//        int length = nums1.length + nums2.length, medianIdx = (length - 1) / 2;
//
//        if(length % 2 == 0) {
//            medianIdx = length / 2;
//        }
//
//        int i = 0, j = 0, result = 0, prev = 0;
//
//        while(medianIdx >= 0) {
//            if(i < nums1.length && j < nums2.length && nums1[i] <= nums2[j]) {
//                prev = result;
//                result = nums1[i++];
//            } else {
//                prev = result;
//                result = nums2[j++];
//            }
//            medianIdx--;
//        }
//
//        if(length % 2 == 0) {
//            // even length
//            return (float) (result + prev) / 2;
//        }
//        // odd length
//        return result;
//
//    }

    // NOTE: NOT THE PERFECT ANSWER as the question says time should O(log m+n) -> this is just an initial idea,
    // with O(n) time complexity.
    private static double getMedianBruteForce(int[] nums1, int[] nums2) {
        // Since we may assume nums1 and nums2 are not empty, we can ignore the length and null check.
        int[] merged = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;

        while(i < nums1.length && j < nums2.length) {
            if(nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }

        while(i < nums1.length) {
            merged[k++] = nums1[i++];
        }

        while(j < nums2.length) {
            merged[k++] = nums2[j++];
        }

        int l1 = nums1.length, l2 = nums2.length;

        if((l1+l2) % 2 != 0) {
            // odd length ( (n-1)/2 will be median )
            return (double) merged[((l1 + l2) - 1) / 2];
        } else {
            // even length
            return (double) (merged[((l1 + l2) - 1) / 2] + merged[(l1 + l2) / 2]) / 2;
        }
    }
}
