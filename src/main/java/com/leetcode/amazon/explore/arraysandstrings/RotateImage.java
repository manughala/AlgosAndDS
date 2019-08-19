package com.leetcode.amazon.explore.arraysandstrings;

/**
 * You are given an n x n 2D matrix representing an image.

 Rotate the image by 90 degrees (clockwise).

 Note:

 You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

 Example 1:

 Given input matrix =
 [
 [1,2,3],
 [4,5,6],
 [7,8,9]
 ],

 rotate the input matrix in-place such that it becomes:
 [
 [7,4,1],
 [8,5,2],
 [9,6,3]
 ]
 Example 2:

 Given input matrix =
 [
 [ 5, 1, 9,11],
 [ 2, 4, 8,10],
 [13, 3, 6, 7],
 [15,14,12,16]
 ],

 rotate the input matrix in-place such that it becomes:
 [
 [15,13, 2, 5],
 [14, 3, 4, 1],
 [12, 6, 8, 9],
 [16, 7,10,11]
 ]

 * @author Santosh Manughala (SM030146).
 */
public class RotateImage {
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

        int[][] input2 = new int[4][4];
        input2[0][0] = 5;
        input2[0][1] = 1;
        input2[0][2] = 9;
        input2[0][3] = 11;
        input2[1][0] = 2;
        input2[1][1] = 4;
        input2[1][2] = 8;
        input2[1][3] = 10;
        input2[2][0] = 13;
        input2[2][1] = 3;
        input2[2][2] = 6;
        input2[2][3] = 7;
        input2[3][0] = 15;
        input2[3][1] = 14;
        input2[3][2] = 12;
        input2[3][3] = 16;

//        System.out.println("rotateMatrixBruteForce: ");
//        System.out.println("before : ");
//        printMatrix(input1);
//        int[][] result1 = rotateMatrixBruteForce(input1);
//        System.out.println("after : ");
//        printMatrix(result1);
//
//        System.out.println("before : ");
//        printMatrix(input2);
//        int[][] result2 = rotateMatrixBruteForce(input2);
//        System.out.println("after : ");
//        printMatrix(result2);

//        System.out.println("rotateMatrixIntermediateCaseI: ");
//        System.out.println("before : ");
//        int[][] input1Copy = copyMatrix(input1);
//        printMatrix(input1Copy);
//        rotateMatrixIntermediateCaseI(input1Copy);
//        System.out.println("after : ");
//        printMatrix(input1Copy);
//
//        System.out.println("before : ");
//        int[][] input2Copy = copyMatrix(input2);
//        printMatrix(input2Copy);
//        rotateMatrixIntermediateCaseI(input2Copy);
//        System.out.println("after : ");
//        printMatrix(input2Copy);

//        System.out.println("rotateMatrixBestCase: ");
//        System.out.println("before : ");
//        int[][] input1Copy1 = copyMatrix(input1);
//        printMatrix(input1Copy1);
//        rotateMatrixBestCase(input1Copy1);
//        System.out.println("after : ");
//        printMatrix(input1Copy1);
//
//        System.out.println("before : ");
//        int[][] input2Copy1 = copyMatrix(input2);
//        printMatrix(input2Copy1);
//        rotateMatrixBestCase(input2Copy1);
//        System.out.println("after : ");
//        printMatrix(input2Copy1);
    }

    private static void printMatrix(int[][] result) {
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[0].length; j++) {
                System.out.println(result[i][j]);
            }
        }
    }

    private static int[][] copyMatrix(int[][] input) {
        int[][] result = new int[input.length][input[0].length];
        for(int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                result[i][j] = input[i][j];
            }
        }
        return result;
    }

    // Time O(N*N)
    // Space: O(1)
    private static void rotateMatrixBestCase(int[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return;
        }

        int len = matrix.length;

        for(int i = 0; i < (len + 1) / 2; i++) {
            for(int j = 0; j < len / 2; j++) {
                // temp = 10
                // 10 = 21
                // 21 = 12
                // 12 = 01
                // 01 = temp

                // temp = 00
                // 00 = 20
                // 20 = 22
                // 22 = 02
                // 02 = temp
                // i = 0 j = 2 pi = 2 pj = 0
                // i = 0 j = 1 pi = 2 pj = 1
                int pi = len - i - 1, pj = len - j - 1;

                int temp = matrix[pj][i];
                matrix[pj][i] = matrix[pi][pj];
                matrix[pi][pj] = matrix[j][pi];
                matrix[j][pi] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }

    }

    // NOTE FOR rotateMatrixIntermediateCaseI please refer {@link com.leetcode.microsoft.arraysandstrings.RotateImage#rotateMatrixIntermediateCaseI}



    // NOT inplace:
    // Time O(N*N)
    // Space: O(N*N)
    private static int[][] rotateMatrixBruteForce(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix.length];

        for(int i = 0; i < matrix.length; i++) {
            int temp = matrix.length - i - 1;

            for(int j = 0; j < matrix[0].length; j++) {
                result[j][temp] = matrix[i][j];
            }
        }

        return result;
    }


}
