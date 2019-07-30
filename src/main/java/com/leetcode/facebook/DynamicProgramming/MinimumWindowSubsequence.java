package com.leetcode.facebook.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

 If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

 Example 1:

 Input:
 S = "abcdebdde", T = "bde"
 Output: "bcde"
 Explanation:
 "bcde" is the answer because it occurs before "bdde" which has the same length.
 "deb" is not a smaller window because the elements of T in the window must occur in order.


 Note:

 All the strings in the input will only contain lowercase letters.
 The length of S will be in the range [1, 20000].
 The length of T will be in the range [1, 100].

 * @author Santosh Manughala (SM030146).
 */
public class MinimumWindowSubsequence {

    public static void main(String args[]) {
        String S = "hpsrhgogezyfrwfrejytjkzvgpjnqil", T = "gys";

        String resultBruteForce = getMinWindowSubSequenceBruteForce(S, T);
        String resultDPI = getMinWindowSubSequenceDPI(S, T);

        System.out.println("resultBruteForce = " + resultBruteForce);
        System.out.println("resultDPI = " + resultDPI);
    }

    // Time: O(st) -> calculation of next in s steps, others in t steps.
    // Space: O(s) -> size of windows
    private static String getMinWindowSubSequenceDPI (String base, String subSequence) {
        int length = base.length();
        int last[] = new int[26];
        int next[][] = new int[length][26];
        Arrays.fill(last, -1);

        for(int i = length - 1; i >= 0; i--) {
            last[base.charAt(i) - 'a'] = i;
            for(int k = 0; k < 26; k++) {
                next[i][k] = last[k];
            }
        }

        List<int[]> windows = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            if(base.charAt(i) == subSequence.charAt(0)) {
                windows.add(new int[]{i, i});
            }
        }

        for(int i = 1; i < subSequence.length(); i++) {
            int idx = subSequence.charAt(i) - 'a';
            for(int[] window : windows) {
                if(window[1] < length - 1 && next[window[1] + 1][idx] >= 0) {
                    window[1] = next[window[1] + 1][idx];
                } else {
                    window[1] = window[0] = -1;
                    break;
                }
            }
        }

        int[] ans = new int[] {-1, length - 1};
        for(int[] window : windows) {
            if(window[0] == -1) {
                break;
            }

            if(window[1] - window[0] < ans[1] - ans[0]) {
                ans = window;
            }
        }

        return ans[0] >= 0 ? base.substring(ans[0] , ans[1] + 1) : "";
    }

    // Time: O(n^2)
    // Space: O(1)
    private static String getMinWindowSubSequenceBruteForce(String base, String subsequent) {
        String result = "";
        int start = 0;

        while (start < base.length()) {
            int j = 0;

            for(int i = start; i < base.length(); i++) {
                if(base.charAt(i) == subsequent.charAt(j) && j == 0) {
                    start = i;
                }

                if(base.charAt(i) == subsequent.charAt(j)) {
                    j++;
                }

                if(j == subsequent.length()) {
                    if(result == "" || i - start + 1 < result.length()) {
                        result = base.substring(start, i + 1);
                    }

                    start = start + 1;
                    break;
                }

                if(i == base.length() - 1) {
                    return result;
                }
            }

        }

        return result;
    }
}
