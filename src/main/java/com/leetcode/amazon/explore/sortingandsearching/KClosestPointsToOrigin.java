package com.leetcode.amazon.explore.sortingandsearching;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

 (Here, the distance between two points on a plane is the Euclidean distance.)

 You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



 Example 1:

 Input: points = [[1,3],[-2,2]], K = 1
 Output: [[-2,2]]
 Explanation:
 The distance between (1, 3) and the origin is sqrt(10).
 The distance between (-2, 2) and the origin is sqrt(8).
 Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 Example 2:

 Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 Output: [[3,3],[-2,4]]
 (The answer [[-2,4],[3,3]] would also be accepted.)


 Note:

 1 <= K <= points.length <= 10000
 -10000 < points[i][0] < 10000
 -10000 < points[i][1] < 10000

 * @author Santosh Manughala (SM030146).
 */
public class KClosestPointsToOrigin {

    public static void main(String args[]) {
        System.out.println("kClosestIntermediateCaseOwnIdea: ");
        int[][] points1 = kClosestIntermediateCaseOwnIdea(new int[][]{{1, 2}, {-2, 2}}, 1);
        printPoints(points1);

        System.out.println("\nkClosestIntermediateCaseOwnIdea: ");
        int[][] points2 = kClosestIntermediateCaseOwnIdea(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2);
        printPoints(points2);


        System.out.println("\nkClosestIntermediateCase: ");
        int[][] points3 = kClosestIntermediateCase(new int[][]{{1, 2}, {-2, 2}}, 1);
        printPoints(points3);

        System.out.println("\nkClosestIntermediateCase: ");
        int[][] points4 = kClosestIntermediateCase(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2);
        printPoints(points4);

        System.out.println("\nkClosestBestCase: ");
        int[][] points5 = kClosestBestCase(new int[][]{{1, 2}, {-2, 2}}, 1);
        printPoints(points5);

        System.out.println("\nkClosestBestCase: ");
        int[][] points6 = kClosestBestCase(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2);
        printPoints(points6);
    }

    private static void printPoints(int[][] points) {
        for(int[] point : points) {
            System.out.print("\n");
            for (int p: point) {
                System.out.print(p + ", ");
            }
        }
    }

    // Idea to use quick select algo
    // NOTE: will only apply if we must return the response in any order, if sorted required, it has to be one of below 2 approaches.
    // Time O(n)
    // Space: O(n)
    private static int[][] kClosestBestCase(int[][] points, int k) {
        quickSelect(points, k, 0, points.length - 1);
        return Arrays.copyOfRange(points, 0, k);
    }

    private static void quickSelect(int[][] points, int k, int left, int right) {
        if(left >= right) {
            return;
        }

        int pivotIdx = left + new Random().nextInt(right - left);
        swap(points, left, pivotIdx);

        pivotIdx = partition(points, left, right);

        int leftLength = pivotIdx - left + 1;
        if(k < leftLength) {
            quickSelect(points, k, left, pivotIdx - 1);
        } else if (k > leftLength) {
            quickSelect(points, k, pivotIdx + 1, right);
        }
    }

    private static int partition(int[][] points, int left, int right) {
        int pivotVal = distance(points[left]);

        int originalLeft = left;
        left++;

        while (true) {
            while(left < right && distance(points[left]) < pivotVal) {
                left++;
            }

            while(left <= right && distance(points[right]) > pivotVal) {
                right--;
            }

            if(left >= right) {
                break;
            }

            swap(points, left, right);
        }

        swap(points, originalLeft, right);
        return right;
    }

    private static void swap(int[][] prices, int left, int right) {
        int[] temp = prices[left];
        prices[left] = prices[right];
        prices[right] = temp;
    }

    // Time: O(nlogn)
    // Space: O(n)
    private static int[][] kClosestIntermediateCase(int[][] points, int k) {
        if(k > points.length) {
            return new int[][]{};
        }

        int[] distance = new int[points.length];
        for(int i = 0; i < points.length; i++) {
            distance[i] = distance(points[i]);
        }

        Arrays.sort(distance);
        int distanceK = distance[k - 1], j = 0;

        int[][] result = new int[k][2];

        for(int i = 0; i < points.length; i++) {
            if(distanceK >= distance(points[i])) {
                result[j++] = points[i];
            }
        }

        return result;
    }

    private static int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    // Time: O(nlogn)
    // Space: O(n)
    private static int[][] kClosestIntermediateCaseOwnIdea(int[][] points, int k) {
        if(k > points.length) {
            return new int[][]{};
        }

        Map<Double, int[][]> distanceToPoints = new HashMap<>();
        int x1 = 0, y1 = 0;

        for(int i = 0; i < points.length; i++) {
            int x2 = points[i][0], y2 = points[i][1];
            double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

            distanceToPoints.put(distance, new int[][]{{x2, y2}});
        }

        // sort desc, smallest to largest as we want closest elements
        PriorityQueue<Double> queue = new PriorityQueue<>(new Comparator<Double>(){
            @Override
            public int compare(Double i1, Double i2) {
                if(i1 > i2) {
                    return -1;
                } else if(i1 < i2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        for(double d : distanceToPoints.keySet()) {
            queue.add(d);

            if(queue.size() > k) {
                queue.poll();
            }
        }

        int[][] result = new int[k][2];
        int j = 0;

        while (!queue.isEmpty()) {
            int[][] kpoint = distanceToPoints.get(queue.poll());
            result[j][0] = kpoint[0][0];
            result[j][1] = kpoint[0][1];
            j++;
        }

        return result;
    }
}
