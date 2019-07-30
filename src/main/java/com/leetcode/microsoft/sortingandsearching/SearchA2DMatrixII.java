package com.leetcode.microsoft.sortingandsearching;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted in ascending from left to right.
 Integers in each column are sorted in ascending from top to bottom.
 Example:

 Consider the following matrix:

 [
 [1,   4,  7, 11, 15],
 [2,   5,  8, 12, 19],
 [3,   6,  9, 16, 22],
 [10, 13, 14, 17, 24],
 [18, 21, 23, 26, 30]
 ]
 Given target = 5, return true.

 Given target = 20, return false.

 * @author Santosh Manughala (SM030146).
 */
public class SearchA2DMatrixII {
    public static void main(String args[]) {
        int[][] matrix = new int[][] {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        System.out.println("find, output: " + find(matrix, 5));
        System.out.println("find, output: " + find(matrix, 20));
        System.out.println("find, output: " + find(new int[][]{{1}}, 1));

//        System.out.println("findBestCase, output: " + findBestCase(matrix, 3));
//        System.out.println("findBestCase, output: " + findBestCase(matrix, 13));
//        System.out.println("findBestCase, output: " + findBestCase(new int[][]{{1}}, 1));
    }

    // Time: O(mlogn) TODO
    // Space: O(1)TODO
    private static boolean find(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        
    }



}
