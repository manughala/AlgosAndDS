package com.leetcode.microsoft.sortingandsearching;

/**
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

 Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

 Note: You are not suppose to use the library's sort function for this problem.

 Example:

 Input: [2,0,2,1,1,0]
 Output: [0,0,1,1,2,2]
 Follow up:

 A rather straight forward solution is a two-pass algorithm using counting sort.
 First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
 Could you come up with a one-pass algorithm using only constant space?

 * @author Santosh Manughala (SM030146).
 */
public class SortColors {
    public static void main(String args[]) {
        int[] input = new int[]{2, 0, 2, 1, 1, 0};
        printNums(input);
        sortColorsIntermediateCase(input);
        printNums(input);

        input = new int[]{2, 0, 2, 1, 1, 0};
        printNums(input);
        sortColorsBestCase(input);
        printNums(input);

        input = new int[]{1, 2, 0};
        printNums(input);
        sortColorsBestCase(input);
        printNums(input);
    }

    // Time: O(n)
    // Space: O(1)
    private static void sortColorsBestCase(int[] input) {
        int i = 0, j = input.length - 1;
        int counter = 0;

        while(counter <= j) {
            if(input[counter] == 0) {
                swap(counter, i, input);
                i++;
                counter++;
            } else if(input[counter] == 2) {
                swap(counter, j, input);
                // DO NOT INCREMENT THE ARRAY LOC, as you might have swapped
                // 2 for 2 since you really did not whats on other side.
                j--;
            } else {
                counter++;
            }
        }
    }

    private static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Time: O(n)
    // Space: O(1)
    private static void sortColorsIntermediateCase(int[] input) {
        if(input == null || input.length == 0) {
            return;
        }
        int redCount = 0, whiteCount = 0, blueCount = 0;
        for(int i = 0; i < input.length; i++) {
            if(input[i] == 0) {
                redCount++;
            } else if(input[i] == 1) {
                whiteCount++;
            } else if(input[i] == 2) {
                blueCount++;
            } else {
                throw new IllegalArgumentException("unknown value : " + input[i]);
            }
        }

        int i = 0;
        while(redCount > 0) {
            input[i++] = 0;
            redCount--;
        }

        while(whiteCount > 0) {
            input[i++] = 1;
            whiteCount--;
        }

        while(blueCount > 0) {
            input[i++] = 2;
            blueCount--;
        }

    }

    private static void printNums(int[] nums) {
        System.out.println("NUMS LENGTH: " + nums.length);
        for(int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
