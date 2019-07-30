package com.leetcode.facebook.stringsandarrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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
public class StringPalindrome {

    public static void main(String args[]) {
//        String input = "race a car";
        String input = "A man, a plan, a canal: Panama";

        if(null == input || input.length() == 1) {
            System.out.println("is palindrome: " + true);
        }

//        boolean result = isPalindromeBruteForce(input);
//        boolean result = isPalindromeLittleBetter(input);
//        boolean result = isPalindromeBestCase(input);
        boolean result = isPalindromeBestCaseI(input);
        System.out.println("is palindrome: " + result);
    }

    private static boolean isPalindromeBruteForce(String input) {
        // One way to replace special chars
        // Also, could use a hashset with collection of all special chars and check if the string contains them
        // Also, could write methods to check for ischar -> if(char >= 'a' && char <='z') ->... and isnum
        // Also, could use a regular expression as in below best case code
        // we cannot do it without eliminating the special chars first.. so one of the above ways should be done no matter what, best solution will be in our ispalindorme code

        String inputStr = "";
        for(int i = 0; i < input.length(); i++) {
            if(Character.isLetter(input.charAt(i)) || Character.isDigit(input.charAt(i))) {
                inputStr += input.charAt(i);
            }
        }

        String reverseInputStr = "";
        for(int i = 0; i < inputStr.length(); i++) {
            reverseInputStr += inputStr.charAt(inputStr.length() - i - 1);
        }

        if(reverseInputStr.equalsIgnoreCase(inputStr)) {
            return true;
        }

        return false;
    }

    private static boolean isPalindromeLittleBetter(String input) {
        input = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        if(input.length() < 2) {
            return true;
        }

        Stack<Character> stack = new Stack<>();
        int index = 0;

        while(index < input.length()/2) {
            stack.push(input.charAt(index));
            index++;
        }

        if(input.length() % 2 == 1) {
            index++;
        }

        while (index < input.length()) {
            if(stack.isEmpty()) {
                return false;
            }

            if(stack.pop() != input.charAt(index)) {
                return false;
            }

            index++;
        }

        return true;
    }

    // TIME O(logn)
    private static boolean isPalindromeBestCaseI(String input) {
        int i = 0, j = input.length() - 1;

        while(i < j) {
            if(!Character.isAlphabetic(input.charAt(i)) && !Character.isDigit(input.charAt(i))) {
                i++;
            } else if(!Character.isAlphabetic(input.charAt(j)) && !Character.isDigit(input.charAt(j))) {
                j--;
            } else if ((input.charAt(i) + 32 - 'a') % 32 != (input.charAt(j) + 32 - 'a') % 32) {
                return false;
            } else {
                i++;
                j--;
            }
        }

        return true;
    }

    private static boolean isPalindromeBestCase(String input) {
        input = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        if(input.length() < 2) {
            return true;
        }

//        for(int i = 0; i < input.length(); i++) {
//            if(input.charAt(i) != input.charAt(input.length() - i - 1)) {
//                return false;
//            }
//        }

        int l = input.length(), i =  0, j = l -1;

        while(i<j) {
            if(input.charAt(i++) != input.charAt(j--)) {
                return false;
            }
        }

        return true;
    }
}
