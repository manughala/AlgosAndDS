package com.leetcode.amazon.explore.arraysandstrings;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn
 * such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

 Note: You may not slant the container and n is at least 2.



 The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.



 Example:

 Input: [1,8,6,2,5,4,8,3,7]
 Output: 49

 * @author Santosh Manughala (SM030146).
 */
public class ContainerWithMostWater {

    public static void main(String args[]) {
        System.out.println("maxAreaBruteForce: " + maxAreaBruteForce(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println("maxAreaBestCase: " + maxAreaBestCase(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    // Time: O(n)
    // Space: O(1)
    private static int maxAreaBestCase(int[] height) {
        int maxArea = 0, left = 0, right = height.length - 1;

        while(left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));

            if(height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    // Time: O(n^2)
    // Space: O(1)
    private static int maxAreaBruteForce(int[] height) {
        int maxArea = 0;

        for(int i = 0; i < height.length; i++) {
            for(int j = i + 1; j < height.length; j++) {
                // area of rectangle = l * b;
                maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
            }
        }

        return maxArea;
    }
}
