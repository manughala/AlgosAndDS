package com.leetcode.microsoft.arraysandstrings;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an input string, reverse the string word by word.

 Example 1:

 Input: "the sky is blue"
 Output: "blue is sky the"
 Example 2:

 Input: "  hello world!  "
 Output: "world! hello"
 Explanation: Your reversed string should not contain leading or trailing spaces.
 Example 3:

 Input: "a good   example"
 Output: "example good a"
 Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.


 Note:

 A word is defined as a sequence of non-space characters.
 Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 You need to reduce multiple spaces between two words to a single space in the reversed string.


 Follow up:

 For C programmers, try to solve it in-place in O(1) extra space.

 * @author Santosh Manughala (SM030146).
 */
public class ReverseWordsInAString {
    public static void main(String args[]) {
        System.out.println(reverseWords("    "));
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords("  Hello world! "));
        System.out.println(reverseWords("a good   example"));
        System.out.println(reverseWords(" "));
    }

    // Time O(n)
    // Space O(n)
    private static String reverseWords(String s) {
        if(s == null || s.length() == 0 || s.trim().length() == 0) {
            return "";
        }

        String splitArray[] = s.trim().split(" ");
        StringBuilder result = new StringBuilder();

        for(int i = splitArray.length - 1; i >= 0 ; i--) {
            if(!splitArray[i].isEmpty()) {
                result.append(splitArray[i] + " ");
            }
        }

        return result.toString().trim();
    }
}
