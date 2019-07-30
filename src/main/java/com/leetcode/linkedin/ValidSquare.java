package com.leetcode.linkedin;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.

 Note: Do not use any built-in library function such as sqrt.

 Example 1:

 Input: 16
 Output: true
 Example 2:

 Input: 14
 Output: false

 * @author Santosh Manughala (SM030146).
 */
public class ValidSquare {

    public static void main(String args[]) {
        System.out.println(isValidSquareBest(16));
        System.out.println(isValidSquareI(16));
    }

    // time: O(logn)
    //SPace O(1)
    private static boolean isValidSquareBest(int num) {
        int left = 1, right = num;

        while(left <= right) {
            long mid = (left + right) / 2;
             if(mid * mid == num) {
                 return true;
             } else if (mid * mid > num) {
                 right = (int) mid - 1;
             } else {
                 left = (int) mid + 1;
             }
        }

        return false;
    }

    // leet code did not accept this- time limit exceeded
    // Time: O(n)
    // Space O(1)
    private static boolean isValidSquareI(int num) {
        for(int sum = 0, i = 1; sum < num; i += 2) {
            sum += i;
            if(sum == num) {
                return true;
            }
        }
        return false;
    }
}
