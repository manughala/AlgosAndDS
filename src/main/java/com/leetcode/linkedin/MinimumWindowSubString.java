package com.leetcode.linkedin;

import java.util.HashMap;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

 Example:

 Input: S = "ADOBECODEBANC", T = "ABC"
 Output: "BANC"
 Note:

 If there is no such window in S that covers all characters in T, return the empty string "".
 If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

 * @author Santosh Manughala (SM030146).
 */
public class MinimumWindowSubString {

    // NOTE: same as #com.leetcode.facebook.others.MinWindowSubString
    public static void main(String args[]) {
        System.out.println(minWindowCaseI("aa", "aa"));
    }

    // note for other cases refer #com.leetcode.facebook.others.MinWindowSubString
    private static String minWindowCaseI(String s, String t) {
        if(s.length() == 0 || t.length() == 0) {
            return "";
        }

        HashMap<Character, Integer> uniqueCharsInT = new HashMap<>();
        HashMap<Character, Integer> uniqueCharsInCurrWindowInS = new HashMap<>();

        for(char c : t.toCharArray()) {
            uniqueCharsInT.put(c, uniqueCharsInT.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0, uniqueCharsCountInT = uniqueCharsInT.size(), uniqueCharsCountInCurrWindow = 0;
        int[] result = new int[]{-1, 0, 0}; // window length, left, right

        while(right < s.length()) {
            char rightChar = s.charAt(right);
            uniqueCharsInCurrWindowInS.put(rightChar, uniqueCharsInCurrWindowInS.getOrDefault(rightChar, 0) + 1);

            if(uniqueCharsInT.containsKey(rightChar) && uniqueCharsInT.get(rightChar).intValue() == uniqueCharsInCurrWindowInS.get(rightChar).intValue()) {
                uniqueCharsCountInCurrWindow++;
            }

            while(left <= right && uniqueCharsCountInT == uniqueCharsCountInCurrWindow) {
                char leftChar = s.charAt(left);

                if(result[0] == -1 || result[0] > right - left + 1) {
                    result[0] = right - left + 1;
                    result[1] = left;
                    result[2] = right;
                }

                uniqueCharsInCurrWindowInS.put(leftChar, uniqueCharsInCurrWindowInS.get(leftChar) - 1);
                if(uniqueCharsInT.containsKey(leftChar) && uniqueCharsInT.get(leftChar).intValue() > uniqueCharsInCurrWindowInS.get(leftChar).intValue()) {
                    uniqueCharsCountInCurrWindow--;
                }

                left++;
            }


            right++;
        }

        return result[0] > 0 ? s.substring(result[1], result[2] + 1) : "";
    }
}
