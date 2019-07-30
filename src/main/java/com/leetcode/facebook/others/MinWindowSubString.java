package com.leetcode.facebook.others;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Minimum Window Substring

 Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

 Example:

 Input: S = "ADOBECODEBANC", T = "ABC"
 Output: "BANC"
 Note:

 If there is no such window in S that covers all characters in T, return the empty string "".
 If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

 * @author Santosh Manughala (SM030146).
 */
public class MinWindowSubString {

    public static void main(String args[]) {
        System.out.println(minWindowCaseI("ADOBECODEBANC", "ABC"));
        System.out.println(minWindowCaseII("ADOBECODEBANC", "ABC"));
    }

    private static String minWindowCaseI(String s, String t) {
        if(s.length() == 0 || t.length() == 0) {
            return "";
        }

        HashMap<Character, Integer> uniqueCharsInT = new HashMap<>();

        for(char c : t.toCharArray()) {
            uniqueCharsInT.put(c, uniqueCharsInT.getOrDefault(c, 0) + 1);
        }


        int left = 0, right = 0, uniqueCharsInTCount = uniqueCharsInT.size(), uniqueCharsCountInCurrWindow = 0;
        HashMap<Character, Integer> uniqueCharsInCurrWindowInS = new HashMap<>();

        int[] ans = new int[] {-1, 0 , 0}; // window length, left, right
        while(right < s.length()) {
            char rightChar = s.charAt(right);
            uniqueCharsInCurrWindowInS.put(rightChar, uniqueCharsInCurrWindowInS.getOrDefault(rightChar, 0) + 1);

            if(uniqueCharsInT.containsKey(rightChar) && uniqueCharsInT.get(rightChar).intValue() == uniqueCharsInCurrWindowInS.get(rightChar).intValue()) {
                uniqueCharsCountInCurrWindow++;
            }

            while(left <= right && uniqueCharsCountInCurrWindow == uniqueCharsInTCount) {
                char leftChar = s.charAt(left);

                if(ans[0] == -1 || ans[0] > right - left + 1) {
                    ans[0]  = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }

                uniqueCharsInCurrWindowInS.put(leftChar, uniqueCharsInCurrWindowInS.get(leftChar) - 1);
                if(uniqueCharsInT.containsKey(leftChar) && uniqueCharsInT.get(leftChar).intValue() > uniqueCharsInCurrWindowInS.get(leftChar).intValue()) {
                    uniqueCharsCountInCurrWindow--;
                }

                left++;
            }

            right++;
        }

        return ans[0] > 0 ? s.substring(ans[1], ans[2] + 1): "";

    }

    // Better than Case I -> Idea is to use a filtered version of the given string by storing the indexes of the chars in T in S.

    private static String minWindowCaseII(String s, String t) {
        if(s.length() == 0 || t.length() == 0) {
            return "";
        }

        HashMap<Character, Integer> uniqueCharsInT = new HashMap<>();
        HashMap<Character, Integer> uniqueCharsInCurrWindowInS = new HashMap<>();

        for(char c : t.toCharArray()) {
            uniqueCharsInT.put(c, uniqueCharsInT.getOrDefault(c, 0) + 1);
        }

        List<Pair<Integer, Character>> filteredS = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            if(uniqueCharsInT.containsKey(s.charAt(i))) {
                filteredS.add(new Pair<>(i, s.charAt(i)));
            }
        }
        int[] ans = new int[] {-1, 0, 0}; // window length, left, right
        int left = 0, right = 0, uniqueCharsInTCount = uniqueCharsInT.size(), uniqueCharsInCurrWindowCount = 0;


        while(right < filteredS.size()) {
            char rightChar = filteredS.get(right).getValue();
            uniqueCharsInCurrWindowInS.put(rightChar, uniqueCharsInCurrWindowInS.getOrDefault(uniqueCharsInCurrWindowInS, 0) + 1);

            if(uniqueCharsInT.get(rightChar).intValue() == uniqueCharsInCurrWindowInS.get(rightChar).intValue()) {
                uniqueCharsInCurrWindowCount++;
            }

            while(left <= right && uniqueCharsInCurrWindowCount == uniqueCharsInTCount) {
                char leftChar = filteredS.get(left).getValue();

                int start = filteredS.get(left).getKey();
                int end = filteredS.get(right).getKey();
                if(ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }

                uniqueCharsInCurrWindowInS.put(leftChar, uniqueCharsInCurrWindowInS.get(leftChar) - 1);
                if(uniqueCharsInT.containsKey(leftChar) && uniqueCharsInT.get(leftChar).intValue() > uniqueCharsInCurrWindowInS.get(leftChar).intValue()) {
                    uniqueCharsInCurrWindowCount--;
                }

                left++;
            }

            right++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}
