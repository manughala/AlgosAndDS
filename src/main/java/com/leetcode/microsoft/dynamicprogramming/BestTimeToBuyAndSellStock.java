package com.leetcode.microsoft.dynamicprogramming;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.

 If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

 Note that you cannot sell a stock before you buy one.

 Example 1:

 Input: [7,1,5,3,6,4]
 Output: 5
 Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 Not 7-1 = 6, as selling price needs to be larger than buying price.
 Example 2:

 Input: [7,6,4,3,1]
 Output: 0
 Explanation: In this case, no transaction is done, i.e. max profit = 0.

 * @author Santosh Manughala (SM030146).
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String args[]) {
        System.out.println("Expected: 5, actual: " + getProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println("Expected: 0, actual: " + getProfit(new int[]{7, 6, 4, 3, 1}));
    }

    // TIme: O(n)
    // Space: O(1)
    private static int getProfit(int[] prices) {
        // min so far, profit so far
        if(prices == null || prices.length < 1) {
            return 0;
        }

        int profit = 0, minSoFar = prices[0];

        for(int i = 1; i < prices.length; i++) {
            if(prices[i] <= minSoFar) {
                minSoFar = prices[i];
            } else if (prices[i] - minSoFar > profit) {
                profit = prices[i] - minSoFar;
            }
        }

        return profit;
    }
}
