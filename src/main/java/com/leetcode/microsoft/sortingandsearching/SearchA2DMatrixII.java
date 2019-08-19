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

        System.out.println("findIntermediateCase, output: " + findIntermediateCase(matrix, 5));
        System.out.println("findIntermediateCase, output: " + findIntermediateCase(matrix, 20));
        System.out.println("findIntermediateCase, output: " + findIntermediateCase(new int[][]{{1}}, 1));

        System.out.println("findBestCase, output: " + findBestCase(matrix, 5));
        System.out.println("findBestCase, output: " + findBestCase(matrix, 20));
        System.out.println("findBestCase, output: " + findBestCase(new int[][]{{1}}, 1));
    }

    // Time: O(m + n)
    // Space: O(1)
    private static boolean findBestCase(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length - 1, column = 0;

        while(row >= 0 && column < matrix[0].length) {
            if(matrix[row][column] > target) {
                row--;
            } else if (matrix[row][column] < target) {
                column++;
            } else {
                return true;
            }
        }

        return false;
    }



    // iterate over diagonals
    // Time: O(lgn!): It's not super obvious how \mathcal{O}(lg(n!))O(lg(n!)) time complexity arises from this algorithm, so let's analyze it step-by-step. The asymptotically-largest amount of work performed is in the main loop, which runs for min(m, n)min(m,n) iterations, where mm denotes the number of rows and nn denotes the number of columns. On each iteration, we perform two binary searches on array slices of length m-im−i and n-in−i. Therefore, each iteration of the loop runs in \mathcal{O}(lg(m-i)+lg(n-i))O(lg(m−i)+lg(n−i)) time, where ii denotes the current iteration. We can simplify this to \mathcal{O}(2\cdot lg(n-i))=\mathcal{O}(lg(n-i))O(2⋅lg(n−i))=O(lg(n−i)) by seeing that, in the worst case, n\approx mn≈m. To see why, consider what happens when n \ll mn≪m (without loss of generality); nn will dominate mm in the asymptotic analysis. By summing the runtimes of all iterations, we get the following expression:
//
//(1) \quad \mathcal{O}(lg(n) + lg(n-1) + lg(n-2) + \ldots + lg(1)) (1)O(lg(n)+lg(n−1)+lg(n−2)+…+lg(1))
//
//    Then, we can leverage the log multiplication rule (lg(a)+lg(b)=lg(ab)lg(a)+lg(b)=lg(ab)) to rewrite the complexity as:
//
//            \begin{aligned} (2) \quad \mathcal{O}(lg(n) + lg(n-1) + lg(n-2) + \ldots + lg(1)) &= \mathcal{O}(lg(n \cdot (n-1) \cdot (n-2) \cdot \ldots \cdot 1)) \\ &= \mathcal{O}(lg(1 \cdot \ldots \cdot (n-2) \cdot (n-1) \cdot n)) \\ &= \mathcal{O}(lg(n!)) \end{aligned}
//(2)O(lg(n)+lg(n−1)+lg(n−2)+…+lg(1))
//            ​
//
//            =O(lg(n⋅(n−1)⋅(n−2)⋅…⋅1))
//            =O(lg(1⋅…⋅(n−2)⋅(n−1)⋅n))
//            =O(lg(n!))
//            ​
//
//
//    Because this time complexity is fairly uncommon, it is worth thinking about its relation to the usual analyses. For one, lg(n!) = \mathcal{O}(nlgn)lg(n!)=O(nlgn). To see why, recall step 1 from the analysis above; there are nn terms, each no greater than lg(n)lg(n). Therefore, the asymptotic runtime is certainly no worse than that of an \mathcal{O}(nlgn)O(nlgn) algorithm.
    // Space: O(1)
    private static boolean findIntermediateCase(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int length = Math.min(matrix.length, matrix[0].length);

        for(int i = 0; i < length; i++) {
            boolean vertical = search(matrix, i, target, true);
            boolean horizontal = search(matrix, i, target, false);
            if(horizontal || vertical) {
                return true;
            }
        }

        return false;
    }

    private static boolean search(int[][] matrix, int start, int target, boolean vertical) {
        int left = start, right = vertical ? matrix[0].length - 1 : matrix.length - 1;

        while(left <= right) {
            int mid = left +  (right - left) / 2;
            if(vertical) {
                // column search
                if(matrix[start][mid] == target) {
                    return true;
                }

                if(target > matrix[start][mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                // row search
                if(matrix[mid][start] == target) {
                    return true;
                }

                if(target > matrix[mid][start]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return false;
    }

    // NOTE: SAME SOLUTION AS OF SearchA2DMatrixII.find but added small if check to go over all rows here.
    // Time: O(mlogn)
    // Space: O(1)
    private static boolean find(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] <= target && matrix[i][matrix[0].length - 1] >= target) {
                if(searchRow(matrix[i], target)) {
                    return true;
                }
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
