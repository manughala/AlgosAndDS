package com.leetcode.linkedin;

/**
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

 The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

 Note:
 All costs are positive integers.

 Example:

 Input: [[1,5,3],[2,9,4]]
 Output: 5
 Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
 Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
 Follow up:
 Could you solve it in O(nk) runtime?

 * @author Santosh Manughala (SM030146).
 */
public class PaintHouseII {
    public static void main(String args[]) {
        int[][] input = new int[][] {
                {1, 5, 3},
                {2, 9, 4}
        };

        System.out.println(minCostToPaint(input));
    }

    // time: O(n*k)
    // space: O(1)
    private static int minCostToPaint(int[][] costs) {
        if(costs == null || costs.length == 0) {
            return 0;
        }

        int prevMin = 0, prevSecondMin = 0, prevIdx = -1;

        for(int i = 0; i < costs.length; i++) {
            int currMin = Integer.MAX_VALUE, currSecondMin = Integer.MAX_VALUE, currIdx = 0;
            for(int j = 0;  j < costs[0].length; j++) {
                // if the current index matches prev house index, use the second min to this house
                // other wise use the first min
                costs[i][j] += j == prevIdx ? prevSecondMin : prevMin;

                if(costs[i][j] < currMin) {
                    currSecondMin = currMin;
                    currMin = costs[i][j];
                    currIdx = j;
                } else if (costs[i][j] < currSecondMin) {
                    currSecondMin = costs[i][j];
                }
            }

            prevIdx = currIdx;
            prevMin = currMin;
            prevSecondMin = currSecondMin;
        }

        int result = Integer.MAX_VALUE;
        for(int i = 0; i < costs[0].length; i++) {
            if(result > costs[costs.length - 1][i]) {
                result = costs[costs.length - 1][i];
            }
        }

        return result;
    }
}
