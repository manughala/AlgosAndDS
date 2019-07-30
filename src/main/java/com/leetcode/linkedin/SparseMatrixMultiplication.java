package com.leetcode.linkedin;

/**
 * Given two sparse matrices A and B, return the result of AB.

 You may assume that A's column number is equal to B's row number.

 Example:

 Input:

 A = [
 [ 1, 0, 0],
 [-1, 0, 3]
 ]

 B = [
 [ 7, 0, 0 ],
 [ 0, 0, 0 ],
 [ 0, 0, 1 ]
 ]

 Output:

 |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 | 0 0 1 |

 * @author Santosh Manughala (SM030146).
 */
public class SparseMatrixMultiplication {

    public static void main(String args[]) {
        int[][] A = new int[][]{ {1, 0, 0}, {-1, 0, 3}};
        int[][] B = new int[][]{ {7, 0, 0}, {0, 0, 0}, {0, 0, 1}};

        int[][] resultBruteForce = sparseMatrixMultiplyBruteForce(A, B);
        int[][] resultBestCase = sparseMatrixMultiplyBestCase(A, B);

        System.out.println("BRUTE FORCE CASE: ");
        for(int i = 0; i < resultBruteForce.length; i++) {
            for (int j  = 0; j < resultBruteForce[0].length; j++) {
                System.out.println(resultBruteForce[i][j]);
            }
        }

        System.out.println("BEST CASE: ");
        for(int i = 0; i < resultBestCase.length; i++) {
            for (int j  = 0; j < resultBestCase[0].length; j++) {
                System.out.println(resultBestCase[i][j]);
            }
        }
    }

    private static int[][] sparseMatrixMultiplyBruteForce(int[][] A, int[][] B) {
        int[][] result = new int[A.length][B[0].length];

        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[0].length; j++) {
                for(int k = 0; k < A[0].length; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    private static int[][] sparseMatrixMultiplyBestCase(int[][] A, int[][] B) {
        int[][] result = new int[A.length][B[0].length];

        for(int i = 0; i < result.length; i++) {
            for(int k = 0; k < A[0].length; k++) {
                if(A[i][k] != 0) {
                    for (int j = 0; j < result[0].length; j++) {
                        result[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
        return result;
    }
}
