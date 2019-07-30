package com.leetcode.linkedin;

/**
 * Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.

 A subsequence of a string S is obtained by deleting 0 or more characters from S.

 A sequence is palindromic if it is equal to the sequence reversed.

 Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.

 Example 1:
 Input:
 S = 'bccb'
 Output: 6
 Explanation:
 The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
 Note that 'bcb' is counted only once, even though it occurs twice.
 Example 2:
 Input:
 S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
 Output: 104860361
 Explanation:
 There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
 Note:

 The length of S will be in the range [1, 1000].
 Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.

 * @author Santosh Manughala (SM030146).
 */
public class CountDifferentPalindromicSubsequences {

    public static void main(String args[]) {
        System.out.println(countPalindromicSubsequences("bccb"));
        System.out.println(countPalindromicSubsequences("abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba"));
        System.out.println(countPalindromicSubsequences("bcbacbabdcbcbdcbddcaaccdcbbcdbcabbcdddadaadddbdbbbdacbabaabdddcaccccdccdbabcddbdcccabccbbcdbcdbdaada"));
    }

    // bottom up DP
    // time O(n^2)
    // space O(n^2)
    private static int countPalindromicSubsequences(String S) {
        int length = S.length(), mod = 1000000007;
        int[][][] dp = new int[4][length][length];

        for(int i = length - 1; i >= 0; i--) {
            for(int j = i; j < length; j++) {
                for(int k = 0; k < 4; k++) {
                    char c = (char) ('a' + k);
                    if(i == j) {
                        if(S.charAt(i) == c) {
                            dp[k][i][j] = 1;
                        } else {
                            dp[k][i][j] = 0;
                        }
                    } else { // j > i
                        if(S.charAt(i) != c) {
                            dp[k][i][j] = dp[k][i+1][j];
                        } else if (S.charAt(j) != c) {
                            dp[k][i][j] = dp[k][i][j-1];
                        } else { // S[i] == S[j] == c
                            dp[k][i][j] = 2;
                            // length is > 2
                            if(i != j + 1) { // if i = j+1 then the string will be like "aa" : {"a", "aa"}. In this case, we dont have to count each subwindow
                                for(int m = 0; m < 4; m++) { // count each one within subwindows [i+1][j-1]
                                    dp[k][i][j] += dp[m][i+1][j-1];
                                    dp[k][i][j] %= mod;
                                }
                            }
                        }
                    }
                }
            }
        }

        int result = 0;
        for(int k = 0; k < 4; k++) {
            result += dp[k][0][length - 1];
            result %= mod;
        }

        return result;
    }
}
