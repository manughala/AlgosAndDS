package com.leetcode.linkedin;

/**
 * Implement pow(x, n), which calculates x raised to the power n (xn).

 Example 1:

 Input: 2.00000, 10
 Output: 1024.00000
 Example 2:

 Input: 2.10000, 3
 Output: 9.26100
 Example 3:

 Input: 2.00000, -2
 Output: 0.25000
 Explanation: 2-2 = 1/22 = 1/4 = 0.25
 Note:

 -100.0 < x < 100.0
 n is a 32-bit signed integer, within the range [−231, 231 − 1]


 * @author Santosh Manughala (SM030146).
 */
public class PowerXN {
    public static void main(String args[]) {
        double x = 2.00000;
        int n = 10;

//        double result = powerXNBruteForceDOESNOTWORKFORNEGATIVEN(x, n);
//        double result = powerXNBruteForce(x, n);
//        double result = powerXNBestCaseRecur(x, n);
        double result = powerXNBestCaseIter(x, n);
        System.out.println("result = " + result);
    }


    // Time O(logn)
    // Space O(1)
    private static double powerXNBestCaseIter(double x, int n) {
        long N = n;
        // to make sure N is always positive
        if(N < 0) {
            x = 1/x;
            N = -N;
        }

        double result = 1, currentProduct = x;
        for(long i = N; i > 0; i /= 2) {
            if(i % 2 == 1) {
                result *= currentProduct;
            }

            currentProduct *= currentProduct;
        }

        return result;
    }

    // Time O(logn)
    // Space O(1)
    private static double powerXNBestCaseRecur(double x, int n) {
        long N = n;
        // to make sure N is always positive
        if(N < 0) {
            x = 1/x;
            N = -N;
        }

        return performRecursion(x, N);
    }

    private static double performRecursion(double x, long N) {
        if(N == 0) {
            return 1.0;
        }

        double half = performRecursion(x, N/2);
        if(N%2 == 0) {
            return half * half;
        } else {
           return half * half * x;
        }
    }

    // Time limit exceeded on leet code
    // Time: O(n)
    // Space: O(1)
    private static double powerXNBruteForce(double x, int n) {
        long N = n;

        if(N < 0) {
            x = 1/x;
            N = -N;
        }

        double result = 1;
        for(long i = 0; i < N; i++) {
            result = result * x;
        }

        return result;
    }

    // Time limit exceeded on leet code
    // Time: O(n)
    // Space: O(1)
    // This approach wont work for negative "n"
    private static double powerXNBruteForceDOESNOTWORKFORNEGATIVEN(double x, int n) {
        double result = 1;

        for(int i = 0; i < n; i++) {
            result = result * x;
        }

        return result;
    }


}
