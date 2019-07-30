package com.leetcode.linkedin;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

 Your algorithm's runtime complexity must be in the order of O(log n).

 If the target is not found in the array, return [-1, -1].

 Example 1:

 Input: nums = [5,7,7,8,8,10], target = 8
 Output: [3,4]
 Example 2:

 Input: nums = [5,7,7,8,8,10], target = 6
 Output: [-1,-1]

 * @author Santosh Manughala (SM030146).
 */
public class FirstAndLastPositionInArray {

    public static void main(String args[]) {
        int[] nums = new int[] {5,7,7,8,8,10};
        int target = 10;

        int[] resultBruteForce = searchRangeBruteForce(nums, target);

        System.out.println("brute force result: ");
        for(int i : resultBruteForce) {
            System.out.println(i);
        }

        System.out.println("done");

        int[] resultBestCase = searchRangeBestCase(nums, target);
        System.out.println("best case result: ");
        for(int i : resultBestCase) {
            System.out.println(i);
        }
    }

    // Time complexity: O(n)
    // Space: O(1)
    private static int[] searchRangeBruteForce(int[] nums, int target) {
        int[] result = new int[] {-1, -1};

        if(nums == null || nums.length == 0) {
            return result;
        }

        for(int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                result[0] = i;
                break;
            }
        }

        if(result[0] == -1) {
            return result;
        }

        for(int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] == target) {
                result[1] = i;
                break;
            }
        }

        return result;
    }

    // TIme: O(logn)
    // Space: O(1)
    private static int[] searchRangeBestCase(int[] nums, int target) {
        int[] result = new int[] {-1, -1};

        if(nums == null || nums.length == 0) {
            return result;
        }

        int leftIdx = findIdx(nums, target, true);

        if(leftIdx == nums.length || target != nums[leftIdx]) {
            return result;
        }

        result[0] = leftIdx;
        result[1] = findIdx(nums, target, false) - 1;

        return result;
    }

    private static int findIdx(int[] nums, int target, boolean leftIdx) {
        // We cannot do nums.length - 1, because, if we did
        // consider an example: we want to search for the last element which is only once in the array
        // now we will end up with left value as 5 which is the right most value if we did length - 1
        // since we are going to -1 for right most index, we will lose one more which results inaccurate.
        int left = 0, right = nums.length;

        while(left < right) {
            int pivot = left + (right - left) / 2;

            if(nums[pivot] > target || (leftIdx && nums[pivot] == target)) {
                right = pivot;
            } else {
                left = pivot + 1;
            }
        }

        return left;
    }
}
