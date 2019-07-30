package com.leetcode.microsoft.arraysandstrings;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

 Example 1:

 Input:
 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 Output: [1,2,3,6,9,8,7,4,5]
 Example 2:

 Input:
 [
 [1, 2, 3, 4],
 [5, 6, 7, 8],
 [9,10,11,12]
 ]
 Output: [1,2,3,4,8,12,11,10,9,5,6,7]

 * @author Santosh Manughala (SM030146).
 */
public class SpiralMatrix {

    public static void main(String args[]) {
        int[][] input1 = new int[3][3];
        input1[0][0] = 1;
        input1[0][1] = 2;
        input1[0][2] = 3;
        input1[1][0] = 4;
        input1[1][1] = 5;
        input1[1][2] = 6;
        input1[2][0] = 7;
        input1[2][1] = 8;
        input1[2][2] = 9;

        int[][] input2 = new int[3][4];
        input2[0][0] = 1;
        input2[0][1] = 2;
        input2[0][2] = 3;
        input2[0][3] = 4;
        input2[1][0] = 5;
        input2[1][1] = 6;
        input2[1][2] = 7;
        input2[1][3] = 8;
        input2[2][0] = 9;
        input2[2][1] = 10;
        input2[2][2] = 11;
        input2[2][3] = 12;

        System.out.println("spiralMatrixBruteForce: ");
        System.out.println("matrix : ");
        printMatrix(input1);
        List<Integer> result1 = spiralMatrixBruteForce(input1);
        System.out.println("result1 : " + result1);

        System.out.println("matrix : ");
        printMatrix(input2);
        List<Integer> result2 = spiralMatrixBruteForce(input2);
        System.out.println("result2 : " + result2);
    }

    private static void printMatrix(int[][] result) {
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[0].length; j++) {
                System.out.println(result[i][j]);
            }
        }
    }

    // Time: O(M*N)
    // Space: O(N) -> the result list
    private static List<Integer> spiralMatrixBruteForce(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if(matrix == null || matrix.length == 0) {
            return result;
        }

        int rowStart = 0, rowEnd = matrix.length;
        int colStart = 0, colEnd = matrix[0].length;

        while(rowStart < rowEnd && colStart < colEnd) {

            for(int currCol = colStart; currCol < colEnd; currCol++) {
                result.add(matrix[rowStart][currCol]);
            }

            rowStart++;

            for(int currRow = rowStart; currRow < rowEnd; currRow++) {
                result.add(matrix[currRow][colEnd - 1]);
            }

            colEnd--;

            if(rowStart < rowEnd) {
                for(int currCol = colEnd - 1; currCol >= colStart; currCol--) {
                    result.add(matrix[rowEnd - 1][currCol]);
                }

                rowEnd--;
            }


            if(colStart < colEnd) {
                for (int currRow = rowEnd - 1; currRow >= rowStart; currRow--) {
                    result.add(matrix[currRow][colStart]);
                }

                colStart++;
            }

        }

        return result;
    }
}
