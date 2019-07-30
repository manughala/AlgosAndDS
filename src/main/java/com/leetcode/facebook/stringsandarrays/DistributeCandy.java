package com.leetcode.facebook.stringsandarrays;

import java.util.Arrays;

/**
 *
 * There are N children standing in a line. Each child is assigned a rating value.

 You are giving candies to these children subjected to the following requirements:

 Each child must have at least one candy.
 Children with a higher rating get more candies than their neighbors.
 What is the minimum candies you must give?

 Example 1:

 Input: [1,0,2]
 Output: 5
 Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 Example 2:

 Input: [1,2,2]
 Output: 4
 Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 The third child gets 1 candy because it satisfies the above two conditions.

 * @author Santosh Manughala (SM030146).
 */
public class DistributeCandy {

    public static void main(String args[]) {
//        int[] nums = new int[]{1, 0, 2};
//        int[] nums = new int[]{1, 2, 2};
        int[] nums = new int[]{1,2,87,87,87,2,1};

        if(nums == null || nums.length == 0) {
            System.out.println("invalid:" + 0);
            return;
        }

//        int result = findMinCandyToDistributeBruteForce(nums);
//        int result = findMinCandyToDistributeBestCaseI(nums);
        int result = findMinCandyToDistributeBestCaseII(nums);
//        int result = findMinCandyToDistributeBestCaseIII(nums);

        System.out.println("The min number of candy needed to distribute: " + result);
    }

    // Time complexity O(n^2) space O(n)
    private static int findMinCandyToDistributeBruteForce(int[] ratings) {
        int result = 0, length = ratings.length;
        int[] minCandies = new int[length];
        Arrays.fill(minCandies, 1);
        boolean flag = true;
        while(flag) {
            flag = false;
            for(int i = 0; i < length; i++) {
                if(i != length-1 && ratings[i] > ratings[i+1] && minCandies[i] <= minCandies[i+1]) {
                    minCandies[i] = minCandies[i+1] + 1;
                    flag = true;
                }

                if(i != 0 && ratings[i] > ratings[i-1] && minCandies[i] <= minCandies[i-1]) {
                    minCandies[i] = minCandies[i-1] + 1;
                    flag = true;
                }
            }
        }


        for(int minCandy : minCandies) {
            result += minCandy;
        }

        return result;
    }

    //Time complexity: O(n) Space: O(n)
    private static int findMinCandyToDistributeBestCaseI(int[] ratings) {
        int result = 0, length = ratings.length;
        int[] leftMax = new int[length], rightMax = new int[length];
        Arrays.fill(leftMax, 1);
        Arrays.fill(rightMax, 1);

        for (int i = 1; i < length; i++) {
            if(ratings[i] > ratings[i-1]) {
                leftMax[i] = leftMax[i-1] + 1;
            }
        }

        for (int i = length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1]) {
                rightMax[i] = rightMax[i+1] + 1;
            }
        }

        for(int i = 0; i < length; i++) {
            result += Math.max(rightMax[i], leftMax[i]);
        }

        return result;
    }

    // Same as above method, just used one array instead of 2
    private static int findMinCandyToDistributeBestCaseII(int[] ratings) {
        int length = ratings.length;
        int[] max = new int[length];
        Arrays.fill(max, 1);

        for (int i = 1; i < length; i++) {
            if(ratings[i] > ratings[i-1]) {
                max[i] = max[i-1] + 1;
            }
        }

        int result = ratings[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1]) {
                max[i] = Math.max(max[i], max[i+1] + 1);
            }
            result += max[i];
        }

        return result;
    }

    private static int findMinCandyToDistributeBestCaseIII(int[] ratings) {
        int result = 0;
    // DId not try to understand the O(1) space appraoch: https://leetcode.com/problems/candy/solution/
        return result;
    }




}
