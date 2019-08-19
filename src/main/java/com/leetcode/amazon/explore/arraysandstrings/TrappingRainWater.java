package com.leetcode.amazon.explore.arraysandstrings;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


 The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

 Example:

 Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 Output: 6

 * @author Santosh Manughala (SM030146).
 */
public class TrappingRainWater {
    public static void main(String args[]) {
        System.out.println("getTrappedRainWaterBest: Expected 6, actual: " + getTrappedRainWaterBest(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println("getTrappedRainWaterIntermediate: Expected 6, actual: " + getTrappedRainWaterIntermediate(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }

    // Time: O(n)
    // Space: O(1)
    private static int getTrappedRainWaterBest(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int leftMax = 0, rightMax = 0, i = 0, j = nums.length - 1, count = 0;

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

    // Time: O(n)
    // Space: O(n)
    private static int getTrappedRainWaterIntermediate(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

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


}
