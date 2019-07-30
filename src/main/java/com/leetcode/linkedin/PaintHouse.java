package com.leetcode.linkedin;

/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

 The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

 Note:
 All costs are positive integers.

 Example:

 Input: [[17,2,17],[16,16,5],[14,3,19]]
 Output: 10
 Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 Minimum cost: 2 + 5 + 3 = 10.

 * @author Santosh Manughala (SM030146).
 */
public class PaintHouse {

    public static void main(String args[]) {
        int[][] input = new int[][] {
                {17, 2, 17},
                {16, 16, 5},
                {14, 3, 19}
        };

        System.out.println(minCostToPaint(input));
    }

    private static int minCostToPaint(int[][] input) {
        if(input == null || input.length == 0) {
            return 0;
        }

        int[] minCostForEachColor = new int[3];

        for(int[] cost : input) {
            int red = minCostForEachColor[0];
            int blue = minCostForEachColor[1];
            int green = minCostForEachColor[2];

            minCostForEachColor[0] = cost[0] + Math.min(blue, green);
            minCostForEachColor[1] = cost[1] + Math.min(red, green);
            minCostForEachColor[2] = cost[2] + Math.min(red, blue);
        }

        return Math.min(Math.min(minCostForEachColor[0], minCostForEachColor[1]), minCostForEachColor[2]);
    }
}
