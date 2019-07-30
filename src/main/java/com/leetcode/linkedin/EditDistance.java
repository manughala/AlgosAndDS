package com.leetcode.linkedin;

/**
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

 You have the following 3 operations permitted on a word:

 Insert a character
 Delete a character
 Replace a character
 Example 1:

 Input: word1 = "horse", word2 = "ros"
 Output: 3
 Explanation:
 horse -> rorse (replace 'h' with 'r')
 rorse -> rose (remove 'r')
 rose -> ros (remove 'e')
 Example 2:

 Input: word1 = "intention", word2 = "execution"
 Output: 5
 Explanation:
 intention -> inention (remove 't')
 inention -> enention (replace 'i' with 'e')
 enention -> exention (replace 'n' with 'x')
 exention -> exection (replace 'n' with 'c')
 exection -> execution (insert 'u')

 * @author Santosh Manughala (SM030146).
 */
public class EditDistance {
    public static void main(String args[]) {
        System.out.println(editDistance("intention", "execution"));
    }

    private static int editDistance(String word1, String word2) {
        if(word1 == null || word2 == null) {
            return 0;
        }

        int m = word1.length(), n = word2.length();

        if(m * n == 0) {
            return m + n;
        }

        int[][] result = new int[m + 1][n + 1];

        for(int i = 0; i < m + 1; i++) {
            result[i][0] = i;
        }

        for(int j = 0; j < n + 1; j++) {
            result[0][j] = j;
        }

        for(int i = 1; i < m + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                int left = result[i-1][j] + 1, up = result[i][j-1] + 1, diagonal = result[i-1][j-1];
                if(word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    diagonal += 1;
                }

                result[i][j] = Math.min(Math.min(left, up), diagonal);
            }
        }

        return result[m][n];
    }
}
