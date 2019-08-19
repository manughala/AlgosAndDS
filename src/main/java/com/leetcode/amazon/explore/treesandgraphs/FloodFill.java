package com.leetcode.amazon.explore.treesandgraphs;

/**
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

 Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

 To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

 At the end, return the modified image.

 Example 1:
 Input:
 image = [[1,1,1],[1,1,0],[1,0,1]]
 sr = 1, sc = 1, newColor = 2
 Output: [[2,2,2],[2,2,0],[2,0,1]]
 Explanation:
 From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 by a path of the same color as the starting pixel are colored with the new color.
 Note the bottom corner is not colored 2, because it is not 4-directionally connected
 to the starting pixel.
 Note:

 The length of image and image[0] will be in the range [1, 50].
 The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
 The value of each color in image[i][j] and newColor will be an integer in [0, 65535].

 * @author Santosh Manughala (SM030146).
 */
public class FloodFill {

    public static void main(String args[]) {
        int[][] image = new int[3][3];
        image[0][0] = 1;
        image[0][1] = 1;
        image[0][2] = 1;
        image[1][0] = 1;
        image[1][1] = 1;
        image[1][2] = 0;
        image[2][0] = 1;
        image[2][1] = 0;
        image[2][2] = 1;

        int[][] result = floodFill(image, 1, 1, 2);
        for(int[] res : result) {
            for(int r : res) {
                System.out.println(r);
            }
        }
    }

    // Time: O(n)
    // Space: O(n)
    private static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int currColor = image[sr][sc];

        if(currColor != newColor) {
            floodFillRecur(image, sr, sc, currColor, newColor);
        }

        return image;
    }

    private static void floodFillRecur(int[][] image, int sr, int sc, int color, int newColor) {
        if(sr < 0 || sr > image.length - 1 || sc < 0 || sc > image[0].length - 1 || image[sr][sc] != color) {
            return;
        }

        image[sr][sc] = newColor;

        floodFillRecur(image, sr - 1, sc, color, newColor);
        floodFillRecur(image, sr + 1, sc, color, newColor);
        floodFillRecur(image, sr, sc - 1, color, newColor);
        floodFillRecur(image, sr, sc + 1, color, newColor);
    }

//    private static void floodFillRecur(int[][] image, int sr, int sc, int color, int newColor) {
//        if(image[sr][sc] == color) {
//            image[sr][sc] = newColor;
//
//            if(sr > 0) {
//                floodFillRecur(image, sr - 1, sc, color, newColor);
//            }
//
//            if(sr < image.length - 1) {
//                floodFillRecur(image, sr + 1, sc, color, newColor);
//            }
//
//            if(sc > 0) {
//                floodFillRecur(image, sr, sc - 1, color, newColor);
//            }
//
//            if(sc < image[0].length - 1) {
//                floodFillRecur(image, sr, sc + 1, color, newColor);
//            }
//        }
//    }
}
