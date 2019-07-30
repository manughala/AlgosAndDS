package com.leetcode.microsoft.sortingandsearching;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted from left to right.
 The first integer of each row is greater than the last integer of the previous row.
 Example 1:

 Input:
 matrix = [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 target = 3
 Output: true
 Example 2:

 Input:
 matrix = [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 target = 13
 Output: false

 * @author Santosh Manughala (SM030146).
 */
public class SearchA2DMatrix {
    public static void main(String args[]) {
        int[][] matrix = new int[][] {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };

        System.out.println("find, output: " + find(matrix, 3));
        System.out.println("find, output: " + find(matrix, 13));
        System.out.println("find, output: " + find(new int[][]{{1}}, 1));

        System.out.println("findBestCase, output: " + findBestCase(matrix, 3));
        System.out.println("findBestCase, output: " + findBestCase(matrix, 13));
        System.out.println("findBestCase, output: " + findBestCase(new int[][]{{1}}, 1));
    }

    // Time: O(logmn)
    // Space: O(1)
    private static boolean findBestCase(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int n = matrix[0].length, m = matrix.length;

        int left = 0, right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if(matrix[mid/n][mid%n] == target) {
                return true;
            } else {
                if(target < matrix[mid/n][mid%n]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return false;
    }

    // Time: O(mlogn)
    // Space: O(1)
    private static boolean find(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] <= target && matrix[i][matrix[0].length - 1] >= target) {
                return searchRow(matrix[i], target);
            }
        }

        return false;
    }

    private static boolean searchRow(int[] matrix, int target) {
        int left = 0, right = matrix.length - 1;

        while(left <= right) {
            int mid = left + (right - left)/ 2;

            if(matrix[mid] == target) {
                return true;
            } else if (matrix[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }
}
