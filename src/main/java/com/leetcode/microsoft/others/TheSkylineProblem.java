package com.leetcode.microsoft.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

 Buildings  Skyline Contour
 The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

 For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

 The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

 For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

 Notes:

 The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 The input list is already sorted in ascending order by the left x position Li.
 The output list must be sorted by the x position.
 There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

 * @author Santosh Manughala (SM030146).
 */
public class TheSkylineProblem {
    public static void main(String[] args) {
//        int [][] buildings = {{1,3,4},{3,4,4},{2,6,2},{8,11,4}, {7,9,3},{10,11,2}};
        int [][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        List<List<Integer>> skylinePoints = getSkylinePoints(buildings);

        System.out.println("Skyline Points: ");
        for(List<Integer> skylinePoint: skylinePoints) {
            System.out.println(skylinePoint);
        }
    }

    // Time: O(nlogn) - add, getMax or remove - 2 operations takes logn time for treemap. We do these 3 operations for all the elements.
    // Space: O(n)
    private static List<List<Integer>> getSkylinePoints(int[][] buildings) {
        List<List<Integer>> skylinePoints = new ArrayList<>();

        if(buildings == null || buildings.length == 0) {
            return skylinePoints;
        }

        List<BuildingPoint> buildingPoints = new ArrayList<>();
        for(int i = 0; i < buildings.length; i++) {
            int x = buildings[i][0], y = buildings[i][1], height = buildings[i][2];

            BuildingPoint startBuildingPoint = new BuildingPoint(x, height, true);
            BuildingPoint endBuildingPoint = new BuildingPoint(y, height, false);

            buildingPoints.add(startBuildingPoint);
            buildingPoints.add(endBuildingPoint);
        }

        Collections.sort(buildingPoints);

        // Using TreeMap because it gives log time performance.
        // PriorityQueue in java does not support remove(object) operation in log time.
        TreeMap<Integer, Integer> heightToCountMap = new TreeMap<>();
        heightToCountMap.put(0, 1);
        int prevMax = 0;

        for(BuildingPoint buildingPoint : buildingPoints) {
            if(buildingPoint.isStart) {
                // If it is start of building then add the height to map. If height already exists then increment the value
                heightToCountMap.put(buildingPoint.height, heightToCountMap.getOrDefault(buildingPoint.height, 0 ) + 1);
            } else {
                // If it is end of building then add remove the height if we only have one building with that height.
                // If height already exists then decrement the value
                if(heightToCountMap.get(buildingPoint.height) == 1) {
                    heightToCountMap.remove(buildingPoint.height);
                } else {
                    heightToCountMap.put(buildingPoint.height, heightToCountMap.get(buildingPoint.height) - 1);
                }
            }

            int currMax = heightToCountMap.lastKey();

            // If height changes from previous height then this building x becomes critical x -> add to result
            if(currMax != prevMax) {
                List<Integer> skylinePoint = new ArrayList<>();
                skylinePoint.add(buildingPoint.x);
                skylinePoint.add(currMax);

                prevMax = currMax;

                skylinePoints.add(skylinePoint);
            }

        }

        return skylinePoints;
    }
}

class BuildingPoint implements Comparable<BuildingPoint> {
    int x, height;
    boolean isStart;

    BuildingPoint(int x, int height, boolean isStart) {
        this.x = x;
        this.height = height;
        this.isStart = isStart;
    }

    @Override
    public int compareTo(BuildingPoint buildingPoint) {
        if(this.x != buildingPoint.x) {
            // If the both points start at different points, return the smallest.
            return this.x - buildingPoint.x;
        } else {
            // If the points start at same point, we should handle:
            // 1. If they both are start points, we should pick highest start first
            // 2. If they both are end points, we should pick smallest end first
            // 3. If one is start, and the other is end, we should pick the smallest start first
            return (this.isStart ? -this.height : this.height) - (buildingPoint.isStart ? -buildingPoint.height : buildingPoint.height);
        }
    }
}
