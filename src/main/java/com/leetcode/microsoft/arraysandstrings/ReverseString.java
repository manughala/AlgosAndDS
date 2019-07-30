package com.leetcode.microsoft.arraysandstrings;

/**
 * Write a function that reverses a string. The input string is given as an array of characters char[].

 Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

 You may assume all the characters consist of printable ascii characters.


 Example 1:

 Input: ["h","e","l","l","o"]
 Output: ["o","l","l","e","h"]
 Example 2:

 Input: ["H","a","n","n","a","h"]
 Output: ["h","a","n","n","a","H"]

 * @author Santosh Manughala (SM030146).
 */
public class ReverseString {

    public static void main(String args[]) {
//        char[] input = new char[]{'h', 'e', 'l', 'l', 'o'};
        char[] input = new char[]{'h', 'a', 'n', 'n', 'a', 'h'};


        System.out.println("before: " + new String(input));
        reverseString(input);
        System.out.println("after: " + new String(input));
    }

    // Time: O(logn)
    // Space: O(1)
    private static void reverseString(char[] s) {
        if(s == null || s.length < 2) {
            return;
        }

        int i = 0, j = s.length - 1;

        while(i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;

            i++;
            j--;
        }
    }
}
