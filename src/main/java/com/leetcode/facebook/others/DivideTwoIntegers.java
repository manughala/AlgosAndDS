package com.leetcode.facebook.others;

/**
 *   Divide Two Integers

 Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

 Return the quotient after dividing dividend by divisor.

 The integer division should truncate toward zero.

 Example 1:

 Input: dividend = 10, divisor = 3
 Output: 3
 Example 2:

 Input: dividend = 7, divisor = -3
 Output: -2
 Note:

 Both dividend and divisor will be 32-bit signed integers.
 The divisor will never be 0.
 Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.

 * @author Santosh Manughala (SM030146).
 */
public class DivideTwoIntegers {
    public static void main(String args[]) {
//        int dividend = 10, divisor = 3;
        int dividend = 7, divisor = -3;
//        int dividend = Integer.MAX_VALUE, divisor = 2;
//        int dividend = Integer.MIN_VALUE, divisor = 1;

        System.out.println("brute force: " + divideTwoNumbersBruteForce(dividend, divisor));
        System.out.println("best case: " + divideTwoNumbersBestCase(dividend, divisor));
    }

    //Time: O(logn)
    // Space:O(1)
    private static int divideTwoNumbersBestCase(int dividend, int divisor) {
        if(divisor == 0) {
            return Integer.MAX_VALUE;
        }

        if(divisor == -1 && dividend == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }

        int sign = (dividend < 0 ^ divisor < 0) ? -1 : 1;

        long pDividend = Math.abs((long)dividend);
        long pDivisor = Math.abs((long)divisor);
        int result = 0;

        while(pDividend >= pDivisor) {
            int numOfShifts = 0;
            while(pDividend >= (pDivisor<<numOfShifts)) {
                numOfShifts++;
            }

            result += 1<<(numOfShifts-1);
            pDividend -= (pDivisor<<(numOfShifts-1));
        }


            return result * sign;
    }

    // Time: O(dividend)
    // Space: O(1)
    // NOTE: THIS WILL NOT WORK if the math.abs are returned as ints and WHEN DIVIDEND IS INTEGER.MIN_VALUE AND DIVISOR IS 1 ->
    // there is no way to convert - 2147483648 to 2147483647, hence conver to long
    private static int divideTwoNumbersBruteForce(int dividend, int divisor) {
        // math.abs will return min value only even if it is negative. so we will overflow in this case.
        // EVEN if its long dividend == Integer.MIN_VALUE && divisor == -1 -> will NOT work
        if(divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
            return Integer.MAX_VALUE;
        }

        int sign = (dividend < 0 ^ divisor < 0) ? -1 : 1; // it has to be bitwise (^) OR not logical OR (|) -> if we were to -10/-4 we will get -result, but it is supposed to be + with logical
        long d = Math.abs((long)dividend);
        long v = Math.abs((long) divisor);

        int quotient = 0;
        while(d >= v) {
            d -= v;
            ++quotient;
        }

        return quotient * sign;
    }

}
