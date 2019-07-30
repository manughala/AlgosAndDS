package com.leetcode.linkedin;

import java.util.HashMap;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

 Example 1:

 Input: [[1,1],[2,2],[3,3]]
 Output: 3
 Explanation:
 ^
 |
 |        o
 |     o
 |  o
 +------------->
 0  1  2  3  4
 Example 2:

 Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 Output: 4
 Explanation:
 ^
 |
 |  o
 |     o        o
 |        o
 |  o        o
 +------------------->
 0  1  2  3  4  5  6
 NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

 * @author Santosh Manughala (SM030146).
 */
public class MaxPointsOnALine {

    public static void main(String args[]) {
        System.out.println(maxPoints(new int[][]{
                {1,1},
                {3,2},
                {5,3},
                {4,1},
                {2,3},
                {1,4}
        }));

        System.out.println(maxPoints(new int[][]{
                {0,0},
                {94911151,94911150},
                {94911152,94911151}
        }));
    }

    // Time O(n^2)
    // Space O(n)
    private static int maxPoints(int[][] points) {
        if(points == null || points.length == 0) {
            return 0;
        }

        HashMap<Double, Integer> slopes = new HashMap<>();

        int max = 0;

        for(int i = 0; i < points.length; i++) {
            int duplicate = 1, vertical = 0;

            for(int j = i + 1; j < points.length; j++) {
                if(points[i][0] == points[j][0]) {
                    if(points[i][1] == points[j][1]) {
                        duplicate++;
                    } else {
                        vertical++;
                    }
                } else {
                    double slope = points[j][1] == points[i][1] ? 0.0 : (10.0 * (points[j][1] - points[i][1])) / (points[j][0] - points[i][0]);

                    slopes.put(slope, slopes.getOrDefault(slope, 0) + 1);
                }
            }

            for (int count : slopes.values()) {
                max = Math.max(count + duplicate, max);
            }

            max = Math.max(vertical + duplicate, max);
            slopes.clear();
        }

        return max;
    }
}
