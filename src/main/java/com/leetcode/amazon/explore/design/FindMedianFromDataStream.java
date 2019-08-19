package com.leetcode.amazon.explore.design;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

 For example,
 [2,3,4], the median is 3

 [2,3], the median is (2 + 3) / 2 = 2.5

 Design a data structure that supports the following two operations:

 void addNum(int num) - Add a integer number from the data stream to the data structure.
 double findMedian() - Return the median of all elements so far.


 Example:

 addNum(1)
 addNum(2)
 findMedian() -> 1.5
 addNum(3)
 findMedian() -> 2


 Follow up:

 If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?

 * @author Santosh Manughala (SM030146).
 */
public class FindMedianFromDataStream {

    public static void main(String args[]) {
        FindMedianFromDataStream medianFinder = new FindMedianFromDataStream();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println("Expected 1.5, actual: " + medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println("Expected 2.0, actual: " + medianFinder.findMedian());
    }

    public PriorityQueue<Integer> minHeap;
    public PriorityQueue<Integer> maxHeap;

    public FindMedianFromDataStream() {
        minHeap = new PriorityQueue();
        maxHeap = new PriorityQueue(Collections.reverseOrder());
    }

    // O(logn)
    public void addNum(int num) {
        minHeap.add(num);
        maxHeap.add(minHeap.poll());

        if(maxHeap.size() > minHeap.size()) {
            minHeap.add(maxHeap.poll());
        }
    }

    //O(1)
    public double findMedian() {
        if(maxHeap.size() < minHeap.size()) {
            // odd length
            return minHeap.peek();
        } else {
            // even length
            // NOTE: if you dont divide by 2.0 you will get a int value, not double
            // You can either case or add 2.0
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
    }
}
