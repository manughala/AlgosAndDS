package com.leetcode.linkedin;

import java.util.ArrayList;
import java.util.List;

/**
 * Numbers can be regarded as product of its factors. For example,

 8 = 2 x 2 x 2;
 = 2 x 4.
 Write a function that takes an integer n and return all possible combinations of its factors.

 Note:

 You may assume that n is always positive.
 Factors should be greater than 1 and less than n.
 Example 1:

 Input: 1
 Output: []
 Example 2:

 Input: 37
 Output:[]
 Example 3:

 Input: 12
 Output:
 [
 [2, 6],
 [2, 2, 3],
 [3, 4]
 ]
 Example 4:

 Input: 32
 Output:
 [
 [2, 16],
 [2, 2, 8],
 [2, 2, 2, 4],
 [2, 2, 2, 2, 2],
 [2, 4, 4],
 [4, 8]
 ]

 * @author Santosh Manughala (SM030146).
 */
public class FactorCombinations {

    public static void main(String args[]) {
        List<List<Integer>> factors = getFactors(37);

        for(List<Integer> factor : factors) {
            System.out.println(factor.toString());
        }
    }

    //https://leetcode.com/problems/factor-combinations/discuss/267738/Java-backtrack-thought-process
    private static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        if (n == 1) {
            return ans;
        }

        backtrack(ans, 2, new ArrayList<>(), n);
//        ans.remove(ans.size() - 1);
        return ans;
    }

    private static void backtrack(List<List<Integer>> ans, int index, List<Integer> currentList, int n) {
        if (n == 1) {
            ans.add(new ArrayList<>(currentList));
            return;
        }

        for (int i = index; i <= n; i++) {
            // Constraint propagation: solution can not be found starting with this state
            if (n % i != 0) {
                continue;
            }

            currentList.add(i);
            // No need to increment j due to kleene star operation
            backtrack(ans, i, currentList, n / i);
            currentList.remove(currentList.size() - 1);
        }
    }
}



