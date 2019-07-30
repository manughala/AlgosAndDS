package com.leetcode.facebook.DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

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
        int[] prices1 = new int[]{7, 1, 5, 3, 6, 4};
        int[] prices2 = new int[]{7, 6, 4, 3, 1};

        int maxProfitBruteForce = getMaxProfitBruteForce(prices2);
        int maxProfitBestCase = getMaxProfitBestCase(prices2);

        System.out.println("maxProfitBruteForce = " + maxProfitBruteForce);
        System.out.println("maxProfitBestCase = " + maxProfitBestCase);

    }

    // Time: O(n^2)
    // space: O(1)
    private static int getMaxProfitBruteForce(int[] prices) {
        int maxProfit = 0;
        if(prices == null || prices.length <= 1) {
            return maxProfit;
        }

        for(int i = 0; i < prices.length  - 1; i++) {
            for(int j = i + 1; j < prices.length; j++) {
                int currentProfit = prices[j] - prices[i];
                if(currentProfit > maxProfit) {
                    maxProfit = currentProfit;
                }
            }
        }

        return maxProfit;
    }

    // Time: O(n)
    // Space: O(1)
    private static int getMaxProfitBestCase(int[] prices) {
        int maxProfit = 0;
        if(prices == null || prices.length <= 1) {
            return maxProfit;
        }

        int minSoFar = prices[0];

        for(int i = 1; i < prices.length; i++) {
            if(minSoFar > prices[i]) {
                minSoFar = prices[i];
            } else if(maxProfit < prices[i] - minSoFar) {
                maxProfit = prices[i] - minSoFar;
            }
        }

        return maxProfit;
    }
}
