package com.leetcode.facebook.stringsandarrays;

import java.util.Arrays;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

 // SUPPOSED TO BE AN IMAGE

 The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 In this case, 6 units of rain water (blue section) are being trapped.
 * @author Santosh Manughala (SM030146).
 */
public class TrappingRainWater {

    public static void main(String args[]) {
//        int nums[] = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int nums[] = new int[]{2, 0, 2};

        if(nums == null || nums.length == 0) {
            System.out.println("we can trap up to : " + 0 + " units of rain water");
        }

//        int result = getUnitsOfTrapperRainWaterBruteForce(nums);
//        int result = getUnitsOfTrapperRainWaterBestCaseI(nums);
        int result = getUnitsOfTrapperRainWaterBestCaseII(nums);
        System.out.println("we can trap up to : " + result + " units of rain water");
    }

    // Time complexity: O(n^2)
    private static int getUnitsOfTrapperRainWaterBruteForce(int[] nums) {
        int result = 0;

        for(int i = 1; i < nums.length; i++) {
            int leftMax = findMax(nums, 1, i);
            int rightMax = findMax(nums, i, nums.length);

            if(Math.min(leftMax, rightMax) > nums[i]) {
                result += Math.min(leftMax, rightMax) - nums[i];
            }
        }

        return result;
    }

    private static int findMax(int[] nums, int start, int end) {
        int max = 0;
        for(int i = start; i < end; i++) {
            if(nums[i] > max) {
                max = nums[i];
            }

        }
        return max;
    }


    //Timecomplexity: O(n) space: O(n)
    private static int getUnitsOfTrapperRainWaterBestCaseI(int[] nums) {
        int result = 0, length = nums.length;
        int[] leftMaxs = new int[length], rightMaxs = new int[length];

        // 0,1,0,2,1,0,1,3,2,1,2,1 = 12 l
        leftMaxs[0] = nums[0];
        for(int i = 1; i < length; i++) {
            leftMaxs[i] = Math.max(nums[i], leftMaxs[i-1]);
        }

        rightMaxs[length-1] = nums[length-1];
        for(int i = length - 2; i >= 0; i--) {
            rightMaxs[i] = Math.max(nums[i], rightMaxs[i+1]);
        }

        for(int i = 0; i < length; i++) {
            result += Math.min(leftMaxs[i], rightMaxs[i]) - nums[i];
        }

        return result;
    }

    // Time complexity O(n) Space complexity O(1)
    private static int getUnitsOfTrapperRainWaterBestCaseII(int[] nums) {
        int result = 0, leftMax = 0, rightMax = 0, start = 0, end = nums.length - 1;

        while(start < end) {
            if(nums[start] < nums[end]) {
                if(leftMax <= nums[start]) {
                    leftMax = nums[start];
                } else {
                    result += leftMax - nums[start];
                }
                ++start;
            } else {
                if(rightMax <= nums[end]) {
                    rightMax = nums[end];
                } else {
                    result += rightMax - nums[end];
                }
                --end;
            }
        }

        return result;
    }
}
