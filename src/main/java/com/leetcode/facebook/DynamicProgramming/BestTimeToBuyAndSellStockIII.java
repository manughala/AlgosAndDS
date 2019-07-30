package com.leetcode.facebook.DynamicProgramming;

/**
 * NOTE: Not in facebook leetcode, but found it online in leetcode problems - tried it
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most two transactions.

 Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

 Example 1:

 Input: [3,3,5,0,0,3,1,4]
 Output: 6
 Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 Example 2:

 Input: [1,2,3,4,5]
 Output: 4
 Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 engaging multiple transactions at the same time. You must sell before buying again.
 Example 3:

 Input: [7,6,4,3,1]
 Output: 0
 Explanation: In this case, no transaction is done, i.e. max profit = 0.

 * @author Santosh Manughala (SM030146).
 */
public class BestTimeToBuyAndSellStockIII {

    // NOTE: the best time to buy and sell for 1 transaction and any transactions,
    // is pretty easy but a question with atmost k transactions need dynamic programming
    public static void main(String args[]) {
        int[] prices1 = new int[]{7, 1, 5, 3, 6, 4};
        int[] prices2 = new int[]{7, 6, 4, 3, 1};
        int[] prices3 = new int[]{1, 4, 5, 7, 6, 3, 2, 9};

        int maxProfitWithoutDynamicProgramming = getMaxProfitWithoutDynamicProgramming(prices1);
        int maxProfitWithDynamicProgramming = getMaxProfitWithDynamicProgramming(prices1, 2);

        System.out.println("maxProfitWithoutDynamicProgramming = " + maxProfitWithoutDynamicProgramming);
        System.out.println("maxProfitWithDynamicProgramming = " + maxProfitWithDynamicProgramming);
    }

    // Time O(n*k) - num of trans * num of days
    // Space O(n*k)
    private static int getMaxProfitWithDynamicProgramming(int[] prices, int numberOfTransactions) {
        if(prices == null || prices.length == 0 || numberOfTransactions == 0) {
            return 0;
        }

        int[][] maxProfitTillNow = new int[numberOfTransactions + 1][prices.length];

        for(int i = 1; i < maxProfitTillNow.length; i++) {
            int maxDiff = -prices[0];
            for(int j = 1; j < maxProfitTillNow[0].length; j++) {
                maxProfitTillNow[i][j] = Math.max(maxProfitTillNow[i][j-1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, maxProfitTillNow[i-1][j] - prices[j]);
            }
        }

        return maxProfitTillNow[numberOfTransactions][prices.length - 1];
    }


    // This is just a simple solution, which works only for case of at most 2 transactions
    // Time O(n)
    // Space O(n)
    private static int getMaxProfitWithoutDynamicProgramming(int[] prices) {
        int maxProfit = 0;

        if(prices == null || prices.length < 2) {
            return maxProfit;
        }

        int[] leftMax = new int[prices.length];
        int[] rightMax = new int[prices.length];

        leftMax[0] = 0;
        int min = prices[0];
        for(int i = 1; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            leftMax[i] = Math.max(leftMax[i-1], prices[i] - min);
        }

        rightMax[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for(int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            rightMax[i] = Math.max(rightMax[i+1], max - prices[i]);
        }

        for(int i = 0; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, leftMax[i] + rightMax[i]);
        }

        return maxProfit;
    }
}
