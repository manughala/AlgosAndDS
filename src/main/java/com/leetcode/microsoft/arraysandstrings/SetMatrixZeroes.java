package com.leetcode.microsoft.arraysandstrings;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

 Example 1:

 Input:
 [
 [1,1,1],
 [1,0,1],
 [1,1,1]
 ]
 Output:
 [
 [1,0,1],
 [0,0,0],
 [1,0,1]
 ]
 Example 2:

 Input:
 [
 [0,1,2,0],
 [3,4,5,2],
 [1,3,1,5]
 ]
 Output:
 [
 [0,0,0,0],
 [0,4,5,0],
 [0,3,1,0]
 ]
 Follow up:

 A straight forward solution using O(mn) space is probably a bad idea.
 A simple improvement uses O(m + n) space, but still not the best solution.
 Could you devise a constant space solution?

 * @author Santosh Manughala (SM030146).
 */
public class SetMatrixZeroes {

    public static void main(String args[]) {
        int[][] input1 = new int[3][3];
        input1[0][0] = 1;
        input1[0][1] = 1;
        input1[0][2] = 1;
        input1[1][0] = 1;
        input1[1][1] = 0;
        input1[1][2] = 1;
        input1[2][0] = 1;
        input1[2][1] = 1;
        input1[2][2] = 1;

        int[][] input2 = new int[3][4];
        input2[0][0] = 0;
        input2[0][1] = 1;
        input2[0][2] = 2;
        input2[0][3] = 0;
        input2[1][0] = 3;
        input2[1][1] = 4;
        input2[1][2] = 5;
        input2[1][3] = 2;
        input2[2][0] = 1;
        input2[2][1] = 3;
        input2[2][2] = 1;
        input2[2][3] = 5;

        System.out.println("setMatrixZeroesBruteForce: ");
        System.out.println("before : ");
        printMatrix(input1);
        int[][] result1 = setMatrixZeroesBruteForce(input1);
        System.out.println("after : ");
        printMatrix(result1);

        System.out.println("before : ");
        printMatrix(input2);
        int[][] result2 = setMatrixZeroesBruteForce(input2);
        System.out.println("after : ");
        printMatrix(result2);

        System.out.println("setMatrixZeroesBetterBruteForce: ");
        System.out.println("before : ");
        printMatrix(input1);
        int[][] result3 = setMatrixZeroesBetterBruteForce(input1);
        System.out.println("after : ");
        printMatrix(result3);

        System.out.println("before : ");
        printMatrix(input2);
        int[][] result4 = setMatrixZeroesBetterBruteForce(input2);
        System.out.println("after : ");
        printMatrix(result4);

        System.out.println("setMatrixZeroesBestCase: ");
        System.out.println("before : ");
        printMatrix(input1);
        setMatrixZeroesBestCase(input1);
        System.out.println("after : ");
        printMatrix(input1);

        System.out.println("before : ");
        printMatrix(input2);
        setMatrixZeroesBestCase(input2);
        System.out.println("after : ");
        printMatrix(input2);
    }

    private static void printMatrix(int[][] result) {
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[0].length; j++) {
                System.out.println(result[i][j]);
            }
        }
    }

    // IDEA: instead of marking the rows/cols OR updating values with in the loop
    // we will just use the first cell of that row/col as identifier to come and
    // update with 0s
    // Time O(m*n)
    // Space O(1)
    private static void setMatrixZeroesBestCase(int[][] input) {
        if(input == null || input.length == 0) {
            return;
        }

        boolean isZeroCol = false;
        for(int i = 0; i < input.length; i++) {
            // If any of the values in the first column is 0, we have to update that column
            // Since we will use first field to indicate, we might miss this data, capturing here
            if(input[i][0] == 0) {
                isZeroCol = true;
            }

            // since we are leveraging isZeroCol to check the first column, we can ignore that column
            for(int j = 1; j < input[0].length; j++) {
                if(input[i][j] == 0) {
                    input[i][0] = 0;
                    input[0][j] = 0;
                }
            }
        }

        // this should ignore first row n col as we update later
        for(int i = 1; i < input.length; i++) {
            for(int j = 1; j < input[0].length; j++) {
                if(input[i][0] == 0 || input[0][j] == 0) {
                    input[i][j] = 0;
                }
            }
        }

        // if the first field of the first row is 0, update the first row
        if(input[0][0] == 0) {
            for(int j = 0; j < input[0].length; j++) {
                input[0][j] = 0;
            }
        }


        // have to update row prior to col check as we are dependant on input[0][0] val n this code may update
        if(isZeroCol) {
            for(int i = 0; i < input.length; i++) {
                input[i][0] = 0;
            }
        }
    }

    // better than brute force as here we are keeping track of the rows and columns while creating a copy
    // we update the rows/columns on next iteration
    // Time O(mn)
    // Space O(m + n)
    private static int[][] setMatrixZeroesBetterBruteForce(int[][] input) {
        if(input == null || input.length == 0) {
            return input;
        }

        Set<Integer> zeroesRows = new HashSet<>();
        Set<Integer> zeroesColumns = new HashSet<>();

        for(int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if(input[i][j] == 0) {
                    zeroesRows.add(i);
                    zeroesColumns.add(j);
                }
            }
        }

        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[0].length; j++) {
                if(zeroesRows.contains(i) || zeroesColumns.contains(j)) {
                    input[i][j] = 0;
                }
            }
        }

        return input;

//        ANOTHER WAY TO DO IT
//        if(input == null || input.length == 0) {
//            return input;
//        }
//
//        Set<Integer> zeroesRows = new HashSet<>();
//        Set<Integer> zeroesColumns = new HashSet<>();
//
//        int[][] result = new int[input.length][input[0].length];
//        for(int i = 0; i < input.length; i++) {
//            for (int j = 0; j < input[0].length; j++) {
//                if(input[i][j] == 0) {
//                    zeroesRows.add(i);
//                    zeroesColumns.add(j);
//                }
//                result[i][j] = input[i][j];
//            }
//        }
//
//        for(int i : zeroesRows) {
//            for(int j = 0; j < input[0].length; j++) {
//                result[i][j] = 0;
//            }
//        }
//
//        for(int j : zeroesColumns) {
//            for(int i = 0; i < input.length; i++) {
//                result[i][j] = 0;
//            }
//        }
//
//        return result;
    }

    // Time O(m * n) * (m + n)
    // Space O(mn)
    // we can avoid using additional space by replacing the row/col val with some random default like -10000
    // (the p and q loops will not update result, but will update input with -100000)
    // then on another iteration, we change that default to 0.
    private static int[][] setMatrixZeroesBruteForce(int[][] input) {
        if(input == null || input.length == 0) {
            return input;
        }

        int[][] result = new int[input.length][input[0].length];
        for(int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                result[i][j] = input[i][j];
            }
        }

        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[0].length; j++) {
                if(input[i][j] == 0) {
                    for(int p = 0; p < input.length; p++) {
                        result[p][j] = 0;
                    }

                    for(int q = 0; q < input[0].length; q++) {
                        result[i][q] = 0;
                    }
                }
            }
        }

        return result;

    }
}
