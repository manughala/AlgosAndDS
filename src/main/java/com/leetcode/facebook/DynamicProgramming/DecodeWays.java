package com.leetcode.facebook.DynamicProgramming;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given a non-empty string containing only digits, determine the total number of ways to decode it.

 Example 1:

 Input: "12"
 Output: 2
 Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 Example 2:

 Input: "226"
 Output: 3
 Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).


 * @author Santosh Manughala (SM030146).
 */
public class DecodeWays {

    public static void main(String[] args) {
        String s1 = "12";
        String s2 = "0";
        String s3 = null;
        String s4 = "226";
        String s5 = "2";
        String s6 = "12345";
        // the below will fail for DP, need to ask the interviewer if we should expect 0s in the input.
        String s7 = "00345";
        String s8 = "12300";
        String s9 = "10034";

        // if we can have 0s in the given string, we should replace all 0s with empty string and pass it down or store to a new string
//         int count2 = decodeWaysDPbestCaseI(s9.replaceAll("[0]", ""));

        int count1 = decodeWaysbruteForceRecursive(s6);
        int count2 = decodeWaysDPbestCaseI(s6);

        System.out.println("recur: " + count1);
        System.out.println("DP I: " + count2);
    }

    private static int decodeWaysDPbestCaseI(String digits) {
        int[] count = new int[digits.length() + 1];

        // question to interviewer - can the number start with 0? -> if yes we need to ignore that char Otherwise we can return here
        if(digits == null || digits.length() == 0 || digits.charAt(0) == '0') {
            return count[0];
        }

        if(digits.length() == 1) {
            return 1;
        }

        count[0] = 1;
        count[1] = 1; // this points to first char in the digits. Assuming the digit is not started with 0

        for(int i = 2; i <= digits.length(); i++) {
            if(digits.charAt(i-1) > '0') {
                count[i] = count[i-1];
            }

            if((digits.charAt(i - 2) == '1' || (digits.charAt(i - 2) == '2' && digits.charAt(i - 1) < '7'))) {
                count[i] += count[i-2];
            }
        }

        return count[digits.length()];
    }

    // This is recursive call and will take exponential time
    // Time O(2^n)
    // Space O(n) - due to recursion
    private static int decodeWaysbruteForceRecursive(String digits) {
        if(digits == null || digits.length() == 0 || digits.charAt(0) == '0') {
            return 0;
        }

        if(digits.length() == 1) {
            return 1;
        }

        return decodeWaysbruteForceRecursive(digits.toCharArray(), digits.length());
    }

    private static int decodeWaysbruteForceRecursive(char[] digits, int length) {
        if(length <= 1) {
            return 1;
        }

        int count = 0;
        if(digits[length - 1] > '0') {
            count = decodeWaysbruteForceRecursive(digits, length - 1);
        }

        if((digits[length - 2] == '1' || (digits[length - 2] == '2' && digits[length - 1] < '7'))) {
            count += decodeWaysbruteForceRecursive(digits, length - 2);
        }

        return count;
    }
}
