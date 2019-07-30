package com.leetcode.microsoft.arraysandstrings;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


 The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

 Example:

 Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 Output: 6

 * @author Santosh Manughala (SM030146).
 */
public class TrappingRainWater {

    public static void main(String args[]) {
        int nums1[] = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int nums2[] = new int[]{2, 0, 2};

        if(nums1 == null || nums2.length == 0) {
            System.out.println("we can trap up to : " + 0 + " units of rain water");
        }

        int result = 0;
        System.out.println("getUnitsOfTrapperRainWaterBruteForce");

        result = getUnitsOfTrapperRainWaterBruteForce(nums1);
        System.out.println("we can trap up to : " + result + " units of rain water");
        result = getUnitsOfTrapperRainWaterBruteForce(nums2);
        System.out.println("we can trap up to : " + result + " units of rain water");

        System.out.println("getUnitsOfTrapperRainWaterBestCaseI");

        result = getUnitsOfTrapperRainWaterBestCaseI(nums1);
        System.out.println("we can trap up to : " + result + " units of rain water");
        result = getUnitsOfTrapperRainWaterBestCaseI(nums2);
        System.out.println("we can trap up to : " + result + " units of rain water");

        System.out.println("getUnitsOfTrapperRainWaterBestCaseII");

        result = getUnitsOfTrapperRainWaterBestCaseII(nums1);
        System.out.println("we can trap up to : " + result + " units of rain water");
        result = getUnitsOfTrapperRainWaterBestCaseII(nums2);
        System.out.println("we can trap up to : " + result + " units of rain water");
    }

    private static int getUnitsOfTrapperRainWaterBestCaseI(int[] nums) {
        int count = 0;

        int[] leftMax = new int[nums.length];
        leftMax[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            leftMax[i] = Math.max(nums[i], leftMax[i-1]);
        }

        int[] rightMax = new int[nums.length];
        rightMax[nums.length - 1] = nums[nums.length - 1];
        for(int i = nums.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(nums[i], rightMax[i+1]);
        }

        for(int i = 0; i < nums.length; i++) {
            count += Math.min(leftMax[i], rightMax[i]) - nums[i];
        }

        return count;
    }

    private static int getUnitsOfTrapperRainWaterBestCaseII(int[] nums) {
        int i = 0, j = nums.length - 1, count = 0;
        int rightMax = 0, leftMax = 0;

        while(i < j) {
            if(nums[i] < nums[j]) {
                if(leftMax <= nums[i]) {
                    leftMax = nums[i];
                } else {
                    count += leftMax - nums[i];
                }
                i++;
            } else {
                if(rightMax <= nums[j]) {
                    rightMax = nums[j];
                } else {
                    count += rightMax - nums[j];
                }
                j--;
            }
        }

        return count;
    }

    // Time complexity: O(n^2)
    // Space O(1)
    private static int getUnitsOfTrapperRainWaterBruteForce(int[] nums) {
        int count = 0;

        for(int i = 0; i < nums.length; i++) {
            int leftMax = getMax(nums, 0, i);
            int rightMax = getMax(nums, i, nums.length);

            if(Math.min(leftMax, rightMax) > nums[i]) {
                count += Math.min(leftMax, rightMax) - nums[i];
            }
        }

        return count;
    }

    private static int getMax(int[] nums, int startIdx, int endIdx) {
        int max = nums[startIdx];
        for(int i = startIdx; i < endIdx; i++) {
            if(max < nums[i]) {
                max = nums[i];
            }
        }

        return max;
    }
}
