package com.leetcode.microsoft.arraysandstrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given an input string , reverse the string word by word.

 Example:

 Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 Note:

 A word is defined as a sequence of non-space characters.
 The input string does not contain leading or trailing spaces.
 The words are always separated by a single space.
 Follow up: Could you do it in-place without allocating extra space?

 * @author Santosh Manughala (SM030146).
 */
public class ReverseWordsInAStringII {
    public static void main(String args[]) {
        char[] input1 = new char[]{'t', 'h', 'e', ' ', 's', 'k', 'y', ' ', 'i', 's', ' ', 'b', 'l', 'u', 'e'};
        char[] input2 = new char[]{' '};

        System.out.println("before: " + new String(input1));
        System.out.println("before: " + new String(input2));

//        reverseWordsBruteForce(input1);
//        reverseWordsBruteForce(input2);
        reverseWordsBestCase(input1);
        reverseWordsBestCase(input2);

        System.out.println("after: " + new String(input1));
        System.out.println("after: " + new String(input2));


    }

    // Time: O(logn) + O(n) = O(n)
    // Space: O(1) - inplace
    private static void reverseWordsBestCase(char[] s) {
        if(s == null || s.length == 0) {
            return;
        }

        int i = 0, j = s.length - 1;

        // reverse the string
        swap(i, j, s);

        int m = 0;

        // reverse words in the string
        while(m < s.length) {
            int start = m, end = m;

            while (m < s.length && s[m] != ' ') {
                end++;
                m++;
            }

            // definitely have to do this, as the above while loop
            // will end the space, meaning end will include the space
            // character too. we will exclude the space to avoid out
            // of bound at the end
            end--;

            swap(start, end, s);
            m++;
        }
    }

    private static void swap(int i, int j, char[] s) {
        while (i  < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

    // Time: O(n) + O(logn) + O(n) == O(n)
    // Space: O(n) -> not inplace
    private static void reverseWordsBruteForce(char[] s) {
        if(s == null || s.length == 0) {
            return;
        }

        List<String> strings = new ArrayList<>();
        String currentWord = "";

        for(int i = 0; i < s.length; i++) {
            currentWord += s[i];

            if(s[i] == ' ' || i+1 == s.length) {
                strings.add(currentWord.trim());
                currentWord = "";
            }
        }

        int i = 0, j = strings.size() - 1;

        while(i < j) {
            String temp = strings.get(i);
            strings.set(i, strings.get(j));
            strings.set(j, temp);
            i++;
            j--;
        }

        int p = 0;
        for(String string : strings) {
            for(char c : string.toCharArray()) {
                s[p++] = c;
            }
            if(p != s.length) {
                s[p++] = ' ';
            }
        }
    }
}
