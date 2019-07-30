package com.practice.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

 Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

 Example 1:

 Input: [7,1,5,3,6,4]
 Output: 7
 Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
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
public class StockBuySell {

    void stockBuySell(int price[], int n) {
        if(n == 1) {
            System.out.println("Cannot perform stock buy sell");
            return;
        }

        List<Interval> intervals = new ArrayList<>();

        int i = 0;
        while(i < n-1) {
            while(i< n-1 && price[i] >= price[i+1]) {
                i++;
            }

            if(i == n-1) {
                break;
            }

            Interval interval = new Interval();
            interval.buy = i++;

            while(i<n && price[i] >= price[i-1]) {
                i++;
            }

            interval.sell = i-1;
            intervals.add(interval);
        }

        if(intervals.isEmpty()) {
            System.out.println("Cannot get profit");
        } else {
            for (Interval interval : intervals) {
                System.out.println("Buy at: " + interval.buy + " and sell at: " + interval.sell);
            }
        }
    }

    public static void main(String args[]) {
        StockBuySell stock = new StockBuySell();

//        int price[] = {7,6,4,6,1};
        int price[] = {100, 180, 260, 310, 10, 30, 20,40};
        int n = price.length;

        stock.stockBuySell(price, n);
    }

}

class Interval {
    int buy, sell;
}
