package com.leetcode.microsoft.arraysandstrings;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 Note: For the purpose of this problem, we define empty string as valid palindrome.

 Example 1:

 Input: "A man, a plan, a canal: Panama"
 Output: true
 Example 2:

 Input: "race a car"
 Output: false

 * @author Santosh Manughala (SM030146).
 */
public class ValidPalindrome {
    public static void main(String args[]) {
        System.out.println(isValidPalindromeBestCase("A man, a plan, a canal: Panama"));
        System.out.println(isValidPalindromeBestCase("race a car"));
    }


    // Time O(n)
    // Space O(1)
    private static boolean isValidPalindromeBestCase(String input) {
        if(input == null || input.length() == 0) {
            return false;
        }

        input = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int i = 0, j = input.length() - 1;
        while(i < j) {
            if(input.charAt(i++) != input.charAt(j--)) {
                return false;
            }
        }

        return true;
    }
}
