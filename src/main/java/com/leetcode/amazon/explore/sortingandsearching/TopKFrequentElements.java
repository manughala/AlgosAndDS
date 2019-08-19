package com.leetcode.amazon.explore.sortingandsearching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a non-empty array of integers, return the k most frequent elements.

 Example 1:

 Input: nums = [1,1,1,2,2,3], k = 2
 Output: [1,2]
 Example 2:

 Input: nums = [1], k = 1
 Output: [1]
 Note:

 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 * @author Santosh Manughala (SM030146).
 */
public class TopKFrequentElements {

    public static void main(String args[]) {
        System.out.println("getTopKFrequentElementsAverageCase: Expected: [1, 2]; Actual: " + getTopKFrequentElementsAverageCase(new int[]{1, 1, 1, 2, 2, 3}, 2));
        System.out.println("getTopKFrequentElementsAverageCase: Expected: [1]; Actual: " + getTopKFrequentElementsAverageCase(new int[]{1}, 1));
        System.out.println("getTopKFrequentElementsAverageCase: Expected: []; Actual: " + getTopKFrequentElementsAverageCase(new int[]{1}, 2));

        System.out.println("getTopKFrequentElementsBestCase: Expected: [1, 2]; Actual: " + getTopKFrequentElementsBestCase(new int[]{1, 1, 1, 1, 1, 2, 2, 3}, 2));
        System.out.println("getTopKFrequentElementsBestCase: Expected: [1, 2]; Actual: " + getTopKFrequentElementsBestCase(new int[]{1, 1, 1, 2, 2, 3}, 2));
        System.out.println("getTopKFrequentElementsBestCase: Expected: [1]; Actual: " + getTopKFrequentElementsBestCase(new int[]{1}, 1));
        System.out.println("getTopKFrequentElementsBestCase: Expected: []; Actual: " + getTopKFrequentElementsBestCase(new int[]{1}, 2));
    }

    // Time: O(n)
    // Space: O(n)
    private static List<Integer> getTopKFrequentElementsBestCase(int[] nums, int k) {
        if(k > nums.length) {
            return new ArrayList<>();
        }

        Map<Integer, Integer> numToCount = new HashMap<>();
        int max = 0;
        for(int i = 0; i < nums.length; i++) {
            numToCount.put(nums[i], numToCount.getOrDefault(nums[i], 0) + 1);
            max = Math.max(max, numToCount.get(nums[i]));
        }

        List<Integer>[] numsPerFrequency = new ArrayList[max + 1];
        for(int i = 1; i <= max; i++) {
            numsPerFrequency[i] = new ArrayList<>();
        }

        for(Map.Entry<Integer, Integer> entry : numToCount.entrySet()) {
            numsPerFrequency[entry.getValue()].add(entry.getKey());
        }

        List<Integer> result = new ArrayList<>();

        for(int i = max; i >= 0; i--) {
            List<Integer> numsWithThisFreq = numsPerFrequency[i];

            for(int num : numsWithThisFreq) {
                result.add(num);
                if(result.size() == k) {
                    return result;
                }
            }
        }

        return new ArrayList<>();
    }

    // Time: O(nlogk)
    // Space: O(k)
    private static List<Integer> getTopKFrequentElementsAverageCase(int[] nums, int k) {
        if(k > nums.length) {
            return new ArrayList<>();
        }

        Map<Integer, Integer> numToCount = new HashMap<>();

        for(int num : nums) {
            numToCount.put(num, numToCount.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return numToCount.get(i1) - numToCount.get(i2);
            }
        });

        for(int num : numToCount.keySet()) {
            queue.add(num);

            if(queue.size() > k) {
                queue.poll();
            }
        }

        List<Integer> result = new ArrayList<>();

        while(!queue.isEmpty()) {
            result.add(queue.poll());
        }

        // queue will add the nums in reverse order
        Collections.reverse(result);
        return result;
    }
}
