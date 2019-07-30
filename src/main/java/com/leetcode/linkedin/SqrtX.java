package com.leetcode.linkedin;

/**
 * Implement int sqrt(int x).

 Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

 Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

 Example 1:

 Input: 4
 Output: 2
 Example 2:

 Input: 8
 Output: 2
 Explanation: The square root of 8 is 2.82842..., and since
 the decimal part is truncated, 2 is returned.

 * @author Santosh Manughala (SM030146).
 */
public class SqrtX {

    public static void main(String args[]) {
        System.out.println(mySqrtI(4));
        System.out.println(mySqrtI(8));
        System.out.println(mySqrtI(2147395600));

        System.out.println(mySqrtII(4));
        System.out.println(mySqrtII(8));
        System.out.println(mySqrtII(2147395600));

    }

    // time O(logn)
    // space O(1)
    private static int mySqrtII(int x) {
        if(x < 2) {
            return x;
        }

        int left = 0, right = x, mid = 0;

        while(left <= right) {
            mid = left + (right - left) / 2;

            if(mid == x / mid) {
                return mid;
            } else if (mid > x / mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    // TIme: O(sqrt(n)
    // space O(1)
    // For some reason this does not work on leetcode -> but the idea is to use every single number until we find the ans.
    private static int mySqrtI(int x) {
        if(x == 0 || x == 1) {
            return 1;
        }

        int i = 1;
        int result = 1;

        while(result <= x) {
            i++;
            result = i * i;
        }

        return i - 1;
    }
}
