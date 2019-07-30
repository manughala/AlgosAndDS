package com.leetcode.linkedin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

 What if we change the game so that players cannot re-use integers?

 For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

 Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

 You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

 Example

 Input:
 maxChoosableInteger = 10
 desiredTotal = 11

 Output:
 false

 Explanation:
 No matter which integer the first player choose, the first player will lose.
 The first player can choose an integer from 1 up to 10.
 If the first player choose 1, the second player can only choose integers from 2 up to 10.
 The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 Same with other integers chosen by the first player, the second player will always win.


 * @author Santosh Manughala (SM030146).
 */
public class CanIWin {
    public static void main(String args[]) {
        System.out.println(canIWin(10, 11));
    }

//    Core logic:
//    We want to see if a path exists where all the branches for the opponent results in a false.
//    The opponent tries every single branch before he gives up and returns a false.
//
//    Memoization:
//    At any point in the decision tree, the path ahead is solely based on the numbers chosen so far. So we can use that to memoize the results.
    private static Map<String, Boolean> dict;
    private static boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(maxChoosableInteger >= desiredTotal) {
            return true;
        }
        dict = new HashMap<>();
        return canIWinWithSituation(maxChoosableInteger, desiredTotal, new boolean[maxChoosableInteger + 1]);
    }

    // Time O(2^n) we at most compute for every subproblem once, and there are O(2^n) subproblems, so the complexity is O(2^n
    // Space O(2^n)
    private static boolean canIWinWithSituation(int maxChoosableInteger, int curDesiredTotal, boolean[] chosen) {
        if(curDesiredTotal <= 0) {
            return false;
        }

        String key = Arrays.toString(chosen);
        if(dict.containsKey(key)) {
            return dict.get(key);
        }

        for(int i = 1; i <= maxChoosableInteger; i++) {
            if(chosen[i] == true) {
                continue;
            }

            chosen[i] = true;

            // at this state if we have to win, previous state we have to loose
            // meaning we take out i from the desired total and see if we loose.
            if(!canIWinWithSituation(maxChoosableInteger, curDesiredTotal - i, chosen)) {
                dict.put(key, true);
                // resetting state
                chosen[i] = false;
                return true;
            }

            // resetting state
            chosen[i] = false;
        }
        dict.put(key, false);
        return false;
    }
}
